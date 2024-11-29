package com.example.ladhd;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ladhd.databinding.TareaBinding;

import java.util.Locale;

public class ListaTareas extends ListAdapter<Tarea, ListaTareas.viewHolder> {
    private final Context context;
    private TextToSpeech textToSpeech;
    private boolean isTextToSpeechReady = false;

    public ListaTareas(Context context) {
        super(CALLBACK);
        this.context = context;

        // Inicializar TextToSpeech
        textToSpeech = new TextToSpeech(context, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(Locale.getDefault());
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(context, "El idioma no es compatible para TTS", Toast.LENGTH_SHORT).show();
                } else {
                    isTextToSpeechReady = true; // TTS está listo para usarse
                }
            } else {
                Toast.makeText(context, "Error al inicializar TTS", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static final DiffUtil.ItemCallback<Tarea> CALLBACK = new DiffUtil.ItemCallback<Tarea>() {
        @Override
        public boolean areItemsTheSame(@NonNull Tarea oldItem, @NonNull Tarea newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Tarea oldItem, @NonNull Tarea newItem) {
            return oldItem.getNombre().equals(newItem.getNombre())
                    && oldItem.getDescripcion().equals(newItem.getDescripcion())
                    && oldItem.getEstado().equals(newItem.getEstado())
                    && oldItem.getFechaEntrega().equals(newItem.getFechaEntrega());
        }
    };

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TareaBinding binding = TareaBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Tarea tarea = getTarea(position);
        holder.binding.nombre.setText(tarea.getNombre());
        holder.binding.estado.setText(tarea.getEstado());
        holder.binding.fechaEntrega.setText(tarea.getFechaEntrega());
        holder.binding.descripcion.setText(tarea.getDescripcion());

        // Configuramos el botón de TTS
        holder.binding.BTDictar.setOnClickListener(v -> {
            if (isTextToSpeechReady) {
                String textToSpeak = "Tarea: " + tarea.getNombre()
                        + ", Estado: " + tarea.getEstado()
                        + ", Fecha de entrega: " + tarea.getFechaEntrega()
                        + ", Descripción: " + tarea.getDescripcion();
                textToSpeech.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null, null);
            } else {
                Toast.makeText(context, "Text-to-Speech no está listo aún", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Tarea getTarea(int position) {
        return getItem(position);
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TareaBinding binding;

        public viewHolder(@NonNull TareaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    // Llamar al método de limpieza del TTS
    public void shutdownTTS() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
