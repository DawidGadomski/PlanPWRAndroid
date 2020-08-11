package pl.pwr.edu.s241223.planpwr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.LinkCard;

public class RecycleViewAdapterForLinks extends RecyclerView.Adapter<RecycleViewAdapterForLinks.ViewHolder> {
    private ArrayList<LinkCard> linkCards;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvLinkName;
        public TextView tvLink;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvLinkName = itemView.findViewById(R.id.tvLinkName);
            tvLink = itemView.findViewById(R.id.tvLink);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public RecycleViewAdapterForLinks(ArrayList<LinkCard> linkCards){
        this.linkCards = linkCards;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LinkCard currentLink = linkCards.get(position);

        holder.tvLinkName.setText(currentLink.getNameOfTheSite());
        holder.tvLink.setText(currentLink.getLink().toString());
    }

    @Override
    public int getItemCount() {
        return linkCards.size();
    }
}
