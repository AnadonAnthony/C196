package com.aanadon.android.anadonc196;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class editAssessment extends AppCompatActivity {

    private static boolean _NewAssessment   = true;
    public static boolean isNewAssessment() {
        return _NewAssessment;
    }

    private static int _AssessmentId    = -1;
    public static int getAssessmentId() {
        return _AssessmentId;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessment);

        Bundle Extras   = getIntent().getExtras();
        _NewAssessment  = null == Extras;

        BottomNavigationView navView = findViewById(R.id.nav_AssessmentView);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_AssessmentInfo, R.id.nav_AssessmentNotes)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_AssessmentFragment);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

}
