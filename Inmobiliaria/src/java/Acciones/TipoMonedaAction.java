/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Controlador.ControladorTipoMoneda;
import Persistencia.Modelo.TipoMoneda;
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
public class TipoMonedaAction extends ActionSupport implements ModelDriven<TipoMoneda> {

    private TipoMoneda tipoMoneda = new TipoMoneda();
    private List<TipoMoneda> tipoMonedaLista = new ArrayList<TipoMoneda>();
    private final ControladorTipoMoneda controladorTipoMoneda = new ControladorTipoMoneda();
    private final Map<String, Object> sesion = ActionContext.getContext().getSession();
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public TipoMoneda getModel() {
        return tipoMoneda;
    }

    private boolean validar() {
        boolean flag = true;
        if (tipoMoneda.getNombre().trim().isEmpty()) {
            addFieldError("tipoMoneda.nombre", Mensaje.ingreseNombre);
            flag = false;

        } else if (controladorTipoMoneda.existe(tipoMoneda)) {
            addFieldError("tipoMoneda.nombre", Mensaje.getElExiste(Mensaje.tipoMoneda));
            flag = false;
        }
        if (tipoMoneda.getSigla().trim().isEmpty()) {
            addFieldError("tipoMoneda.simbolo", Mensaje.ingreseSimbolo);
            flag = false;

        }

        return flag;
    }

    public String guardarOModificar() {
        if (!validar()) {
            return INPUT;
        }
        if (tipoMoneda.getIdTipoMoneda() != 0) {
            controladorTipoMoneda.actualizar(tipoMoneda);
            sesion.put("mensaje", Mensaje.getModificado(Mensaje.tipoMoneda));
        } else {
            controladorTipoMoneda.guardar(tipoMoneda);;
            sesion.put("mensaje", Mensaje.getAgregado(Mensaje.tipoMoneda));
        }
        return SUCCESS;
    }

    public String list() {
        tipoMonedaLista = controladorTipoMoneda.getTodos();
        String mensaje = (String) sesion.get("mensaje");
        addActionMessage(mensaje);
        String alerta = (String) sesion.get("alerta");
        addActionError(alerta);
        sesion.put("mensaje", "");
        sesion.put("alerta", "");
        return SUCCESS;
    }

    public String eliminar() {
        if (controladorTipoMoneda.tipoMonedaEnUso(id)) {
            sesion.put("alerta", Mensaje.getUsado(Mensaje.tipoMoneda, Mensaje.propiedad));
        } else {
            controladorTipoMoneda.eliminar(id);
            sesion.put("mensaje", Mensaje.getEliminado(Mensaje.tipoMoneda));
        }
        return SUCCESS;
    }

    public String editar() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        tipoMoneda = controladorTipoMoneda.getOne(Integer.parseInt(request.getParameter("idTipoMoneda")));
        return SUCCESS;
    }

    public TipoMoneda getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(TipoMoneda tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public List<TipoMoneda> getTipoMonedaLista() {
        return tipoMonedaLista;
    }

    public void setTipoMonedaLista(List<TipoMoneda> tipoMonedaLista) {
        this.tipoMonedaLista = tipoMonedaLista;
    }

}
