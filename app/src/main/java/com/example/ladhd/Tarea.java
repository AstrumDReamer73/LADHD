package com.example.ladhd;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="tarea")
public class Tarea {
    @PrimaryKey(autoGenerate = true) private int id;
    private String nombre;
    private String fechaEntrega;
    private String estado;
    private String descripcion;

    public Tarea( String nombre, String fechaEntrega, String estado, String descripcion) {
        this.nombre = nombre;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

