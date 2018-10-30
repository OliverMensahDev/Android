package bawo.toolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
//        setActionBar(toolbar);// erroe because of backward compatibility
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Welcome !");
        toolbar.setSubtitle("Forks");
//        toolbar.setLogo(R.drawable.good_day);
//        toolbar.setNavigationIcon(R.drawable.navigation_back);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;
    }
}
