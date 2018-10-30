package bawo.com.databinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bawo.com.databinding.data.DataSource;
import bawo.com.databinding.databinding.ActivityTextBinding;
import bawo.com.databinding.databinding.ItemViewBinding;

public class TextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inflate
        ActivityTextBinding activityTextBinding = DataBindingUtil.setContentView(this, R.layout.activity_text);

        //Data Source
        DataSource dataSource = DataSource.get("World");
        //Data Source
        DataSource included = DataSource.get("Included");


        // Bind the data to the view
        activityTextBinding.setDataSource(dataSource);
        activityTextBinding.setIncludeDataSource(included);

        //repeating views
        activityTextBinding.list.setLayoutManager(new LinearLayoutManager(this));
        activityTextBinding.list.setAdapter(new DataSourceAdapter(getLayoutInflater()));
    }

    private class DataSourceAdapter extends RecyclerView.Adapter<ViewHolder> {
        private LayoutInflater layoutInflater;

        public DataSourceAdapter(LayoutInflater layoutInflater) {
            this.layoutInflater = layoutInflater;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ItemViewBinding binding = ItemViewBinding.inflate(layoutInflater, parent, false);
            return new ViewHolder(binding.getRoot());
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            DataSource dataSource = DataSource.get("Item Binding" + position);
            ItemViewBinding binding = DataBindingUtil.getBinding(holder.itemView);
            binding.setDataSource(dataSource);
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
