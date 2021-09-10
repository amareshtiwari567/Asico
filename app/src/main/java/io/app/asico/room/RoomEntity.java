package io.app.asico.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

class Borders{
    @ColumnInfo(name="Borders")
    public String border;
}

class Languages{
    public String iso639_1;
    public String iso639_2;
    public String name;
    public String nativeName;
}

@Entity
public class RoomEntity {
 @PrimaryKey
 @NonNull
    public String countryName;

    @ColumnInfo(name="Capital")
    public String capital;
    @ColumnInfo(name="Falg")
    public String flag;
    @ColumnInfo(name="Region")
    public String region;
    @ColumnInfo(name="SubRegion")
    public String subregion;
    @ColumnInfo(name="Population")
    public String population;
    @Embedded
    public Borders borders;
    @Embedded
    public Languages languages;
}
