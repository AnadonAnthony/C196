package com.aanadon.android.anadonc196.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.aanadon.android.anadonc196.R;
import com.aanadon.android.anadonc196.editTerm;
import com.aanadon.android.anadonc196.models.TermEntity;
import com.aanadon.android.anadonc196.utilities.Constants;
import com.aanadon.android.anadonc196.utilities.Random;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TermItemAdapter extends RecyclerView.Adapter<TermItemAdapter.ViewHolder> {

    private final Context _Context;
    private final List<TermEntity> _TermList;

    public TermItemAdapter(List<TermEntity> _TermList, Context _Context) {
        Log.i(Constants.LOG_TAG, "Constructing the TermItemAdapter");

        this._TermList = _TermList;
        this._Context = _Context;
        Log.i(Constants.LOG_TAG, "TermItemAdapter Constructed");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater Inflater = LayoutInflater.from(parent.getContext());
        View Root               = Inflater.inflate(R.layout.item_term, parent, false);
        return new ViewHolder(Root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TermEntity Term   = _TermList.get(position);
        holder._TitleText.setText(Term.getTermTitle());

        holder._CourseCount.setText(String.format("%02d", Random._Rand.nextInt(3) + 2));

        Date Start  = Term.getTermStart();
        if (null != Start)
            holder._StartText.setText(new SimpleDateFormat("MMM. dd yyyy").format(Start));

        holder.CardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent EditTermIntent   = new Intent(_Context, editTerm.class);
                EditTermIntent.putExtra("termId", Term.getTermId());
                _Context.startActivity(EditTermIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return _TermList.size();
    }

    //  This manages each of the singular item views
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_TermTitle)
        TextView _TitleText;
        @BindView(R.id.txt_StartDate)
        TextView _StartText;
        @BindView(R.id.txt_CourseCount)
        TextView _CourseCount;
        @BindView(R.id.topCardLayout)
        ConstraintLayout CardLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
