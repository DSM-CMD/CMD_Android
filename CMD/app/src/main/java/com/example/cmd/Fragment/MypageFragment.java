
package com.example.cmd.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.cmd.Activity.InfoUpdateActivity;
import com.example.cmd.Activity.SignInActivity;
import com.example.cmd.Response.MyInfoResponse;
import com.example.cmd.databinding.FragmentMypageBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MypageFragment extends Fragment {
    private FragmentMypageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMypageBinding.inflate(getLayoutInflater(), container, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        mypage();

        // 정보 수정 버튼 클릭 리스너
        binding.cbchangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InfoUpdateActivity.class);
                startActivity(intent);

            }
        });

        // 로그아웃 버튼 클릭 리스너
        binding.cbLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("로그아웃");
                builder.setMessage("로그아웃 하시겠습니까?");

                builder.setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SignInActivity.editor.putString("Id", "").commit();
                        SignInActivity.editor.putString("Pw", "").commit();
                        SignInActivity.editor.putInt("Check", 0).commit();

                        Intent intent = new Intent(getActivity(), SignInActivity.class);
                        startActivity(intent);
                        getActivity().finish();

                        Toast.makeText(getActivity(), "로그아웃 되었습니다!", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return binding.getRoot();
    }

    // 내 정보 조회 메서드
    private void mypage() {
        SignInActivity.serverApi.myInfo(SignInActivity.accessToken).enqueue(new Callback<MyInfoResponse>() {
            @Override
            public void onResponse(Call<MyInfoResponse> call, Response<MyInfoResponse> response) {
                if(response.isSuccessful()){
                    binding.tvmyName.setText(response.body().getUsername());
                    binding.tvmyId.setText(response.body().getUserId());
                    binding.tvmyNumber.setText(response.body().getNumber());
                    binding.tvmyBirth.setText(response.body().getBirthday());
                    binding.tvmyMajor.setText(response.body().getField());

                    SignInActivity.editor.putString("Birth", response.body().getBirthday());
                    SignInActivity.editor.putString("Major", response.body().getField());
                }
            }

            @Override
            public void onFailure(Call<MyInfoResponse> call, Throwable t) {

            }
        });
    }

    // Resume 상태에서 mypage 호출
    @Override
    public void onResume() {
        super.onResume();
        mypage();
    }
}