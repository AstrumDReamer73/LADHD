package com.example.ladhd;
import androidx.lifecycle.LiveData;
import androidx.room.*;
import java.util.List;
@Dao public interface TareaDAO {
    @Insert public void insertar(Tarea tarea);
    @Update public void modificar(Tarea tarea);
    @Delete public void eliminar(Tarea tarea);
    @Query(value = "SELECT * FROM tarea")public LiveData<List<Tarea>> obtenerTodas();
}
