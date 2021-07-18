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

public class TeamsDetailsAdapter extends RecyclerView.Adapter<TeamsDetailsAdapter.ViewHolder> {

    private List<ItemsTeamsDetails> listData;
    private Context context;
    private String country;
    private String flag;
    private String name_team;
    private String imageUrl;

    public TeamsDetailsAdapter(List<ItemsTeamsDetails> listData, Context context, String country, String flag, String name_team, String imageUrl){
        this.listData = listData;
        this.context = context;
        this.country = country;
        this.flag = flag;
        this.name_team = name_team;
        this.imageUrl = imageUrl;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_teams, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final ItemsTeamsDetails listItem = listData.get(i);
        viewHolder.positionView.setText(listItem.getPosition());
        viewHolder.nameView.setText(listItem.getname());
        viewHolder.numberView.setText(listItem.getNumber());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayersDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("country", country);
                intent.putExtra("player_id", listItem.getId());
                intent.putExtra("imageUrl", imageUrl);
                intent.putExtra("flag", flag);
                intent.putExtra("team", name_team);
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
        public TextView nameView;
        public TextView numberView;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            positionView = (TextView) itemView.findViewById(R.id.positionView);
            nameView = (TextView) itemView.findViewById(R.id.nameView);
            numberView = (TextView) itemView.findViewById(R.id.numberView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.layout_teams);
        }
    }
}
