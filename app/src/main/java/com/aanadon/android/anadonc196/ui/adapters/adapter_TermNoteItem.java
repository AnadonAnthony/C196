package com.aanadon.android.anadonc196.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aanadon.android.anadonc196.R;
import com.aanadon.android.anadonc196.editTermNote;
import com.aanadon.android.anadonc196.models.TermNoteEntity;
import com.aanadon.android.anadonc196.utilities.Constants;
import com.aanadon.android.anadonc196.utilities.Utilities;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class adapter_TermNoteItem extends RecyclerView.Adapter<adapter_TermNoteItem.ViewHolder> {

    @BindView(R.id.txtNoteItemDate)
    TextView _Date;

    private final Context _Context;
    private final List<TermNoteEntity> _Notes;

    public adapter_TermNoteItem(List<TermNoteEntity> notes, Context context) {
        this._Context = context;
        this._Notes = notes;
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
        final TermNoteEntity Note   = _Notes.get(position);

        holder._Text.setText(Note.getNoteText());
        holder._User.setText(Note.getUserName());
        holder._Date.setText(Utilities.toString(Note.getCreateDate()));
        holder._Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddTermNote = new Intent(_Context, editTermNote.class);
                AddTermNote.putExtra(TermNoteEntity.PRIMARY_KEY,
                    Note.getNoteId());
                _Context.startActivity(AddTermNote);
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
