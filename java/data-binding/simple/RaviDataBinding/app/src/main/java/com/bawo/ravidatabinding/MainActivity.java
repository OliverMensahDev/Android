package com.bawo.ravidatabinding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.bawo.ravidatabinding.databinding.ActivityMainBinding;
import com.bawo.ravidatabinding.model.User;


public class MainActivity extends AppCompatActivity {

    private User user;
    private MyClickHandlers handlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

       // setSupportActionBar(binding.toolbar);

        user = new User();
        user.setName("Ravi Tamada");
        user.setEmail("ravi@androidhive.info");

        binding.setUser(user);

        handlers = new MyClickHandlers(this);
        binding.content.setHandlers(handlers);
    }

    public class MyClickHandlers {

        Context context;

        public MyClickHandlers(Context context) {
            this.context = context;
        }

        public void onFabClicked(View view) {
            Toast.makeText(getApplicationContext(), "FAB clicked!", Toast.LENGTH_SHORT).show();
        }

        public void onButtonClick(View view) {
            user.setEmail("Oliver");
            user.setName("adafds");
            Toast.makeText(getApplicationContext(), "Button clicked!", Toast.LENGTH_SHORT).show();
        }

        public void onButtonClickWithParam(View view, User user) {
            Toast.makeText(getApplicationContext(), "Button clicked! Name: " + user.getName(), Toast.LENGTH_SHORT).show();
        }

        public boolean onButtonLongPressed(View view) {
            Toast.makeText(getApplicationContext(), "Button long pressed!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}