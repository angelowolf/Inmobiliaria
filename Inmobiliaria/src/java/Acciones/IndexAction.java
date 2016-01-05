
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Controlador.ControladorContacto;
import Controlador.ControladorDestacado;
import Controlador.ControladorPropiedad;
import Persistencia.Modelo.Contacto;
import Persistencia.Modelo.Destacado;
import Persistencia.Modelo.Propiedad;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Angelo
 */
public class IndexAction extends ActionSupport {

    private List<Destacado> destacados = new ArrayList<Destacado>();
    private final ControladorDestacado cd = new ControladorDestacado();
    private List<Propiedad> propiedades = new ArrayList<Propiedad>();
    private final ControladorPropiedad controladorPropiedad = new ControladorPropiedad();
    private final Map<String, Object> application = ActionContext.getContext().getApplication();

    @Override
    public String execute() {
        destacados = cd.getTodos();
        propiedades = controladorPropiedad.getOportunidades();
        for (Propiedad cadaPropiedad : propiedades) {
            try {
                cadaPropiedad.setImagenDefault(cadaPropiedad.getImagenes().get(0));

            } catch (IndexOutOfBoundsException e) {
                addActionError("Algunas propiedades no poseen imagenes.");
            }
        }
        Contacto c = (Contacto) application.get("contacto");
        if (c == null) {
            ControladorContacto cc = new ControladorContacto();
            c = cc.getOne(1);
            application.put("contacto", c);
        }
        return SUCCESS;
    }

    public List<Destacado> getDestacados() {
        return destacados;
    }

    public List<Propiedad> getPropiedades() {
        return propiedades;
    }

}
