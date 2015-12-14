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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Angelo
 */
public class ControladorPropiedad {

    private final PropiedadDAO propiedadDAO;

    public ControladorPropiedad() {
        propiedadDAO = new PropiedadDAO();
    }

    public void guardar(Propiedad o) {
        o.setNombre(Soporte.mayusculaPrimeraLetra(o.getNombre()));
        propiedadDAO.guardar(o);
    }

    public void actualizar(Propiedad o) {
        o.setNombre(Soporte.mayusculaPrimeraLetra(o.getNombre()));
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
        System.out.println(ruta);
        if (directory.exists()) {
            try {
                delete(directory);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    public Propiedad getOne(int id) {
        return propiedadDAO.buscar(id);
    }

    private static void delete(File file)
            throws IOException {

        if (file.isDirectory()) {

            //directory is empty, then delete it
            if (file.list().length == 0) {
                file.delete();
            } else {

                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    delete(fileDelete);
                }

                //check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();
                }
            }

        } else {
            //if file, then delete it
            file.delete();
        }
    }
}
