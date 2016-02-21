/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Controlador.ControladorServicio;
import Persistencia.Modelo.Servicio;
import Soporte.Mensaje;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Angelo
 */
public class ServicioAction extends ActionSupport implements ModelDriven<Servicio> {

    private Servicio servicio = new Servicio();
    private List<Servicio> servicioLista = new ArrayList<Servicio>();
    private final Controlador.ControladorServicio controladorServicio = new ControladorServicio();
    private final Map<String, Object> sesion = ActionContext.getContext().getSession();
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Servicio getModel() {
        return servicio;
    }

    private boolean validar() {
        boolean flag = true;
        if (servicio.getNombre().trim().isEmpty()) {
            addFieldError("servicio.nombre", Mensaje.ingreseNombre);
            flag = false;

        } else if (controladorServicio.existe(servicio)) {
            addFieldError("servicio.nombre", Mensaje.getElExiste(Mensaje.servicio));
            flag = false;
        }

        return flag;
    }

    public String guardarOModificar() {
        if (!validar()) {
            return INPUT;
        }
        if (servicio.getIdServicio() != 0) {
            controladorServicio.actualizar(servicio);
            sesion.put("mensaje", Mensaje.getModificado(Mensaje.servicio));
        } else {
            controladorServicio.guardar(servicio);;
            sesion.put("mensaje", Mensaje.getAgregado(Mensaje.servicio));
        }
        return SUCCESS;
    }

    public String list() {
        servicioLista = controladorServicio.getTodos();
        String mensaje = (String) sesion.get("mensaje");
        addActionMessage(mensaje);
        String alerta = (String) sesion.get("alerta");
        addActionError(alerta);
        sesion.put("mensaje", "");
        sesion.put("alerta", "");
        return SUCCESS;
    }

    public String eliminar() {

//        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
//        int id = Integer.parseInt(request.getParameter("id"));
        if (controladorServicio.servicioEnUso(id)) {
            sesion.put("alerta", Mensaje.getUsado(Mensaje.servicio, Mensaje.propiedad));
        } else {
            controladorServicio.eliminar(id);
            sesion.put("mensaje", Mensaje.getEliminado(Mensaje.servicio));
        }
        return SUCCESS;
    }

    public String editar() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        servicio = controladorServicio.getOne(Integer.parseInt(request.getParameter("idServicio")));
        return SUCCESS;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public List<Servicio> getServicioLista() {
        return servicioLista;
    }

    public void setServicioLista(List<Servicio> servicioLista) {
        this.servicioLista = servicioLista;
    }

}
