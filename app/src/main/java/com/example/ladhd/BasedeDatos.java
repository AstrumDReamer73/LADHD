package com.example.ladhd;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = Tarea.class, version = 1)
abstract class BasedeDatos extends RoomDatabase {
    private static BasedeDatos instance;
    public abstract TareaDAO tareaDao();
    public static synchronized BasedeDatos getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), BasedeDatos.class, "base de datos de notas")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}