package com.ming6464.minhngph25430_assignmnet.Fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import com.ming6464.minhngph25430_assignmnet.Adapter.ItemWebAdapter;
import com.ming6464.minhngph25430_assignmnet.DTO.ItemWeb;
import com.ming6464.minhngph25430_assignmnet.LoaderWeb;
import com.ming6464.minhngph25430_assignmnet.MainActivity;
import com.ming6464.minhngph25430_assignmnet.R;
import com.ming6464.minhngph25430_assignmnet.databinding.FragmentTinTucBinding;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

public class TinTucFragment extends Fragment implements ItemWebAdapter.OnEvent {
    private FragmentTinTucBinding binding;
    private List<ItemWeb> list;
    private ItemWebAdapter adapter;
    private OnEventFragmentTinTuc event;
    public static TinTucFragment newInstance() {
        return new TinTucFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTinTucBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handlerToobar();
        handlerLogo();
        handlerRecycler();
        new LoadListFromRss().execute();
    }

    private void handlerLogo() {
        Animation animation = AnimationUtils.loadAnimation(requireActivity(), R.anim.animation_logo);
        binding.fragTinTucImgLogo.startAnimation(animation);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        event = (OnEventFragmentTinTuc) requireActivity();
    }

    private void handlerRecycler() {
        adapter = new ItemWebAdapter(this);
        binding.tinTucFragRecycler.setAdapter(adapter);
        binding.tinTucFragRecycler.setLayoutManager(new LinearLayoutManager(requireActivity()));
    }

    private void handlerToobar() {
        binding.tinTucFragTb.setNavigationIcon(R.drawable.back_30);
        binding.tinTucFragTb.setNavigationOnClickListener(view -> new MainActivity().chuyenFragment(requireActivity().getSupportFragmentManager(),ManHinhChinhFragment.newInstance()));
    }

    @Override
    public void onActionClick(int position) {
        event.chuyenLink(list.get(position).getLink());
    }

    private class LoadListFromRss extends AsyncTask<Void,Void,List<ItemWeb>>{

        @Override
        protected List<ItemWeb> doInBackground(Void... voids) {
            try {
                HttpsURLConnection https = (HttpsURLConnection) new URL("https://vnexpress.net/rss/giao-duc.rss").openConnection();
                https.setReadTimeout(1000);
                https.connect();
                if(https.getResponseCode() == HttpsURLConnection.HTTP_OK){
                    list = new LoaderWeb().getListItemWeb(https.getInputStream());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(List<ItemWeb> itemWebs) {
            super.onPostExecute(itemWebs);
            if(itemWebs == null)
                Toast.makeText(requireActivity(), "Kết nối internet không ổn định !", Toast.LENGTH_SHORT).show();
            else{
                adapter.setData(itemWebs);
                new Handler().postDelayed(() -> {
                    binding.tinTucFragProgress.setVisibility(View.GONE);
                    binding.tinTucFragRecycler.setVisibility(View.VISIBLE);
                },2000);

            }
        }
    }
    public interface OnEventFragmentTinTuc{
        void chuyenLink(String link);
    }
}