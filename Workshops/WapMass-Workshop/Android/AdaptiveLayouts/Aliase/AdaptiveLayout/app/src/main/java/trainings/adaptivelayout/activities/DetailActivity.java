package trainings.adaptivelayout.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import trainings.adaptivelayout.R;
import trainings.adaptivelayout.fragments.DetailFragmentB;


public class DetailActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		// Retrive the data coming from MainActivity.java
		String description = getIntent().getStringExtra("description");
		String title = getIntent().getStringExtra("title");

		// Pass the data to DetailFragmentB.java to display it
		FragmentManager fragmentManager = getFragmentManager();
		DetailFragmentB detailFragmentB = (DetailFragmentB) fragmentManager.findFragmentById(R.id.fragmentB);
		detailFragmentB.displayData(title, description);
	}
}
