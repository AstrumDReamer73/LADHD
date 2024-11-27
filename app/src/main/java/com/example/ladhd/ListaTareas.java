package com.example.ladhd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ladhd.databinding.ListatareasBinding;

public class ListaTareas extends ListAdapter<Tarea, ListaTareas.viewHolder> {
    public ListaTareas(){super(CALLBACK);}
    private static final DiffUtil.ItemCallback<Tarea> CALLBACK=new DiffUtil.ItemCallback<Tarea>() {
        @Override public boolean areItemsTheSame(@NonNull Tarea oldItem, @NonNull Tarea newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override public boolean areContentsTheSame(@NonNull Tarea oldItem, @NonNull Tarea newItem) {
            return oldItem.getNombre().equals(newItem.getNombre()) && oldItem.getDescripcion().equals(newItem.getDescripcion());
        }
    };

    @NonNull @Override public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listatareas,parent,false);
        return new RecyclerView.ViewHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Tarea tarea = getTarea(position);
        holder.binding.nombre.setText(tarea.getNombre());
        holder.binding.fechaEntrega.setText(tarea.getDescripcion());

        holder.binding.PorcentajeProgreso.setText(tarea.getDescripcion());

    }

    public Tarea getTarea(int position){return  getItem(position);}

    public class viewHolder extends RecyclerView.ViewHolder{
        ListatareasBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ListatareasBinding.bind(itemView);
        }
    }
}
