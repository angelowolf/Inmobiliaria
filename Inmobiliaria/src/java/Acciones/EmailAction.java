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

    private String mensaje = "";
    private int codigo = 200;

    private boolean validar() {
        boolean flag = true;
        if (StringUtils.isBlank(nombre)) {
            flag = false;
        }
        if (StringUtils.isBlank(email)) {
            flag = false;
        }
        if (StringUtils.isBlank(telefono)) {
            flag = false;
        }
        if (StringUtils.isBlank(consulta)) {
            flag = false;
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
                c = cc.getOne();
                application.put("contacto", c);
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
                            mensaje = Soporte.Mensaje.emailEnviado;
                        }
                    } catch (Exception e) {
                        System.out.println(e.toString());
                        mensaje = Soporte.Mensaje.emailNoEnviado;
                        codigo = 400;
                    }
                } else {
                    try {
                        if (StringUtils.isNotBlank(c.getEmail())) {
                            ce.enviarEmail(c.getEmail(), "Consulta propiedad ",
                                    "Nombre: " + nombre
                                    + "\n" + "Email: " + email
                                    + "\n" + "Telefono: " + telefono
                                    + "\n" + "Mensaje: " + consulta);
                            mensaje = Soporte.Mensaje.emailEnviado;
                        }
                    } catch (Exception e) {
                        System.out.println(e.toString());
                        mensaje = Soporte.Mensaje.emailNoEnviado;
                        codigo = 400;
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

    public String getMensaje() {
        return mensaje;
    }

    public int getCodigo() {
        return codigo;
    }
}
