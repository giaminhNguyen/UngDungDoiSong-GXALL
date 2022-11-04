package com.ming6464.minhngph25430_assignmnet.Fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ming6464.minhngph25430_assignmnet.Adapter.BaiHatAdapter;
import com.ming6464.minhngph25430_assignmnet.DTO.BaiHat;
import com.ming6464.minhngph25430_assignmnet.MainActivity;
import com.ming6464.minhngph25430_assignmnet.R;
import com.ming6464.minhngph25430_assignmnet.databinding.FragmentAmNhacBinding;
import java.util.ArrayList;
import java.util.List;

public class AmNhacFragment extends Fragment implements BaiHatAdapter.OnEvent {
    private FragmentAmNhacBinding binding;
    private List<BaiHat> list;
    private OnEventFragAmNhac event;
    public static final String KEYOBJ_AMNHACFRAG = "KEYOBJ_AMNHACFRAG";
    public static AmNhacFragment newInstance() {
        return new AmNhacFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAmNhacBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        event = (OnEventFragAmNhac) requireActivity();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handlerList();
        handlerToobar();
        handlerRecycler();
    }

    private void handlerRecycler() {
        BaiHatAdapter adapter = new BaiHatAdapter(this);
        binding.amNhacFragRc.setAdapter(adapter);
        binding.amNhacFragRc.setLayoutManager(new LinearLayoutManager(requireActivity()));
        adapter.setData(list);
    }

    private void handlerList() {
        list = new ArrayList<>();
        list.add(new BaiHat("Ký ngực fan","Low G",R.raw.kyngucfan,R.drawable.lowg));
        list.add(new BaiHat("Bigcity boy","BinZ",R.raw.bigcity_boy,R.drawable.binz));
        list.add(new BaiHat("Thủ đô cypher", "RPT Orijinn, LOW G, RZMas, RPT MCK",R.raw.thu_do_cypher,R.drawable.thu_do_cypher));
        list.add(new BaiHat("See tình","Hoàng Thuỳ Linh", R.raw.see_tinh,R.drawable.hoang_thuy_linh));
        list.add(new BaiHat("Thủ đô cypher", "RPT Orijinn, LOW G, RZMas, RPT MCK",R.raw.thu_do_cypher,R.drawable.thu_do_cypher));
        list.add(new BaiHat("Bigcity boy","BinZ",R.raw.bigcity_boy,R.drawable.binz));
        list.add(new BaiHat("See tình","Hoàng Thuỳ Linh", R.raw.see_tinh,R.drawable.hoang_thuy_linh));
        list.add(new BaiHat("Bigcity boy","BinZ",R.raw.bigcity_boy,R.drawable.binz));
        list.add(new BaiHat("Thủ đô cypher", "RPT Orijinn, LOW G, RZMas, RPT MCK",R.raw.thu_do_cypher,R.drawable.thu_do_cypher));
        list.add(new BaiHat("See tình","Hoàng Thuỳ Linh", R.raw.see_tinh,R.drawable.hoang_thuy_linh));
        list.add(new BaiHat("Ký ngực fan","Low G",R.raw.kyngucfan,R.drawable.lowg));
        list.add(new BaiHat("Bigcity boy","BinZ",R.raw.bigcity_boy,R.drawable.binz));
        list.add(new BaiHat("See tình","Hoàng Thuỳ Linh", R.raw.see_tinh,R.drawable.hoang_thuy_linh));
        list.add(new BaiHat("Thủ đô cypher", "RPT Orijinn, LOW G, RZMas, RPT MCK",R.raw.thu_do_cypher,R.drawable.thu_do_cypher));
        list.add(new BaiHat("See tình","Hoàng Thuỳ Linh", R.raw.see_tinh,R.drawable.hoang_thuy_linh));
        list.add(new BaiHat("Thủ đô cypher", "RPT Orijinn, LOW G, RZMas, RPT MCK",R.raw.thu_do_cypher,R.drawable.thu_do_cypher));
        list.add(new BaiHat("Ký ngực fan","Low G",R.raw.kyngucfan,R.drawable.lowg));
    }


    private void handlerToobar() {
        binding.amNhacFragTb.setNavigationIcon(R.drawable.back_30);
        binding.amNhacFragTb.setNavigationOnClickListener(view -> new MainActivity().chuyenFragment(requireActivity().getSupportFragmentManager(),ManHinhChinhFragment.newInstance()));
    }


    @Override
    public void onClickItem(int position) {
        event.phatNhac(list.get(position));
    }

    public interface OnEventFragAmNhac{
        void phatNhac(BaiHat obj);
    }
}