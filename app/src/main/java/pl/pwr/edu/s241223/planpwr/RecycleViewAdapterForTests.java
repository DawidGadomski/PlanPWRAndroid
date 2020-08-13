package pl.pwr.edu.s241223.planpwr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.TestCard;

public class RecycleViewAdapterForTests extends RecyclerView.Adapter<RecycleViewAdapterForTests.ViewHolder> {
    private ArrayList<TestCard> testCards;
    private OnItemClickListener listener;

    public interface  OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
        void onEditClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTestName;
        public TextView tvDateOfTest;
        public ImageButton ibEditTestCard;
        public ImageButton ibDeleteTestCard;


        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            tvTestName = itemView.findViewById(R.id.tvTestName);
            tvDateOfTest = itemView.findViewById(R.id.tvDateOfTest);
            ibDeleteTestCard = itemView.findViewById(R.id.ibDeleteTestCard);
            ibEditTestCard = itemView.findViewById(R.id.ibEditTestCard);

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
            ibDeleteTestCard.setOnClickListener(new View.OnClickListener() {
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

            ibEditTestCard.setOnClickListener(new View.OnClickListener() {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, listener);
        return viewHolder;
    }

    public RecycleViewAdapterForTests(ArrayList<TestCard> testCards){
        this.testCards = testCards;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TestCard currentTest = testCards.get(position);

        holder.tvTestName.setText(currentTest.getTestName());
        holder.tvDateOfTest.setText(currentTest.getTestDate());
    }

    @Override
    public int getItemCount() {
        return testCards.size();
    }
}
