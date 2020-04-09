package com.example.projectuts.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectuts.R;
import com.example.projectuts.model.Transaction;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    public interface OnItemTransactionListener{
        void onTransactionClicked(int index, Transaction item);
    }

    private List<Transaction> items;
    private OnItemTransactionListener listener;

    public TransactionAdapter(List<Transaction> items, OnItemTransactionListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_groom, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        Transaction item = items.get(position);
        if(item.getType() == Transaction.Type.STANDARD){
            holder.linearLayout.setBackgroundColor(Color.parseColor("#E7E6E6"));
        }
        holder.bind(position, item);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View linearLayout;
        ImageView imageView;
        TextView namaHewan;
        TextView tanggal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            imageView = itemView.findViewById(R.id.imageView2);
            namaHewan = itemView.findViewById(R.id.namaHewantxt);
            tanggal = itemView.findViewById(R.id.textViewTanggal);
        }

        public void bind(final int index, final Transaction item) {
            imageView.setImageURI(item.getImage());
            namaHewan.setText(item.getNamaHewan());
            tanggal.setText(String.valueOf(item.getTanggal()));
            //interaksi click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTransactionClicked(index,item);
                }
            });
        }
    }
}
