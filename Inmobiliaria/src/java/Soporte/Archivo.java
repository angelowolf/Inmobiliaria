/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Soporte;

import Controlador.ControladorImagenPropiedad;
import Persistencia.Modelo.Propiedad;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author angelo
 */
public class Archivo {

    public static void renombrarCarpeta(String rutaOriginal, String rutaNueva) {
        // File (or directory) with old name
        File file = new File(rutaOriginal);
        System.out.println("1");
        // File (or directory) with new name
        File file2 = new File(rutaNueva);
        System.out.println("2");
        // Rename file (or directory)
        file.renameTo(file2);
        System.out.println("3");
    }

    public static void delete(File file)
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
