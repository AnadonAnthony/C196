package com.aanadon.android.anadonc196;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.aanadon.android.anadonc196.utilities.Utilities;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class editCourseNote extends AppCompatActivity {

    private void saveNote() {
        if (isReadyToSave())    {

        }
    }

    private boolean isReadyToSave() {
        return false;
    }

    @Override
    public void onBackPressed() {
        saveNote();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course_note);
        Toolbar toolbar = findViewById(R.id.termToolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
    }

}
