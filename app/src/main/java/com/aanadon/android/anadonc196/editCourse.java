package com.aanadon.android.anadonc196;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.aanadon.android.anadonc196.models.CourseEntity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class editCourse extends AppCompatActivity {

    private static boolean _NewCourse   = true;
    public static boolean isNewCourse() {
        return _NewCourse;
    }

    private static int _CourseId    = -1;
    public static int getCourseId() {
        return _CourseId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle Extras   = getIntent().getExtras();
        _NewCourse      = (null == Extras);

        if (!_NewCourse)
            _CourseId   = Extras.getInt(CourseEntity.PRIMARY_KEY);
        else
            _CourseId   = -1;


        setContentView(R.layout.activity_edit_course);

        BottomNavigationView navView = findViewById(R.id.nav_CourseView);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_CourseInfo, R.id.nav_CourseMentors, R.id.nav_CourseAssessments, R.id.nav_CourseNotes).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_CourseFragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
}
