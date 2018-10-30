package bawo.recyclerview.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import bawo.recyclerview.Model.Landscape;
import bawo.recyclerview.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<Landscape> landscapes;

    public RecyclerAdapter(Context context, ArrayList<Landscape> landscapes){
        this.context = context;
        this.landscapes = landscapes;
    }
    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);
        return new MyViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        Landscape landscape = landscapes.get(position);
        holder.setData(landscape);

    }

    @Override
    public int getItemCount() {
        return landscapes.size();
    }

    public class MyViewHolder  extends  RecyclerView.ViewHolder{
        public ImageView delete,copyImage, imageData;
        public TextView title,description;
        public MyViewHolder(View itemView, Context ctx) {
            super(itemView);
            delete = itemView.findViewById(R.id.img_row_delete);
            copyImage = itemView.findViewById(R.id.img_row_add);
            imageData = itemView.findViewById(R.id.img_row);
            title = itemView.findViewById(R.id.tvTitle);
            description = itemView.findViewById(R.id.tvDescription);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    landscapes.remove(getAdapterPosition());
                    //notifyItemRemoved(getAdapterPosition());
                    notifyDataSetChanged();
                }
            });

            copyImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    landscapes.add(getAdapterPosition(), landscapes.get(getAdapterPosition()));
                    notifyDataSetChanged();
                }
            });

        }

        public void setData(Landscape landscape){
            imageData.setImageResource(landscape.getImageID());
            description.setText(landscape.getDescription().substring(0, 20) + "...");
            title.setText(landscape.getTitle());

        }
    }
}
