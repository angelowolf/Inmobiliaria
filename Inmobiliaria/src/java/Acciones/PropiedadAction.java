/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Controlador.ControladorImagenPropiedad;
import Controlador.ControladorPropiedad;
import Controlador.ControladorServicio;
import Persistencia.Modelo.ImagenPropiedad;
import Persistencia.Modelo.Propiedad;
import Persistencia.Modelo.Servicio;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
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
public class PropiedadAction extends ActionSupport {

    private Propiedad propiedad = new Propiedad();
    private List<Propiedad> propiedadLista = new ArrayList<>();
    private final ControladorPropiedad controladorPropiedad = new ControladorPropiedad();
    private final ControladorServicio controladorServicio = new ControladorServicio();
    private final ControladorImagenPropiedad controladorImagenPropiedad = new ControladorImagenPropiedad();
    private final Map<String, Object> sesion = ActionContext.getContext().getSession();
    private File imagen;
    private String imagenFileName;
    private String imagenContentType;

    private String[] serviciosElegidos;

    public void setImagenFileName(String imagenFileName) {
        this.imagenFileName = imagenFileName;
    }

    public void setImagenContentType(String imagenContentType) {
        this.imagenContentType = imagenContentType;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
    }

    public String[] getServiciosElegidos() {
        return serviciosElegidos;
    }

    public void setServiciosElegidos(String[] serviciosElegidos) {
        this.serviciosElegidos = serviciosElegidos;
    }

    private boolean validar() {
        boolean flag = true;
        if (propiedad.getNombre().trim().isEmpty()) {
            addFieldError("servicio.nombre", "Ingrese un nombre.");
            flag = false;
        }
//        if (controladorServicio.existe(servicio)) {
//            addFieldError("servicio.nombre", "El servicio ya existe!.");
//            flag = false;
//        }
        return flag;
    }

    public String guardar() {
//        if (!validar()) {
//            return INPUT;
//        }

        for (String serviciosElegido : serviciosElegidos) {
            Servicio s = controladorServicio.getOne(serviciosElegido);
            propiedad.addServicio(s);
        }

        String ruta = ServletActionContext.getServletContext().getRealPath("ImagenPropiedad");
        ruta += "\\" + propiedad.getCodigoPropiedad();

        File directorio = new File(ruta);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        String rutaBD = "ImagenPropiedad\\" + propiedad.getCodigoPropiedad() + "\\" + imagenFileName;
        try {
            FileUtils.copyFile(imagen, new File(directorio, imagenFileName));
        } catch (IOException ex) {
            Logger.getLogger(PropiedadAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        ImagenPropiedad imagenPropiedad = new ImagenPropiedad();
        imagenPropiedad.setRuta(rutaBD);
        propiedad.addImagenPropiedad(imagenPropiedad);

        controladorPropiedad.guardar(propiedad);;
        sesion.put("mensaje", "Propiedad Agregada.");
        return SUCCESS;
    }

    public String modificar() {
//        if (!validar()) {
//            return INPUT;
//        }
        List<Servicio> temp = new ArrayList<>();
        if (serviciosElegidos != null) {
            for (String serviciosElegido : serviciosElegidos) {
                Servicio s = controladorServicio.getOne(serviciosElegido);
                temp.add(s);
            }
            propiedad.setServicios(temp);
        } else {
            propiedad.setServicios(temp);
        }

//        String ruta = ServletActionContext.getServletContext().getRealPath("ImagenPropiedad");
//        ruta += "\\" + propiedad.getCodigoPropiedad();
//
//        File directorio = new File(ruta);
//        if (!directorio.exists()) {
//            directorio.mkdirs();
//        }
//
//        String rutaBD = "\\ImagenPropiedad\\" + propiedad.getCodigoPropiedad() + "\\" + imagenFileName;
//        try {
//            FileUtils.copyFile(imagen, new File(directorio, imagenFileName));
//        } catch (IOException ex) {
//            Logger.getLogger(PropiedadAction.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NullPointerException e) {
////            no cargo archivos;
//        }
//
//        ImagenPropiedad imagenPropiedad = new ImagenPropiedad();
//        imagenPropiedad.setRuta(rutaBD);
//        propiedad.addImagenPropiedad(imagenPropiedad);
        List<ImagenPropiedad> imgTemp = controladorImagenPropiedad.getTodos(propiedad.getIdPropiedad());
        propiedad.setImagenes(imgTemp);
        controladorPropiedad.actualizar(propiedad);
        sesion.put("mensaje", "Propiedad Modificada.");

        return SUCCESS;
    }

    public String list() {
        propiedadLista = controladorPropiedad.getTodos();
        String mensaje = (String) sesion.get("mensaje");
        addActionMessage(mensaje);
        sesion.put("mensaje", "");

        for (Propiedad propiedadLista1 : propiedadLista) {
            System.out.println(propiedadLista1.toString());
            propiedadLista1.setImagenDefault(propiedadLista1.getImagenes().get(0));
        }

        return SUCCESS;
    }

    public String eliminar() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        int id = Integer.parseInt(request.getParameter("idPropiedad"));
        Propiedad p = controladorPropiedad.getOne(id);
        String ruta = ServletActionContext.getServletContext().getRealPath("ImagenPropiedad");
        ruta += "\\" + p.getCodigoPropiedad();
        p = null;
        controladorPropiedad.eliminar(id, ruta);
        sesion.put("mensaje", "Propiedad Eliminada.");
        return SUCCESS;
    }

    public String editar() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        propiedad = controladorPropiedad.getOne(Integer.parseInt(request.getParameter("idPropiedad")));
        this.cargarServicios();
        this.cargarSeleccionados();
        return SUCCESS;
    }
    private List<String> todos = new ArrayList<>();
    private List<String> serviciosDefault = new ArrayList<>();

    public List<String> getTodos() {
        return todos;
    }

    public void setTodos(List<String> todos) {
        this.todos = todos;
    }

    public List<String> getServiciosDefault() {
        return serviciosDefault;
    }

    public void setServiciosDefault(List<String> serviciosDefault) {
        this.serviciosDefault = serviciosDefault;
    }

    public String nuevo() {
        this.cargarServicios();
        return "nuevo";
    }

    private void cargarServicios() {
        List<Servicio> servicioLista = controladorServicio.getTodos();
        for (Servicio s : servicioLista) {
            todos.add(s.getNombre());
        }
    }

    private void cargarSeleccionados() {
        List<Servicio> serviciosDefault2 = propiedad.getServicios();
        for (Servicio s : serviciosDefault2) {
            serviciosDefault.add(s.getNombre());
        }
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    public List<Propiedad> getPropiedadLista() {
        return propiedadLista;
    }

    public void setPropiedadLista(List<Propiedad> propiedadLista) {
        this.propiedadLista = propiedadLista;
    }

}
