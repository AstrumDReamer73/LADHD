package com.example.ladhd;
import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;
public class RepositorioTareas {
    private TareaDAO tareaDao;
    private LiveData<List<Tarea>> listaTareas;
    public RepositorioTareas(Application application){
        BasedeDatos dbnotas= BasedeDatos.getInstance(application);
        tareaDao = dbnotas.tareaDao();
        listaTareas= tareaDao.obtenerTodas();
    }
    public void insertarTarea(Tarea tarea) { new InsertarTarea(tareaDao).execute(tarea); }
    public void modificarTarea(Tarea tarea) { new ModificarTarea(tareaDao).execute(tarea); }
    public void eliminarTarea(Tarea tarea) { new EliminarTarea(tareaDao).execute(tarea); }
    public LiveData<List<Tarea>> obtenerTodas() { return listaTareas; }
    private static class InsertarTarea extends AsyncTask<Tarea, Void, Void> {
        private TareaDAO tareaDao;
        public InsertarTarea(TareaDAO tareaDao) { this.tareaDao = tareaDao; }
        @Override protected Void doInBackground(Tarea... tareas) {
            tareaDao.insertar(tareas[0]);
            return null;
        }
    }
    private static class EliminarTarea extends AsyncTask<Tarea, Void, Void> {
        private TareaDAO tareaDao;
        public EliminarTarea(TareaDAO tareaDao) { this.tareaDao = tareaDao; }
        @Override protected Void doInBackground(Tarea... tareas) {
            tareaDao.eliminar(tareas[0]);
            return null;
        }
    }
    private static class ModificarTarea extends AsyncTask<Tarea, Void, Void> {
        private TareaDAO tareaDao;
        public ModificarTarea(TareaDAO tareaDao) { this.tareaDao = tareaDao; }
        @Override protected Void doInBackground(Tarea... tareas) {
            tareaDao.modificar(tareas[0]);
            return null;
        }
    }
}