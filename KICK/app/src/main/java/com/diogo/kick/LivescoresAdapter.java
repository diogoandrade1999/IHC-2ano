package com.diogo.kick;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class LivescoresAdapter extends RecyclerView.Adapter<LivescoresAdapter.ViewHolder> {

    private List<ItemsLivescores> listData;
    private Context context;
    private String country;
    private String flag;

    public LivescoresAdapter(List<ItemsLivescores> listData, Context context, String country, String flag){
        this.listData = listData;
        this.context = context;
        this.country = country;
        this.flag = flag;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_livescores, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final ItemsLivescores listItem = listData.get(i);
        viewHolder.dateView.setText(listItem.getDate());
        viewHolder.team1View.setText(listItem.getName1());
        viewHolder.team2View.setText(listItem.getName2());
        viewHolder.scoreHomeView.setText(listItem.getScoreHome());
        viewHolder.scoreAwayView.setText(listItem.getScoreAway());
        Picasso.with(context).load(listItem.getImageUrl1()).into(viewHolder.logo1View);
        Picasso.with(context).load(listItem.getImageUrl2()).into(viewHolder.logo2View);
        viewHolder.team1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TeamsDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("country", country);
                intent.putExtra("flag", flag);
                intent.putExtra("name", listItem.getName1());
                intent.putExtra("imageUrl", listItem.getImageUrl1());
                intent.putExtra("id", listItem.getId1());
                context.startActivity(intent);
            }
        });
        viewHolder.team2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TeamsDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("country", country);
                intent.putExtra("flag", flag);
                intent.putExtra("name", listItem.getName2());
                intent.putExtra("imageUrl", listItem.getImageUrl2());
                intent.putExtra("id", listItem.getId2());
                context.startActivity(intent);
            }
        });
        viewHolder.logo1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TeamsDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("country", country);
                intent.putExtra("flag", flag);
                intent.putExtra("name", listItem.getName1());
                intent.putExtra("imageUrl", listItem.getImageUrl1());
                intent.putExtra("id", listItem.getId1());
                context.startActivity(intent);
            }
        });
        viewHolder.logo2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TeamsDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("country", country);
                intent.putExtra("flag", flag);
                intent.putExtra("name", listItem.getName2());
                intent.putExtra("imageUrl", listItem.getImageUrl2());
                intent.putExtra("id", listItem.getId2());
                context.startActivity(intent);
            }
        });

        viewHolder.oddsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LivescoresDetails.progressDialog.show();
                new LivescoresOddsData(context, listItem.getIdf(), listItem.getName1(), listItem.getName2()).execute();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView dateView;
        public ImageView logo1View;
        public ImageView logo2View;
        public TextView team1View;
        public TextView team2View;
        public TextView scoreHomeView;
        public TextView scoreAwayView;
        public Button oddsView;

        public ViewHolder(View itemView) {
            super(itemView);

            dateView = (TextView) itemView.findViewById(R.id.dateView);
            team1View = (TextView) itemView.findViewById(R.id.team1View);
            team2View = (TextView) itemView.findViewById(R.id.team2View);
            scoreHomeView = (TextView) itemView.findViewById(R.id.scoreHomeView);
            scoreAwayView = (TextView) itemView.findViewById(R.id.scoreAwayView);
            logo1View = (ImageView) itemView.findViewById(R.id.logo1View);
            logo2View = (ImageView) itemView.findViewById(R.id.logo2View);
            oddsView = itemView.findViewById(R.id.oddsView);
        }
    }
}
