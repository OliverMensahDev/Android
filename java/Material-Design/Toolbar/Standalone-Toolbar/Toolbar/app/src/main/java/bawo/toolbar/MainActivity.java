package bawo.toolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Welcome !");
        toolbar.setSubtitle("Folks !");
        toolbar.inflateMenu(R.menu.menu_main);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String msg = "";
                switch (item.getItemId()){
                    case R.id.discard:
                        msg = "Discard";
                        break;

                    case R.id.search:
                        msg ="Search";
                        break;
                }
                Toast.makeText(MainActivity.this,msg, Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }


}
