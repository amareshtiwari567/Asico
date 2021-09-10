package io.app.asico.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {RoomEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RoomDAO asiaDao();
}
