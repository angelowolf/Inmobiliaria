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
public class ImagenPropiedad {

    private int idImagenPropiedad;
    private String ruta;

    public ImagenPropiedad() {
    }

    public ImagenPropiedad(int idImagenPropiedad, String ruta) {
        this.idImagenPropiedad = idImagenPropiedad;
        this.ruta = ruta;
    }

    public int getIdImagenPropiedad() {
        return idImagenPropiedad;
    }

    public void setIdImagenPropiedad(int idImagenPropiedad) {
        this.idImagenPropiedad = idImagenPropiedad;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "ImagenPropiedad{" + "idImagenPropiedad=" + idImagenPropiedad + ", ruta=" + ruta + '}';
    }

}
