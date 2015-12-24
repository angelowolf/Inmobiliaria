/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Controlador.ControladorDestacado;
import Persistencia.Modelo.Destacado;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Angelo
 */
public class IndexAction extends ActionSupport {

    private List<Destacado> destacados = new ArrayList<Destacado>();
    private final ControladorDestacado cd = new ControladorDestacado();

    @Override
    public String execute() {
        destacados = cd.getTodos();
        return SUCCESS;
    }

    public List<Destacado> getDestacados() {
        return destacados;
    }

}
