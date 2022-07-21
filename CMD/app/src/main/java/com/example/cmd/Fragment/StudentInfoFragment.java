package com.example.cmd.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmd.R;
import com.example.cmd.RecyclerView.StudentAdapter;
import com.example.cmd.StudentInfo;

import java.util.ArrayList;

public class StudentInfoFragment extends Fragment {

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private StudentAdapter studentAdapter;
    ArrayList<StudentInfo> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_studentinfo, container, false);

        arrayList = new ArrayList<>();

        recyclerView = rootView.findViewById(R.id.studentrecyclerview);

        gridLayoutManager = new GridLayoutManager(getActivity(), 3);

        recyclerView.setLayoutManager(gridLayoutManager);

        studentAdapter = new StudentAdapter(arrayList);

        recyclerView.setAdapter(studentAdapter);

        add();

        return rootView;
    }

    public void add(){
        arrayList.add(new StudentInfo("강용수"));
        arrayList.add(new StudentInfo("강지인"));
        arrayList.add(new StudentInfo("길근우"));
        arrayList.add(new StudentInfo("김민채"));
        arrayList.add(new StudentInfo("김은오"));
        arrayList.add(new StudentInfo("김정현"));
        arrayList.add(new StudentInfo("김주원"));
        arrayList.add(new StudentInfo("김현민"));
        arrayList.add(new StudentInfo("마재영"));
        arrayList.add(new StudentInfo("유나현"));
        arrayList.add(new StudentInfo("유현담"));
        arrayList.add(new StudentInfo("NULL"));
        arrayList.add(new StudentInfo("정승훈"));
        arrayList.add(new StudentInfo("정지관"));
        arrayList.add(new StudentInfo("조문성"));
        arrayList.add(new StudentInfo("최하은"));
        arrayList.add(new StudentInfo("한예슬"));
        arrayList.add(new StudentInfo("홍승재"));
    }
}