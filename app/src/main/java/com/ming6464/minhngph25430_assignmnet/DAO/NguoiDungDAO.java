package com.ming6464.minhngph25430_assignmnet.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.ming6464.minhngph25430_assignmnet.DTO.NguoiDung;
import java.util.List;

@Dao
public abstract class NguoiDungDAO {
    @Insert
    public abstract void insertObj(NguoiDung ...obj);

    public boolean insert(NguoiDung obj){
        if(getObj(obj.getTaiKhoan()) == null){
            insertObj(obj);
            return true;
        }
        return false;
    }
    @Query("SELECT * FROM nguoidung")
    public abstract List<NguoiDung> getAll();

    @Query("SELECT pathAvatar FROM nguoidung WHERE taiKhoan = :taiKhoan")
    public abstract String getPathAvatar(String taiKhoan);

    @Query("SELECT * FROM nguoidung WHERE taiKhoan = :taiKhoan")
    public abstract NguoiDung getObj(String taiKhoan);

    @Update
    public abstract void update(NguoiDung obj);

    @Query("UPDATE nguoidung SET pathAvatar = :pathAvatar WHERE taiKhoan = :taiKhoan")
    public abstract void updatePathAvatar(String taiKhoan,String pathAvatar);

    @Delete
    public abstract void delete(NguoiDung obj);
}
