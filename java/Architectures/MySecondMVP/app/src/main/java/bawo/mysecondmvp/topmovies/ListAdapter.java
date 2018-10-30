package bawo.mysecondmvp.topmovies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import bawo.mysecondmvp.R;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListItemViewHolder>{
    private List<ViewModel> list;

    public ListAdapter(List<ViewModel> list){
        this.list = list;
    }
    @NonNull
    @Override
    public ListAdapter.ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ListItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListItemViewHolder extends RecyclerView.ViewHolder{
        public ListItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
