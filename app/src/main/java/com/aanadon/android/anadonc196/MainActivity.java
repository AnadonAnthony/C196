package com.aanadon.android.anadonc196;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.aanadon.android.anadonc196.models.TermEntity;
import com.aanadon.android.anadonc196.ui.adapters.adapter_TermItem;
import com.aanadon.android.anadonc196.utilities.Constants;
import com.aanadon.android.anadonc196.vms.vm_Term;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity
    extends AppCompatActivity {

    @BindView(R.id.btnAddTerm)
    FloatingActionButton _AddTerm;
    @OnClick(R.id.btnAddTerm)
    public void onClick_AddTerm()   {
        Intent TermIntent   = new Intent(this, editTerm.class);
        startActivity(TermIntent);
    }

    @BindView(R.id.viewTermList)
    RecyclerView _TermView;

    private vm_Term _TermModel;
    private adapter_TermItem _TermAdapter;
    private List<TermEntity> _TermList  = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.termToolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initializeRecyclerView();
        initializeViewModel();
        requestPermission();
    }

    private static final int REQ_GET_ACCOUNTS   = 128;
    private static final int REQ_READ_PROFILE   = 64;
    private static final int REQ_READ_CONTACTS  = 32;

    private static String _Username = "UNKNOWN";
    public static String getUsername()  {
        return _Username;
    }

    private void requestPermission() {

        Context AppContext  = getApplicationContext();

        if (ContextCompat.checkSelfPermission(AppContext, Manifest.permission.GET_ACCOUNTS)
            != PackageManager.PERMISSION_GRANTED)   {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.GET_ACCOUNTS))  {
                Toast.makeText(getApplicationContext(),
                    "Your account is used to determine the UserName stored with each Note.",
                    Toast.LENGTH_SHORT).show();

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.GET_ACCOUNTS},
                        REQ_GET_ACCOUNTS);
            }
            else    {
                ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.GET_ACCOUNTS},
                    REQ_GET_ACCOUNTS);
            }
        }
        else    {
            _Username   = getEmailId(getApplicationContext());
        }
    }

    private void initializeViewModel() {
        final Observer<List<TermEntity>> TermObserver   = new Observer<List<TermEntity>>() {
            @Override
            public void onChanged(List<TermEntity> termEntities) {
                _TermList.clear();
                _TermList.addAll(termEntities);

                if (null == _TermAdapter)   {
                    _TermAdapter    = new adapter_TermItem(_TermList, MainActivity.this);
                    _TermView.setAdapter(_TermAdapter);
                }
                else    {
                    _TermAdapter.notifyDataSetChanged();
                }
            }
        };

        _TermModel  = ViewModelProviders.of(this)
            .get(vm_Term.class);
        _TermModel.TermList.observe(this, TermObserver);
    }

    private void initializeRecyclerView() {
        _TermView.setHasFixedSize(true);

        LinearLayoutManager Layout  = new LinearLayoutManager(this);
        _TermView.setLayoutManager(Layout);
    }

    private static String getEmailId(Context pContext)  {
        AccountManager Manager  = AccountManager.get(pContext);
        Account[] Accounts      = Manager.getAccountsByType("google.com");
        Account Account;
        if (Accounts.length > 0)
            Account = Accounts[0];
        else
            return "student@wgu.edu";
        return Account.name;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)    {
            case REQ_GET_ACCOUNTS:
                if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED)    {
                    _Username   = getEmailId(getApplicationContext());
                }
        }
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
