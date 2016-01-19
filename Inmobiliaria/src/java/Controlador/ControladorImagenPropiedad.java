/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Persistencia.DAO.Implementacion.ImagenPropiedadDAO;
import Persistencia.Modelo.ImagenPropiedad;
import java.io.File;
import java.util.List;

/**
 *
 * @author Angelo
 */
public class ControladorImagenPropiedad {

    private final ImagenPropiedadDAO imagenPropiedadDAO;

    public ControladorImagenPropiedad() {
        imagenPropiedadDAO = new ImagenPropiedadDAO();
    }

    /**
     * Guarda una Imagen.
     *
     * @param o La imagen.
     */
    public void guardar(ImagenPropiedad o) {
        imagenPropiedadDAO.guardar(o);
    }

    /**
     * Actualzia una imagen.
     *
     * @param o La imagen.
     */
    public void actualizar(ImagenPropiedad o) {
        imagenPropiedadDAO.actualizar(o);
    }

    /**
     * Elimina una imagen.
     *
     * @param o La imagen.
     */
    public void eliminar(ImagenPropiedad o) {
        imagenPropiedadDAO.eliminar(o);
    }

    /**
     * Elimina todas las imagenes de una propiedad.
     *
     * @param id El id de la propiedad.
     */
    public void eliminarTodos(int id) {
        imagenPropiedadDAO.eliminarTodos(id);
    }

    /**
     * Todas las imagenes que existen.
     *
     * @return Las imagenes.
     */
    public List<ImagenPropiedad> getTodos() {
        return imagenPropiedadDAO.listar();
    }

    /**
     * Todas las imagenes de una propiedad.
     *
     * @param idPropiedad EL id de la propiedad.
     * @return Las imagenes.
     */
    public List<ImagenPropiedad> getTodos(int idPropiedad) {
        return imagenPropiedadDAO.listar(idPropiedad);
    }

    /**
     * Busca una imagen por el id.
     *
     * @param id El id de la imagen.
     * @return La imagen.
     */
    public ImagenPropiedad getOne(int id) {
        return imagenPropiedadDAO.buscar(id);
    }

}
