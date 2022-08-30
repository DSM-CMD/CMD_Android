package com.example.cmd.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import com.example.cmd.R;
import com.example.cmd.databinding.ActivityInfoUpdateBinding;
import com.example.cmd.request.InfoUpdateRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoUpdateActivity extends AppCompatActivity {
    private ActivityInfoUpdateBinding binding;
    private AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInfoUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        builder = new AlertDialog.Builder(getApplicationContext());

        Toast.makeText(this, SignInActivity.preferences.getString("Birth", ""), Toast.LENGTH_SHORT).show();

        // 비밀번호 visible 이미지 클릭 리스너
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

        // 새 비밀번호 visible 이미지 클릭 리스너
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

        // 새 비밀번호 확인 visible 이미지 클릭 리스너
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

        // 확인 버튼 클릭 리스너
        binding.btinfoUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCheck();
            }
        });
    }

    // 정보 입력 확인
    private void updateCheck() {
        // 현재 비밀번호
        String currentPw = binding.etupdatePw.getText().toString();
        // 새 비밀번호
        String newPw = binding.etupdatenewPw.getText().toString();
        // 새 비밀번호 확인
        String rePw = binding.etupdatenewpwCheck.getText().toString();
        // 생일
        String birth = binding.etupdateBirth.getText().toString();
        // 전공
        String major = binding.etupdateMajor.getText().toString();

        String prefBirth = SignInActivity.preferences.getString("Birth", "");
        String prefMajor = SignInActivity.preferences.getString("Major", "");

        exception(currentPw, newPw, rePw, birth, major, prefBirth, prefMajor);
    }

    // 예외 처리 메서드
    private void exception(String currentPw, String newPw, String rePw, String birth, String major, String prefBirth, String prefMajor) {
        
        if(currentPw.isEmpty()){
            Toast.makeText(this, "필수 입력란을 입력해주세요", Toast.LENGTH_SHORT).show();
        } else{
            if(currentPw.equals(SignInActivity.preferences.getString("Pw", "")) == true){
                if(!newPw.isEmpty()){
                    if(newPw.equals(rePw) == false){
                        Toast.makeText(this, "비밀번호 확인이 다릅니다", Toast.LENGTH_SHORT).show();
                    }else {
                        update(currentPw, newPw, birth, major, prefBirth, prefMajor);
                    }
                }else{
                    if(birth.isEmpty() && major.isEmpty()){
                        Toast.makeText(this, "변경점이 없습니다", Toast.LENGTH_SHORT).show();
                    } else {
                        update(currentPw, newPw, birth, major, prefBirth, prefMajor);
                    }
                }
            }else {
                Toast.makeText(this, "비밀번호가 틀렸습니다!", Toast.LENGTH_SHORT).show();
            }
        }
        
        
//        if(currentPw.equals(SignInActivity.preferences.getString("Pw", "")) == true &&
//           currentPw.equals(newPw) == false && newPw.equals(rePw) == true && prefBirth.length() != 0 &&
//            prefMajor.length() != 0){
//            update(currentPw, newPw, birth, major, prefBirth, prefMajor);
//        }else if(currentPw.equals(SignInActivity.preferences.getString("Pw", "")) == false){
//            Toast.makeText(this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
//        }else if(newPw.length() == 0){
//            Toast.makeText(this, "새 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
//        }else if(rePw.length() == 0){
//            Toast.makeText(this, "비밀번호 확인을 입력해주세요", Toast.LENGTH_SHORT).show();
//        }else if(newPw.equals(rePw) == false){
//            Toast.makeText(this, "비밀번호 확인이 다릅니다", Toast.LENGTH_SHORT).show();
//        }else if(prefBirth == null && birth.length() == 0 &&
//                prefMajor != null && major.length() != 0){
//            Toast.makeText(this, "생일을 입력해주세요", Toast.LENGTH_SHORT).show();
//        }else if(prefMajor == null && major.length() == 0 &&
//                prefBirth != null && birth.length() != 0){
//            Toast.makeText(this, "전공을 입력해주세요", Toast.LENGTH_SHORT).show();
//        }else Toast.makeText(this, "이상한 오류", Toast.LENGTH_SHORT).show();
    }

    // 업데이트 메서드
    private void update(String currentPw,String newPw, String birth, String major, String prefBirth, String prefMajor) {

        if(newPw == null){
            newPw = currentPw;
        }else if(birth == null){
            birth = prefBirth;
        }else if(major == null){
            major = prefMajor;
        }

        InfoUpdateRequest infoUpdateRequest = new InfoUpdateRequest(newPw, birth, major);

        SignInActivity.serverApi.infoUpdate(SignInActivity.accessToken, infoUpdateRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(InfoUpdateActivity.this, "정보가 변경되었습니다!", Toast.LENGTH_SHORT).show();
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);

                    SignInActivity.editor.putString("Id", "").commit();
                    SignInActivity.editor.putString("Pw", "").commit();
                    SignInActivity.editor.putBoolean("Check", false).commit();

                    Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                    startActivity(intent);

                    finish();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}