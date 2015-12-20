/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author Angelo
 */
public class Asd extends ActionSupport {

    private String nombre;

    @Override
    public String execute() {
        System.out.println(nombre);
        return SUCCESS;
    }   

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
