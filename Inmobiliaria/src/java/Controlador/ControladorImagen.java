/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Persistencia.DAO.Implementacion.ImagenDAO;
import Persistencia.Modelo.Imagen;
import java.util.List;

/**
 *
 * @author Angelo
 */
public class ControladorImagen {

    private final ImagenDAO imagenDAO;

    public ControladorImagen() {
        imagenDAO = new ImagenDAO();
    }

    public void guardar(Imagen o) {
        imagenDAO.guardar(o);
    }

    public void actualizar(Imagen o) {
        imagenDAO.actualizar(o);
    }

    public void eliminar(Imagen o) {
        imagenDAO.eliminar(o);
    }

    public void eliminar(int id) {
        Imagen m = new Imagen();
        m.setIdImagen(id);
        imagenDAO.eliminar(m);
    }

    public Imagen getOne(int id) {
        return imagenDAO.buscar(id);
    }

}
