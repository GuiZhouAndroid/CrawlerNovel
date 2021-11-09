package com.lpssfyx.ldy.crawlernovel.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lpssfyx.ldy.crawlernovel.R;
import com.lpssfyx.ldy.crawlernovel.bean.SelectAllUserInfo;

import java.util.List;

/**
 * created by on 2021/11/8
 * 描述：
 *
 * @author ZSAndroid
 * @create 2021-11-08-23:25
 */

public class Recyadap extends RecyclerView.Adapter<Recyadap.Viewholder> {

    Context context;
    List<SelectAllUserInfo.Data> list;

    public Recyadap(Context context, List<SelectAllUserInfo.Data> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(context).inflate(R.layout.admin_recycler_view_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull Recyadap.Viewholder holder, int position) {
       // holder.name.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private final TextView name;

        public Viewholder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_id);
        }
    }
}
