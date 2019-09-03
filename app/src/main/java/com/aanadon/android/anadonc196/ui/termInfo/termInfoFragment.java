package com.aanadon.android.anadonc196.ui.termInfo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Delete;

import com.aanadon.android.anadonc196.R;
import com.aanadon.android.anadonc196.models.TermEntity;
import com.aanadon.android.anadonc196.utilities.Constants;
import com.aanadon.android.anadonc196.utilities.Utilities;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class termInfoFragment extends Fragment {

    @BindView(R.id.txtTermTitle)
    TextView _Title;
    @BindView(R.id.txtStartDate)
    TextView _Start;
    @BindView(R.id.txtEndDate)
    TextView _EndDate;
    @BindView(R.id.btnDeleteTerm)
    FloatingActionButton _Delete;
    @OnClick(R.id.btnDeleteTerm)
    public void onClick_DeleteTerm()    {
        if (null != _Data)  {
            _ViewModel.deleteTerm(_Data);

            //  Clear the title text. This ensures that the term is not 'saved' when leaving the
            //  activity.
            _Title.setText("");

            // TODO: 9/3/2019 Ensure there are no courses attached to this term prior to delete

            getActivity().finish();
        }
    }

    private TermEntity _Data;
    private boolean _NewTerm    = false;
    private termInfoViewModel _ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root   = inflater.inflate(R.layout.frag_term_info, container, false);

        ButterKnife.bind(this, root);

        initializeViewModel();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        Log.i(Constants.LOG_TAG, "termInfoFragment.onActivityCreated");

        ActionBar Bar   = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (null != Bar) {
            Bar.setTitle("TermData Information");
            Bar.setDisplayHomeAsUpEnabled(true);
            Bar.setHomeAsUpIndicator(R.drawable.ic_save);
        }

        Bundle Extras   = getActivity().getIntent().getExtras();
        if (null == Extras) {
            _NewTerm    = true;
            _Title.setHint("Term Title");

            Calendar Start  = Calendar.getInstance();
            _Start.setHint(Utilities.toString(Start.getTime()));

            Start.add(Calendar.MONTH, 6);
            _EndDate.setHint("(" + Utilities.toString(Start.getTime()) + ")");

            //  If this is a new entry, then there's no reason to show the 'delete' button
            _Delete.setVisibility(View.INVISIBLE);
        }
        else    {
            final int termId  = Extras.getInt("termId");
            _ViewModel.fetchTerm(termId);
            final TermEntity ThisTerm = _ViewModel.TermData.getValue();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        saveAndReturn();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int ItemId  = item.getItemId();

        switch (ItemId) {
            case android.R.id.home:
                saveAndReturn();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveAndReturn() {

        String Title    = _Title.getText().toString().trim();
        String DateText = _Start.getText().toString().trim();

        if (Title.length() > 0 && DateText.length() >= 8) {

            Calendar Start = Calendar.getInstance();
            Start.setTime(Utilities.parseDate(DateText));

            TermEntity Temp = new TermEntity();
            Temp.setTermTitle(_Title.getText().toString().trim());

            Temp.setTermStart(Start.getTime());

            _ViewModel.saveTerm(Temp);
        }
    }

    private void initializeViewModel() {
        _ViewModel  = ViewModelProviders.of(this).get(termInfoViewModel.class);
        _ViewModel.TermData.observe(this, new Observer<TermEntity>() {
            @Override
            public void onChanged(final TermEntity termEntity) {
                _Data   = termEntity;
                _Title.setText(termEntity.getTermTitle());
                _Start.setText(Utilities.toString(termEntity.getTermStart()));
                _EndDate.setText("(" + Utilities.toString(termEntity.getTermEnd()) + ")");
            }
        });
    }
}