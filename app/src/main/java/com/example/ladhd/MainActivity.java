package com.example.ladhd;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.*;
import com.example.ladhd.databinding.MainActivityBinding;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    MainActivityBinding binding;
    private TareaViewModel tareaViewModel;
    @Override protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding=MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tareaViewModel=new ViewModelProvider(this,(ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TareaViewModel.class);
        binding.BTAnadirTarea.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DataInsert.class);
                intent.putExtra("tipo","añadir");
                startActivityForResult(intent,1);
            }
        });
        binding.listaTareas.setLayoutManager(new LinearLayoutManager(this));
        binding.listaTareas.setHasFixedSize(true);
        ListaTareas listaTareas=new ListaTareas(this);
        binding.listaTareas.setAdapter(listaTareas);
        tareaViewModel.getAllTareas().observe(this,new Observer<List<Tarea>>(){
            @Override public void onChanged(List<Tarea> tareas) {listaTareas.submitList(tareas);}
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {return false;}
            @Override public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.RIGHT) {
                    Toast.makeText(MainActivity.this, "eliminar", Toast.LENGTH_SHORT).show();
                    tareaViewModel.eliminarTarea(listaTareas.getTarea(viewHolder.getAdapterPosition()));
                } else {
                    Intent intent = new Intent(MainActivity.this, DataInsert.class);
                    intent.putExtra("tipo", "modificar");
                    Tarea tarea = listaTareas.getTarea(viewHolder.getAdapterPosition());
                    intent.putExtra("id", tarea.getId());
                    intent.putExtra("nombre", tarea.getNombre());
                    intent.putExtra("fechaEntrega", tarea.getFechaEntrega());
                    intent.putExtra("estado", tarea.getEstado());
                    intent.putExtra("descripcion", tarea.getDescripcion());
                    startActivityForResult(intent, 2);
                }
            }
        }).attachToRecyclerView(binding.listaTareas);
    }
    @Override protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) { // Añadir tarea
            String nombre = data.getStringExtra("nombre");
            String estado = data.getStringExtra("estado");
            String fechaEntrega = data.getStringExtra("fechaEntrega");
            String descripcion = data.getStringExtra("descripcion");
            Tarea tarea = new Tarea(nombre, estado, fechaEntrega, descripcion);
            tareaViewModel.insertarTarea(tarea);
            Toast.makeText(this, "tarea añadida", Toast.LENGTH_LONG).show();
        } else if (requestCode == 2) { // Modificar tarea
            int id = data.getIntExtra("id", -1); // Obtener el ID
            if (id != -1) {
                String nombre = data.getStringExtra("nombre");
                String estado = data.getStringExtra("estado");
                String fechaEntrega = data.getStringExtra("fechaEntrega");
                String descripcion = data.getStringExtra("descripcion");
                Tarea tarea = new Tarea(id, nombre, estado, fechaEntrega, descripcion);
                tareaViewModel.modificarTarea(tarea);
                Toast.makeText(this, "tarea modificada", Toast.LENGTH_LONG).show();
            } else {Toast.makeText(this, "Error al modificar tarea: ID no válido", Toast.LENGTH_SHORT).show();}
        }
    }
}
//git remote add origin https://github.com/AstrumDReamer73/LADHD.git
