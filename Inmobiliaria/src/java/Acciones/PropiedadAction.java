/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Controlador.ControladorAmbiente;
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
import Soporte.Mensaje;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Angelo
 */
public class PropiedadAction extends ActionSupport implements ModelDriven<Propiedad> {

    private Propiedad propiedad = new Propiedad();
    private List<Propiedad> propiedadLista = new ArrayList<Propiedad>();
    private final ControladorPropiedad controladorPropiedad = new ControladorPropiedad();
    private final ControladorServicio controladorServicio = new ControladorServicio();
    private final ControladorAmbiente controladorAmbiente = new ControladorAmbiente();
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
        } else if (controladorPropiedad.existe(propiedad)) {
            addFieldError("", Mensaje.codigoEnUso);
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
        if (!validarGuardar()) {
            this.cargarServiciosINPUT();
            this.cargarAmbientesINPUT();
            this.cargarTipoMoneda();
            this.cargarTipoPropiedad();
            return INPUT;
        }
        controladorPropiedad.guardar(propiedad, serviciosElegidos, ambientesElegidos, imagenFileName, imagen);
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
        controladorPropiedad.actualizar(propiedad, todosImagenes, imagenesElegidos, ambientesElegidos, serviciosElegidos, sesion, imagen, imagenFileName);
        sesion.put("mensaje", Mensaje.getModificada(Mensaje.propiedad));
        return SUCCESS;
    }

    public String list() {
        propiedadLista = controladorPropiedad.getTodos();
        String mensaje = (String) sesion.get("mensaje");
        addActionMessage(mensaje);
        sesion.put("mensaje", "");
        String alerta = (String) sesion.get("alerta");
        addActionError(alerta);
        sesion.put("alerta", "");
        for (Propiedad cadaPropiedad : propiedadLista) {
            try {
                cadaPropiedad.setImagenDefault(cadaPropiedad.getImagenes().get(0));
            } catch (IndexOutOfBoundsException e) {
                addActionError("Algunas propiedades no poseen imagenes.");
                e.printStackTrace();
            }
        }
        return SUCCESS;
    }

    public String eliminar() {
        if (controladorPropiedad.propiedadEnUso(propiedad.getIdPropiedad())) {
            sesion.put("alerta", Mensaje.getUsada(Mensaje.propiedad, Mensaje.propiedadDestacada));
        } else {
            Propiedad p = controladorPropiedad.getOne(propiedad.getIdPropiedad());
            controladorPropiedad.eliminar(p);
            sesion.put("mensaje", Mensaje.getEliminada(Mensaje.propiedad));
        }
        return SUCCESS;
    }

    public String editar() {
        propiedad = controladorPropiedad.getOne(propiedad.getIdPropiedad());
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

    public String imagenes() {
        addActionMessage(controladorPropiedad.getArchivoTamaño());
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
        propiedad = controladorPropiedad.getOne(propiedad.getIdPropiedad());
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

    @Override
    public Propiedad getModel() {
        return propiedad;
    }

}
