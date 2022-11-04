package com.ming6464.minhngph25430_assignmnet.SharePreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.ming6464.minhngph25430_assignmnet.DTO.NguoiDung;

public class AssignmentShare {
    private final SharedPreferences share;
    private final String KEY_TK = "TK",KEY_MK = "MK",KEY_TK2 = "tk2",KEY_TENNGUOIDUNG = "KEY_TENNGUOIDUNG";
    private final SharedPreferences.Editor editor;
    public AssignmentShare(Context context){
        share = context.getSharedPreferences("SHARE_NAME",Context.MODE_PRIVATE);
        editor = share.edit();
    }

    public static AssignmentShare getInstance(Context context){
        return new AssignmentShare(context);
    }

    public void putAccount(NguoiDung obj, boolean check){
        editor.putString(KEY_TK2,obj.getTaiKhoan());
        editor.putString(KEY_TENNGUOIDUNG,obj.getTenNguoiDung());
        if(check){
            editor.putString(KEY_TK,obj.getTaiKhoan());
            editor.putString(KEY_MK,obj.getMatKhau());
        }else if(obj.getTaiKhoan().equals(share.getString(KEY_TK, null)))
            clearAccount();
        editor.apply();
    }

    public NguoiDung getAccount(){
        String tk = share.getString(KEY_TK,null);
        if(tk != null)
            return  new NguoiDung(tk,share.getString(KEY_MK,null),null);
        return null;
    }

    public boolean checkTK(String tk){
        return getTK() != null && getTK().equals(tk);
    }

    public String getTK2(){
        return share.getString(KEY_TK2,null);
    }

    public String getTK(){
        return share.getString(KEY_TK,null);
    }

    public String getMK(){
        return share.getString(KEY_MK, null);
    }

    public String getTenNguoiDung() {
        return share.getString(KEY_TENNGUOIDUNG,null);
    }

    public void clearAccount(){
        editor.remove(KEY_TK);
        editor.remove(KEY_MK);
        editor.apply();
    }

}
