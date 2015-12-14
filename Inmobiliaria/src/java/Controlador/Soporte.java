/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author Angelo
 */
public class Soporte {

    public static String mayusculaPrimeraLetra(String s) {
        String output = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
        return output;
    }
}
