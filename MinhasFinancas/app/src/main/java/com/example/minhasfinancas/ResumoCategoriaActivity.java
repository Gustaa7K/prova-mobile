package com.example.minhasfinancas;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.minhasfinancas.model.Gasto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumoCategoriaActivity extends AppCompatActivity {

    private TextView txtResumo;
    private List<Gasto> listaGastos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_categoria);

        txtResumo = findViewById(R.id.txtResumo);
        listaGastos = (List<Gasto>) getIntent().getSerializableExtra("gastos");

        new Thread(() -> {
            try {
                Thread.sleep(2000); // Simula processamento pesado
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Map<String, Double> totalPorCategoria = new HashMap<>();
            double totalGeral = 0;
            String maiorCategoria = "";
            double maiorValor = 0;

            for (Gasto g : listaGastos) {
                double valor = g.getValor();
                totalGeral += valor;

                double soma = totalPorCategoria.getOrDefault(g.getCategoria(), 0.0) + valor;
                totalPorCategoria.put(g.getCategoria(), soma);

                if (soma > maiorValor) {
                    maiorValor = soma;
                    maiorCategoria = g.getCategoria();
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("Total por categoria:\n");
            for (Map.Entry<String, Double> entry : totalPorCategoria.entrySet()) {
                sb.append(entry.getKey()).append(": R$ ")
                        .append(String.format("%.2f", entry.getValue())).append("\n");
            }
            sb.append("\nGasto total: R$ ").append(String.format("%.2f", totalGeral));
            sb.append("\nCategoria com maior gasto: ").append(maiorCategoria);

            runOnUiThread(() -> txtResumo.setText(sb.toString()));
        }).start();
    }
}
