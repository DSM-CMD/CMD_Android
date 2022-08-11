
package com.example.cmd.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cmd.Api.ApiProvider;
import com.example.cmd.Api.ServerApi;
import com.example.cmd.Main.SignInActivity;
import com.example.cmd.R;
import com.example.cmd.Response.TimetableResponse;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimetableFragment extends Fragment {

    private TextView tvdate;
    private TextView period01;
    private TextView period02;
    private TextView period03;
    private TextView period04;
    private TextView period05;
    private TextView period06;
    private TextView period07;
    private TextView period08;
    private TextView period09;
    private TextView period10;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_timetable, container, false);

        tvdate = rootView.findViewById(R.id.tvdate);

        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("MM월 dd일" + " " + calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.KOREA) + "요일");

        String format_time = format.format(calendar.getTime());

        tvdate.setText(format_time);

        calendar.setTime(currentDate);

        period01 = rootView.findViewById(R.id.tvperiod01);
        period02 = rootView.findViewById(R.id.tvperiod02);
        period03 = rootView.findViewById(R.id.tvperiod03);
        period04 = rootView.findViewById(R.id.tvperiod04);
        period05 = rootView.findViewById(R.id.tvperiod05);
        period06 = rootView.findViewById(R.id.tvperiod06);
        period07 = rootView.findViewById(R.id.tvperiod07);
        period08 = rootView.findViewById(R.id.tvperiod08);
        period09 = rootView.findViewById(R.id.tvperiod09);
        period10 = rootView.findViewById(R.id.tvperiod10);

        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);

        serverApi.timetable(SignInActivity.accessToken, calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US)).enqueue(new Callback<TimetableResponse>() {
            @Override
            public void onResponse(Call<TimetableResponse> call, Response<TimetableResponse> response) {
                if(response.isSuccessful()){
                    period01.setText(response.body().getPeriod1st());
                    period02.setText(response.body().getPeriod2nd());
                    period03.setText(response.body().getPeriod3th());
                    period04.setText(response.body().getPeriod4th());
                    period05.setText(response.body().getPeriod5th());
                    period06.setText(response.body().getPeriod6th());
                    period07.setText(response.body().getPeriod7th());
                    period08.setText(response.body().getPeriod8th());
                    period09.setText(response.body().getPeriod9th());
                    period10.setText(response.body().getPeriod10th());
                }
            }

            @Override
            public void onFailure(Call<TimetableResponse> call, Throwable t) {

            }
        });

        return rootView;
    }
}