package com.aanadon.android.anadonc196;

import android.os.Bundle;

import com.aanadon.android.anadonc196.models.TermEntity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class editTerm extends AppCompatActivity {

    //  <editor-fold defaultstate="collapsed" des="Member Variables">
    private static boolean _NewTerm    = true;
    public static boolean IsNewTerm()   {
        return _NewTerm;
    }

    private static int _TermId          = -1;
    public static int getTermId()   { return _TermId; }
    //  </editor-fold>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Capture the Term mode (Create NEW || Modify CURRENT) in the static field. This way,
        //  the test is only performed when the entire activity set is generated
        Bundle Extras = getIntent().getExtras();
        _NewTerm = (null == Extras);


        if (!_NewTerm)
            _TermId = Extras.getInt(TermEntity.PRIMARY_KEY);
        else
            _TermId = -1;

        setContentView(R.layout.activity_edit_term);

        BottomNavigationView navView = findViewById(R.id.nav_TermView);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_TermInfo, R.id.nav_TermCourses, R.id.nav_TermNotes).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_TermFragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
}


