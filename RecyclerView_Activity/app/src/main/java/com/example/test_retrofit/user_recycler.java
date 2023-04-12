package com.example.test_retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class user_recycler extends AppCompatActivity {

    private static final String TAG = "user_recycler 액티비티";
    // 어댑터
    private CustomAdapter adapter;
    // 리사이클러뷰
    private RecyclerView recyclerView;
    // 진행바
    private ProgressDialog progressDialog;
    private TextView tv_hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_recycler);

        progressDialog = new ProgressDialog(user_recycler.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        tv_hint = findViewById(R.id.tv_hint);

        // 레트로핏 연동
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
                    //로그 찍기
                    String err_code = "에러 코드 : " + response.code() + "";
                    Log.d(TAG, err_code);
                }

                // 프로그레스 바 삭제
                progressDialog.dismiss();
                getDataList(response.body());
                // 힌트 삭제
                tv_hint.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<admin>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(user_recycler.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure() 함수 호출");
                Log.d(TAG, t.getMessage());
            }
        });
    }

    // 리사이클러뷰
    private void getDataList(List<admin> ad) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this, ad);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(user_recycler.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}