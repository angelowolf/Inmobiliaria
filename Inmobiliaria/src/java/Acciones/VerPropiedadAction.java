/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Controlador.ControladorPropiedad;
import Persistencia.Modelo.Propiedad;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Angelo
 */
public class VerPropiedadAction extends ActionSupport {

    private Propiedad propiedad;
    private final ControladorPropiedad controladorPropiedad = new ControladorPropiedad();
    private int idPropiedad;

    @Override
    public String execute() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        int id = Integer.parseInt(request.getParameter("idPropiedad"));
        propiedad = controladorPropiedad.getOne(id);
        return SUCCESS;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setIdPropiedad(int idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

}
