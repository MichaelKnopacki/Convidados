package com.example.convidados.view.viewholder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.convidados.R;
import com.example.convidados.model.GuestModel;
import com.example.convidados.view.listener.OnListClick;

public class GuestViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextName;
    private Context mContext;

    public GuestViewHolder(@NonNull View itemView) {
        super( itemView );

        this.mContext = itemView.getContext();
        this.mTextName = itemView.findViewById( R.id.text_name );
        //this.mContext = itemView.getContext();

    }

    public void bind (GuestModel guest, final OnListClick listener){
        this.mTextName.setText( guest.getName() );

        this.mTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCLick(guest.getId());

            }
        } );

        this.mTextName.setOnLongClickListener( new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                new AlertDialog.Builder( mContext )
                        .setTitle( "Remoção de convidado" )
                        .setMessage( "Deseja realmente remover este convidado?" )
                        .setPositiveButton( "Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                listener.onDelete(guest.getId());
                            }
                        } )
                        .setNeutralButton( "Não", null )
                        .show();

                return false;
            }
        } );

    }
}
