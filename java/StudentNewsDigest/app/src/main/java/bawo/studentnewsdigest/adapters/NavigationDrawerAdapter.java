package bawo.studentnewsdigest.adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bawo.studentnewsdigest.R;
import bawo.studentnewsdigest.activities.LoginActivity;
import bawo.studentnewsdigest.model.NavigationDrawerItem;
import bawo.studentnewsdigest.util.FirebaseUtil;

public class NavigationDrawerAdapter  extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder>{
    private List<NavigationDrawerItem> mDataList;
    private LayoutInflater inflater;
    private Context context;

    public NavigationDrawerAdapter(Context context, List<NavigationDrawerItem> data){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.mDataList = data;
    }

    @NonNull
    @Override
    public NavigationDrawerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_list_item, parent, false);
        MyViewHolder  holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final NavigationDrawerAdapter.MyViewHolder holder, final int position) {
        NavigationDrawerItem current  = mDataList.get(position);

        holder.imgIcon.setImageResource(current.getImageId());
        holder.title.setText(current.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        FirebaseUtil.setupAuth().signOut();
                        SharedPreferences preferences = context.getSharedPreferences("loggedIn",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.apply();
                        context.startActivity(new Intent(context, LoginActivity.class));
                        ((Activity)v.getContext()).finish();
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView title;
        ImageView imgIcon;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            imgIcon = itemView.findViewById(R.id.imgIcon);
        }
    }
}
