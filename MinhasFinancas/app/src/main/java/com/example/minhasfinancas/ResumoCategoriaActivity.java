package com.example.minhasfinancas;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.minhasfinancas.model.ResumoCategoria;

import java.util.List;

public class ResumoCategoriaActivity extends AppCompatActivity {

    private TextView txtResumo;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_categoria);

        txtResumo = findViewById(R.id.txtResumo);
        db = AppDatabase.getInstance(this); // Agora corretamente acessando a classe AppDatabase

        new Thread(() -> {
            List<ResumoCategoria> resumoList = db.gastoDao().resumoPorCategoria();
            double totalGasto = db.gastoDao().getTotalGasto();
            String categoriaMaisGasta = db.gastoDao().getCategoriaMaisGasta();

            StringBuilder sb = new StringBuilder();
            sb.append("Resumo por categoria:\n\n");
            for (ResumoCategoria resumo : resumoList) {
                sb.append(resumo.getCategoria())
                        .append(": R$ ")
                        .append(String.format("%.2f", resumo.getTotal()))
                        .append("\n");
            }

            sb.append("\nTotal do mês: R$ ").append(String.format("%.2f", totalGasto));
            sb.append("\nCategoria com maior gasto: ").append(categoriaMaisGasta);

            runOnUiThread(() -> txtResumo.setText(sb.toString()));
        }).start();
    }
}
