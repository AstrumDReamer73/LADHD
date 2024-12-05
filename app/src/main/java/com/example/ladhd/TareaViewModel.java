package com.example.ladhd;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
public class TareaViewModel extends AndroidViewModel {
    private RepositorioTareas repositorioTareas;
    private LiveData<List<Tarea>> listaTareas;
    public TareaViewModel(@NonNull Application application) {
        super(application);
        repositorioTareas=new RepositorioTareas(application);
        listaTareas=repositorioTareas.obtenerTodas();
    }
    public void insertarTarea(Tarea tarea){repositorioTareas.insertarTarea(tarea);}
    public void eliminarTarea(Tarea tarea){repositorioTareas.eliminarTarea(tarea);}
    public void modificarTarea(Tarea tarea){repositorioTareas.modificarTarea(tarea);}
    public LiveData<List<Tarea>> getAllTareas(){return listaTareas;}
}
