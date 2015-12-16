/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Controlador.ControladorPropiedad;
import Persistencia.Modelo.Propiedad;
import Persistencia.Modelo.Servicio;
import Soporte.ServicioDoble;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
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
    private List<Servicio> serviciosUno = new ArrayList<>();
    private List<Servicio> serviciosDos = new ArrayList<>();

    @Override
    public String execute() {
//        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
//        int id = Integer.parseInt(request.getParameter("idPropiedad"));
        propiedad = controladorPropiedad.getOne(idPropiedad);
        List<Servicio> todosLosServicios = propiedad.getServicios();
        boolean flag = true;
        for (Servicio caradaServicio : todosLosServicios) {
            if (flag) {
                serviciosUno.add(caradaServicio);
                flag = false;
            } else {
                serviciosDos.add(caradaServicio);
                flag = true;
            }
        }
        return SUCCESS;
    }

    public List<Servicio> getServiciosUno() {
        return serviciosUno;
    }

    public List<Servicio> getServiciosDos() {
        return serviciosDos;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setIdPropiedad(int idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

}
