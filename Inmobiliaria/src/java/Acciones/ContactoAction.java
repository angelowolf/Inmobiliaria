/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Controlador.ControladorContacto;
import Persistencia.Modelo.Contacto;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.Map;

/**
 *
 * @author Angelo
 */
public class ContactoAction extends ActionSupport implements ModelDriven<Contacto> {

    private Contacto contacto = new Contacto();
    private final ControladorContacto controladorContacto = new ControladorContacto();
    private final Map<String, Object> application = ActionContext.getContext().getApplication();
    private int marker;

    @Override
    public Contacto getModel() {
        return contacto;
    }

    public String guardarOModificar() {
        if (contacto.getIdContacto() != 0) {
            controladorContacto.actualizar(contacto);
            addActionMessage("Contacto Modificado.");
        } else {
            controladorContacto.guardar(contacto);
            addActionMessage("Contacto Agregado.");
        }
        application.put("contacto", contacto);
        return SUCCESS;

    }

    public String cargar() {
        Contacto c = (Contacto) application.get("contacto");
        if (c == null) {
            c = controladorContacto.getOne();
            application.put("contacto", c);
        }
        return SUCCESS;
    }

    public String loadContacto() {
        contacto = controladorContacto.getOne();
        return SUCCESS;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public int getMarker() {
        return marker;
    }

    public void setMarker(int marker) {
        this.marker = marker;
    }
}
