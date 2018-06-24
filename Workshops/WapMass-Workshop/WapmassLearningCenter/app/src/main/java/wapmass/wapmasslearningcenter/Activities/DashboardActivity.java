package wapmass.wapmasslearningcenter.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import wapmass.wapmasslearningcenter.Adapter.CourseAdapter;
import wapmass.wapmasslearningcenter.Data.DatabaseHandler;
import wapmass.wapmasslearningcenter.Model.Course;
import wapmass.wapmasslearningcenter.R;

public class DashboardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
//        for(Course course: databaseHandler.getAllCourses()) Log.d("output", course.toString());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        Bundle   bundle = getIntent().getExtras();
        if(bundle.getString("user").equals("admin")) {
            MenuItem item = menu.findItem(R.id.add_course);
            item.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.about_wapmass:
                startActivity(new Intent(DashboardActivity.this, AboutActivity.class));
                break;

            case R.id.add_course:
                startActivity(new Intent(DashboardActivity.this, AddCourse.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
