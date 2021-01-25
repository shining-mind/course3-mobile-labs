package com.shiningmind.photogallery.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.shiningmind.photogallery.model.Photo;

import java.util.List;

@Dao
public interface PhotoDAO {
    @Query("SELECT * FROM photo")
    List<Photo> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Photo photo);

    @Delete
    void delete(Photo photo);
}
