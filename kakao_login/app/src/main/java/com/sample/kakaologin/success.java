package com.sample.kakaologin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import org.w3c.dom.Text;

public class success extends AppCompatActivity {
private String strName, strProfileImg, strBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        Intent intent = getIntent();
        strName = intent.getStringExtra("name");
        strProfileImg = intent.getStringExtra("profileImg");
        strBirth = intent.getStringExtra("birth");

        TextView tv_name = findViewById(R.id.tv_name);
        TextView tv_birth = findViewById(R.id.tv_birth);
        ImageView iv_profile = findViewById(R.id.iv_profile);

        //이름 set
        tv_name.setText(strName);
        //생일 set
        tv_birth.setText(strBirth);
        //프로필 이미지 사진 set
        Glide.with(this).load(strProfileImg).into(iv_profile);

        //로그아웃하기
        findViewById(R.id.btn_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "로그아웃이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        // 로그아웃 성공 시 수행하는 지점
                        finish(); // 현재 액티비티 종료
                    }
                });
            }
        });
    }

}