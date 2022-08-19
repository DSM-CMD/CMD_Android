package com.example.cmd.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import com.example.cmd.Api.ApiProvider;
import com.example.cmd.Api.ServerApi;
import com.example.cmd.R;
import com.example.cmd.Request.InfoUpdateRequest;
import com.example.cmd.databinding.ActivityInfoUpdateBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoUpdateActivity extends AppCompatActivity {
    private ActivityInfoUpdateBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInfoUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ivupdatevisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.ivupdatevisible.setImageResource(R.drawable.ic_baseline_visibility_24);
                binding.etupdatePw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });

        binding.ivupdatenewvisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.ivupdatenewvisible.setImageResource(R.drawable.ic_baseline_visibility_24);
                binding.etupdatenewPw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });

        binding.ivupdatenewcheckvisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.ivupdatenewcheckvisible.setImageResource(R.drawable.ic_baseline_visibility_24);
                binding.etupdatenewpwCheck.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);;
            }
        });

        binding.btinfoUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCheck();
            }
        });

        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);



    }

    private void updateCheck() {
        if(binding.etupdatePw.getText().length() !=0 && binding.etupdatenewPw.getText().length() !=0 && binding.etupdatenewpwCheck.getText().length() !=0){
            if(binding.etupdatePw.getText().toString().equals(SignInActivity.preferences.getString("Pw", "")) == true){
                if(binding.etupdatenewPw.getText().toString().equals(binding.etupdatenewpwCheck.getText().toString()) == true){
                    update();
                }
            }
        }else Toast.makeText(this, "모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show();
    }

    private void update() {
        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);

        String newPw = binding.etupdatenewPw.getText().toString();

        InfoUpdateRequest infoUpdateRequest = new InfoUpdateRequest(newPw);

        serverApi.infoUpdate(SignInActivity.accessToken, infoUpdateRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(InfoUpdateActivity.this, "비밀번호가 변경되었습니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}