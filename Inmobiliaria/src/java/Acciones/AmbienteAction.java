/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Controlador.ControladorAmbiente;
import Persistencia.Modelo.Ambiente;
import Soporte.Mensaje;
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
public class AmbienteAction extends ActionSupport implements ModelDriven<Ambiente> {

    private Ambiente ambiente = new Ambiente();
    private List<Ambiente> ambienteLista = new ArrayList<Ambiente>();
    private final ControladorAmbiente controladorAmbiente = new ControladorAmbiente();
    private final Map<String, Object> sesion = ActionContext.getContext().getSession();

    @Override
    public Ambiente getModel() {
        return ambiente;
    }

    private boolean validar() {
        boolean flag = true;
        if (ambiente.getNombre().trim().isEmpty()) {
            addFieldError("ambiente.nombre", Mensaje.ingreseNombre);
            flag = false;
        } else {
            if (controladorAmbiente.existe(ambiente)) {
                addFieldError("ambiente.nombre", Mensaje.getElExiste(Mensaje.ambiente));
                flag = false;
            }
        }
        return flag;
    }

    public String guardarOModificar() {
        if (!validar()) {
            return INPUT;
        }
        if (ambiente.getIdAmbiente() != 0) {
            controladorAmbiente.actualizar(ambiente);
            sesion.put("mensaje", Mensaje.getModificado(Mensaje.ambiente));
        } else {
            controladorAmbiente.guardar(ambiente);;
            sesion.put("mensaje", Mensaje.getAgregado(Mensaje.ambiente));
        }
        return SUCCESS;
    }

    public String list() {
        ambienteLista = controladorAmbiente.getTodos();
        String mensaje = (String) sesion.get("mensaje");
        addActionMessage(mensaje);
        String alerta = (String) sesion.get("alerta");
        addActionError(alerta);
        sesion.put("mensaje", "");
        sesion.put("alerta", "");
        return SUCCESS;
    }

    public String eliminar() {

        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        int id = Integer.parseInt(request.getParameter("idAmbiente"));
        if (controladorAmbiente.ambienteEnUso(id)) {
            sesion.put("alerta", Mensaje.getUsado(Mensaje.ambiente, Mensaje.propiedad));
        } else {
            controladorAmbiente.eliminar(id);
            sesion.put("mensaje", Mensaje.getEliminado(Mensaje.ambiente));
        }
        return SUCCESS;
    }

    public String editar() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        ambiente = controladorAmbiente.getOne(Integer.parseInt(request.getParameter("idAmbiente")));
        return SUCCESS;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public List<Ambiente> getAmbienteLista() {
        return ambienteLista;
    }

    public void setAmbienteLista(List<Ambiente> ambienteLista) {
        this.ambienteLista = ambienteLista;
    }

}
