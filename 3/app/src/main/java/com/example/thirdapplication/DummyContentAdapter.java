package com.example.thirdapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class DummyContentAdapter extends RecyclerView.Adapter<DummyContentAdapter.DummyItemViewHolder> {
    private DummyContent content;

    public static class DummyItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tvItemId;
        public TextView tvItemContent;
        public View itemView;
        public DummyItemViewHolder(View v) {
            super(v);
            itemView = v;
            tvItemId = v.findViewById(R.id.tvItemId);
            tvItemContent = v.findViewById(R.id.tvItemContent);
        }
    }

    public DummyContentAdapter(DummyContent content) {
        this.content = content;
    }

    @Override
    public DummyContentAdapter.DummyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dummy_item, parent, false);
        DummyItemViewHolder vh = new DummyItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(DummyItemViewHolder holder, int position) {
        DummyItem item = this.content.getItem(position);
        if (item != null) {
            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(onClickListener);
            holder.tvItemId.setText(item.id);
            holder.tvItemContent.setText(item.content);
        }
    }

    @Override
    public int getItemCount() {
        return content.getItemCount();
    }

    final private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = (int) view.getTag();
            DummyItem item = content.getItem(position);
            if (item != null) {
                Context context = view.getContext();
                Intent intent = new Intent(context, DummyItemDetailsActivity.class);
                intent.putExtra(DummyItemDetailsActivity.ARG_ITEM_ID, item.id);
                context.startActivity(intent);
            }
        }
    };

}