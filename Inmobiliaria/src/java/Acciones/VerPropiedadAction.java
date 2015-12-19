/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Controlador.ControladorPropiedad;
import Persistencia.Modelo.Ambiente;
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
    private List<Servicio> serviciosUno = new ArrayList<Servicio>();
    private List<Servicio> serviciosDos = new ArrayList<Servicio>();
    private List<Ambiente> ambientesUno = new ArrayList<Ambiente>();
    private List<Ambiente> ambientesDos = new ArrayList<Ambiente>();

    @Override
    public String execute() {
//        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
//        int id = Integer.parseInt(request.getParameter("idPropiedad"));
        propiedad = controladorPropiedad.getOne(idPropiedad);
        //obtengo los servicios
        List<Servicio> todosLosServicios = propiedad.getServicios();
        boolean flag = true;
        for (Servicio cadaServicio : todosLosServicios) {
            if (flag) {
                serviciosUno.add(cadaServicio);
                flag = false;
            } else {
                serviciosDos.add(cadaServicio);
                flag = true;
            }
        }
        //obtengo los ambientes
        List<Ambiente> todosLosAmbientes = propiedad.getAmbientes();
        boolean flag2 = true;
        for (Ambiente cadaAmbiente : todosLosAmbientes) {
            if (flag2) {
                ambientesUno.add(cadaAmbiente);
                flag2 = false;
            } else {
                ambientesDos.add(cadaAmbiente);
                flag2 = true;
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

    public List<Ambiente> getAmbientesUno() {
        return ambientesUno;
    }

    public void setAmbientesUno(List<Ambiente> ambientesUno) {
        this.ambientesUno = ambientesUno;
    }

    public List<Ambiente> getAmbientesDos() {
        return ambientesDos;
    }

    public void setAmbientesDos(List<Ambiente> ambientesDos) {
        this.ambientesDos = ambientesDos;
    }

}
