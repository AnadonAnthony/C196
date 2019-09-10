package com.aanadon.android.anadonc196.ui.assessmentInfo;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SingleGeneratedAdapterObserver;
import androidx.lifecycle.ViewModelProviders;

import com.aanadon.android.anadonc196.R;
import com.aanadon.android.anadonc196.db.AppRepository;
import com.aanadon.android.anadonc196.editAssessment;
import com.aanadon.android.anadonc196.editCourse;
import com.aanadon.android.anadonc196.models.AssessmentEntity;
import com.aanadon.android.anadonc196.utilities.Constants;
import com.aanadon.android.anadonc196.utilities.Utilities;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class frag_AssessmentInfo extends Fragment {

    //  <editor-fold defaultstate="collapsed" des="Butterknife Injections">
    @BindView(R.id.txtAssessmentTitle)
    TextView _Title;
    @BindView(R.id.btnAssessmentType)
    Switch _Type;
    @BindView(R.id.btnDeleteAssessment)
    FloatingActionButton _Delete;

    @OnClick(R.id.btnAssessmentType)
    public void onClick_AssessmentType()    {
        boolean Checked = _Type.isChecked();

        _Type.setText(Checked ?
            getString(R.string.txt_AssessmentTypePractical) :
            getString(R.string.txt_AssessmentTypeObjective));
    }

    @OnClick(R.id.btnDeleteAssessment)
    public void onClick_DeleteAssessment()  {
        _Repo.deleteAssessment(_Data.getValue());
        getActivity().finish();
    }

    @OnTextChanged(R.id.txtAssessmentTitle)
    public void onTextChanged_AssessmentTitle(CharSequence pText)   {
        String Text = _Title.getText().toString().trim();

        TitleOK     = Text.length() >= 4;

        _Title.setTextColor(TitleOK ?
            Color.DKGRAY :
            Color.RED);
    }
    //  </editor-fold>


    private boolean TitleOK = false;

    private AppRepository _Repo;
    private frag_vmdl_AssessmentInfo _ViewModel;
    private Executor _Executor  = Executors.newSingleThreadExecutor();
    private MutableLiveData<AssessmentEntity> _Data = new MutableLiveData<>();

    private void saveAssessment()   {
        if (isReadyToSave())    {
            AssessmentEntity Assessment = _Data.getValue();

            if (null == Assessment)
                Assessment  = new AssessmentEntity();

            Log.i(Constants.LOG_TAG,
                "Setting Assessment CourseId to: " + editCourse.getCourseId());
            Assessment.setCourseId(editCourse.getCourseId());
            Assessment.setAssessmentName(_Title.getText().toString().trim());
            Assessment.setAssessmentType(Utilities.toInt(_Type.isChecked()));

            _Repo.insertAssessment(Assessment);

            Utilities.ButterToast(this,
                "Saving Assessment (" + _Title.getText() + ")");
        }
    }

    private boolean isReadyToSave() {

        return TitleOK;
    }

    private void initializeFragment() {
        _Repo   = AppRepository.getInstance(getActivity().getBaseContext());
        _Data.observe(this, new Observer<AssessmentEntity>() {
            @Override
            public void onChanged(AssessmentEntity assessment) {
                _Title.setText(assessment.getAssessmentName());
                if (Utilities.toBoolean(assessment.getAssessmentType()))    {
                    _Type.setChecked(true);
                    _Type.setText(R.string.txt_AssessmentTypePractical);
                }
                else    {
                    _Type.setChecked(false);
                    _Type.setText(R.string.txt_AssessmentTypeObjective);
                }
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        _ViewModel =
                ViewModelProviders.of(this).get(frag_vmdl_AssessmentInfo.class);
        View root = inflater.inflate(R.layout.frag_assessment_info, container, false);

        ButterKnife.bind(this, root);

        initializeFragment();

        return root;
    }

    @Override
    public void onDetach()  {
        saveAssessment();
        super.onDetach();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ActionBar Bar   = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (null != Bar) {
            Bar.setDisplayHomeAsUpEnabled(true);
            Bar.setHomeAsUpIndicator(R.drawable.ic_save);
            Bar.setTitle(Html.fromHtml(String.format(Constants.TITLE_MOD, "Assessment Information")));
        }

        Menu NavMenu    = ((BottomNavigationView)getActivity().findViewById(R.id.nav_AssessmentView)).getMenu();
        if (editAssessment.isNewAssessment())   {
            _Title.setTextColor(Color.RED);

            NavMenu.findItem(R.id.nav_AssessmentNotes).setVisible(false);
        }
        else    {
            Bundle Extras           = getActivity().getIntent().getExtras();
            final int AssessmentId  = Extras.getInt(AssessmentEntity.PRIMARY_KEY);
            _Executor.execute(new Runnable() {
                @Override
                public void run() {
                    AssessmentEntity Assessment = _Repo.fetchAssessmentData(AssessmentId);
                    _Data.postValue(Assessment);
                }
            });

        }
    }
}