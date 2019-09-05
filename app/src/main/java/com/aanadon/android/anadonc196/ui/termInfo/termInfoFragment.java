package com.aanadon.android.anadonc196.ui.termInfo;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.aanadon.android.anadonc196.R;
import com.aanadon.android.anadonc196.db.AppRepository;
import com.aanadon.android.anadonc196.editTerm;
import com.aanadon.android.anadonc196.models.TermEntity;
import com.aanadon.android.anadonc196.utilities.Utilities;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class termInfoFragment extends Fragment {

    //  <editor-fold default-state="collapsed" desc="Butterknife Injections">
    @BindView(R.id.txtTermTitle)
    TextView _Title;
    @OnTextChanged(R.id.txtTermTitle)
    public void onTextChanged(CharSequence text)    {
        //  Step 01) Retrieve the label name ensure it contains AT LEAST 01 character(s)
        String NewText  = text.toString().trim();
        _TitleOK        = !TextUtils.isEmpty(NewText);

        //  Step 02) Test for special characters
        Pattern Pat = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher Mat = Pat.matcher(NewText);
        _TitleOK    = !Mat.find();

        if (_TitleOK)
            _TitleLabel.setTextColor(Color.GRAY);
        else
            _TitleLabel.setTextColor(Color.RED);
    }
    @BindView(R.id.lblTermTitle)
    TextView _TitleLabel;
    @BindView(R.id.txtStartDate)
    TextView _Start;
    @BindView(R.id.lblTermStart)
    TextView _StartLabel;
    @BindView(R.id.txtEndDate)
    TextView _EndDate;
    @BindView(R.id.btnDeleteTerm)
    FloatingActionButton _Delete;
    @OnClick(R.id.btnDeleteTerm)
    public void onClick_DeleteTerm()    {
        if (null != _Data)  {
            _Deleting   = true;


            // TODO: 9/3/2019 Ensure there are no courses attached to this term prior to delete

            _Executor.execute(new Runnable() {
                @Override
                public void run() {
                    _Repository.deleteTerm(_Data.getValue());
                }
            });

            getActivity().finish();
        }
    }
    @OnClick(R.id.btnStartDate)
    public void onClick_SelectDate()    {
        Calendar Date   = Calendar.getInstance();
        int Year        = Date.get(Calendar.YEAR);
        int Month       = Date.get(Calendar.MONTH);

        DatePickerDialog Dialog = new DatePickerDialog(getContext(),
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    Calendar Start  = Calendar.getInstance();
                    Start.set(year, month,1);
                    _Start.setText(Utilities.toString(Start.getTime()));

                    Start.add(Calendar.MONTH, 6);
                    _EndDate.setText(Utilities.toString(Start.getTime()));

                    _StartOK    = true;
                    _StartLabel.setTextColor(Color.GRAY);
                }
            }, Year, Month, 1);
        Dialog.show();
    }
    //  </editor-fold>

    //  <editor-fold default-state="collapsed" desc="Member Variable Declerations">
    private boolean _NewTerm    = false;
    private boolean _StartOK    = false;
    private boolean _TitleOK    = false;
    private boolean _Deleting   = false;

    private AppRepository _Repository;
    private MutableLiveData<TermEntity> _Data   = new MutableLiveData<>();
    private Executor _Executor  = Executors.newSingleThreadExecutor();
    //  </editor-fold>

    private void saveAndReturn() {

        if (IsReadyToSave())    {
            TermEntity Term = _Data.getValue();

            if (null == Term)
                Term    = new TermEntity();

            Term.setTermTitle(_Title.getText().toString().trim());
            Term.setTermStart(Utilities.parseDate(_Start.getText().toString().trim()));

            _Repository.insertTerm(Term);
        }
    }

    private boolean IsReadyToSave() {
        if (!_NewTerm)  {
            //  Has anything changed since it was loaded?
            String Title01  = _Data.getValue().getTermTitle();
            String Start01  = Utilities.toString(_Data.getValue().getTermStart());

            String Title02  = _Title.getText().toString().trim();
            String Start02  = _Start.getText().toString().trim();

            boolean TitleChanged    = !Title01.equals(Title02);
            boolean StartChanged    = !Start01.equals(Start02);

            if ((TitleChanged && _TitleOK) ||
                (StartChanged && _StartOK)) {
                Utilities.ButterToast(this,
                        String.format(getString(R.string.toastChanged),
                                getString(R.string.txtTerm),
                                Title01));
                return true;
            }
        }

        return _StartOK && _TitleOK;
    }

    private void initializeFragment() {
        _Repository = AppRepository.getInstance(getActivity().getBaseContext());
        _Data.observe(this, new Observer<TermEntity>() {
            @Override
            public void onChanged(final TermEntity termEntity) {
                _Title.setText(termEntity.getTermTitle());
                _Start.setText(Utilities.toString(termEntity.getTermStart()));
                _EndDate.setText("(" + Utilities.toString(termEntity.getTermEnd()) + ")");
            }
        });

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root   = inflater.inflate(R.layout.frag_term_info, container, false);

        ButterKnife.bind(this, root);

        initializeFragment();

        return root;
    }

    @Override
    public void onDetach() {
        saveAndReturn();

        // TODO: 9/3/2019 Look for TermNotes & Courses References with termId's of -1
        super.onDetach();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //  Add in the 'home' button (before the header text) and change the icon to the checkmark.
        ActionBar Bar   = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (null != Bar) {
            Bar.setTitle("Term Information");
            Bar.setDisplayHomeAsUpEnabled(true);
            Bar.setHomeAsUpIndicator(R.drawable.ic_save);
        }


        //  Retrieve any extras that came with the bundle.
        //  If there are no extras, then process this as a NEW TERM entry. Otherwise, process this
        //  as a CURRENT TERM modification.
        if (editTerm.IsNewTerm()) {
            _NewTerm    = true;
            _Title.setHint("Term Title");
            _TitleLabel.setTextColor(Color.RED);

            Calendar Start  = Calendar.getInstance();
            _Start.setHint(Utilities.toString(Start.getTime()));
            _StartLabel.setTextColor(Color.RED);

            Start.add(Calendar.MONTH, 6);
            _EndDate.setHint("(" + Utilities.toString(Start.getTime()) + ")");

            //  If this is a new entry, then there's no reason to show the 'delete' button
            _Delete.setVisibility(View.INVISIBLE);

            //  Retrieve the bottom navigational menu and hide the items that are currently
            //  useless to the user
            BottomNavigationView BotView    = getActivity().findViewById(R.id.nav_TermView);
            Menu NavMenu    = BotView.getMenu();
            NavMenu.findItem(R.id.nav_TermNotes).setVisible(false);
            NavMenu.findItem(R.id.nav_TermCourses).setVisible(false);
        }
        else    {
            Bundle Extras       = getActivity().getIntent().getExtras();
            final int termId    = Extras.getInt("termId");
            _Executor.execute(new Runnable() {
                @Override
                public void run() {
                    TermEntity Term = _Repository.fetchTermData(termId);
                    _Data.postValue(Term);
                }
            });
        }
    }
}