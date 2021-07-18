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

public class LeaguesDetailsAdapter extends RecyclerView.Adapter<LeaguesDetailsAdapter.ViewHolder> {

    private List<ItemsLeaguesDetails> listData;
    private Context context;
    private String country;
    private String flag;

    public LeaguesDetailsAdapter(List<ItemsLeaguesDetails> listData, Context context, String country, String flag){
        this.listData = listData;
        this.context = context;
        this.country = country;
        this.flag = flag;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_leagues_details, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final ItemsLeaguesDetails listItem = listData.get(i);
        viewHolder.positionView.setText(listItem.getRank());
        viewHolder.teamView.setText(listItem.getname());
        viewHolder.pjView.setText(listItem.getMatchsPlayed());
        viewHolder.goalsView.setText(listItem.getGoalsFor() + ":" + listItem.getGoalsAgainst());
        viewHolder.pointsView.setText(listItem.getPoints());
        Picasso.with(context).load(listItem.getimageUrl()).into(viewHolder.logoView);
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TeamsDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("country", country);
                intent.putExtra("flag", flag);
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

        public TextView positionView;
        public TextView teamView;
        public TextView pjView;
        public TextView goalsView;
        public TextView pointsView;
        public ImageView logoView;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            positionView = (TextView) itemView.findViewById(R.id.positionView);
            teamView = (TextView) itemView.findViewById(R.id.teamView);
            pjView = (TextView) itemView.findViewById(R.id.pjView);
            goalsView = (TextView) itemView.findViewById(R.id.goalsView);
            pointsView = (TextView) itemView.findViewById(R.id.pointsView);
            logoView = (ImageView) itemView.findViewById(R.id.logoView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.layout_leagues_details);
        }
    }
}
