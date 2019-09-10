package com.aanadon.android.anadonc196.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aanadon.android.anadonc196.R;
import com.aanadon.android.anadonc196.editCourseNote;
import com.aanadon.android.anadonc196.models.CourseNoteEntity;
import com.aanadon.android.anadonc196.utilities.Utilities;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class adapter_CourseNoteItem extends RecyclerView.Adapter<adapter_CourseNoteItem.ViewHolder> {

    private final Context _Context;
    private final List<CourseNoteEntity> _Notes;

    public adapter_CourseNoteItem(List<CourseNoteEntity> notes, Context context) {
        _Context = context;
        _Notes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater Inflater = LayoutInflater.from(parent.getContext());
        View Root               = Inflater.inflate(R.layout.item_note, parent, false);
        return new ViewHolder(Root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CourseNoteEntity Note = _Notes.get(position);

        holder._Text.setText(Note.getNoteText());
        holder._User.setText(Note.getUserName());
        holder._Date.setText(Utilities.toString(Note.getCreateDate()));
        holder._Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent EditNote = new Intent(_Context, editCourseNote.class);
                EditNote.putExtra(CourseNoteEntity.PRIMARY_KEY, Note.getNoteId());
                _Context.startActivity(EditNote);
            }
        });
    }

    @Override
    public int getItemCount() {
        return _Notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_NoteDate)
        TextView _Date;
        @BindView(R.id.item_NoteText)
        TextView _Text;
        @BindView(R.id.item_NoteUser)
        TextView _User;
        @BindView(R.id.item_NoteCard)
        CardView _Card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
