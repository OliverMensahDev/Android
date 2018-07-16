package e.olivermensah.fragmentstatepageradapter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import e.olivermensah.fragmentstatepageradapter.R;


public class FragmentSix extends Fragment {

    public FragmentSix(){
        Log.i("Fragment", "Fragment six created");
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_six,container, false);
    }
}
