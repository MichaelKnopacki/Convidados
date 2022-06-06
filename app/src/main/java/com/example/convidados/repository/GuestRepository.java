package com.example.convidados.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.convidados.constants.DataBaseConstants;
import com.example.convidados.constants.GuestConstants;
import com.example.convidados.model.GuestModel;

import java.util.ArrayList;
import java.util.List;

public class GuestRepository {

    // SINGLETON - DESING PATTERN
    // UM ACESSO POR VEZ A CLASSES QUE SÃO SENSIVEIS A ACESSOS;
    // CONSTRUTO FECHADO

    private static GuestRepository INSTANCE;//SINGLETON
    private GuestDataBaseHelper mHelper;//BANCO DE DADOS

    private GuestRepository(Context context){
        this.mHelper = new GuestDataBaseHelper( context );
    }

    public static GuestRepository getInstance(Context context){
        if( INSTANCE == null){
            INSTANCE = new GuestRepository (context);
        }
        return INSTANCE;
    }

    //CARREGANDO A LISTA DE CONVIDADOS DO BANCO DE DADOS
    private List<GuestModel> getList(String selection, String[] selectionArgs) {
        List<GuestModel> list = new ArrayList<>();
        try {
            SQLiteDatabase db = this.mHelper.getReadableDatabase();

            String table = DataBaseConstants.GUEST.TABLE_NAME;

            // COLUNAS QUE SERÃO RETORNADAS
            String[] columns = {DataBaseConstants.GUEST.COLUMNS.ID,
                    DataBaseConstants.GUEST.COLUMNS.NAME,
                    DataBaseConstants.GUEST.COLUMNS.PRESENCE};

            // FAZ A SELEÇÃO
            Cursor cursor = db.query(table, columns, selection, selectionArgs, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {

                    int id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID));
                    String name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME));
                    int presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE));

                    list.add(new GuestModel(id, name, presence));
                }
            }

            // FECHA O CURSOR
            if (cursor != null) {
                cursor.close();
            }

            // RETORNA A LISTAGEM
            return list;
        } catch (Exception e) {
            return list;
        }
    }

    public List<GuestModel> getAll(){

        return this.getList(null, null);
    }

    public List<GuestModel> getPresents(){

        String selection = DataBaseConstants.GUEST.COLUMNS.PRESENCE + " = ?";
        String[] selectionArgs = {String.valueOf( GuestConstants.CONFIRMATION.PRESENT )};

        return this.getList(selection, selectionArgs);
    }

    public List<GuestModel> getAbsents(){

        String selection = DataBaseConstants.GUEST.COLUMNS.PRESENCE + " = ?";
        String[] selectionArgs = {String.valueOf( GuestConstants.CONFIRMATION.ABSENT )};

        return this.getList(selection, selectionArgs);

    }


    //CARREGANDO UM CONVIDADO DO BANCO DE DADOS
    public GuestModel load ( int id){

        try {

            GuestModel guest = null;
            SQLiteDatabase db = this.mHelper.getReadableDatabase();//BUSCA A INFORMAÇÃO

            String table = DataBaseConstants.GUEST.TABLE_NAME;

            String[] columns = {DataBaseConstants.GUEST.COLUMNS.ID,
                    DataBaseConstants.GUEST.COLUMNS.NAME ,
                    DataBaseConstants.GUEST.COLUMNS.PRESENCE};

            String selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?";
            String[] selectionArgs = {String.valueOf(id)};

            Cursor cursor = db.query( table, columns, selection, selectionArgs, null,
                    null, null); //COMO UM PONTEIRO DENTRO DO BD
            if ( cursor != null && cursor.getCount() > 0){
                cursor.moveToFirst();

               String name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME));// BUSCA O NOME
               int presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE));//BUSCA O STATUS

                guest = new GuestModel( id, name, presence );
            }

            if (cursor!= null){
                cursor.close();//ENCERRA O CURSOR
            }
            return guest;//RETORNA A LISTA

        }catch ( Exception e){
            return null;
        }
    }

    // INSERINDO DADOS NO BANCO DE DADOS
    public boolean insert (GuestModel guest){

        try{
            SQLiteDatabase db = this.mHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put( DataBaseConstants.GUEST.COLUMNS.NAME, guest.getName() );
            values.put( DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.getConfirmation() );

            db.insert( DataBaseConstants.GUEST.TABLE_NAME, null, values );//INSERÇÃO DO DADO
            db.close(); //DEPOIS DA INSERÇÃO O BD É FECHADO.

            return true;

        } catch ( Exception e ){
            return false;
        }

    }

    public boolean update (GuestModel guest){

        try{

            SQLiteDatabase db = this.mHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put( DataBaseConstants.GUEST.COLUMNS.NAME, guest.getName() );
            values.put( DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.getConfirmation() );

            String where = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"; //FILTRA PELO ID ONDE SERÁ ALTERADO
            String[] args = {String.valueOf( guest.getId())};

            db.update( DataBaseConstants.GUEST.TABLE_NAME, values, where, args );
            db.close();

            return true;

        }catch ( Exception e ){
          return false;
        }

    }

    public boolean delete (int id){

        try{

            SQLiteDatabase db = this.mHelper.getWritableDatabase();

            String where = DataBaseConstants.GUEST.COLUMNS.ID + " = ?";
            String[] args = {String.valueOf(id)};

            db.delete( DataBaseConstants.GUEST.TABLE_NAME, where, args );
            db.close();

            return true;

        }catch ( Exception e ){
            return false;
        }

    }
}
