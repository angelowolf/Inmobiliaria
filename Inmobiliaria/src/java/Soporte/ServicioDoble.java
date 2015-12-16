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
public class ServicioDoble {

    private String nombre1, nombre2;
    private boolean nombre2Existe;

    public ServicioDoble() {
    }

    public ServicioDoble(String nombre1, String nombre2, boolean nombre2Existe) {
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.nombre2Existe = nombre2Existe;
    }

    public boolean isNombre2Existe() {
        return nombre2Existe;
    }

    public void setNombre2Existe(boolean nombre2Existe) {
        this.nombre2Existe = nombre2Existe;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

}
