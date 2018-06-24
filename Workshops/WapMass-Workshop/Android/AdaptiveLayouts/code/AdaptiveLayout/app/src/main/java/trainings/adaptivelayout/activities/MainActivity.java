package trainings.adaptivelayout.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import trainings.adaptivelayout.MyCommunicator;
import trainings.adaptivelayout.fragments.DetailFragmentB;
import trainings.adaptivelayout.R;

public class MainActivity extends AppCompatActivity implements MyCommunicator {

	private boolean mIsDualPane = false;
	private DetailFragmentB detailFragmentB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FragmentManager fragmentManager = getFragmentManager();
		detailFragmentB = (DetailFragmentB) fragmentManager.findFragmentById(R.id.fragmentB);

		View fragmentBView = findViewById(R.id.fragmentB);
		mIsDualPane = fragmentBView != null && fragmentBView.getVisibility() == View.VISIBLE;
	}

	@Override
	public void displayDetails(String title, String description) {

		if (mIsDualPane) { // If we are in Tablet
			detailFragmentB.displayData(title, description);
		} else { // When we are in Smartphone
			Intent intent = new Intent(this, DetailActivity.class);
			intent.putExtra("title", title);
			intent.putExtra("description", description);
			startActivity(intent);
		}
	}
}
