/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Modelo;

/**
 *
 * @author Angelo
 */
public class Destacado {

    private int idDestacado;
    private String nombre;
    private Propiedad propiedad;
    private Imagen imagen;

    public int getIdDestacado() {
        return idDestacado;
    }

    public void setIdDestacado(int idDestacado) {
        this.idDestacado = idDestacado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

}
