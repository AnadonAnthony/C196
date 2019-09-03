package com.aanadon.android.anadonc196;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class editAssessmentNote extends AppCompatActivity {

    @BindView(R.id.txtDate)
    TextView _Date;
    @BindView(R.id.txtNote)
    TextView _Note;
    @BindView(R.id.txtUserName)
    TextView _UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessment_note);
        Toolbar toolbar = findViewById(R.id.termToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create/Edit Assessment Note");

        ButterKnife.bind(this);
        _Note.setMovementMethod(new ScrollingMovementMethod());
    }

}
