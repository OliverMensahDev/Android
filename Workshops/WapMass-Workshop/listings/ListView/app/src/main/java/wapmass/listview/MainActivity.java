package wapmass.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;



import java.util.ArrayList;

import wapmass.listview.Data.LandScapeData;
import wapmass.listview.Model.LandScape;

public class MainActivity extends AppCompatActivity {
    private ArrayList<LandScape> dataList;
    private ListView listView;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataList = new ArrayList<>();

        listView = findViewById(R.id.listview);

        setData();

    }

    public void setData() {
        dataList = LandScapeData.getData();

        ArrayList arrayList = new ArrayList();
        for(LandScape landScape : dataList) arrayList.add(landScape.getTitle());

        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,
                android.R.id.text1, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("index",position);
                startActivity(intent);
            }
        });
        arrayAdapter.notifyDataSetChanged();
    }
}
