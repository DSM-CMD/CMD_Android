
package com.example.cmd.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cmd.Api.ApiProvider;
import com.example.cmd.Api.ServerApi;
import com.example.cmd.R;
import com.example.cmd.RecyclerView.Adapter;
import com.example.cmd.Response.TimetableResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimetableFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Adapter adapter;
    List<TimetableResponse> timetableResponseList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_timetable, container, false);

        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        timetableResponseList = new ArrayList<>();

        recyclerView = rootView.findViewById(R.id.recyclerview);

        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new Adapter(timetableResponseList);

        recyclerView.setAdapter(adapter);

        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);

        serverApi.timetable(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US)).enqueue(new Callback<List<TimetableResponse>>() {
            @Override
            public void onResponse(Call<List<TimetableResponse>> call, Response<List<TimetableResponse>> response) {
                if(response.isSuccessful()){
                    timetableResponseList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "시간표 불러오기 성공", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TimetableResponse>> call, Throwable t) {
                Toast.makeText(getActivity(), "시간표 불러오기 실패 사실 니잘못 ㅋ", Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }
}