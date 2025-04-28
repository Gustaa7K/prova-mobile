package com.example.minhasfinancas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.example.minhasfinancas.model.Gasto;

public class CadastroGastoActivity extends AppCompatActivity {

    private EditText edtDescricao, edtValor, edtData;
    private Spinner spnCategoria;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_gasto);

        edtDescricao = findViewById(R.id.edtDescricao);
        edtValor = findViewById(R.id.edtValor);
        edtData = findViewById(R.id.edtData);
        spnCategoria = findViewById(R.id.spnCategoria);
        btnSalvar = findViewById(R.id.btnSalvar);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categorias_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategoria.setAdapter(adapter);

        btnSalvar.setOnClickListener(v -> {
            String descricao = edtDescricao.getText().toString();
            String valorTexto = edtValor.getText().toString();
            String categoria = spnCategoria.getSelectedItem().toString();
            String data = edtData.getText().toString();

            if (descricao.isEmpty() || valorTexto.isEmpty() || data.isEmpty()) {
                // Se algum campo obrigatório estiver vazio, mostra erro
                edtDescricao.setError(descricao.isEmpty() ? "Preencha a descrição" : null);
                edtValor.setError(valorTexto.isEmpty() ? "Preencha o valor" : null);
                edtData.setError(data.isEmpty() ? "Preencha a data" : null);
                return; // para aqui e não deixa salvar
            }

            double valor = Double.parseDouble(valorTexto);

            Gasto gasto = new Gasto(descricao, valor, categoria, data);
            Intent resultIntent = new Intent();
            resultIntent.putExtra("gasto", gasto);  // Passando como Parcelable
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }
}
