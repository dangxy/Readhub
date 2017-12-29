package com.dangxy.readhub.model.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dangxy.readhub.R;
import com.dangxy.readhub.entity.NewEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dangxueyi
 * @description
 * @date 2017/12/14
 */

public class NewshListAdapter extends RecyclerView.Adapter<NewshListAdapter.ViewHolder> {

    private List<NewEntity.DataBean> listEntities = new ArrayList<>();

    public NewshListAdapter(List<NewEntity.DataBean> listEntities) {
        this.listEntities = listEntities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.readhub_item_topic, null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.title.setText(listEntities.get(position).getTitle());
        holder.summary.setText(listEntities.get(position).getSummary());
        holder.more.setText(listEntities.get(position).getId()+"");
    }


    @Override
    public int getItemCount() {
        return listEntities.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, summary, more;


        public ViewHolder(View convertView) {
            super(convertView);
            title = (TextView) convertView.findViewById(R.id.title);
            summary = (TextView) convertView.findViewById(R.id.summary);
            more = (TextView) convertView.findViewById(R.id.more);
        }
    }

    public void addAll(List<NewEntity.DataBean> newsList) {
        listEntities.addAll(newsList);
        notifyDataSetChanged();
    }
    public void refresh(List<NewEntity.DataBean>  newsList) {
        listEntities.clear();
        listEntities.addAll(newsList);
        notifyDataSetChanged();
    }
}
