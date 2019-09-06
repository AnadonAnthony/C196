package com.aanadon.android.anadonc196.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aanadon.android.anadonc196.R;
import com.aanadon.android.anadonc196.editCourse;
import com.aanadon.android.anadonc196.models.CourseEntity;
import com.aanadon.android.anadonc196.utilities.Constants;
import com.aanadon.android.anadonc196.utilities.Utilities;

import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class adapter_TermCourseItem extends RecyclerView.Adapter<adapter_TermCourseItem.ViewHolder> {

    private final Context _Context;
    private final List<CourseEntity> _Courses;

    public adapter_TermCourseItem(List<CourseEntity> Courses, Context context) {
        this._Context = context;
        this._Courses = Courses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater Inflater = LayoutInflater.from(parent.getContext());
        View Root               = Inflater.inflate(R.layout.item_course, parent, false);

        return new ViewHolder(Root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CourseEntity Course   = _Courses.get(position);

        holder._Assessments.setText("0");
        holder._Title.setText(Course.getCourseTitle());


        holder._End.setText(Utilities.toString(Course.getEndDate()));
        holder._Start.setText(Utilities.toString(Course.getStartDate()));


        Date Today  = new Date();
        Date End    = Course.getEndDate();
        Date Start  = Course.getStartDate();
        holder._Progress.setMax((int)(End.getTime() - Start.getTime()));
        holder._Progress.setProgress((int)(Today.getTime() - Start.getTime()));
        holder._Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent EditCourse   = new Intent(_Context, editCourse.class);
                EditCourse.putExtra(CourseEntity.PRIMARY_KEY,
                    Course.getCourseId());
                _Context.startActivity(EditCourse);
            }
        });
    }

    @Override
    public int getItemCount() {
        return _Courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_CourseTitle)
        TextView _Title;
        @BindView(R.id.item_CourseStart)
        TextView _Start;
        @BindView(R.id.item_CourseEnd)
        TextView _End;
        @BindView(R.id.item_CourseState)
        TextView _State;
        @BindView(R.id.item_CourseAssessments)
        TextView _Assessments;
        @BindView(R.id.item_CourseProgress)
        ProgressBar _Progress;
        @BindView(R.id.item_CourseCard)
        CardView _Card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
