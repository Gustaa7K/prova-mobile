package com.example.minhasfinancas;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minhasfinancas.adapter.GastoAdapter;
import com.example.minhasfinancas.model.Gasto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GastoAdapter adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        FloatingActionButton btnResumo = findViewById(R.id.btnResumo);

        btnAdd.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, CadastroGastoActivity.class))
        );

        btnResumo.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ResumoCategoriaActivity.class))
        );

        carregarGastos();
    }

    private void carregarGastos() {
        new Thread(() -> {
            List<Gasto> lista = db.gastoDao().listarTodos();
            runOnUiThread(() -> {
                if (adapter == null) {
                    adapter = new GastoAdapter(lista);
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter.updateLista(lista);  // Atualiza a lista no adapter
                }
            });
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarGastos();
    }
}
