/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Controlador.ControladorDestacado;
import Controlador.ControladorImagen;
import Controlador.ControladorPropiedad;
import Persistencia.Modelo.Destacado;
import Persistencia.Modelo.Imagen;
import Persistencia.Modelo.Propiedad;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Angelo
 */
public class DestacadoAction extends ActionSupport implements ModelDriven<Destacado> {

    private static final String STORAGE_PATH = System.getenv("OPENSHIFT_DATA_DIR") == null ? "D:/imagenes/tmp/" : System.getenv("OPENSHIFT_DATA_DIR");
    private Destacado destacado = new Destacado();
    private List<Destacado> destacadosLista = new ArrayList<Destacado>();
    private List<Propiedad> propiedadesLista = new ArrayList<Propiedad>();
    private final ControladorDestacado controladorDestacado = new ControladorDestacado();
    private final ControladorPropiedad controladorPropiedad = new ControladorPropiedad();
    private final Map<String, Object> sesion = ActionContext.getContext().getSession();
    private File upload;
    private String uploadContentType, uploadFileName;

    @Override
    public Destacado getModel() {
        return destacado;
    }

    private boolean validar() {
        boolean flag = true;
        if (destacado.getNombre().trim().isEmpty()) {
            addFieldError("destacado.nombre", "Ingrese un titulo.");
            flag = false;
        }
        if (destacado.getPropiedad().getIdPropiedad() <= 0) {
            addFieldError("", "Seleccione una propiedad.");
            flag = false;
        }
        return flag;
    }

    public String guardar() {
        if (!validar()) {
            this.cargarPropiedades();
            return INPUT;
        }
        if (destacado.getIdDestacado() != 0) {
            controladorDestacado.actualizar(destacado);
            sesion.put("mensaje", "Propiedad Destacada Modificada.");
        } else {
            int id = destacado.getPropiedad().getIdPropiedad();
            String ruta = STORAGE_PATH + "ImagenDestacada/" + id;
            File directorio = new File(ruta);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            String rutaBD = ruta + "/" + uploadFileName;
            try {
                FileUtils.copyFile(upload, new File(directorio, uploadFileName));
            } catch (IOException ex) {
                Logger.getLogger(PropiedadAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            Imagen im = new Imagen();
            im.setRuta(rutaBD);
            destacado.setImagen(im);
            ControladorImagen ci = new ControladorImagen();
            ci.guardar(im);
            controladorDestacado.guardar(destacado);
            sesion.put("mensaje", "Propiedad Destacada Agregado.");
        }
        return SUCCESS;
    }

    public String modificar() {
        return SUCCESS;
    }

    public String list() {
        destacadosLista = controladorDestacado.getTodos();
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
        int id = Integer.parseInt(request.getParameter("idDestacado"));
        controladorDestacado.eliminar(id);
        sesion.put("mensaje", "Propiedad Destacada Eliminada.");

        return SUCCESS;
    }

    public String editar() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        destacado = controladorDestacado.getOne(Integer.parseInt(request.getParameter("idDestacado")));
        return SUCCESS;
    }

    public String nuevo() {
        this.cargarPropiedades();
        return "nuevo";
    }

    public Destacado getDestacado() {
        return destacado;
    }

    public void setDestacado(Destacado destacado) {
        this.destacado = destacado;
    }

    public List<Destacado> getDestacadosLista() {
        return destacadosLista;
    }

    public void setDestacadosLista(List<Destacado> destacadosLista) {
        this.destacadosLista = destacadosLista;
    }

    public List<Propiedad> getPropiedadesLista() {
        return propiedadesLista;
    }

    public void setPropiedadesLista(List<Propiedad> propiedadesLista) {
        this.propiedadesLista = propiedadesLista;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    private void cargarPropiedades() {
        this.propiedadesLista = controladorPropiedad.getTodos();
    }

}