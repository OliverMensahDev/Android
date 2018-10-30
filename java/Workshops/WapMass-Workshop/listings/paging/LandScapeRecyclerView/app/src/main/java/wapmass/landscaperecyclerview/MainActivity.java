package wapmass.landscaperecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import wapmass.landscaperecyclerview.Adapter.RecyclerViewAdapter;
import wapmass.landscaperecyclerview.Data.LandScapeData;
import wapmass.landscaperecyclerview.Model.LandScape;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private int mStart=0,mEnd=20;
    public int total = 0;
    private ArrayList<LandScape> landScapes;
    private ArrayList<LandScape> temp;
    private static int pageNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pageNumber = 1;
        recyclerView = findViewById(R.id.recyclerView);
        landScapes = new ArrayList<>();
        temp = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        linearLayoutManager =  new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerViewAdapter(landScapes,  recyclerView);
        recyclerView.setAdapter(adapter);
        loadData();
        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if( temp.size()> 0) {
                    landScapes.add(null);
                    adapter.notifyItemInserted(landScapes.size() - 1);
                    int start = pageNumber * 20;
                    start = start + 1;
                    ++ pageNumber;
                    temp.clear();
                    GetData("" + start,""+ end);
                }
            }
        });

        setupToolbar();
    }

    public void GetData(final String LimitStart, final String LimitEnd) {
        landScapes.get(LimitStart, )
    }
    // load initial data
    private void loadData() {
        landScapes.addAll(LandScapeData.getData());
        adapter.notifyItemInserted(landScapes.size() - 10);
    }

    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home Page!");
        setSupportActionBar(toolbar);
    }
    public interface OnLoadMoreListener { void onLoadMore(); }
}
