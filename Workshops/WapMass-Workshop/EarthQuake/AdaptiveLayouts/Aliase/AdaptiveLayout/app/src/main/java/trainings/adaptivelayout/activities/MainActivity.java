package trainings.adaptivelayout.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import trainings.adaptivelayout.MyCommunicator;
import trainings.adaptivelayout.R;
import trainings.adaptivelayout.fragments.DetailFragmentB;


public class MainActivity extends AppCompatActivity implements MyCommunicator {

	private boolean mIsDualPane = false;
	private DetailFragmentB detailFragmentB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);

		FragmentManager fragmentManager = getFragmentManager();
		detailFragmentB = (DetailFragmentB) fragmentManager.findFragmentById(R.id.fragmentB);

//		View fragmentBView = findViewById(R.id.fragmentB);
//		mIsDualPane = fragmentBView != null && fragmentBView.getVisibility() == View.VISIBLE;

		mIsDualPane = getResources().getBoolean(R.bool.is_dual_pane);
	}

	@Override
	public void displayDetails(String title, String description) {

		if (mIsDualPane) { // If we are in Tablet [Landscape]
			detailFragmentB.displayData(title, description);
		} else { // When we are in Smartphone Or portrait mode of Tablet
			Intent intent = new Intent(this, DetailActivity.class);
			intent.putExtra("title", title);
			intent.putExtra("description", description);
			startActivity(intent);
		}
	}
}
