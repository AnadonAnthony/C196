package com.aanadon.android.anadonc196.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aanadon.android.anadonc196.R;
import com.aanadon.android.anadonc196.editAssessment;
import com.aanadon.android.anadonc196.models.AssessmentEntity;
import com.aanadon.android.anadonc196.utilities.Utilities;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class adapter_AssessmentItem extends RecyclerView.Adapter<adapter_AssessmentItem.ViewHolder> {

    private final Context _Context;
    private final List<AssessmentEntity> _Assessments;

    public adapter_AssessmentItem(List<AssessmentEntity> assessments, Context context)  {
        _Context        = context;
        _Assessments    = assessments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater Inflater = LayoutInflater.from(parent.getContext());
        View Root               = Inflater.inflate(R.layout.item_assessment, parent, false);

        return new ViewHolder(Root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final AssessmentEntity Assessment   = _Assessments.get(position);

        holder._Title.setText(Assessment.getAssessmentName());
        if (Utilities.toBoolean(Assessment.getAssessmentType()))
            holder._Type.setText("Performance");
        else
            holder._Type.setText("Objective");

        holder._Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent EditAssessment   = new Intent(_Context, editAssessment.class);
                EditAssessment.putExtra(AssessmentEntity.PRIMARY_KEY, Assessment.getAssessmentId());
                _Context.startActivity(EditAssessment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return _Assessments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtAssessmentTitle)
        TextView _Title;
        @BindView(R.id.txtAssessmentType)
        TextView _Type;
        @BindView(R.id.item_AssessmentCard)
        CardView _Card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
