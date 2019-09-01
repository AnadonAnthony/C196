package com.aanadon.android.anadonc196;

import android.content.Intent;
import android.os.Bundle;

import com.aanadon.android.anadonc196.models.TermEntity;
import com.aanadon.android.anadonc196.ui.adapters.TermItemAdapter;
import com.aanadon.android.anadonc196.utilities.Constants;
import com.aanadon.android.anadonc196.utilities.Samples;
import com.aanadon.android.anadonc196.vms.TermViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnAddTerm)
    FloatingActionButton _AddTerm;
    @OnClick(R.id.btnAddTerm)
    public void onClick_AddTerm()   {
        Intent TermIntent   = new Intent(this, editTerm.class);
        startActivity(TermIntent);
    }

    @BindView(R.id.viewTermList)
    RecyclerView _TermView;

    private TermViewModel _TermModel;
    private TermItemAdapter _TermAdapter;
    private List<TermEntity> _TermList  = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initializeRecyclerView();
        initializeViewModel();
    }

    private void initializeViewModel() {
        final Observer<List<TermEntity>> TermObserver   = new Observer<List<TermEntity>>() {
            @Override
            public void onChanged(List<TermEntity> termEntities) {
                _TermList.clear();
                _TermList.addAll(termEntities);

                if (null == _TermAdapter)   {
                    _TermAdapter    = new TermItemAdapter(_TermList, MainActivity.this);
                    _TermView.setAdapter(_TermAdapter);
                }
                else    {
                    _TermAdapter.notifyDataSetChanged();
                }
            }
        };

        _TermModel  = ViewModelProviders.of(this)
            .get(TermViewModel.class);
        _TermModel.TermList.observe(this, TermObserver);
    }

    private void initializeRecyclerView() {
        _TermView.setHasFixedSize(true);

        LinearLayoutManager Layout  = new LinearLayoutManager(this);
        _TermView.setLayoutManager(Layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_AddSamples:
                addSampleData();
                return true;

            case R.id.action_DeleteSamples:
                deleteSampleData();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteSampleData() {
        _TermModel.deleteSampleData();
    }

    private void addSampleData() {
        _TermModel.addSampleData();
    }
}
