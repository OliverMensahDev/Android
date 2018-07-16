package bawo.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import bawo.recyclerview.Adapter.RecyclerAdapter;
import bawo.recyclerview.Data.LandscapeData;
import bawo.recyclerview.Model.Landscape;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home Page !");
//        toolbar.setSubtitle("Folks !");
        toolbar.inflateMenu(R.menu.menu_main);
//
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                String msg = "";
//                switch (item.getItemId()){
//                    case R.id.discard:
//                        msg = "Discard";
//                        break;
//
//                    case R.id.search:
//                        msg ="Search";
//                        break;
//                }
//                Toast.makeText(MainActivity.this,msg, Toast.LENGTH_LONG).show();
//                return false;
//            }
//        });

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // with default layout and recyclerView
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////        for(Landscape landscape: LandscapeData.getData()) Log.w("data", landscape.toString());
//        recyclerView.setAdapter(new RecyclerAdapter(this, LandscapeData.getData()));

        // setting layout and recyclerView
        RecyclerAdapter adapter = new RecyclerAdapter(this, LandscapeData.getData());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());// the recyclerview  uses this by default
    }


}
