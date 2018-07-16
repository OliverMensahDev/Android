package bawo.materialtabs.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import bawo.materialtabs.R;
import bawo.materialtabs.tabs.CustomTabs;
import bawo.materialtabs.tabs.IconTabs;
import bawo.materialtabs.tabs.ScrollTabs;
import bawo.materialtabs.tabs.TextIconTabs;
import bawo.materialtabs.tabs.TextTabs;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private Button btnSimpleTabs, btnScrollableTabs, btnIconTabs, btnCustomIconTextTabs ,btnTextIconTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Implementation of Tabs");
        setSupportActionBar(toolbar);

        btnSimpleTabs = (Button) findViewById(R.id.btnSimpleTabs);
        btnScrollableTabs = (Button) findViewById(R.id.btnScrollableTabs);
        btnIconTabs = (Button) findViewById(R.id.btnIconTabs);
        btnCustomIconTextTabs = (Button) findViewById(R.id.btnCustomIconTabs);
        btnTextIconTabs = findViewById(R.id.btnTextIconTabs);

        btnSimpleTabs.setOnClickListener(this);
        btnScrollableTabs.setOnClickListener(this);
        btnIconTabs.setOnClickListener(this);
        btnCustomIconTextTabs.setOnClickListener(this);
        btnTextIconTabs.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSimpleTabs:
                Intent i = new Intent(MainActivity.this, TextTabs.class);
                startActivity(i);
                break;
            case R.id.btnIconTabs:
                startActivity(new Intent(MainActivity.this, IconTabs.class));
                break;
            case R.id.btnTextIconTabs:
                startActivity(new Intent(MainActivity.this, TextIconTabs.class));
                break;
            case R.id.btnScrollableTabs:
                startActivity(new Intent(MainActivity.this, ScrollTabs.class));
                break;
            case R.id.btnCustomIconTabs:
                startActivity(new Intent(MainActivity.this, CustomTabs.class));
                break;
        }

    }
}
