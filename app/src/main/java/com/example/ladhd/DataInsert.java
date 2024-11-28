package com.example.ladhd;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ladhd.databinding.AnadirtareaBinding;

import java.util.ArrayList;

public class DataInsert extends AppCompatActivity {
    AnadirtareaBinding binding;

    // Declaramos el ActivityResultLauncher
    private ActivityResultLauncher<Intent> speechLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AnadirtareaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Inicializamos el ActivityResultLauncher
        speechLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        ArrayList<String> speechResult = result.getData().getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        if (speechResult != null && !speechResult.isEmpty()) {
                            // Usamos el primer resultado para llenar el campo de texto
                            binding.descripcion.setText(speechResult.get(0));
                        }
                    }
                });

        binding.BTEscuchar.setOnClickListener(v -> {
            // Llamamos a la actividad de reconocimiento de voz
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hable ahora...");
            speechLauncher.launch(intent);
        });

        String type = getIntent().getStringExtra("tipo");

        if (type.equals("modificar")) {
            setTitle("modificar");
            binding.nombre.setText(getIntent().getStringExtra("nombre"));
            binding.fechaEntrega.setText(getIntent().getStringExtra("fechaEntrega"));
            binding.estado.setText(getIntent().getStringExtra("estado"));
            binding.descripcion.setText(getIntent().getStringExtra("descripcion"));
            int id = getIntent().getIntExtra("id", 0);
            binding.BTAnadir.setText("modificar tarea");
            binding.BTAnadir.setOnClickListener(v -> {
                Intent intent = new Intent();
                intent.putExtra("nombre", binding.nombre.getText().toString());
                intent.putExtra("estado", binding.nombre.getText().toString());
                intent.putExtra("fecha de entrega", binding.nombre.getText().toString());
                intent.putExtra("descripcion", binding.nombre.getText().toString());
                intent.putExtra("id", id);
                setResult(RESULT_OK, intent);
                finish();
            });
        } else {
            setTitle("añadir");
            binding.BTAnadir.setOnClickListener(v -> {
                Intent intent = new Intent();
                intent.putExtra("nombre", binding.nombre.getText().toString());
                intent.putExtra("estado", binding.nombre.getText().toString());
                intent.putExtra("fecha de entrega", binding.nombre.getText().toString());
                intent.putExtra("descripcion", binding.nombre.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DataInsert.this, MainActivity.class));
    }
}
