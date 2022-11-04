package com.ming6464.minhngph25430_assignmnet.DTO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;
import java.io.Serializable;

@Entity
public class NguoiDung implements Serializable {
    @PrimaryKey
    @NotNull
    private String taiKhoan;

    private String matKhau,tenNguoiDung,pathAvatar;

    public NguoiDung(@NotNull String taiKhoan, String matKhau, String tenNguoiDung) {
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.tenNguoiDung = tenNguoiDung;
        this.pathAvatar = null;
    }

    public NguoiDung() {
    }

    @NotNull
    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(@NotNull String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getPathAvatar() {
        return pathAvatar;
    }

    public void setPathAvatar(String avatar) {
        this.pathAvatar = avatar;
    }
}
