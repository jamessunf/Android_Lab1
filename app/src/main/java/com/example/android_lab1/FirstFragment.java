package com.example.android_lab1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class FirstFragment extends Fragment {

    private TextView txtMsg, txtId;
    private Button btnDel;



    public static FirstFragment newInstance(String id,String msg){

        FirstFragment firstFragment = new FirstFragment();
        Bundle bundle = new Bundle();
        bundle.putString("input_id",id);
        bundle.putString("input_msg",msg);
        firstFragment.setArguments(bundle);
        return firstFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        final DatabaseHelper mydb = new DatabaseHelper(getActivity());

        txtMsg = (TextView) view.findViewById(R.id.txt_msg);
        txtId = (TextView) view.findViewById(R.id.txt_id);
        btnDel = (Button) view.findViewById(R.id.btn_del);

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              String msg = getArguments().getString("input_msg");

               long input_id = mydb.deleteData(msg);
                Toast.makeText(getActivity(),"del=" + getArguments().getString("input_id"),Toast.LENGTH_SHORT).show();

            }
        });

        if(getArguments()!= null){

            txtMsg.setText("Massage: "  + getArguments().getString("input_msg"));
            txtId.setText("ID: " + getArguments().getString("input_id"));
        }

    }
}
