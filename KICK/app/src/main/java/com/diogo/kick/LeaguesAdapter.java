package com.diogo.kick;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class LeaguesAdapter extends RecyclerView.Adapter<LeaguesAdapter.ViewHolder> {

    private List<ItemsLeagues> listData;
    private Context context;

    public LeaguesAdapter(List<ItemsLeagues> listData, Context context){
        this.listData = listData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_leagues, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final ItemsLeagues listItem = listData.get(i);
        viewHolder.nameView.setText(listItem.getname());
        viewHolder.countryView.setText(listItem.getCountry());
        Picasso.with(context).load(listItem.getFlag()).into(viewHolder.imageView);
        Picasso.with(context).load(listItem.getimageUrl()).into(viewHolder.logoView);
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LeaguesDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("country", listItem.getCountry());
                intent.putExtra("flag", listItem.getFlag());
                intent.putExtra("name", listItem.getname());
                intent.putExtra("imageUrl", listItem.getimageUrl());
                intent.putExtra("id", listItem.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public ImageView logoView;
        public TextView nameView;
        public TextView countryView;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            logoView = (ImageView) itemView.findViewById(R.id.logoView);
            nameView = (TextView) itemView.findViewById(R.id.nameView);
            countryView = (TextView) itemView.findViewById(R.id.countryView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.layout_leagues);
        }
    }
}
