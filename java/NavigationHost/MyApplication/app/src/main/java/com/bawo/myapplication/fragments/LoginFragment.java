package com.bawo.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawo.myapplication.NavigationHost;
import com.bawo.myapplication.R;

public class LoginFragment extends Fragment {

    public LoginFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loginfragment, container, false);

        TextView nextButton = view.findViewById(R.id.next);

        // Set an error if the password is less than 8 characters.
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    ((NavigationHost) getActivity()).navigateTo(new PageFragment(), false); // Navigate to the next Fragment
            }
        });

        return view;
    }
}
