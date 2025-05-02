package com.example.minhasfinancas.model;

public class ResumoCategoria {
    public String categoria;
    public double total;

    public ResumoCategoria(String categoria, double total) {
        this.categoria = categoria;
        this.total = total;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getTotal() {
        return total;
    }
}
