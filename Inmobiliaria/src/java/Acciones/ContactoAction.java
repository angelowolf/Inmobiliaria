/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Controlador.ControladorContacto;
import Persistencia.Modelo.Contacto;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 *
 * @author Angelo
 */
public class ContactoAction extends ActionSupport implements ModelDriven<Contacto> {

    private Contacto contacto = new Contacto();
    private final ControladorContacto controladorContacto = new ControladorContacto();

    @Override
    public Contacto getModel() {
        return contacto;
    }

    public String guardarOModificar() {
        System.out.println(contacto.toString());
        if (contacto.getIdContacto() != 0) {
            controladorContacto.actualizar(contacto);
            addActionMessage("Contacto Modificado.");
        } else {
            controladorContacto.guardar(contacto);
            addActionMessage("Contacto Agregado.");
        }
        return SUCCESS;

    }

    public String loadContacto() {
        contacto = controladorContacto.getOne(1);
        return SUCCESS;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

}