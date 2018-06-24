package wapmass.landscapeapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import wapmass.landscapeapp.Data.LandScapeData;
import wapmass.landscapeapp.Model.LandScape;

public class MainActivity extends AppCompatActivity {
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        setUpData();
    }

    private void setUpData() {
        //pull our data
        ArrayList<LandScape> dataList = LandScapeData.getData();
        // get only title for our listview
        ArrayList<String> titleList = new ArrayList<>();
        for(LandScape landScape : dataList) titleList.add(landScape.getTitle());
        //set data to adapter and its context.
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, titleList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                createPopupDialog(position);
            }
        });
        arrayAdapter.notifyDataSetChanged();
    }

    private void createPopupDialog(final int position) {

        LandScape landScape = LandScapeData.getData().get(position);

        AlertDialog.Builder dialogBuilder  = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup, null);
        Button nextBtn = view.findViewById(R.id.goToDA);
        ImageView imageView = view.findViewById(R.id.image);
        imageView.setImageResource(landScape.getImageId());
        TextView textView = view.findViewById(R.id.title);
        textView.setText(landScape.getTitle());

        dialogBuilder.setView(view);
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });




    }
}
