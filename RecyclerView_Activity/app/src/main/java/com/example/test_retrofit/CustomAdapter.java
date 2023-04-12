package com.example.test_retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private List<admin> adminList;
    private Context context;

public CustomAdapter(Context context, List<admin> adminList) {
        this.context = context;
        this.adminList = adminList;
        }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        TextView user_name;
        TextView user_age;
        TextView user_univ;
        private ImageView coverImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            user_name = mView.findViewById(R.id.name);
            user_age = mView.findViewById(R.id.age);
            user_univ = mView.findViewById(R.id.univ);
            coverImage = mView.findViewById(R.id.coverImage);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.customrow, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position)
    {
        // 이름
        holder.user_name.setText(adminList.get(position).getName());
        String age = adminList.get(position).getAge()+"";
        // 나이
        holder.user_age.setText(age);
        // 학교
        holder.user_univ.setText(adminList.get(position).getUniv());
        // 이미지
        Glide.with(context)
                .load(R.drawable.ic_launcher_background)
                .load(R.drawable.ic_launcher_foreground)
                .skipMemoryCache(true)
                .circleCrop()
                .skipMemoryCache(true)
                .error(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.coverImage);
    }

    @Override
    public int getItemCount() {
        return adminList.size();
    }
}
