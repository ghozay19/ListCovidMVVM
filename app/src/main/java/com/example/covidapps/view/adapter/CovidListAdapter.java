package com.example.covidapps.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapps.R;
import com.example.covidapps.model.webService.pojo.response.FeaturesItem;

import java.util.ArrayList;
import java.util.List;

public class CovidListAdapter extends RecyclerView.Adapter<CovidListAdapter.viewHolder> {

    private Context context;
    private List<FeaturesItem> listData = new ArrayList<>();


    public CovidListAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<FeaturesItem> data) {
        listData.addAll(data);
        notifyDataSetChanged();
    }

    public void clearData() {
        listData.clear();
        notifyDataSetChanged();
    }

    public void searchData(List<FeaturesItem> filter) {
        this.listData = filter;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CovidListAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(context).inflate(R.layout.item_list_data_covid, parent, false);
        return new CovidListAdapter.viewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CovidListAdapter.viewHolder holder, int position) {
        FeaturesItem item = listData.get(position);

        final String provinceName = item.getAttributes().getProvinsi();
        final String positiveCase = String.valueOf(item.getAttributes().getKasusPosi());
        final String deathCase = String.valueOf(item.getAttributes().getKasusMeni());
        final String recoverCase = String.valueOf(item.getAttributes().getKasusSemb());
        holder.tvDeath.setText(deathCase);
        holder.tvPositive.setText(positiveCase);
        holder.tvRecover.setText(recoverCase);
        holder.tvProvinceName.setText(provinceName);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        TextView tvDeath,tvRecover,tvPositive,tvProvinceName;

        viewHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView) {
            tvProvinceName = itemView.findViewById(R.id.tv_province);
            tvDeath = itemView.findViewById(R.id.tv_death);
            tvPositive = itemView.findViewById(R.id.tv_positive);
            tvRecover = itemView.findViewById(R.id.tv_recover);
        }
    }
}
