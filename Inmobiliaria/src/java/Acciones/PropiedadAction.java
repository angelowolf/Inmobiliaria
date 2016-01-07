/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Controlador.ControladorAmbiente;
import Controlador.ControladorDestacado;
import Controlador.ControladorImagenPropiedad;
import Controlador.ControladorPropiedad;
import Controlador.ControladorServicio;
import Controlador.ControladorTipoMoneda;
import Controlador.ControladorTipoPropiedad;
import Persistencia.Modelo.Ambiente;
import Persistencia.Modelo.ImagenPropiedad;
import Persistencia.Modelo.Propiedad;
import Persistencia.Modelo.Servicio;
import Persistencia.Modelo.TipoMoneda;
import Persistencia.Modelo.TipoPropiedad;
import Soporte.Archivo;
import Soporte.Mensaje;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

    private static final String STORAGE_PATH = System.getenv("OPENSHIFT_DATA_DIR") == null ? "D:/imagenes/tmp/" : System.getenv("OPENSHIFT_DATA_DIR");
    private Propiedad propiedad = new Propiedad();
    private List<Propiedad> propiedadLista = new ArrayList<Propiedad>();
    private final ControladorPropiedad controladorPropiedad = new ControladorPropiedad();
    private final ControladorDestacado controladorDestacado = new ControladorDestacado();
    private final ControladorServicio controladorServicio = new ControladorServicio();
    private final ControladorAmbiente controladorAmbiente = new ControladorAmbiente();
    private final ControladorImagenPropiedad controladorImagenPropiedad = new ControladorImagenPropiedad();
    private final ControladorTipoMoneda controladorTipoMoneda = new ControladorTipoMoneda();
    private final ControladorTipoPropiedad controladorTipoPropiedad = new ControladorTipoPropiedad();
    private final Map<String, Object> sesion = ActionContext.getContext().getSession();
    private List<File> imagen;
    private List<String> imagenFileName;
    private List<String> imagenContentType;
    private String[] serviciosElegidos;
    private String[] ambientesElegidos;
    private String[] imagenesElegidos;
    private List<ImagenPropiedad> todosImagenes = new ArrayList<ImagenPropiedad>();
    private List<String> imagenesDefault = new ArrayList<String>();
    private List<Servicio> todosServicios = new ArrayList<Servicio>();
    private List<String> serviciosDefault = new ArrayList<String>();
    private List<Ambiente> todosAmbientes = new ArrayList<Ambiente>();
    private List<String> ambientesDefault = new ArrayList<String>();
    private List<TipoPropiedad> tipoPropiedadLista = new ArrayList<TipoPropiedad>();
    private List<TipoMoneda> tipoMonedaLista = new ArrayList<TipoMoneda>();

    private boolean validar() {
        boolean flag = true;
        if (propiedad.getDireccion().trim().isEmpty()) {
            addFieldError("", Mensaje.ingreseDireccion);
            flag = false;
        }
        if (propiedad.getCodigoPropiedad() == 0) {
            addFieldError("", Mensaje.ingreseUnCodigo);
            flag = false;
        }
        if (propiedad.getLongitud() == 0 || propiedad.getLatitud() == 0) {
            addFieldError("", Mensaje.seleccioneUbicacion);
            flag = false;
        }
        if (propiedad.getTipoMoneda().getIdTipoMoneda() <= 0) {
            addFieldError("", Mensaje.seleccioneMoneda);
            flag = false;
        }
        if (propiedad.getTipoPropiedad().getIdTipoPropiedad() <= 0) {
            addFieldError("", Mensaje.seleccioneTipoPropiedad);
            flag = false;
        }
        return flag;
    }

    private boolean validarGuardar() {
        boolean flag = validar();
        if (imagen == null || imagen.isEmpty()) {
            addFieldError("", Mensaje.ingreseUnaImagen);
            flag = false;
        }
        return flag;
    }

    private boolean validarModificar() {
        boolean flag = validar();
        if (imagen == null || imagen.isEmpty()) {
            if (imagenesElegidos != null) {
                if (imagenesElegidos.length == 1 && imagenesElegidos[0].equals("false")) {
                    addActionError(Mensaje.imagenMinimo);
                    cargarServiciosINPUT();
                    flag = false;
                }
            } else {
                addActionError(Mensaje.imagenMinimo);
                cargarServiciosINPUT();
                flag = false;
            }
        }
        return flag;
    }

    public String guardar() {
//        this.cargarServicios();
        if (!validarGuardar()) {
            this.cargarServiciosINPUT();
            this.cargarAmbientesINPUT();
            this.cargarTipoMoneda();
            this.cargarTipoPropiedad();
            return INPUT;
        }
        //Agrego los servicios elegidos a la propiedad
        if (serviciosElegidos != null) {
            for (String serviciosElegido : serviciosElegidos) {
                int id = Integer.parseInt(serviciosElegido);
                Servicio s = controladorServicio.getOne(id);
                propiedad.addServicio(s);
            }
        }

        //Agrego los ambientes elegidos a la propiedad
        if (ambientesElegidos != null) {
            for (String ambientesElegido : ambientesElegidos) {
                int id = Integer.parseInt(ambientesElegido);
                Ambiente a = controladorAmbiente.getOne(id);
                propiedad.addAmbiente(a);
            }
        }

//        String ruta = ServletActionContext.getServletContext().getRealPath("ImagenPropiedad");
//        ruta += "\\" + propiedad.getCodigoPropiedad();
        String ruta = STORAGE_PATH + "ImagenPropiedad/" + propiedad.getCodigoPropiedad();
        sesion.put("ruta", ruta);
        File directorio = new File(ruta);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        for (int i = 0; i < imagen.size(); i++) {
            File cadaImagen = imagen.get(i);
//            String rutaBD = "ImagenPropiedad\\" + propiedad.getCodigoPropiedad() + "\\" + imagenFileName.get(i);
            String rutaBD = ruta + "/" + imagenFileName.get(i);
            try {
                FileUtils.copyFile(cadaImagen, new File(directorio, imagenFileName.get(i)));
            } catch (IOException ex) {
                Logger.getLogger(PropiedadAction.class.getName()).log(Level.SEVERE, null, ex);
            }

            ImagenPropiedad imagenPropiedad = new ImagenPropiedad();
            imagenPropiedad.setRuta(rutaBD);
            propiedad.addImagenPropiedad(imagenPropiedad);
        }
        int idPropiedad = controladorPropiedad.guardar(propiedad);
        sesion.put("mensaje", Mensaje.getAgregada(Mensaje.propiedad));
        return SUCCESS;
    }

    public String modificar() {
        if (!validarModificar()) {
            this.cargarServiciosINPUT();
            this.cargasImagenesINPUT();
            this.cargarAmbientesINPUT();
            this.cargarTipoMoneda();
            this.cargarTipoPropiedad();
            return INPUT;
        }

        //OBTENGO TODAS LAS IMAGENES QUE POSEE LA PROPIEDAD
        Map<Integer, ImagenPropiedad> mapImagenes = new HashMap<Integer, ImagenPropiedad>();
        todosImagenes = controladorImagenPropiedad.getTodos(propiedad.getIdPropiedad());
        for (ImagenPropiedad i : todosImagenes) {
            mapImagenes.put(i.getIdImagenPropiedad(), i);
        }
        //OBTENGO LAS IMAGENES QUE EL USUARIO NO QUIERE ELIMINAR
        boolean cambioCodigo = false;
        int codigoOriginal = 0;
        int codigoNuevo = 0;
        List<ImagenPropiedad> imagenesDeLaPropiedad = new ArrayList<ImagenPropiedad>();

        //SE TIENEN Q ELIMINAR TODAS LAS IMAGENES DE LA BD...      
        if (imagenesElegidos != null) {
            if (!imagenesElegidos[0].equals("false")) {
                for (String cadaImageneElegido : imagenesElegidos) {
                    int id = Integer.parseInt(cadaImageneElegido);
                    ImagenPropiedad p = controladorImagenPropiedad.getOne(id);
                    Propiedad original = (Propiedad) sesion.get("propiedadOriginal");
                    //SI EL CODIGO CAMBIO MODIFICAR LOS CODIGOS EN LAS IMAGENES BD..
                    if (original.getCodigoPropiedad() != propiedad.getCodigoPropiedad()) {
                        String rutaOriginal = p.getRuta();
                        String rutamodificada = rutaOriginal.replaceAll("" + original.getCodigoPropiedad(), "" + propiedad.getCodigoPropiedad());
                        p.setRuta(rutamodificada);
                        if (!cambioCodigo) {
                            codigoOriginal = original.getCodigoPropiedad();
                            codigoNuevo = propiedad.getCodigoPropiedad();
                        }
                        cambioCodigo = true;
                    }
                    imagenesDeLaPropiedad.add(p);
                    //SACO DEL MAPA LAS IMAGENES QUE NO QUIERO ELIMINAR
                    mapImagenes.remove(p.getIdImagenPropiedad());
                }
            }
        }

        if (imagenesElegidos != null && imagenesElegidos.length != 1 && !imagenesElegidos[0].equals("false")) {

        }
        propiedad.setImagenes(imagenesDeLaPropiedad);
        //PROCEDO A ELIMINAR TODAS LAS IMAGENES QUE EL USUARIO NO "SELECCIONO"
        for (Map.Entry<Integer, ImagenPropiedad> cadaImagen : mapImagenes.entrySet()) {
            ImagenPropiedad imagenPropiedad = cadaImagen.getValue();
//            String ruta = ServletActionContext.getServletContext().getRealPath("ImagenPropiedad");
//            String ruta = STORAGE_PATH + "ImagenPropiedad\\";
//            String pathImagen = imagenPropiedad.getRuta().substring(16);
//            ruta += "\\" + pathImagen;
            String ruta = imagenPropiedad.getRuta();
            controladorImagenPropiedad.eliminarImagen(ruta);
        }
        if (cambioCodigo) {
//            String ruta = ServletActionContext.getServletContext().getRealPath("ImagenPropiedad");
            String ruta = STORAGE_PATH + "ImagenPropiedad";
            String rutaOriginal = ruta + "/" + codigoOriginal;
            String rutaNueva = ruta + "/" + codigoNuevo;
            Archivo.renombrarCarpeta(rutaOriginal, rutaNueva);
        }
        //AGREGO LAS NUEVAS IMAGENES

//        String ruta = ServletActionContext.getServletContext().getRealPath("ImagenPropiedad");
//        String ruta = STORAGE_PATH + "ImagenPropiedad\\" + propiedad.getCodigoPropiedad();
//        ruta += "\\" + propiedad.getCodigoPropiedad();
        String ruta = STORAGE_PATH + "ImagenPropiedad/" + propiedad.getCodigoPropiedad();
        File directorio = new File(ruta);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        if (imagen != null) {
            for (int i = 0; i < imagen.size(); i++) {
                File cadaImagen = imagen.get(i);
//            String rutaBD = "ImagenPropiedad\\" + propiedad.getCodigoPropiedad() + "\\" + imagenFileName.get(i);
                String rutaBD = ruta + "/" + imagenFileName.get(i);
                try {
                    FileUtils.copyFile(cadaImagen, new File(directorio, imagenFileName.get(i)));
                } catch (IOException ex) {
                    Logger.getLogger(PropiedadAction.class.getName()).log(Level.SEVERE, null, ex);
                }

                ImagenPropiedad imagenPropiedad = new ImagenPropiedad();
                imagenPropiedad.setRuta(rutaBD);
                propiedad.addImagenPropiedad(imagenPropiedad);
            }
        }
        //GUARDO LOS NUEVOS SERVICIOS ELEGIDOS A LA PROPIEDAD
        List<Servicio> temp = new ArrayList<Servicio>();
        if (serviciosElegidos != null) {
            for (String cadaServicioElegido : serviciosElegidos) {
                int id = Integer.parseInt(cadaServicioElegido);
                Servicio s = controladorServicio.getOne(id);
                temp.add(s);
            }
            propiedad.setServicios(temp);
        } else {
            propiedad.setServicios(temp);
        }
        //GUARDO LOS NUEVOS AMBIENTES ELEGIDOS A LA PROPIEDAD
        List<Ambiente> temp2 = new ArrayList<Ambiente>();
        if (ambientesElegidos != null) {
            for (String cadaAmbienteElegido : ambientesElegidos) {
                int id = Integer.parseInt(cadaAmbienteElegido);
                Ambiente a = controladorAmbiente.getOne(id);
                temp2.add(a);
            }
            propiedad.setAmbientes(temp2);
        } else {
            propiedad.setAmbientes(temp2);
        }

        //ACTUALIZO LOS CAMBIOS
        controladorPropiedad.actualizar(propiedad);
        sesion.put("mensaje", Mensaje.getModificada(Mensaje.propiedad));
        return SUCCESS;
    }

    public String list() {
        propiedadLista = controladorPropiedad.getTodos();
        String mensaje = (String) sesion.get("mensaje");
        addActionMessage(mensaje);
        sesion.put("mensaje", "");
        for (Propiedad cadaPropiedad : propiedadLista) {
            try {
                cadaPropiedad.setImagenDefault(cadaPropiedad.getImagenes().get(0));

            } catch (IndexOutOfBoundsException e) {
                addActionError("Algunas propiedades no poseen imagenes.");
            }
        }

        return SUCCESS;
    }

    public String eliminar() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        int id = Integer.parseInt(request.getParameter("idPropiedad"));
        if (controladorPropiedad.propiedadEnUso(id)) {
            sesion.put("alerta", Mensaje.getUsado(Mensaje.propiedad, Mensaje.propiedadDestacada));
        } else {
            Propiedad p = controladorPropiedad.getOne(id);
            String ruta = STORAGE_PATH + "ImagenPropiedad/" + p.getCodigoPropiedad();
            controladorPropiedad.eliminar(id, ruta);
            sesion.put("mensaje", Mensaje.getEliminada(Mensaje.propiedad));
        }
        return SUCCESS;
    }

    public String editar() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        propiedad = controladorPropiedad.getOne(Integer.parseInt(request.getParameter("idPropiedad")));
        this.cargarServicios();
        this.cargarAmbientes();
        this.cargarTipoMoneda();
        this.cargarTipoPropiedad();
        this.cargarServiciosSeleccionados();
        this.cargarAmbientesSeleccionados();
        this.cargarImagenesSeleccionadas();
        todosImagenes = propiedad.getImagenes();
        sesion.put("propiedadOriginal", propiedad);
        return SUCCESS;

    }

    public String nuevo() {
        this.cargarServicios();
        this.cargarAmbientes();
        this.cargarTipoMoneda();
        this.cargarTipoPropiedad();
        return "nuevo";
    }

    public String oportunidad() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        propiedad = controladorPropiedad.getOne(Integer.parseInt(request.getParameter("idPropiedad")));
        if (propiedad.isOportunidad()) {
            propiedad.setOportunidad(false);
        } else {
            propiedad.setOportunidad(true);
        }
        controladorPropiedad.actualizar(propiedad);
        sesion.put("mensaje", "Oportunidad modificada !");
        return SUCCESS;
    }

    private void cargarTipoMoneda() {
        tipoMonedaLista = controladorTipoMoneda.getTodos();
    }

    private void cargarTipoPropiedad() {
        tipoPropiedadLista = controladorTipoPropiedad.getTodos();
    }

    private void cargarServicios() {
        todosServicios = controladorServicio.getTodos();
    }

    private void cargarAmbientes() {
        todosAmbientes = controladorAmbiente.getTodos();
    }

    private void cargarAmbientesSeleccionados() {
        List<Ambiente> ambientesDefault2 = propiedad.getAmbientes();
        for (Ambiente a : ambientesDefault2) {
            ambientesDefault.add("" + a.getIdAmbiente());
        }
    }

    private void cargarServiciosSeleccionados() {
        List<Servicio> serviciosDefault2 = propiedad.getServicios();
        for (Servicio s : serviciosDefault2) {
            serviciosDefault.add("" + s.getIdServicio());
        }
    }

    private void cargarImagenesSeleccionadas() {
        List<ImagenPropiedad> imagenlista = propiedad.getImagenes();
        for (ImagenPropiedad s : imagenlista) {
            imagenesDefault.add("" + s.getIdImagenPropiedad());
        }
    }

    private void cargarServiciosINPUT() {
        this.cargarServicios();
        if (serviciosElegidos != null) {
            for (String cadaServicioElegido : serviciosElegidos) {
                int id = Integer.parseInt(cadaServicioElegido);
                serviciosDefault.add("" + id);
            }
        }
    }

    private void cargasImagenesINPUT() {
        Propiedad p = controladorPropiedad.getOne(propiedad.getIdPropiedad());
        todosImagenes = p.getImagenes();
        for (ImagenPropiedad s : todosImagenes) {
            imagenesDefault.add("" + s.getIdImagenPropiedad());
        }
    }

    private void cargarAmbientesINPUT() {
        this.cargarAmbientes();
        if (ambientesElegidos != null) {
            for (String cadaAmbienteElegido : ambientesElegidos) {
                int id = Integer.parseInt(cadaAmbienteElegido);
                ambientesDefault.add("" + id);
            }
        }
    }

    public List<TipoPropiedad> getTipoPropiedadLista() {
        return tipoPropiedadLista;
    }

    public void setTipoPropiedadLista(List<TipoPropiedad> tipoPropiedadLista) {
        this.tipoPropiedadLista = tipoPropiedadLista;
    }

    public List<TipoMoneda> getTipoMonedaLista() {
        return tipoMonedaLista;
    }

    public void setTipoMonedaLista(List<TipoMoneda> tipoMonedaLista) {
        this.tipoMonedaLista = tipoMonedaLista;
    }

    public List<ImagenPropiedad> getTodosImagenes() {
        return todosImagenes;
    }

    public List<String> getImagenesDefault() {
        return imagenesDefault;
    }

    public List<String> getServiciosDefault() {
        return serviciosDefault;
    }

    public List<Servicio> getTodosServicios() {
        return todosServicios;
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

    public void setImagenesElegidos(String[] imagenesElegidos) {
        this.imagenesElegidos = imagenesElegidos;
    }

    public void setImagen(List<File> imagen) {
        this.imagen = imagen;
    }

    public List<File> getImagen() {
        return imagen;
    }

    public String[] getServiciosElegidos() {
        return serviciosElegidos;
    }

    public void setServiciosElegidos(String[] serviciosElegidos) {
        this.serviciosElegidos = serviciosElegidos;
    }

    public List<String> getImagenFileName() {
        return imagenFileName;
    }

    public List<String> getImagenContentType() {
        return imagenContentType;
    }

    public void setImagenFileName(List<String> imagenFileName) {
        this.imagenFileName = imagenFileName;
    }

    public void setImagenContentType(List<String> imagenContentType) {
        this.imagenContentType = imagenContentType;
    }

    public String[] getAmbientesElegidos() {
        return ambientesElegidos;
    }

    public void setAmbientesElegidos(String[] ambientesElegidos) {
        this.ambientesElegidos = ambientesElegidos;
    }

    public List<Ambiente> getTodosAmbientes() {
        return todosAmbientes;
    }

    public void setTodosAmbientes(List<Ambiente> todosAmbientes) {
        this.todosAmbientes = todosAmbientes;
    }

    public List<String> getAmbientesDefault() {
        return ambientesDefault;
    }

    public void setAmbientesDefault(List<String> ambientesDefault) {
        this.ambientesDefault = ambientesDefault;
    }

}
