package com.example.ladhd;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName="tarea")
public class Tarea {
    @PrimaryKey(autoGenerate = true) private int id;
    private String nombre;
    private String estado;
    private String fechaEntrega;
    private String descripcion;
    public Tarea(int id, String nombre, String estado,String fechaEntrega, String descripcion) {
        this.id = id;
        this.nombre = nombre != null ? nombre : "";
        this.descripcion = descripcion != null ? descripcion : "";
        this.estado = estado != null ? estado : "";
        this.fechaEntrega = fechaEntrega != null ? fechaEntrega : "";
    }
    @Ignore public Tarea(){}
    @Ignore public Tarea(String nombre, String descripcion, String estado, String fechaEntrega) {
        this.nombre = nombre != null ? nombre : "";
        this.descripcion = descripcion != null ? descripcion : "";
        this.estado = estado != null ? estado : "";
        this.fechaEntrega = fechaEntrega != null ? fechaEntrega : "";
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getFechaEntrega() {return fechaEntrega;}
    public void setFechaEntrega(String fechaEntrega) {this.fechaEntrega = fechaEntrega;}

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}

    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
}

