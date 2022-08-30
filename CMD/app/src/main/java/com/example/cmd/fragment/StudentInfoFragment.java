package com.example.cmd.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.cmd.Activity.SignInActivity;
import com.example.cmd.Adapter.StudentAdapter;
import com.example.cmd.Response.StudentInfoResponse;
import com.example.cmd.databinding.FragmentStudentinfoBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentInfoFragment extends Fragment {

    private FragmentStudentinfoBinding binding;

    private GridLayoutManager gridLayoutManager;
    private StudentAdapter studentAdapter;
    List<StudentInfoResponse> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentStudentinfoBinding.inflate(getLayoutInflater(), container, false);

        list = new ArrayList<>();
        gridLayoutManager= new GridLayoutManager(getActivity(), 3);
        binding.studentrecyclerview.setLayoutManager(gridLayoutManager);

        studentAdapter = new StudentAdapter(list);

        binding.studentrecyclerview.setAdapter(studentAdapter);

        SignInActivity.serverApi.studentInfo(SignInActivity.accessToken).enqueue(new Callback<List<StudentInfoResponse>>() {
            @Override
            public void onResponse(Call<List<StudentInfoResponse>> call, Response<List<StudentInfoResponse>> response) {
                if(response.isSuccessful()){
                    list.addAll(response.body());
                    studentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<StudentInfoResponse>> call, Throwable t) {
            }
        });

        return binding.getRoot();
    }
}