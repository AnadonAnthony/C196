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
        initializeViewModel();
        initializeRecyclerView();

        Log.i(Constants.LOG_TAG, "Creating Sample Data");
        _TermList.addAll(_TermModel.TermList);
    }

    private void initializeViewModel() {
        _TermModel  = ViewModelProviders.of(this)
            .get(TermViewModel.class);
    }

    private void initializeRecyclerView() {
        Log.i(Constants.LOG_TAG, "Initializing the Recycler View");
        LinearLayoutManager Layout  = new LinearLayoutManager(this);

        _TermView.setHasFixedSize(true);
        _TermView.setLayoutManager(Layout);

        Log.i(Constants.LOG_TAG, "Creating the TermItem Adapter (@ MainActivity)");
        _TermAdapter    = new TermItemAdapter(_TermList, this);
        _TermView.setAdapter(_TermAdapter);

        Log.i(Constants.LOG_TAG, "Recycler View Initialization Complete");
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
