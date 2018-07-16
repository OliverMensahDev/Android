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

import bawo.recyclerview.Model.Animal;
import bawo.recyclerview.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<Animal> animals;

    public RecyclerAdapter(Context context, ArrayList<Animal> animals){
        this.context = context;
        this.animals = animals;
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
        Animal animal = animals.get(position);
        holder.imageData.setImageResource(animal.getImageId());
        holder.name.setText(animal.getTitle());

    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    public class MyViewHolder  extends  RecyclerView.ViewHolder{
        public ImageView imageData;
        public TextView name;
        public MyViewHolder(View itemView, Context ctx) {
            super(itemView);

            imageData = itemView.findViewById(R.id.img_row);
            name = itemView.findViewById(R.id.txv_row);

        }

    }
}
