package com.ming6464.minhngph25430_assignmnet;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.ming6464.minhngph25430_assignmnet.DTO.NguoiDung;
import com.ming6464.minhngph25430_assignmnet.Services.MyService;
import com.ming6464.minhngph25430_assignmnet.databinding.ActivityDangKyBinding;

public class DangKyActi extends AppCompatActivity {
    private ActivityDangKyBinding binding;
    public static final String KEYOBJ_DANGKYACTI = "KEYOBJ_DANGKYACTI",
            KEYPHANHOI_DANGKYACTI = "KEYPHANHOI_DANGKYACTI";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDangKyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        handerActionPhanHoi();
    }

    private void handerActionPhanHoi() {
        IntentFilter intentFilter = new IntentFilter(KEYPHANHOI_DANGKYACTI);
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                binding.dangKyActiEtTenNguoiDung.setText("");
                binding.dangKyActiEtMatKhau.setText("");
                binding.dangKyActiEtNhapLaiMatKhau.setText("");
            }
        };
        registerReceiver(receiver,intentFilter);
    }

    public void actionButtonTroLai(View view) {
        finish();
    }

    public void actionButtonDangKy(View view) {
        String taiKhoan = binding.dangKyActiEtTaiKhoan.getText().toString(),
                matKhau = binding.dangKyActiEtMatKhau.getText().toString(),
                nhapLaiMatKhau = binding.dangKyActiEtNhapLaiMatKhau.getText().toString(),
                tenNguoiDung = binding.dangKyActiEtTenNguoiDung.getText().toString();
        if(tenNguoiDung.isEmpty() || taiKhoan.isEmpty() || matKhau.isEmpty() || nhapLaiMatKhau.isEmpty()){
            Toast.makeText(this, "Dữ liệu bị trống !", Toast.LENGTH_SHORT).show();
            return;
        }else if(!matKhau.equals(nhapLaiMatKhau)){
            Toast.makeText(this, "Mật khâu không giống nhau !", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra(KEYOBJ_DANGKYACTI, new NguoiDung(taiKhoan,matKhau,tenNguoiDung));
        startService(intent);
    }
}