package com.aanadon.android.anadonc196;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.aanadon.android.anadonc196.db.AppRepository;
import com.aanadon.android.anadonc196.models.CourseNoteEntity;
import com.aanadon.android.anadonc196.utilities.Constants;
import com.aanadon.android.anadonc196.utilities.Utilities;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class editCourseNote extends AppCompatActivity {

    @BindView(R.id.txtNoteItemDate)
    TextView _Date;
    @BindView(R.id.txtNoteItemText)
    TextView _Text;
    @BindView(R.id.txtNoteItemUser)
    TextView _User;
    @BindView(R.id.btnNoteItemShare)
    FloatingActionButton _Share;
    @BindView(R.id.btnNoteItemDelete)
    FloatingActionButton _Delete;

    @OnClick(R.id.btnNoteItemShare)
    public void onClick_ShareNote() {

    }
    @OnClick(R.id.btnNoteItemDelete)
    public void onClick_DeleteNote()    {

        _Repo.deleteCourseNote(_Data.getValue());
        finish();
    }
    @OnTextChanged(R.id.txtNoteItemText)
    public void onTextChanged_NoteText(CharSequence text)   {
        String Text = _Text.getText().toString().trim();

        TextOK  = (Text.length() >= 5);

        Utilities.ColorLabel(_Text, TextOK);
    }

    private boolean NewNote = true;
    private boolean TextOK  = false;

    private AppRepository _Repo;
    private Executor _Executor  = Executors.newSingleThreadExecutor();
    private MutableLiveData<CourseNoteEntity> _Data = new MutableLiveData<>();

    private void saveNote() {
        if (isReadyToSave())    {
            CourseNoteEntity Note   = _Data.getValue();

            if (null == Note)
                Note    = new CourseNoteEntity();

            if (NewNote)    {
                Note.setCreateDate(new Date());
                Note.setUserName(MainActivity.getUsername());
            }

            Note.setCourseId(editCourse.getCourseId());
            Note.setNoteText(_Text.getText().toString().trim());

            _Repo.insertCourseNote(Note);
            Log.i(Constants.LOG_TAG, "Course Note Saved");
        }
        else
            Log.i(Constants.LOG_TAG, "Course Note not ready for saving.");
    }

    private boolean isReadyToSave() {

        return TextOK;
    }

    @SuppressLint("RestrictedApi")
    private void initEditCourseNote() {
        _Repo   = AppRepository.getInstance(getBaseContext());

        Bundle Extras   = getIntent().getExtras();
        NewNote         = (null == Extras);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_save);
        if (NewNote)    {
            getSupportActionBar().setTitle("Create Course Note");
            _Share.setVisibility(View.INVISIBLE);
            _Delete.setVisibility(View.INVISIBLE);

            _Date.setText(Utilities.toString(new Date()));
            _User.setText("- " + MainActivity.getUsername());
        }
        else    {
            getSupportActionBar().setTitle("Edit Course Note");
            final int NoteId    = Extras.getInt(CourseNoteEntity.PRIMARY_KEY);

            _Executor.execute(new Runnable() {
                @Override
                public void run() {
                    CourseNoteEntity Note   = _Repo.fethCourseNote(NoteId);
                    _Data.postValue(Note);
                }
            });
        }

        _Data.observe(this, new Observer<CourseNoteEntity>() {
            @Override
            public void onChanged(CourseNoteEntity note) {
                _Text.setText(note.getNoteText());
                _User.setText(note.getUserName());
                _Date.setText(Utilities.toString(note.getCreateDate()));
            }
        });
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

        initEditCourseNote();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            saveNote();

        return super.onOptionsItemSelected(item);
    }
}
