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

public class FragmentTwo extends Fragment {

    public FragmentTwo(){
        Log.i("Fragment", "Fragment two created");
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_two,container, false);
    }
}
