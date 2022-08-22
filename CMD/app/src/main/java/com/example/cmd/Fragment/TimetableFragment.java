
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
import com.example.cmd.databinding.FragmentTimetableBinding;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimetableFragment extends Fragment {

    private FragmentTimetableBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTimetableBinding.inflate(getLayoutInflater(), container, false);

        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("MM월 dd일" + " " + calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.KOREA) + "요일");

        String format_time = format.format(calendar.getTime());

        binding.tvdate.setText(format_time);

        calendar.setTime(currentDate);

        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);

        serverApi.timetable(SignInActivity.accessToken, calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US)).enqueue(new Callback<TimetableResponse>() {
            @Override
            public void onResponse(Call<TimetableResponse> call, Response<TimetableResponse> response) {
                if(response.isSuccessful()){
                    binding.tvperiod01.setText(response.body().getPeriod1st());
                    binding.tvperiod02.setText(response.body().getPeriod2nd());
                    binding.tvperiod03.setText(response.body().getPeriod3th());
                    binding.tvperiod04.setText(response.body().getPeriod4th());
                    binding.tvperiod05.setText(response.body().getPeriod5th());
                    binding.tvperiod06.setText(response.body().getPeriod6th());
                    binding.tvperiod07.setText(response.body().getPeriod7th());
                    binding.tvperiod08.setText(response.body().getPeriod8th());
                    binding.tvperiod09.setText(response.body().getPeriod9th());
                    binding.tvperiod10.setText(response.body().getPeriod10th());

                    setperiod();

                }
                else if(response.code() == 500){
                    binding.tvperiod01.setText("시");
                    binding.tvperiod02.setText("간");
                    binding.tvperiod03.setText("표");
                    binding.tvperiod04.setText("가");
                    binding.tvperiod05.setText("없");
                    binding.tvperiod06.setText("어");
                    binding.tvperiod07.setText("용");
                    binding.tvperiod08.setText("!");
                    binding.tvperiod09.setText("!");
                    binding.tvperiod10.setText("!");
                }
            }

            @Override
            public void onFailure(Call<TimetableResponse> call, Throwable t) {
            }
        });

        return binding.getRoot();
    }

    private void setperiod() {
        binding.period01.setText("1교시");
        binding.period02.setText("2교시");
        binding.period03.setText("3교시");
        binding.period04.setText("4교시");
        binding.period05.setText("5교시");
        binding.period06.setText("6교시");
        binding.period07.setText("7교시");
        binding.period08.setText("8교시");
        binding.period09.setText("9교시");
        binding.period10.setText("10교시");
    }
}