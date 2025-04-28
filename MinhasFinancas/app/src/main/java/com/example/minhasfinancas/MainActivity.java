package com.example.minhasfinancas;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.minhasfinancas.adapter.GastoAdapter;
import com.example.minhasfinancas.model.Gasto;
import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private List<Gasto> listaGastos = new ArrayList<>();
    private GastoAdapter adapter;
    private RecyclerView recyclerView;
    private Button btnNovo, btnResumo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnNovo = findViewById(R.id.btnNovo);
        btnResumo = findViewById(R.id.btnResumo);

        adapter = new GastoAdapter(listaGastos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnNovo.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, CadastroGastoActivity.class);
            startActivityForResult(i, 1);
        });

        btnResumo.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, ResumoCategoriaActivity.class);
            i.putParcelableArrayListExtra("gastos", new ArrayList<>(listaGastos));
            startActivity(i);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Gasto gasto = data.getParcelableExtra("gasto");  // Usando getParcelableExtra
            listaGastos.add(gasto);
            adapter.notifyDataSetChanged();
        }
    }
}
