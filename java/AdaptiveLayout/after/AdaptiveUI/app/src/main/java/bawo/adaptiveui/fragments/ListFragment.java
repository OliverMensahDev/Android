package bawo.adaptiveui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bawo.adaptiveui.R;
import bawo.adaptiveui.adapter.PostAdapter;
import bawo.adaptiveui.data.PostData;

public class ListFragment extends Fragment {
    public ListFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, null);
        setupRecyclerView(view);
        return  view;
    }



    private void setupRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        PostAdapter adapter = new PostAdapter(getContext(), PostData.postList());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
