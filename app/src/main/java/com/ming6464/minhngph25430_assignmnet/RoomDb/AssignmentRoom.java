package com.ming6464.minhngph25430_assignmnet.RoomDb;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.ming6464.minhngph25430_assignmnet.DAO.NguoiDungDAO;
import com.ming6464.minhngph25430_assignmnet.DTO.NguoiDung;

@Database(entities = {NguoiDung.class},version = 1)
public abstract class AssignmentRoom extends RoomDatabase {
    private static  AssignmentRoom instance;
    private static final String NAMEDB = "ROOM.DB";

    public static AssignmentRoom getInstance(Context context){
        if(instance == null)
            instance = Room.databaseBuilder(context,AssignmentRoom.class,NAMEDB).allowMainThreadQueries().build();
        return instance;
    }

    public abstract NguoiDungDAO getNguoiDungDAO();
}
