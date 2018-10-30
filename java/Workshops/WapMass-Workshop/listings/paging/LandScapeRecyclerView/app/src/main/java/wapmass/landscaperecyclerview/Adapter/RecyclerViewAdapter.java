package wapmass.landscaperecyclerview.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import wapmass.landscaperecyclerview.DetailActivity;
import wapmass.landscaperecyclerview.MainActivity;
import wapmass.landscaperecyclerview.Model.LandScape;
import wapmass.landscaperecyclerview.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private List<LandScape> landScapeList;
    private Context context;
    // The minimum amount of items to have below your current scroll position
// before loading more.
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private MainActivity.OnLoadMoreListener onLoadMoreListener;

    public RecyclerViewAdapter(List<LandScape> landScapes, RecyclerView recyclerView) {
        landScapeList = landScapes;
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager)
                    recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!loading && totalItemCount <= (lastVisibleItem +
                            visibleThreshold)) {
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return landScapeList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = null;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent,
                    false);
            vh = new LandScapeViewHolder(v, context);
        } else {
//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_item,
//                    parent, false);
//            vh = new ProgressViewHolder(v);
        }
        return vh;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LandScapeViewHolder) {
            LandScape singleStudent= landScapeList.get(position);
            ((LandScapeViewHolder) holder).image.setImageResource(singleStudent.getImageId());
            ((LandScapeViewHolder) holder).title.setText(singleStudent.getImageId());
        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }
    public void setLoaded(boolean state) {
        loading = state;
    }
    @Override
    public int getItemCount() {
        return landScapeList.size();
    }
    public void setOnLoadMoreListener(MainActivity.OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public class LandScapeViewHolder extends  RecyclerView.ViewHolder{
        //instantiate our row_item views
        private TextView title;
        private ImageView image;
        public LandScapeViewHolder(View rowList , Context ctx) {
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
    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public ProgressViewHolder(View v) {
            super(v);
//            progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
        }
    }
}