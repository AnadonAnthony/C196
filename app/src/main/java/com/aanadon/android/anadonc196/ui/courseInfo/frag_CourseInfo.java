package com.aanadon.android.anadonc196.ui.courseInfo;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.aanadon.android.anadonc196.R;
import com.aanadon.android.anadonc196.db.AppRepository;
import com.aanadon.android.anadonc196.editCourse;
import com.aanadon.android.anadonc196.editTerm;
import com.aanadon.android.anadonc196.models.CourseEntity;
import com.aanadon.android.anadonc196.utilities.Constants;
import com.aanadon.android.anadonc196.utilities.Utilities;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class frag_CourseInfo extends Fragment {

    @BindView(R.id.txtEditCourseTitle)
    TextView _Title;
    @BindView(R.id.txtEditCourseStart)
    TextView _Start;
    @BindView(R.id.txtEditCourseEnd)
    TextView _End;
    @BindView(R.id.txtEditCourseStatus)
    Spinner _Status;

    @OnClick(R.id.txtEditCourseEnd)
    public void onClick_EndDate()   {
        final Calendar Start  = Calendar.getInstance();
        int Year    = Start.get(Calendar.YEAR);
        int Month   = Start.get(Calendar.MONTH);
        int Date    = Start.get(Calendar.DATE);

        DatePickerDialog Dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                Calendar Selection  = Calendar.getInstance();
                Selection.set(year, month, date);
                _End.setText(Utilities.toString(Selection.getTime()));

                _EndOK  = true;

                if (Selection.before(Start))
                    _Status.setSelection(3, true);
            }
        }, Year, Month, Date);
        Dialog.show();
    }
    @OnClick(R.id.txtEditCourseStart)
    public void onClick_StartDate() {
        final Calendar Start  = Calendar.getInstance();
        int Year    = Start.get(Calendar.YEAR);
        int Month   = Start.get(Calendar.MONTH);
        int Date    = Start.get(Calendar.DATE);

        DatePickerDialog Dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                Calendar Selection  = Calendar.getInstance();
                Selection.set(year, month, date);
                _Start.setText(Utilities.toString(Selection.getTime()));
                _StartOK    = true;

                if (Selection.before(Start))
                    _Status.setSelection(1, true);
                else if (Selection.after(Start))
                    _Status.setSelection(0, true);

                String EndText  = _End.getText().toString().trim();
                if (TextUtils.isEmpty(EndText)) {
                    Selection.add(Calendar.MONTH, 1);
                    _End.setText(Utilities.toString(Selection.getTime()));
                    _EndOK  = true;
                }
            }
        }, Year, Month, Date);
        Dialog.show();
    }
    @OnTextChanged(R.id.txtEditCourseTitle)
    public void onTextChanged_Title(CharSequence pText) {
        String Text = _Title.getText().toString().trim();
        Utilities.ColorLabel(_Title, Text.length() >= 2);

        _TitleOK    = Text.length() >= 2;
    }

    private boolean _EndOK      = false;
    private boolean _StartOK    = false;
    private boolean _TitleOK    = false;

    private AppRepository _Repo;
    private frag_vmdl_CourseInfo homeViewModel;
    private Executor _Executor  = Executors.newSingleThreadExecutor();
    private MutableLiveData<CourseEntity> _Data = new MutableLiveData<>();

    private void saveCourse() {
        if (isReadyToSave())    {
            CourseEntity Course = _Data.getValue();

            if (null == Course)
                Course  = new CourseEntity();

            Course.setTermId(editTerm.getTermId());
            Course.setCourseTitle(_Title.getText().toString().trim());
            Course.setCourseStatus(_Status.getSelectedItemPosition());
            Course.setEndDate(Utilities.parseDate(_End.getText().toString().trim()));
            Course.setStartDate(Utilities.parseDate(_Start.getText().toString().trim()));

            _Repo.insertCourse(Course);
        }
    }

    private boolean isReadyToSave() {
        if (!editCourse.isNewCourse())  {
            //  Test for changes
            String InitTitle    = _Data.getValue().getCourseTitle();
            int InitStatus      = _Data.getValue().getCourseStatus();
            String InitEnd      = Utilities.toString(_Data.getValue().getEndDate());
            String InitStart    = Utilities.toString(_Data.getValue().getStartDate());

            String CurEnd       = _End.getText().toString().trim();
            int CurStatus       = _Status.getSelectedItemPosition();
            String CurTitle     = _Title.getText().toString().trim();
            String CurStart     = _Start.getText().toString().trim();

            boolean StatusChanged   = InitStatus != CurStatus;
            boolean EndChanged      = !InitEnd.equals(CurEnd);
            boolean TitleChanged    = !InitTitle.equals(CurTitle);
            boolean StartChanged    = !InitStart.equals(CurStart);

            boolean Result          = TitleChanged || StartChanged || EndChanged || StatusChanged;
            Result                  = Result && ((EndChanged && _EndOK) || !EndChanged);
            Result                  = Result && ((StartChanged && _StartOK) || !StartChanged);
            Result                  = Result && ((TitleChanged && _TitleOK) || !TitleChanged);

            if (Result)
                Utilities.ButterToast(this,
                    String.format(getString(R.string.toastChanged),
                        getString(R.string.txtCourse),
                        InitTitle));

            return Result;
        }

        return _TitleOK && _StartOK && _EndOK;
    }

    private void initializeFragment()   {
        _Repo   = AppRepository.getInstance(getActivity().getBaseContext());
        _Data.observe(this, new Observer<CourseEntity>() {
            @Override
            public void onChanged(CourseEntity course)    {
                _Title.setText(course.getCourseTitle());
                _End.setText(Utilities.toString(course.getEndDate()));
                _Start.setText(Utilities.toString(course.getStartDate()));
                _Status.setSelection(course.getCourseStatus(), true);
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(frag_vmdl_CourseInfo.class);
        View root = inflater.inflate(R.layout.frag_course_info, container, false);

        ButterKnife.bind(this, root);

        initializeFragment();

        return root;
    }

    @Override
    public void onDetach() {
        saveCourse();
        super.onDetach();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ActionBar Bar   = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (null != Bar) {
            Bar.setDisplayHomeAsUpEnabled(true);
            Bar.setHomeAsUpIndicator(R.drawable.ic_save);
            Bar.setTitle(Html.fromHtml(String.format(Constants.TITLE_MOD, "Course Information")));
        }

        Menu NavMenu    = ((BottomNavigationView)getActivity().findViewById(R.id.nav_CourseView)).getMenu();
        if (editCourse.isNewCourse())   {
            _Title.setHint("Course Title");
            _Title.setTextColor(Color.RED);

            Calendar Today  = Calendar.getInstance();
            _Start.setHint(Utilities.toString(Today.getTime()));

            Today.add(Calendar.MONTH, 1);
            _End.setHint(Utilities.toString(Today.getTime()));

            _Status.setSelection(0, false);

            //  Hide the menu items that are useless when working on a NEW CourseEntity object
            NavMenu.findItem(R.id.nav_CourseNotes).setVisible(false);
            NavMenu.findItem(R.id.nav_CourseMentors).setVisible(false);
            NavMenu.findItem(R.id.nav_CourseAssessments).setVisible(false);
        }
        else    {
            Bundle Extras       = getActivity().getIntent().getExtras();
            final int courseId  = Extras.getInt(CourseEntity.PRIMARY_KEY);
            _Executor.execute(new Runnable() {
                @Override
                public void run() {
                    CourseEntity Course = _Repo.fetchCourseData(courseId);
                    _Data.postValue(Course);
                }
            });
        }
    }
}