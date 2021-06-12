package com.kotlintest.app.database.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.kotlintest.app.model.Sample;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;


@Dao
public interface UserDao {


/*
    @Query("SELECT * FROM users ORDER BY username ASC ")
    List<Sample> loadAllUser();

*/

    @Query("SELECT * FROM users")
    abstract List<Sample> getBusinessInternal();


    @Transaction @Query("SELECT * FROM users")
    public default ArrayList<Sample> getBusiness(){
        return new ArrayList<>(getBusinessInternal());
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Sample callsModel);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Sample... callsModels);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Sample callsModel);

    @Delete
    void delete(Sample callsModel);


}