package bawo.com.databinding;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bawo.com.databinding.data.DataSource;
import bawo.com.databinding.databinding.FragmentDataBinding;

public class DataFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentDataBinding binding = FragmentDataBinding.inflate(inflater, container, false);
        DataSource data  = DataSource.get("Fragment Binding");
        binding.setDataSource(data);
        //getRoot
        return binding.getRoot();
    }
}
