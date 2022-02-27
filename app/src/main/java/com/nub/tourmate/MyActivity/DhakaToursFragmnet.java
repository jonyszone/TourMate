package com.nub.tourmate.MyActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nub.tourmate.R;

public class DhakaToursFragmnet extends Fragment {

    private RecyclerView recyclerView;
    String s1[],s2[];
    int[] images = {R.drawable.ahsan_manzil,R.drawable.karzan_hall};
    public DhakaToursFragmnet() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dhaka_tours, container, false);
        recyclerView = view.findViewById(R.id.recyclerVw);
        s2 = getResources().getStringArray(R.array.places__name);
        s1 = getResources().getStringArray(R.array.division_description);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ImageAdapter imageAdapter = new ImageAdapter(s1,s2,images,getContext());

        // ImageAdapter imageAdapter = new ImageAdapter(this,getImageList());
        recyclerView.setAdapter(imageAdapter);
        return view;
    }
}
