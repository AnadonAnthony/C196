package com.aanadon.android.anadonc196.ui.termInfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.aanadon.android.anadonc196.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class termInfoFragment extends Fragment {


    private termInfoViewModel _ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        _ViewModel  = ViewModelProviders.of(this).get(termInfoViewModel.class);
        View root   = inflater.inflate(R.layout.frag_term_info, container, false);



        ButterKnife.bind(this, root);


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Term Information");
    }
}