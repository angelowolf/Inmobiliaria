/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Angelo
 */
public class Propiedad {

    private int idPropiedad;
    private int codigoPropiedad, habitacion;
    private float terreno, edificado;
    private String nombre, detalle;
    private List<Ambiente> ambientes;
    private List<Servicio> servicios;
    private List<ImagenPropiedad> imagenes = new ArrayList<ImagenPropiedad>();
    private ImagenPropiedad imagenDefault;

    public Propiedad() {
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(int habitacion) {
        this.habitacion = habitacion;
    }

    public float getTerreno() {
        return terreno;
    }

    public void setTerreno(float terreno) {
        this.terreno = terreno;
    }

    public float getEdificado() {
        return edificado;
    }

    public void setEdificado(float edificado) {
        this.edificado = edificado;
    }

    public ImagenPropiedad getImagenDefault() {
        return imagenDefault;
    }

    public void setImagenDefault(ImagenPropiedad imagenDefault) {
        this.imagenDefault = imagenDefault;
    }

    public int getIdPropiedad() {
        return idPropiedad;
    }

    public int getCodigoPropiedad() {
        return codigoPropiedad;
    }

    public void setCodigoPropiedad(int codigoPropiedad) {
        this.codigoPropiedad = codigoPropiedad;
    }

    public void setIdPropiedad(int idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public List<Ambiente> getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(List<Ambiente> ambientes) {
        this.ambientes = ambientes;
    }

    public List<ImagenPropiedad> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<ImagenPropiedad> imagenes) {
        this.imagenes = imagenes;
//        imagenDefault = imagenes.get(0);
    }

    public void addServicio(Servicio s) {
        if (servicios != null) {
            this.servicios.add(s);
        } else {
            servicios = new ArrayList<Servicio>();
            this.servicios.add(s);
        }
    }

    public void addAmbiente(Ambiente a) {
        if (ambientes != null) {
            this.ambientes.add(a);
        } else {
            ambientes = new ArrayList<Ambiente>();
            this.ambientes.add(a);
        }
    }

    public void addImagenPropiedad(ImagenPropiedad ip) {
        if (imagenes != null) {
            this.imagenes.add(ip);
        } else {
            imagenes = new ArrayList<ImagenPropiedad>();
            this.imagenes.add(ip);
        }
    }

    @Override
    public String toString() {
        return "Propiedad{" + "idPropiedad=" + idPropiedad + ", codigoPropiedad=" + codigoPropiedad + ", nombre=" + nombre + ", servicios=" + servicios + ", imagenes=" + imagenes + '}';
    }

}
