package bawo.com.databinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import bawo.com.databinding.R;
import bawo.com.databinding.data.DataSource;
import bawo.com.databinding.databinding.ActivityAnotherBinding;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAnotherBinding viewDataBinding = DataBindingUtil.setContentView(this,R.layout.activity_another);

        DataSource dataSource = DataSource.get("World");

        viewDataBinding.setDataSource(dataSource);


    }
}
