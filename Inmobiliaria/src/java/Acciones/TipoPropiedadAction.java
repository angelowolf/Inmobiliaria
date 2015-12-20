/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Controlador.ControladorTipoPropiedad;
import Persistencia.Modelo.TipoPropiedad;
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
public class TipoPropiedadAction extends ActionSupport implements ModelDriven<TipoPropiedad> {

    private TipoPropiedad tipoPropiedad = new TipoPropiedad();
    private List<TipoPropiedad> tipoPropiedadLista = new ArrayList<TipoPropiedad>();
    private final ControladorTipoPropiedad controladorTipoPropiedad = new ControladorTipoPropiedad();
    private final Map<String, Object> sesion = ActionContext.getContext().getSession();

    @Override
    public TipoPropiedad getModel() {
        return tipoPropiedad;
    }

    private boolean validar() {
        boolean flag = true;
        if (tipoPropiedad.getNombre().trim().isEmpty()) {
            addFieldError("tipoPropiedad.nombre", "Ingrese un nombre.");
            flag = false;

        } else {
            if (controladorTipoPropiedad.existe(tipoPropiedad)) {
                addFieldError("tipoPropiedad.nombre", "El tipo de propiedad ya existe!.");
                flag = false;
            }
        }

        return flag;
    }

    public String guardarOModificar() {
        if (!validar()) {
            return INPUT;
        }
        if (tipoPropiedad.getIdTipoPropiedad() != 0) {
            controladorTipoPropiedad.actualizar(tipoPropiedad);
            sesion.put("mensaje", "Tipo de propiedad Modificado.");
        } else {
            controladorTipoPropiedad.guardar(tipoPropiedad);;
            sesion.put("mensaje", "Tipo de propiedad Agregado.");
        }
        return SUCCESS;
    }

    public String list() {
        tipoPropiedadLista = controladorTipoPropiedad.getTodos();
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
        int id = Integer.parseInt(request.getParameter("idTipoPropiedad"));
        if (controladorTipoPropiedad.tipoPropiedadEnUso(id)) {
            sesion.put("alerta", "Este tipo de propiedad esta siendo utilizada por algunas propiedades, debe eliminar esas propiedades para poder eliminar este tipo de propiedad!");
        } else {
            controladorTipoPropiedad.eliminar(id);
            sesion.put("mensaje", "Tipo de propiedad Eliminado.");
        }
        return SUCCESS;
    }

    public String editar() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        tipoPropiedad = controladorTipoPropiedad.getOne(Integer.parseInt(request.getParameter("idTipoPropiedad")));
        return SUCCESS;
    }

    public TipoPropiedad getTipoPropiedad() {
        return tipoPropiedad;
    }

    public void setTipoPropiedad(TipoPropiedad tipoPropiedad) {
        this.tipoPropiedad = tipoPropiedad;
    }

    public List<TipoPropiedad> getTipoPropiedadLista() {
        return tipoPropiedadLista;
    }

    public void setTipoPropiedadLista(List<TipoPropiedad> tipoPropiedadLista) {
        this.tipoPropiedadLista = tipoPropiedadLista;
    }

}
