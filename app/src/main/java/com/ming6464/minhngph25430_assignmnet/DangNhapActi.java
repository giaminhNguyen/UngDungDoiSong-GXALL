package com.ming6464.minhngph25430_assignmnet;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;
import com.ming6464.minhngph25430_assignmnet.DTO.NguoiDung;
import com.ming6464.minhngph25430_assignmnet.Services.MyService;
import com.ming6464.minhngph25430_assignmnet.SharePreferences.AssignmentShare;
import com.ming6464.minhngph25430_assignmnet.databinding.ActivityDangNhapBinding;

public class DangNhapActi extends AppCompatActivity {
    private ActivityDangNhapBinding binding;
    private Intent intent;
    public static final String KEYPHANHOi_DANGNHAPACTI = "KEYPHANHOi_DANGNHAPACTI",
            KEYOBJ_DANGNHAPACTI = "KEYOBJ_DANGNHAPACTI",
            KEYCHECK_DANGNHAPACTI = "KEYCHECK_DANGNHAPACTI";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDangNhapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        handlerGif();
        handlerCheck();
        handlerActionPhanHoi();
    }

    private void handlerCheck() {
        AssignmentShare share = AssignmentShare.getInstance(this);
        NguoiDung obj = share.getAccount();
        if(obj != null){
            binding.dangNhapActiEtTaiKhoan.setText(obj.getTaiKhoan());
            binding.dangNhapActiEtMatKhau.setText(obj.getMatKhau());
            binding.dangNhapActiChkNhoMatKhau.setChecked(true);
        }
    }

    private void handlerGif() {
        new Handler().postDelayed(() -> {
            binding.dangNhapActiGifStudy.setVisibility(View.GONE);
            binding.dangNhapActiLayoutConstrain.setVisibility(View.VISIBLE);
        },2500);
    }

    private void handlerActionPhanHoi() {
        IntentFilter filter = new IntentFilter(KEYPHANHOi_DANGNHAPACTI);
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                finish();
            }
        };
        registerReceiver(receiver,filter);
    }

    public void actionDangNhap(View view) {
        String taiKhoan = binding.dangNhapActiEtTaiKhoan.getText().toString(),
                matKhau = binding.dangNhapActiEtMatKhau.getText().toString();
        if(taiKhoan.isEmpty() || matKhau.isEmpty()){
            Toast.makeText(this, "Dữ liệu bị trống !", Toast.LENGTH_SHORT).show();
            return;
        }
        intent = new Intent(this, MyService.class);
        intent.putExtra(KEYOBJ_DANGNHAPACTI,new NguoiDung(taiKhoan,matKhau,null));
        intent.putExtra(KEYCHECK_DANGNHAPACTI,binding.dangNhapActiChkNhoMatKhau.isChecked());
        startService(intent);
    }

    public void actionDangKy(View view) {
        intent = new Intent(this,DangKyActi.class);
        startActivity(intent);
    }
}