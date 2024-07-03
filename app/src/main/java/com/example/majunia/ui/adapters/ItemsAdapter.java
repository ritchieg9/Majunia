package com.example.majunia.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.majunia.R;
import com.example.majunia.ui.model.Channels;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Channels> itemsModelArrayList;

    // Constructor
    public ItemsAdapter(Context context, ArrayList<Channels> itemsModelArrayList) {
        this.context = context;
        this.itemsModelArrayList = itemsModelArrayList;
    }

    @NonNull
    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Channels model = itemsModelArrayList.get(position);
        holder.channelName.setText(model.getChannelName());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return itemsModelArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView channelName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            channelName = itemView.findViewById(R.id.channelName);
        }
    }
}