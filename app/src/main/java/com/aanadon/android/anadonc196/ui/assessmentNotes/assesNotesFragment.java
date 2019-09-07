package com.aanadon.android.anadonc196.ui.assessmentNotes;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.aanadon.android.anadonc196.R;
import com.aanadon.android.anadonc196.editAssessmentNote;
import com.aanadon.android.anadonc196.utilities.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class assesNotesFragment extends Fragment {

    @BindView(R.id.btnAddAssessmentNote)
    FloatingActionButton _AddAssessmentNote;
    @OnClick(R.id.btnAddAssessmentNote)
    public void onClick_AddAssessmentNote() {
        Intent NoteIntent   = new Intent(getActivity().getBaseContext(), editAssessmentNote.class);
        startActivity(NoteIntent);
    }

    private assesNotesViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(assesNotesViewModel.class);
        View root = inflater.inflate(R.layout.frag_assessment_notes, container, false);

        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActionBar Bar   = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (null != Bar) {
            Bar.setDisplayHomeAsUpEnabled(true);
            Bar.setHomeAsUpIndicator(R.drawable.ic_save);
            Bar.setTitle(Html.fromHtml(String.format(Constants.TITLE_MOD, "Assessment Note List")));
        }
    }
}