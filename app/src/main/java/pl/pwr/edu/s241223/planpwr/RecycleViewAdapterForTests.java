package pl.pwr.edu.s241223.planpwr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.TestCard;

public class RecycleViewAdapterForTests extends RecyclerView.Adapter<RecycleViewAdapterForTests.ViewHolder> {
    private ArrayList<TestCard> testCards;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tvTestCard);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public RecycleViewAdapterForTests(ArrayList<TestCard> testCards){
        this.testCards = testCards;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TestCard currentTest = testCards.get(position);

        holder.textView.setText(currentTest.getTestInfo());
    }

    @Override
    public int getItemCount() {
        return testCards.size();
    }
}
