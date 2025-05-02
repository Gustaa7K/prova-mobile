package com.example.minhasfinancas;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.minhasfinancas.model.Gasto;

public class CadastroGastoActivity extends AppCompatActivity {

    private EditText edtDescricao, edtValor, edtData;
    private Spinner spnCategoria;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_gasto);

        edtDescricao = findViewById(R.id.edtDescricao);
        edtValor = findViewById(R.id.edtValor);
        edtData = findViewById(R.id.edtData);
        spnCategoria = findViewById(R.id.spnCategoria);
        Button btnSalvar = findViewById(R.id.btnSalvar);

        db = AppDatabase.getInstance(getApplicationContext());

        // Adapter para o Spinner de categorias
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categorias, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategoria.setAdapter(adapter);

        // Evento de clique para salvar o gasto
        btnSalvar.setOnClickListener(v -> salvarGasto());
    }

    private void salvarGasto() {
        String descricao = edtDescricao.getText().toString().trim();
        String valorString = edtValor.getText().toString().trim();
        String data = edtData.getText().toString().trim();
        String categoria = spnCategoria.getSelectedItem().toString();

        if (descricao.isEmpty() || valorString.isEmpty() || data.isEmpty() || categoria.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double valor;
        try {
            valor = Double.parseDouble(valorString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valor inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        Gasto gasto = new Gasto();
        gasto.setDescricao(descricao);
        gasto.setValor(valor);
        gasto.setData(data);
        gasto.setCategoria(categoria);

        // Salvar em uma thread separada
        new Thread(() -> {
            db.gastoDao().inserir(gasto);
            runOnUiThread(() -> {
                Toast.makeText(this, "Gasto salvo com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            });
        }).start();
    }
}
