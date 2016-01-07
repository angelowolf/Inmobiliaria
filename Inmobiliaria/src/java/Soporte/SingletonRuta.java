/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Soporte;

/**
 *
 * @author angelo
 */
public class SingletonRuta {

    private static SingletonRuta sr;
    private static final String STORAGE_PATH = System.getenv("OPENSHIFT_DATA_DIR") == null ? "D:/imagenes/tmp/" : System.getenv("OPENSHIFT_DATA_DIR");

    public static SingletonRuta getInstancia() {
        if (sr == null) {
            sr = new SingletonRuta();
        }
        return sr;
    }

    private SingletonRuta() {

    }

    public String getSTORAGE_PATH() {
        return STORAGE_PATH;
    }

}
