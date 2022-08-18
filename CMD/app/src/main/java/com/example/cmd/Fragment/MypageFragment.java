
package com.example.cmd.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.SingleLineTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.cmd.Main.SignInActivity;
import com.example.cmd.R;
import com.example.cmd.databinding.FragmentMypageBinding;

public class MypageFragment extends Fragment {
    private FragmentMypageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMypageBinding.inflate(getLayoutInflater(), container, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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
}