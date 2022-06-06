package com.example.convidados.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.convidados.constants.DataBaseConstants;

public class GuestDataBaseHelper extends SQLiteOpenHelper {

    //NOME E VERSÃO DO BANCO DE DADOS
    private static final String NAME = "convidados.bd"; //NOME DO BANCO DE DADOS
    private static final int VERSION = 1;

    //AQUI ESTÁ DECLARADO DO QUE É PRECISO PARA CRIAR UM BANCO DE DADOS, USA DATABASE CONSTANTS
    private static final String CREATE_TABLE_GUEST = "create table " //cria uma tabela
            + DataBaseConstants.GUEST.TABLE_NAME + " ("
            + DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, " //id é uma coluna do tipo int
            + DataBaseConstants.GUEST.COLUMNS.NAME + " text, " //name é uma coluna do tipo
            + DataBaseConstants.GUEST.COLUMNS.PRESENCE + " interger); ";

    //CONSTRUTOR
    public GuestDataBaseHelper( Context context ) {
        super( context, NAME, null, VERSION );
    }

    //BANCO DE DADOS É CRIADO, CHAMANDO OS ATRIBUTOS ACIMA.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( CREATE_TABLE_GUEST ); //cria o banco de Dados

    }

    //BANDO É ATUALIZADO
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
