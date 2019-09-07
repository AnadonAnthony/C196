package com.aanadon.android.anadonc196.utilities;

public class Constants {
    public static final String LOG_TAG      = "AnadonC196";
    public static final String TITLE_MOD    = "<FONT COLOR='#FFFFFF'>%1$s</FONT>";

    public class CourseState    {
        public static final int PLANNED     = 0;
        public static final int IN_PROGRESS = 1;
        public static final int DROPPED     = 2;
        public static final int COMPLETED   = 3;
    }
    public static String stateToString(int pCourseState) {
        switch (pCourseState)   {
            case CourseState.COMPLETED:
                return "Completed";

            case CourseState.DROPPED:
                return "Dropped";

            case CourseState.IN_PROGRESS:
                return "In Progress";

            case CourseState.PLANNED:
            default:
                return "Planned";
        }
    }
}
