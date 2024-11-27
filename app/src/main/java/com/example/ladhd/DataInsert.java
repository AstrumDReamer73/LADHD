package com.example.ladhd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ladhd.databinding.AnadirtareaBinding;

public class DataInsert extends AppCompatActivity {
    AnadirtareaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AnadirtareaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Recibir datos del Intent
        String type = getIntent().getStringExtra("tipo");

        // Manejo de "modificar"
        if ("modificar".equals(type)) {
            setTitle("Modificar");
            binding.nombre.setText(getIntent().getStringExtra("nombre"));
            binding.fechaEntrega.setText(getIntent().getStringExtra("fechaEntrega"));
            binding.estado.setText(getIntent().getStringExtra("estado"));
            binding.descripcion.setText(getIntent().getStringExtra("descripcion"));
            int id = getIntent().getIntExtra("id", 0);

            // Configuración del botón en modo "modificar"
            binding.BTEditar.setText("Modificar tarea");

            binding.BTEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("id", getIntent().getIntExtra("id", -1));
                    intent.putExtra("nombre", binding.nombre.getText().toString());
                    intent.putExtra("estado", binding.estado.getText().toString());
                    intent.putExtra("fechaEntrega", binding.fechaEntrega.getText().toString());
                    intent.putExtra("descripcion", binding.descripcion.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        } else {
            // Manejo de "añadir"
            setTitle("Añadir");

            // Configuración del botón en modo "añadir"
            binding.BTEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("id", getIntent().getIntExtra("id", -1));
                    intent.putExtra("nombre", binding.nombre.getText().toString());
                    intent.putExtra("estado", binding.estado.getText().toString());
                    intent.putExtra("fechaEntrega", binding.fechaEntrega.getText().toString());
                    intent.putExtra("descripcion", binding.descripcion.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DataInsert.this, MainActivity.class));
    }
}
