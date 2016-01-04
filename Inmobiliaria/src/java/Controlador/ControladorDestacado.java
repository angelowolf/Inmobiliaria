/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Persistencia.DAO.Implementacion.DestacadoDAO;
import Persistencia.Modelo.Destacado;
import Soporte.Archivo;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.lang.WordUtils;

/**
 *
 * @author Angelo
 */
public class ControladorDestacado {

    private final DestacadoDAO destacadoDAO;

    public ControladorDestacado() {
        destacadoDAO = new DestacadoDAO();
    }

    public int guardar(Destacado o) {
        o.setNombre(WordUtils.capitalize(o.getNombre()));
        return destacadoDAO.guardar(o);
    }

    public void actualizar(Destacado o) {
        o.setNombre(WordUtils.capitalize(o.getNombre()));
        destacadoDAO.actualizar(o);
    }

    public void eliminar(Destacado o) {
        destacadoDAO.eliminar(o);
    }

    public List<Destacado> getTodos() {
        return destacadoDAO.listar();
    }

    public void eliminar(int id) {
        Destacado m = new Destacado();
        m.setIdDestacado(id);
        destacadoDAO.eliminar(m);
    }

    public Destacado getOne(int id) {
        return destacadoDAO.buscar(id);
    }

    public void eliminar(Destacado d, String ruta) {
        ControladorImagen ci = new ControladorImagen();
        int idImagen = d.getImagen().getIdImagen();
        destacadoDAO.eliminar(d);
        ci.eliminar(idImagen);
        //Eliminar archivos.
        File directory = new File(ruta);
        if (directory.exists()) {
            try {
                Archivo.delete(directory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
