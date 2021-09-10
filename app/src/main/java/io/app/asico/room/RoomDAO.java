package io.app.asico.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RoomDAO {

    @Insert
    public void insertAsiaDetail(List<RoomEntity> detail);
    @Query("DELETE FROM regions")
    public void nuke();
    @Query("SELECT * FROM regions")
    public List<RoomEntity> getRegions();
}

