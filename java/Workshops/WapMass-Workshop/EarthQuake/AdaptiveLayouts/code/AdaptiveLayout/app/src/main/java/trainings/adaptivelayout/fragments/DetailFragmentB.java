package trainings.adaptivelayout.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import trainings.adaptivelayout.R;

public class DetailFragmentB extends Fragment {

	private TextView mTxvTitle, mTxvDescription;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_b_details, null);

		mTxvTitle = (TextView) view.findViewById(R.id.txvTitle);
		mTxvDescription = (TextView) view.findViewById(R.id.txvDescription);

		return view;
	}

	public void displayData(String title, String description) {

		mTxvTitle.setText(title);
		mTxvDescription.setText(description);
	}
}
