package com.example.minhasfinancas;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.minhasfinancas.model.Gasto;
import com.example.minhasfinancas.model.ResumoCategoria;

import java.util.List;

@Dao
public interface GastoDao {

    @Insert
    void inserir(Gasto gasto);

    @Delete
    void deletar(Gasto gasto);

    @Query("SELECT * FROM Gasto ORDER BY data DESC")
    List<Gasto> listarTodos();

    @Query("SELECT categoria, SUM(valor) as total FROM Gasto GROUP BY categoria")
    List<ResumoCategoria> resumoPorCategoria();

    @Query("SELECT SUM(valor) FROM Gasto")
    double getTotalGasto();

    @Query("SELECT categoria FROM Gasto GROUP BY categoria ORDER BY SUM(valor) DESC LIMIT 1")
    String getCategoriaMaisGasta();
}
