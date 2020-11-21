package com.udacity.sandwichclub.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.sandwichclub.DetailActivity;
import com.udacity.sandwichclub.R;

public class SandwichListAdapter extends RecyclerView.Adapter<SandwichListAdapter.SandwichViewHolder> {

    private String[] sandwiches;

    public SandwichListAdapter(String[] sandwiches) {
        this.sandwiches = sandwiches;
    }

    public static class SandwichViewHolder extends RecyclerView.ViewHolder {
        public TextView tvSandwich;
        public SandwichViewHolder(View view) {
            super(view);
            tvSandwich = view.findViewById(R.id.sandwich_tv);
        }
    }

    @NonNull
    @Override
    public SandwichViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.sandwich_list_item, viewGroup, false);
        return new SandwichViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SandwichViewHolder sandwichViewHolder, int i) {
        final int position = i;
        String sandwich = this.sandwiches[position];
        sandwichViewHolder.tvSandwich.setText(sandwich);
        sandwichViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("debug", "Clicked: " + position);
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_POSITION, position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.sandwiches.length;
    }
}
