package com.nub.tourmate.Fragment;


import android.os.Bundle;
import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nub.tourmate.Adapter.MomentAdapter;
import com.nub.tourmate.BottomSheet.BottomSheet_AddMemory;
import com.nub.tourmate.Classes.MemoryClass;
import com.nub.tourmate.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemoryFragment extends Fragment {

    private FloatingActionButton floatingActionButtonMemory;
    private BottomSheet_AddMemory bottomSheet_addMemory;
    // private BottomSheet_AddMemory bottomSheet_addMemory;


    private DatabaseReference database;
    private MomentAdapter momentAdapter;
    private List<MemoryClass> memorylist;
    public String eventId;
    String currentuser;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    private RecyclerView memoryRecycler;

    private ProgressDialog loadinbar;

    public MemoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_memory, container, false);

        // floatingActionButtonMemory = view.findViewById(R.id.fabMemory);
        assert getArguments() != null;
        eventId = getArguments().getString("message");


        memorylist = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        currentuser = firebaseAuth.getCurrentUser().getUid();
        floatingActionButtonMemory = view.findViewById(R.id.fab);
        memoryRecycler = view.findViewById(R.id.memoryRecyclerView);
        loadinbar = new ProgressDialog(getContext());


        memoryRecycler.setLayoutManager(new LinearLayoutManager(null));

        loadinbar.setTitle("Add new Post");
        loadinbar.setMessage("Updating new post");
        loadinbar.show();
        loadinbar.setCanceledOnTouchOutside(true);
        //Toast.makeText(this, ""+eventId, Toast.LENGTH_SHORT).show();

//
//        floatingActionButtonMemory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//

//            }
//        });

        floatingActionButtonMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet_addMemory = new BottomSheet_AddMemory();
                bottomSheet_addMemory.setcID(eventId);
                bottomSheet_addMemory.show(getFragmentManager(), "bottomSheetImageDialog");

            }
        });


        database = FirebaseDatabase.getInstance().getReference().child("UserList").child(currentuser).child("Events").child(eventId);
        database.child("Memories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    memorylist.clear();
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        MemoryClass memoryClass = data.getValue(MemoryClass.class);
                        memorylist.add(memoryClass);
                    }
                    // Toast.makeText(getContext(), "" + memorylist.size(), Toast.LENGTH_SHORT).show();
                    momentAdapter = new MomentAdapter(memorylist, getContext());
                    memoryRecycler.setAdapter(momentAdapter);
                    momentAdapter.notifyDataSetChanged();
                    loadinbar.dismiss();
                } else {
                    Toast.makeText(getContext(), "Empty database", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

}
