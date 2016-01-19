/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Soporte;

import Acciones.PropiedadAction;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author angelo
 */
public class Archivo {

    /**
     * Renombra una carpeta o un archivo.
     *
     * @param rutaOriginal Nombre original.
     * @param rutaNueva Nuevo nombre.
     */
    public static void renombrarCarpeta(String rutaOriginal, String rutaNueva) {
        // File (or directory) with old name
        File file = new File(rutaOriginal);
        // File (or directory) with new name
        File file2 = new File(rutaNueva);
        // Rename file (or directory)
        file.renameTo(file2);
    }

    /**
     * Crea un archivo en el disco del sistema, y devuelve la ruta del archivo
     * para poder ser guardaro en la base de datos.
     *
     * @param ruta La ruta del archivo donde sera creado.
     * @param upload EL archivo.
     * @param uploadFileName El nombre del archivo.
     * @return La ruta para la BD.
     */
    public static String crearImagen(String ruta, File upload, String uploadFileName) {
        File directorio = new File(ruta);
        String rutaBD = ruta + "/" + uploadFileName;
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        try {
            FileUtils.copyFile(upload, new File(directorio, uploadFileName));
        } catch (IOException ex) {
            Logger.getLogger(PropiedadAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rutaBD;
    }

    /**
     * Elimina todos los archivos que contenga una carpeta, o el archivo en si.
     *
     * @param ruta La ruta de una carpeta o un archivo.
     */
    public static void delete(String ruta) {
        try {
            File f = new File(ruta);
            delete(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina todos los archivos que contenga una carpeta, o el archivo en si.
     *
     * @param file Una carpeta o un archivo.
     */
    public static void delete(File file) {
        try {
            if (file.exists()) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
