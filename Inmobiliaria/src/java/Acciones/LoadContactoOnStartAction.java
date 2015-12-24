/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Controlador.ControladorContacto;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

/**
 *
 * @author Angelo
 */
public class LoadContactoOnStartAction extends ActionSupport {

    private final ControladorContacto cc = new ControladorContacto();
    private final Map<String, Object> apl = ActionContext.getContext().getApplication();

    @Override
    public String execute() {
        if (apl.get("contacto") == null) {
            apl.put("contacto", cc.getOne(1));
        }
        return SUCCESS;
    }

}
