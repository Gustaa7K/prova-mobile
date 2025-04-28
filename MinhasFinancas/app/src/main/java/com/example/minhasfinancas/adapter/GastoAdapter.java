package com.example.minhasfinancas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.minhasfinancas.R;
import com.example.minhasfinancas.model.Gasto;
import java.util.List;

public class GastoAdapter extends RecyclerView.Adapter<GastoAdapter.GastoViewHolder> {
    private List<Gasto> listaGastos;

    public GastoAdapter(List<Gasto> listaGastos) {
        this.listaGastos = listaGastos;
    }

    @Override
    public GastoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gasto, parent, false);
        return new GastoViewHolder(item);
    }

    @Override
    public void onBindViewHolder(GastoViewHolder holder, int position) {
        Gasto gasto = listaGastos.get(position);
        holder.txtDescricao.setText(gasto.getDescricao());
        holder.txtValor.setText("R$ " + String.format("%.2f", gasto.getValor()));
        holder.txtCategoria.setText(gasto.getCategoria());
        holder.txtData.setText(gasto.getData());
    }

    @Override
    public int getItemCount() {
        return listaGastos.size();
    }

    public static class GastoViewHolder extends RecyclerView.ViewHolder {
        TextView txtDescricao, txtValor, txtCategoria, txtData;

        public GastoViewHolder(View itemView) {
            super(itemView);
            txtDescricao = itemView.findViewById(R.id.txtDescricao);
            txtValor = itemView.findViewById(R.id.txtValor);
            txtCategoria = itemView.findViewById(R.id.txtCategoria);
            txtData = itemView.findViewById(R.id.txtData);
        }
    }
}
