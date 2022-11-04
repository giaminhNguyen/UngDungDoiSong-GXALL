package com.ming6464.minhngph25430_assignmnet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import com.ming6464.minhngph25430_assignmnet.DTO.BaiHat;
import com.ming6464.minhngph25430_assignmnet.Fragment.AmNhacFragment;
import com.ming6464.minhngph25430_assignmnet.Fragment.ManHinhChinhFragment;
import com.ming6464.minhngph25430_assignmnet.Fragment.SubWebFragment;
import com.ming6464.minhngph25430_assignmnet.Fragment.TinTucFragment;
import com.ming6464.minhngph25430_assignmnet.Interface.Action;
import com.ming6464.minhngph25430_assignmnet.Services.MyService;
import com.ming6464.minhngph25430_assignmnet.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements Action, TinTucFragment.OnEventFragmentTinTuc, AmNhacFragment.OnEventFragAmNhac {
    private FragmentTransaction transaction;
    public static final String KEYBUNDLE_MAINACTI = "KEYBUNDLE_MAINACTI";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        chuyenFragment(getSupportFragmentManager(),ManHinhChinhFragment.newInstance());
    }

    @Override
    public void chuyenFragment(FragmentManager manager,Fragment fragment) {
        transaction = manager.beginTransaction();
        transaction.replace(R.id.mainActi_layout_constraint,fragment);
        transaction.commit();
    }

    @Override
    public void chuyenLink(String link) {
        transaction = getSupportFragmentManager().beginTransaction();
        SubWebFragment fragment = SubWebFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString(KEYBUNDLE_MAINACTI,link);
        fragment.setArguments(bundle);
        transaction.replace(R.id.mainActi_layout_constraint,fragment);
        transaction.commit();
    }


    @Override
    public void phatNhac(BaiHat obj) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra(AmNhacFragment.KEYOBJ_AMNHACFRAG,obj);
        startService(intent);
    }
}