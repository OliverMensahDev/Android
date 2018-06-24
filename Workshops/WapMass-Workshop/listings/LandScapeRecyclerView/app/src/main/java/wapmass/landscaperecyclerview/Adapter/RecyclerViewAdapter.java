package wapmass.landscaperecyclerview.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import wapmass.landscaperecyclerview.DetailActivity;
import wapmass.landscaperecyclerview.Model.LandScape;
import wapmass.landscaperecyclerview.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowViewClass> {
    //get instance of our data
    private ArrayList<LandScape> landScapes;
    // A context to make use of the RecyclerViewAdapter in any Activity
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<LandScape> landScapes){
        this.landScapes = landScapes;
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerViewAdapter.RowViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // get access to row item from the xml. In our case, its called row_item.xml
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);

        return new RowViewClass(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RowViewClass holder, int position) {
        LandScape landScape = landScapes.get(position);
        holder.image.setImageResource(landScape.getImageId());
        holder.title.setText(landScape.getTitle());
    }

    @Override
    public int getItemCount() {
        return landScapes.size();
    }

    public class RowViewClass extends  RecyclerView.ViewHolder{
        //instantiate our row_item views
        private TextView title;
        private ImageView image;
        public RowViewClass(View rowList , Context ctx) {
            super(rowList);
            context = ctx;
            title = rowList.findViewById(R.id.title);
            image = rowList.findViewById(R.id.image);

            rowList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    int index = getAdapterPosition();
                    intent.putExtra("index", index);
                    context.startActivity(intent);

                }
            });
        }

    }
}
