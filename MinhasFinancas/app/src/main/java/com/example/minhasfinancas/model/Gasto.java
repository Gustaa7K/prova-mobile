package com.example.minhasfinancas.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Gasto {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String descricao;
    private double valor;
    private String data;
    private String categoria;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
