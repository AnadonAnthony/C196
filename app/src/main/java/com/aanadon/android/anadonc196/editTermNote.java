package com.aanadon.android.anadonc196;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.aanadon.android.anadonc196.db.AppRepository;
import com.aanadon.android.anadonc196.models.TermNoteEntity;
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

public class editTermNote extends AppCompatActivity {

    //  <editor-fold defaultstate="collapsed" des="Butterknife Injections">
    @BindView(R.id.txtNoteItemDate)
    TextView _Date;
    @BindView(R.id.txtNoteItemText)
    TextView _Text;
    @BindView(R.id.txtNoteItemUser)
    TextView _User;
    @BindView(R.id.btnNoteItemDelete)
    FloatingActionButton _Delete;
    @BindView(R.id.btnNoteItemShare)
    FloatingActionButton _Share;

    @OnClick(R.id.btnNoteItemShare)
    public void onClick_Share() {
        // TODO: 9/5/2019 Add Share note options action
    }
    @OnClick(R.id.btnNoteItemDelete)
    public void onClick_Delete()    {
        if (!_NewNote) {
            TermNoteEntity Note = _Data.getValue();
            _Repository.deleteTermNote(Note);
            finish();
        }
    }
    @OnTextChanged(R.id.txtNoteItemText)
    public void onTextChanged_NoteText(CharSequence pText)  {
        //  Step 01) Retrieve the text field
        String Text = _Text.getText().toString().trim();

        //  Step 02) Start simple: Length of the text. It's gotta be AT LEAST one word long...
        _TextOK = Text.length() >= 5;

        //  Step 03) Test for special characters
        _TextOK = _TextOK && !Text.matches("[@{}]");

        Utilities.ColorLabel(_Text, _TextOK);
    }
    //  </editor-fold>

    //  <editor-fold defaultstate="collapsed" des="Member Variables">
    private AppRepository _Repository;
    private Executor _Executor  = Executors.newSingleThreadExecutor();
    private MutableLiveData<TermNoteEntity> _Data   = new MutableLiveData<>();

    private boolean _TextOK = false;

    private static boolean _NewNote = true;
    //  </editor-fold>

    private void saveNote() {
        if (_TextOK)    {
            TermNoteEntity Note = _Data.getValue();

            if (null == Note)
                Note = new TermNoteEntity();

            if (_NewNote) {
                Note.setCreateDate(new Date());
                Note.setUserName(MainActivity.getUsername());
            }

            Note.setTermId(editTerm.getTermId());
            Note.setNoteText(_Text.getText().toString().trim());



            _Repository.insertTermNote(Note);
        }
    }

    @SuppressLint("RestrictedApi")
    private void init_EditTermNote() {
        _Repository = AppRepository.getInstance(getBaseContext());

        Bundle Extras   = getIntent().getExtras();
        _NewNote        = (null == Extras);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (_NewNote) {
            _Date.setText(Utilities.toString(new Date()));
            _User.setText("- " + MainActivity.getUsername());
            getSupportActionBar().setTitle(getString(R.string.txt_CreateTermNote));

            _Share.setVisibility(View.INVISIBLE);
            _Delete.setVisibility(View.INVISIBLE);
        }
        else {
            getSupportActionBar().setTitle(getString(R.string.txt_EditTermNote));
            final int NoteId  = Extras.getInt(TermNoteEntity.PRIMARY_KEY);

            _Executor.execute(new Runnable() {
                @Override
                public void run() {
                    TermNoteEntity Note = _Repository.fetchTermNoteData(NoteId);
                    _Data.postValue(Note);
                }
            });
        }

        _Data.observe(this, new Observer<TermNoteEntity>() {
            @Override
            public void onChanged(TermNoteEntity NoteEntity) {
                _Text.setText(NoteEntity.getNoteText());
                _User.setText("- " + NoteEntity.getUserName());
                _Date.setText(Utilities.toString(NoteEntity.getCreateDate()));
            }
        });
    }

    @Override
    public void onBackPressed() {
        saveNote();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term_note);
        Toolbar toolbar = findViewById(R.id.termToolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        init_EditTermNote();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            saveNote();

        return super.onOptionsItemSelected(item);
    }
}
