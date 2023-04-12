package com.example.test_retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    private TextView tv_result;
    private String TAG = "MainActivity";
    private Button btn_recycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_recycle = findViewById(R.id.btn_m2);
        tv_result = findViewById(R.id.tv_admin_list);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.209.196.123/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestAdmin restAdmin = retrofit.create(RestAdmin.class);
        Call<List<admin>> call = restAdmin.getMyInfo();

        call.enqueue(new Callback<List<admin>>() {
            @Override
            public void onResponse(Call<List<admin>> call, Response<List<admin>> response) {
                // 응답이 성공적이지 못할 때
                if (!response.isSuccessful()) {
                    //tv_result.setText("Code : " + response.code());
                    //로그 찍기
                    String err_code = "에러 코드 : " + response.code() + "";
                    Log.d(TAG, err_code);
                }

                //응답이 성공적일 때
                List<admin> student = response.body();
                for (admin s : student)
                {
                    String name = s.getName();
                    Long age = s.getAge();
                    String univ = s.getUniv();
                    String result = "";
                    result += "이름 : " + name + "\n";
                    result += "나이 : " + age + "\n";
                    result += "대학 : " + univ + "\n";
                    result += "\n";
                    if (tv_result.getText().toString() == null)
                        tv_result.setText(result);
                    else
                        tv_result.append(result);
                }
            }

            @Override
            public void onFailure(Call<List<admin>> call, Throwable t) {
                tv_result.setText(t.getMessage());
                Log.d(TAG, "onFailure() 함수 호출");
                Log.d(TAG, t.getMessage());
            }
        });

        btn_recycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, user_recycler.class);
                startActivity(intent);
            }
        });
    }
}