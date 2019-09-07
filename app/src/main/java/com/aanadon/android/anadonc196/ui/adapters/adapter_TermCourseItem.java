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

import java.util.Calendar;
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

        Calendar Today  = Calendar.getInstance();
        holder._Title.setText(Course.getCourseTitle());


        holder._End.setText(Utilities.toString(Course.getEndDate()));
        holder._Start.setText(Utilities.toString(Course.getStartDate()));

        int Status  = Course.getCourseStatus();
        switch (Status) {
            case Constants.CourseState.DROPPED:
            case Constants.CourseState.COMPLETED:
                holder._Progress.setProgress(holder._Progress.getMax());
                break;

            case Constants.CourseState.IN_PROGRESS:
                holder._Progress.setProgress(holder._Progress.getMax() / 2);

                if (Today.after(Course.getEndDate()))
                    holder._Title.setTextColor(Color.RED);

                break;

            default:
                if (Today.after(Course.getStartDate()))
                    holder._Progress.setProgress(holder._Progress.getMax() / 4);
                else
                    holder._Progress.setProgress(0);
                break;
        }

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
