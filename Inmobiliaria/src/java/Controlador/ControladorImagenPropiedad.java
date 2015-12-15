/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Persistencia.DAO.Implementacion.ImagenPropiedadDAO;
import Persistencia.Modelo.ImagenPropiedad;
import java.io.File;
import java.io.IOException;
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

    public ImagenPropiedad getOne(int id) {
        return imagenPropiedadDAO.buscar(id);
    }

    public void eliminarImagen(String ruta) {
        File archivo = new File(ruta);
        try {
            archivo.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void renombrarCarpeta(String rutaOriginal, String rutaNueva) {
        // File (or directory) with old name
        File file = new File(rutaOriginal);

// File (or directory) with new name
        File file2 = new File(rutaNueva);

// Rename file (or directory)
        file.renameTo(file2);
    }
}
