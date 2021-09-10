package io.app.asico.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RoomDAO {

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    public void insertAsiaDetail(List<RoomEntity> detail);
    @Update
    public void updateAsiaDetail(List<RoomEntity> detail);
    @Delete
    public void deleteAll(List<RoomEntity> detail);
}

