package com.ming6464.minhngph25430_assignmnet.DTO;

import java.io.Serializable;

public class BaiHat implements Serializable {
    private String tenBaiHat,caSi;
    private int music,anh;

    public BaiHat(String tenBaiHat, String caSi, int music,int anh) {
        this.tenBaiHat = tenBaiHat;
        this.caSi = caSi;
        this.music = music;
        this.anh = anh;
    }

    public BaiHat() {
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    public String getCaSi() {
        return caSi;
    }

    public void setCaSi(String caSi) {
        this.caSi = caSi;
    }

    public int getMusic() {
        return music;
    }

    public void setMusic(int music) {
        this.music = music;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }
}
