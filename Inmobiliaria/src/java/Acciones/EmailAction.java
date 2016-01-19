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
import Soporte.Mensaje;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Angelo
 */
public class EmailAction extends ActionSupport implements ModelDriven<Propiedad> {

    private String nombre, email, telefono, consulta;
    private Propiedad propiedad;
    private final Map<String, Object> application = ActionContext.getContext().getApplication();

    private boolean validar() {
        boolean flag = true;
        if (StringUtils.isBlank(nombre)) {
            flag = false;
            addActionError(Mensaje.ingreseNombre);
        }
        if (StringUtils.isBlank(email)) {
            flag = false;
            addActionError(Mensaje.ingreseMail);
        }
        if (StringUtils.isBlank(telefono)) {
            flag = false;
            addActionError(Mensaje.ingreseTelefono);
        }
        if (StringUtils.isBlank(consulta)) {
            flag = false;
            addActionError(Mensaje.ingreseConsulta);
        }
        return flag;
    }

    @Override
    public String execute() {
        if (validar()) {
            ControladorEmail ce = new ControladorEmail();
            Contacto c = (Contacto) application.get("contacto");
            if (c == null) {
                ControladorContacto cc = new ControladorContacto();
                c = cc.getOne(1);
                application.put("contacto", c);
                addActionError(Mensaje.emailNoEnviado);
            }
            if (c != null) {
                if (propiedad != null) {
                    try {
                        if (StringUtils.isNotBlank(c.getEmail())) {
                            ControladorPropiedad cp = new ControladorPropiedad();
                            Propiedad p = cp.getOne(propiedad.getIdPropiedad());
                            ce.enviarEmail(c.getEmail(), "Consulta propiedad ",
                                    "Nombre: " + nombre
                                    + "\n" + "Email: " + email
                                    + "\n" + "Telefono: " + telefono
                                    + "\n" + "Mensaje: " + consulta
                                    + "\n" + "El codigo de la propiedad consultada es : " + p.getCodigoPropiedad()
                            );
                            addActionMessage(Mensaje.emailEnviado);
                            return NONE;
                        }
                    } catch (NullPointerException e) {
                        System.out.println("error al mandar el email, xq no crearon el contacto...");
                    }
                } else {
                    try {
                        if (StringUtils.isNotBlank(c.getEmail())) {
                            ce.enviarEmail(c.getEmail(), "Consulta propiedad ",
                                    "Nombre: " + nombre
                                    + "\n" + "Email: " + email
                                    + "\n" + "Telefono: " + telefono
                                    + "\n" + "Mensaje: " + consulta);
                            addActionMessage(Mensaje.emailEnviado);
                        }
                    } catch (NullPointerException e) {
                        System.out.println("error al mandar el email, xq no crearon el contacto...");
                        addActionError("No se pudo enviar su consulta.");
                    }
                }
            }
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

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    @Override
    public Propiedad getModel() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }
}
