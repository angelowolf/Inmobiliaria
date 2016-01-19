/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Persistencia.DAO.Implementacion.ImagenDAO;
import Persistencia.Modelo.Imagen;

/**
 *
 * @author Angelo
 */
public class ControladorImagen {

    private final ImagenDAO imagenDAO;

    public ControladorImagen() {
        imagenDAO = new ImagenDAO();
    }

    /**
     * Guarda una imagen.
     *
     * @param o La imagen.
     */
    public void guardar(Imagen o) {
        imagenDAO.guardar(o);
    }

    /**
     * Actualiza una imagen.
     *
     * @param o La imagen.
     */
    public void actualizar(Imagen o) {
        imagenDAO.actualizar(o);
    }

    /**
     * Elimina una imagen.
     *
     * @param o La imagen.
     */
    public void eliminar(Imagen o) {
        imagenDAO.eliminar(o);
    }

    /**
     * Elimina una imagen.
     *
     * @param id El id de la imagen.
     */
    public void eliminar(int id) {
        Imagen m = new Imagen();
        m.setIdImagen(id);
        imagenDAO.eliminar(m);
    }

    /**
     * Retorna una imagen.
     *
     * @param id El id de la imagen.
     * @return La imagen.
     */
    public Imagen getOne(int id) {
        return imagenDAO.buscar(id);
    }

}
