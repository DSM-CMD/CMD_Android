
package com.example.cmd.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cmd.Api.ApiProvider;
import com.example.cmd.Api.ServerApi;
import com.example.cmd.Main.SignInActivity;
import com.example.cmd.R;
import com.example.cmd.RecyclerView.NoticeAdapter;
import com.example.cmd.Response.NoticeResponse;
import com.example.cmd.databinding.FragmentNoticeBoardBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeBoardFragment extends Fragment {

    private LinearLayoutManager linearLayoutManager;
    private NoticeAdapter noticeAdapter;
    private FragmentNoticeBoardBinding binding;
    Animation animation;
    List<NoticeResponse> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentNoticeBoardBinding.inflate(inflater, container, false);

        if(SignInActivity.preferences.getBoolean("Switch", false) == true){
            binding.ivswitch.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
            animation = AnimationUtils.loadAnimation(getActivity(), R.anim.up);
        }else if(SignInActivity.preferences.getBoolean("Switch", false) == false){
            binding.ivswitch.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
            animation = AnimationUtils.loadAnimation(getActivity(), R.anim.down);
        }

        binding.ivswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.ivswitch.startAnimation(animation);
                if(SignInActivity.preferences.getBoolean("Switch", false) == false){
                    SignInActivity.editor.putBoolean("Switch", true).commit();
                }else SignInActivity.editor.putBoolean("Switch", false).commit();
            }
        });

        list = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(getActivity());

        binding.noticerecyclerview.setLayoutManager(linearLayoutManager);

        noticeAdapter = new NoticeAdapter(list);

        binding.noticerecyclerview.setAdapter(noticeAdapter);

        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);

        serverApi.notice(SignInActivity.accessToken).enqueue(new Callback<List<NoticeResponse>>() {
            @Override
            public void onResponse(Call<List<NoticeResponse>> call, Response<List<NoticeResponse>> response) {
                if (response.isSuccessful()) {
                    list.addAll(response.body());
                    noticeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<NoticeResponse>> call, Throwable t) {
            }
        });

        return binding.getRoot();
    }
}