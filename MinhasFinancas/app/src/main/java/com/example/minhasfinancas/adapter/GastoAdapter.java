package com.example.minhasfinancas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minhasfinancas.R;
import com.example.minhasfinancas.model.Gasto;

import java.util.List;

public class GastoAdapter extends RecyclerView.Adapter<GastoAdapter.ViewHolder> {

    private List<Gasto> lista;

    public GastoAdapter(List<Gasto> lista) {
        this.lista = lista;
    }

    public void updateLista(List<Gasto> novaLista) {
        this.lista = novaLista;
        notifyDataSetChanged();  // Notifica o RecyclerView para atualizar a lista
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView descricao, valor, categoria, data;

        public ViewHolder(View view) {
            super(view);
            descricao = view.findViewById(R.id.txtDescricao);
            valor = view.findViewById(R.id.txtValor);
            categoria = view.findViewById(R.id.txtCategoria);
            data = view.findViewById(R.id.txtData);
        }
    }

    @NonNull
    @Override
    public GastoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gasto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GastoAdapter.ViewHolder holder, int position) {
        Gasto g = lista.get(position);
        holder.descricao.setText(g.getDescricao());
        holder.valor.setText(String.format("R$ %.2f", g.getValor()));
        holder.categoria.setText(g.getCategoria());
        holder.data.setText(g.getData());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
