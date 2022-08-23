package com.example.cmd.Activity;

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
                if(SignInActivity.preferences.getBoolean("updatevisible", false) == false) {
                    binding.ivupdatevisible.setImageResource(R.drawable.ic_baseline_visibility_24);
                    binding.etupdatePw.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
                    binding.etupdatePw.setSelection(binding.etupdatePw.getText().length());
                    SignInActivity.editor.putBoolean("updatevisible", true).commit();
                }else if(SignInActivity.preferences.getBoolean("updatevisible", false) == true){
                    binding.ivupdatevisible.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    binding.etupdatePw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.etupdatePw.setSelection(binding.etupdatePw.getText().length());
                    SignInActivity.editor.putBoolean("updatevisible", false).commit();
                }
            }
        });

        binding.ivupdatenewvisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SignInActivity.preferences.getBoolean("updatenewvisible", false) == false) {
                    binding.ivupdatenewvisible.setImageResource(R.drawable.ic_baseline_visibility_24);
                    binding.etupdatenewPw.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
                    binding.etupdatenewPw.setSelection(binding.etupdatenewPw.getText().length());
                    SignInActivity.editor.putBoolean("updatenewvisible", true).commit();
                }else if(SignInActivity.preferences.getBoolean("updatenewvisible", false) == true){
                    binding.ivupdatenewvisible.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    binding.etupdatenewPw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.etupdatenewPw.setSelection(binding.etupdatenewPw.getText().length());
                    SignInActivity.editor.putBoolean("updatenewvisible", false).commit();
                }
            }
        });

        binding.ivupdatenewcheckvisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SignInActivity.preferences.getBoolean("updatenewvisiblecheck", false) == false) {
                    binding.ivupdatenewcheckvisible.setImageResource(R.drawable.ic_baseline_visibility_24);
                    binding.etupdatenewpwCheck.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
                    binding.etupdatenewpwCheck.setSelection(binding.etupdatenewpwCheck.getText().length());
                    SignInActivity.editor.putBoolean("updatenewvisiblecheck", true).commit();
                }else if(SignInActivity.preferences.getBoolean("updatenewvisiblecheck", false) == true){
                    binding.ivupdatenewcheckvisible.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    binding.etupdatenewpwCheck.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.etupdatenewpwCheck.setSelection(binding.etupdatenewpwCheck.getText().length());
                    SignInActivity.editor.putBoolean("updatenewvisiblecheck", false).commit();
                }
            }
        });

        binding.btinfoUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCheck();
            }
        });
    }

    private void updateCheck() {
        if(binding.etupdatePw.getText().length() !=0 && binding.etupdateBirth.getText().length() != 0 && binding.etupdateMajor.getText().length() != 0){
            if(binding.etupdatePw.getText().toString().equals(SignInActivity.preferences.getString("Pw", "")) == true){
                if(binding.etupdatePw.getText().toString().equals(binding.etupdatenewPw.getText().toString()) == true){
                    Toast.makeText(this, "현재 비밀번호와 새 비밀번호가 같습니다", Toast.LENGTH_SHORT).show();
                } else if(binding.etupdatenewPw.getText().toString().equals(binding.etupdatenewpwCheck.getText().toString()) == false){
                    Toast.makeText(this, "비밀번호 확인이 다릅니다", Toast.LENGTH_SHORT).show();
                } else if(binding.etupdatenewPw.getText().toString().equals(binding.etupdatenewpwCheck.getText().toString()) == true){
                    update();
                }
            } else if(binding.etupdatePw.getText().toString().equals(SignInActivity.preferences.getString("Pw", "")) == false){
                Toast.makeText(this, "비밀번호가 틀립니다", Toast.LENGTH_SHORT).show();
            }
        }else Toast.makeText(this, "모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show();
    }

    private void update() {
        ServerApi serverApi = ApiProvider.getInstance().create(ServerApi.class);

        String newPw = binding.etupdatenewPw.getText().toString();
        String birthday = binding.etupdateBirth.getText().toString();
        String major = binding.etupdateMajor.getText().toString();

        InfoUpdateRequest infoUpdateRequest = new InfoUpdateRequest(newPw, birthday, major);

        serverApi.infoUpdate(SignInActivity.accessToken, infoUpdateRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(InfoUpdateActivity.this, "비밀번호가 변경되었습니다", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}