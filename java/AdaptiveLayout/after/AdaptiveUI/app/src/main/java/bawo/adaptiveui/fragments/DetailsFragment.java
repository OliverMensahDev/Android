package bawo.adaptiveui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bawo.adaptiveui.R;

public class DetailsFragment extends Fragment {
    private TextView textViewDescription, textViewTitle;
    String description, title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment,null);
        textViewDescription = view.findViewById(R.id.detailFragmentDescription);
        textViewTitle = view.findViewById(R.id.detailFragmentTitle);

        this.description = savedInstanceState == null ? this.description : savedInstanceState.getString("description");
        this.title = savedInstanceState == null ? this.title : savedInstanceState.getString("title");
        setViews(this.description, this.title);

        return view;
    }

    public void setViews( String description, String title){
        this.description = description;
        this.title = title;
        textViewDescription.setText(this.description);
        textViewTitle.setText(this.title);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("description", this.description);
        outState.putString("title", this.title);
    }
}
