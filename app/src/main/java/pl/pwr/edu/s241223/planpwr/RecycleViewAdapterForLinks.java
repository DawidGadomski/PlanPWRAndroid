package pl.pwr.edu.s241223.planpwr;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.LinkCard;

public class RecycleViewAdapterForLinks extends RecyclerView.Adapter<RecycleViewAdapterForLinks.ViewHolder> {
    private ArrayList<LinkCard> linkCards;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
        void onEditClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvLinkName;
        public TextView tvLink;
        public ImageButton ibDeleteLinkCard;
        public ImageButton ibEditLinkCard;




        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            tvLinkName = itemView.findViewById(R.id.tvLinkName);
            tvLink = itemView.findViewById(R.id.tvLink);
            ibDeleteLinkCard = itemView.findViewById(R.id.ibDeleteLinkCard);
            ibEditLinkCard = itemView.findViewById(R.id.ibEditLinkCard);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
            ibDeleteLinkCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });

            ibEditLinkCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onEditClick(position);
                        }
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.link_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, listener);
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
