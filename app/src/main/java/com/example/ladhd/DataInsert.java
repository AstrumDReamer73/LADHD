package com.example.ladhd;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ladhd.databinding.AnadirtareaBinding;

import java.util.ArrayList;

public class DataInsert extends AppCompatActivity {
    AnadirtareaBinding binding;
    private ActivityResultLauncher<Intent> speechLauncher;
    private EditText activeField; // Campo activo

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
                        if (speechResult != null && !speechResult.isEmpty() && activeField != null) {
                            activeField.setText(speechResult.get(0)); // Llenar el campo activo
                        }
                    }
                });

        // Detectar campo activo
        setFieldFocusListeners();

        // Configurar botón para activar reconocimiento de voz
        binding.BTEscuchar.setOnClickListener(v -> startSpeechRecognition());

        // Configurar los botones de añadir/modificar
        String type = getIntent().getStringExtra("tipo");
        if ("modificar".equals(type)) {
            setTitle("Modificar Tarea");
            binding.nombre.setText(getIntent().getStringExtra("nombre"));
            binding.fechaEntrega.setText(getIntent().getStringExtra("fechaEntrega"));
            binding.estado.setText(getIntent().getStringExtra("estado"));
            binding.descripcion.setText(getIntent().getStringExtra("descripcion"));
            int id = getIntent().getIntExtra("id", 0);
            binding.BTAnadir.setText("Modificar Tarea");
            binding.BTAnadir.setOnClickListener(v -> modificarTarea(id));
        } else {
            setTitle("Añadir Tarea");
            binding.BTAnadir.setOnClickListener(v -> insertarTarea());
        }
    }

    private void insertarTarea() {
        String nombre = binding.nombre.getText().toString().trim();
        String descripcion = binding.descripcion.getText().toString().trim();
        String estado = binding.estado.getText().toString().trim();
        String fechaEntrega = binding.fechaEntrega.getText().toString().trim();

        if (nombre.isEmpty() || descripcion.isEmpty()) {
            Toast.makeText(this, "El nombre y la descripción son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Reorganizar datos según el requisito
        String nuevoFechaEntrega = fechaEntrega;
        String nuevoEstado = descripcion;
        String nuevaDescripcion = estado;

        Intent intent = new Intent();
        intent.putExtra("nombre", nombre);
        intent.putExtra("estado", nuevoEstado);
        intent.putExtra("fechaEntrega", nuevoFechaEntrega);
        intent.putExtra("descripcion", nuevaDescripcion);

        setResult(RESULT_OK, intent);
        finish();
    }

    private void modificarTarea(int id) {
        String nombre = binding.nombre.getText().toString().trim();
        String descripcion = binding.descripcion.getText().toString().trim();
        String estado = binding.estado.getText().toString().trim();
        String fechaEntrega = binding.fechaEntrega.getText().toString().trim();

        if (nombre.isEmpty() || descripcion.isEmpty()) {
            Toast.makeText(this, "El nombre y la descripción son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Reorganizar datos según el requisito
        String nuevoFechaEntrega = fechaEntrega;
        String nuevoEstado = descripcion;
        String nuevaDescripcion = estado;

        Intent intent = new Intent();
        intent.putExtra("nombre", nombre);
        intent.putExtra("estado", nuevoEstado);
        intent.putExtra("fechaEntrega", nuevoFechaEntrega);
        intent.putExtra("descripcion", nuevaDescripcion);
        intent.putExtra("id", id);

        setResult(RESULT_OK, intent);
        finish();
    }

    private void setFieldFocusListeners() {
        // Listener para actualizar el campo activo al hacer clic
        View.OnClickListener clickListener = view -> {
            if (view instanceof EditText) {
                activeField = (EditText) view; // Actualizar el campo activo
            }
        };

        // Asignar el listener de clic a cada campo
        binding.nombre.setOnClickListener(clickListener);
        binding.estado.setOnClickListener(clickListener);
        binding.fechaEntrega.setOnClickListener(clickListener);
        binding.descripcion.setOnClickListener(clickListener);

        // Listener adicional para cambios de foco (por seguridad)
        View.OnFocusChangeListener focusChangeListener = (view, hasFocus) -> {
            if (hasFocus && view instanceof EditText) {
                activeField = (EditText) view; // Establecer el campo activo
            }
        };

        // Asignar el listener de foco a cada campo
        binding.nombre.setOnFocusChangeListener(focusChangeListener);
        binding.estado.setOnFocusChangeListener(focusChangeListener);
        binding.fechaEntrega.setOnFocusChangeListener(focusChangeListener);
        binding.descripcion.setOnFocusChangeListener(focusChangeListener);
    }

    private void startSpeechRecognition() {
        if (activeField == null) {
            Toast.makeText(this, "Por favor, seleccione un campo para llenar", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hable ahora...");
        speechLauncher.launch(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DataInsert.this, MainActivity.class));
    }
}
