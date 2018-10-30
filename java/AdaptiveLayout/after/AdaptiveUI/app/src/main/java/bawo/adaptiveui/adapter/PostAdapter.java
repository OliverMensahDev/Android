package bawo.adaptiveui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import bawo.adaptiveui.InterCommunicator;
import bawo.adaptiveui.activities.DetailsActivity;
import bawo.adaptiveui.R;

import bawo.adaptiveui.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.RowViewClass> {
    //get instance of our data
    private ArrayList<Post> posts;
    // A context to make use of the RecyclerViewAdapter in any Activity
    private Context context;

    public PostAdapter(Context context, ArrayList<Post> landScapes){
        this.posts = landScapes;
        this.context = context;
    }
    @NonNull
    @Override
    public PostAdapter.RowViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // get access to row item from the xml. In our case, its called row_item.xml
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new RowViewClass(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.RowViewClass holder, int position) {
        Post post = posts.get(position);
        holder.image.setImageResource(post.getImageResourceId(context));
        holder.title.setText(post.getPostName());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class RowViewClass extends  RecyclerView.ViewHolder{
        private TextView title;
        private ImageView image;
        public RowViewClass(View rowList , final Context ctx) {
            super(rowList);
            context = ctx;
            title = rowList.findViewById(R.id.title);
            image = rowList.findViewById(R.id.image);


            rowList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InterCommunicator myCommunicator = (InterCommunicator) ctx;
                    myCommunicator.sendCurrentItemPosition(getAdapterPosition());
                }
            });

        }

    }
}