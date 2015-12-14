/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Persistencia.DAO.Implementacion.ImagenPropiedadDAO;
import Persistencia.Modelo.ImagenPropiedad;
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

    public void guardar(ImagenPropiedad o) {
        imagenPropiedadDAO.guardar(o);
    }

    public void actualizar(ImagenPropiedad o) {
        imagenPropiedadDAO.actualizar(o);
    }

    public void eliminar(ImagenPropiedad o) {
        imagenPropiedadDAO.eliminar(o);
    }

    public void eliminarTodos(int id) {
        imagenPropiedadDAO.eliminarTodos(id);
    }

    public List<ImagenPropiedad> getTodos() {
        return imagenPropiedadDAO.listar();
    }

    public List<ImagenPropiedad> getTodos(int idPropiedad) {
        return imagenPropiedadDAO.listar(idPropiedad);
    }
}
