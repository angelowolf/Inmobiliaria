/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Controlador.ControladorContacto;
import Controlador.ControladorEmail;
import Controlador.ControladorPropiedad;
import Persistencia.Modelo.Contacto;
import Persistencia.Modelo.Propiedad;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

/**
 *
 * @author Angelo
 */
public class EmailAction extends ActionSupport {

    private String nombre, email, telefono, mensaje;
    private int idPropiedad;
    private final Map<String, Object> application = ActionContext.getContext().getApplication();

    @Override
    public String execute() {
        ControladorEmail ce = new ControladorEmail();
        Contacto c = (Contacto) application.get("contacto");
        if (c == null) {
            ControladorContacto cc = new ControladorContacto();
            c = cc.getOne(1);
            application.put("contacto", c);
        }
        if (idPropiedad > 0) {
            ControladorPropiedad cp = new ControladorPropiedad();
            Propiedad p = cp.getOne(idPropiedad);
            ce.metodo(c.getEmail(), "Consulta propiedad ",
                    "Nombre: " + nombre
                    + "\n" + "Email: " + email
                    + "\n" + "Telefono: " + telefono
                    + "\n" + "Mensaje: " + mensaje
                    + "\n El codigo de la propiedad consultada es : " + p.getCodigoPropiedad()
            );
        } else {
            ce.metodo(c.getEmail(), "Consulta propiedad ",
                    "Nombre: " + nombre
                    + "\n" + "Email: " + email
                    + "\n" + "Telefono: " + telefono
                    + "\n" + "Mensaje: " + mensaje);
        }
        return SUCCESS;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setIdPropiedad(int idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

}
