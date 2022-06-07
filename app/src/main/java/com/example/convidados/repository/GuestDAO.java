package com.example.convidados.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.convidados.model.GuestModel;

import java.util.List;

@Dao
public interface GuestDAO {

    @Insert
    long insert(GuestModel guest);//CRUD

    @Update
    int update(GuestModel guest);//CRUD

    @Delete
    int delete(GuestModel guest );//CRUD

    @Query( "SELECT * FROM guest WHERE id = :id" )//SELECIONA UM ELEMNTO APARTIR DE ID
    GuestModel load (int id);

    @Query( " SELECT * FROM guest " )//SELECIONA TODOS OS CONVIDADOS
    List<GuestModel> getAll ();

    @Query( "SELECT * FROM guest WHERE confirmation = :confirmation" )
    List<GuestModel> getListByPresence (int confirmation);

}
