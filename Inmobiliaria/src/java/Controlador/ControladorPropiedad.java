/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Persistencia.DAO.Implementacion.ImagenPropiedadDAO;
import Persistencia.DAO.Implementacion.PropiedadDAO;
import Persistencia.Modelo.ImagenPropiedad;
import Persistencia.Modelo.Propiedad;
import Soporte.Archivo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.WordUtils;

/**
 *
 * @author Angelo
 */
public class ControladorPropiedad {

    private final PropiedadDAO propiedadDAO;

    public ControladorPropiedad() {
        propiedadDAO = new PropiedadDAO();
    }

    public int guardar(Propiedad o) {
        o.setDireccion(WordUtils.capitalize(o.getDireccion()));
        return propiedadDAO.guardar(o);
    }

    public void actualizar(Propiedad o) {
        o.setDireccion(WordUtils.capitalize(o.getDireccion()));
        propiedadDAO.actualizar(o);
    }

    public void eliminar(Propiedad o) {
        propiedadDAO.eliminar(o);
    }

    public List<Propiedad> getTodos() {
        return propiedadDAO.listar();
    }

    public void eliminar(int id) {
        Propiedad m = new Propiedad();
        ControladorImagenPropiedad cip = new ControladorImagenPropiedad();
        cip.eliminarTodos(id);
        m.setIdPropiedad(id);
        propiedadDAO.eliminar(m);
    }

    public void eliminar(int id, String ruta) {
        Propiedad m = new Propiedad();
        ControladorImagenPropiedad cip = new ControladorImagenPropiedad();
        cip.eliminarTodos(id);
        m.setIdPropiedad(id);
        propiedadDAO.eliminar(m);
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

    public Propiedad getOne(int id) {
        return propiedadDAO.buscar(id);
    }

    public List<Propiedad> getOportunidades() {
        return propiedadDAO.listarOportunidades();
    }

    public List<Propiedad> buscar(int idTipoPropiedad, int habitacion, int bano) {
        return propiedadDAO.buscar(idTipoPropiedad, habitacion, bano);
    }

    public boolean propiedadEnUso(int id) {
        return !propiedadDAO.propiedadEnUso(id).isEmpty();
    }
}
