package com.example.ladhd;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ladhd.databinding.TareaBinding;

public class ListaTareas extends ListAdapter<Tarea, ListaTareas.viewHolder> {
    public ListaTareas() {
        super(CALLBACK);
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

    @NonNull @Override public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TareaBinding binding = TareaBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewHolder(binding);
    }

    @Override public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Tarea tarea = getTarea(position);
        holder.binding.nombre.setText(tarea.getNombre());
        holder.binding.estado.setText(tarea.getEstado());
        holder.binding.fechaEntrega.setText(tarea.getFechaEntrega());
        holder.binding.descripcion.setText(tarea.getDescripcion());
    }

    public Tarea getTarea(int position) {return getItem(position);}

    public static class viewHolder extends RecyclerView.ViewHolder {
        TareaBinding binding;
        public viewHolder(@NonNull TareaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
