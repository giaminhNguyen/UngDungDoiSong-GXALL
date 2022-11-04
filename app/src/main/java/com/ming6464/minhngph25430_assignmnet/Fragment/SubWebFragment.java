package com.ming6464.minhngph25430_assignmnet.Fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ming6464.minhngph25430_assignmnet.MainActivity;
import com.ming6464.minhngph25430_assignmnet.R;
import com.ming6464.minhngph25430_assignmnet.databinding.FragmentSubWebBinding;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class SubWebFragment extends Fragment {
    private FragmentSubWebBinding binding;
    public static SubWebFragment newInstance() {
        SubWebFragment fragment = new SubWebFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSubWebBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handlerTb();
        assert getArguments() != null;
        showWeb(getArguments().getString(MainActivity.KEYBUNDLE_MAINACTI));
    }

    private void handlerTb() {
        binding.subWebFragTb.setNavigationIcon(R.drawable.back_30);
        binding.subWebFragTb.setNavigationOnClickListener( v -> {
            new MainActivity().chuyenFragment(requireActivity().getSupportFragmentManager(),TinTucFragment.newInstance());
        });
    }

    private void showWeb(String url){
        new LoadWeb().execute(url);
    }
    private class  LoadWeb extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                HttpsURLConnection https = (HttpsURLConnection) new URL(strings[0]).openConnection();
                https.setReadTimeout(1000);
                https.connect();
                if(https.getResponseCode() == HttpsURLConnection.HTTP_OK){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(https.getInputStream()));
                    StringBuilder buffer = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        buffer.append(line);
                    }
                    return buffer.toString();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s != null){
                binding.subWebFragWv.loadDataWithBaseURL(null,s,"text/html","UTF-8",null);
                binding.subWebFragWv.setVisibility(View.VISIBLE);
                binding.subWebFragProgress.setVisibility(View.GONE);
            }
        }
    }



}