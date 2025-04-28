package com.example.minhasfinancas.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Gasto implements Parcelable {
    private String descricao;
    private double valor;
    private String categoria;
    private String data;

    public Gasto(String descricao, double valor, String categoria, String data) {
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.data = data;
    }

    protected Gasto(Parcel in) {
        descricao = in.readString();
        valor = in.readDouble();
        categoria = in.readString();
        data = in.readString();
    }

    public static final Creator<Gasto> CREATOR = new Creator<Gasto>() {
        @Override
        public Gasto createFromParcel(Parcel in) {
            return new Gasto(in);
        }

        @Override
        public Gasto[] newArray(int size) {
            return new Gasto[size];
        }
    };

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getData() {
        return data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(descricao);
        parcel.writeDouble(valor);
        parcel.writeString(categoria);
        parcel.writeString(data);
    }
}
