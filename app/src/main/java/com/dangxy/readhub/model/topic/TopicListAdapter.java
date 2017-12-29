package com.dangxy.readhub.model.topic;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dangxy.readhub.R;
import com.dangxy.readhub.entity.TopicEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dangxueyi
 * @description
 * @date 2017/12/14
 */

public class TopicListAdapter extends RecyclerView.Adapter<TopicListAdapter.ViewHolder> {

    private List<TopicEntity.DataBean> listEntities = new ArrayList<>();

    public TopicListAdapter(List<TopicEntity.DataBean> listEntities) {
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
        holder.more.setText(listEntities.get(position).getId());
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

    public void addAll(List<TopicEntity.DataBean> topicList) {
        listEntities.addAll(topicList);
        notifyDataSetChanged();
    }
    public void refresh(List<TopicEntity.DataBean>  topicList) {
        listEntities.clear();
        listEntities.addAll(topicList);
        notifyDataSetChanged();
    }
}
