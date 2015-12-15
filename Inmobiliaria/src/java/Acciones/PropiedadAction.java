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

    private Propiedad propiedad = new Propiedad();
    private List<Propiedad> propiedadLista = new ArrayList<>();
    private final ControladorPropiedad controladorPropiedad = new ControladorPropiedad();
    private final ControladorServicio controladorServicio = new ControladorServicio();
    private final ControladorImagenPropiedad controladorImagenPropiedad = new ControladorImagenPropiedad();
    private final Map<String, Object> sesion = ActionContext.getContext().getSession();
    private List<File> imagen;
    private List<String> imagenFileName;
    private List<String> imagenContentType;
    private String[] serviciosElegidos;
    private String[] imagenesElegidos;

    private boolean validar() {
        boolean flag = true;
        if (propiedad.getNombre().trim().isEmpty()) {
            addFieldError("", "Ingrese un nombre.");
            flag = false;
        }
        if (propiedad.getCodigoPropiedad() == 0) {
            addFieldError("", "Ingrese un codigo.");
            flag = false;
        }

        return flag;
    }

    private boolean validarGuardar() {
        boolean flag = validar();
        if (imagen == null || imagen.isEmpty()) {
            addFieldError("", "Ingrese al menos una imagen.");
            flag = false;
        }
        return flag;
    }

    private boolean validarModificar() {
        boolean flag = validar();
        if (imagen == null || imagen.isEmpty()) {
            if (imagenesElegidos != null) {
                if (imagenesElegidos.length == 1 && imagenesElegidos[0].equals("false")) {
                    addActionError("La propiedad debe contar con una imagen como minimo.");
                    cargarServiciosINPUT();
                    flag = false;
                }
            } else {
                addActionError("La propiedad debe contar con una imagen como minimo.");
                cargarServiciosINPUT();
                flag = false;
            }
        }
        return flag;
    }

    public String guardar() {
        this.cargarServicios();
        if (!validarGuardar()) {
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

        String ruta = ServletActionContext.getServletContext().getRealPath("ImagenPropiedad");
        ruta += "\\" + propiedad.getCodigoPropiedad();

        File directorio = new File(ruta);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        for (int i = 0; i < imagen.size(); i++) {
            File cadaImagen = imagen.get(i);
            System.out.println(propiedad.getCodigoPropiedad());
            System.out.println(imagenFileName.get(i));
            String rutaBD = "ImagenPropiedad\\" + propiedad.getCodigoPropiedad() + "\\" + imagenFileName.get(i);
            try {
                FileUtils.copyFile(cadaImagen, new File(directorio, imagenFileName.get(i)));
            } catch (IOException ex) {
                Logger.getLogger(PropiedadAction.class.getName()).log(Level.SEVERE, null, ex);
            }

            ImagenPropiedad imagenPropiedad = new ImagenPropiedad();
            imagenPropiedad.setRuta(rutaBD);
            propiedad.addImagenPropiedad(imagenPropiedad);
        }

        controladorPropiedad.guardar(propiedad);;
        sesion.put("mensaje", "Propiedad Agregada.");
        return SUCCESS;
    }

    public String modificar() {
        if (!validarModificar()) {
            cargarServiciosINPUT();
            cargasImagenesINPUT();
            return INPUT;
        }

        //OBTENGO TODAS LAS IMAGENES QUE POSEE LA PROPIEDAD
        Map<Integer, ImagenPropiedad> mapImagenes = new HashMap<>();
        todosImagenes = controladorImagenPropiedad.getTodos(propiedad.getIdPropiedad());
        for (ImagenPropiedad i : todosImagenes) {
            mapImagenes.put(i.getIdImagenPropiedad(), i);
        }
        //OBTENGO LAS IMAGENES QUE EL USUARIO NO QUIERE ELIMINAR
        boolean cambioCodigo = false;
        int codigoOriginal = 0;
        int codigoNuevo = 0;
        List<ImagenPropiedad> imagenesDeLaPropiedad = new ArrayList<>();

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
            String ruta = ServletActionContext.getServletContext().getRealPath("ImagenPropiedad");
            String pathImagen = imagenPropiedad.getRuta().substring(16);
            ruta += "\\" + pathImagen;
            controladorImagenPropiedad.eliminarImagen(ruta);
        }
        if (cambioCodigo) {
            String ruta = ServletActionContext.getServletContext().getRealPath("ImagenPropiedad");
            String rutaOriginal = ruta + "\\" + codigoOriginal;
            String rutaNueva = ruta + "\\" + codigoNuevo;
            controladorImagenPropiedad.renombrarCarpeta(rutaOriginal, rutaNueva);
        }
        //AGREGO LAS NUEVAS IMAGENES

        String ruta = ServletActionContext.getServletContext().getRealPath("ImagenPropiedad");
        ruta += "\\" + propiedad.getCodigoPropiedad();

        File directorio = new File(ruta);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        if (imagen != null) {
            for (int i = 0; i < imagen.size(); i++) {
                File cadaImagen = imagen.get(i);
                System.out.println(propiedad.getCodigoPropiedad());
                System.out.println(imagenFileName.get(i));
                String rutaBD = "ImagenPropiedad\\" + propiedad.getCodigoPropiedad() + "\\" + imagenFileName.get(i);
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
        List<Servicio> temp = new ArrayList<>();
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
        //ACTUALIZO LOS CAMBIOS
        controladorPropiedad.actualizar(propiedad);
        sesion.put("mensaje", "Propiedad Modificada.");
        return SUCCESS;
    }

    public String list() {
        propiedadLista = controladorPropiedad.getTodos();
        String mensaje = (String) sesion.get("mensaje");
        addActionMessage(mensaje);
        sesion.put("mensaje", "");
        try {
            for (Propiedad propiedadLista1 : propiedadLista) {
                propiedadLista1.setImagenDefault(propiedadLista1.getImagenes().get(0));
            }
        } catch (IndexOutOfBoundsException e) {
            addActionError("Algunas propiedades no poseen imagenes.");
        }

        return SUCCESS;
    }

    public String eliminar() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        int id = Integer.parseInt(request.getParameter("idPropiedad"));
        Propiedad p = controladorPropiedad.getOne(id);
        String ruta = ServletActionContext.getServletContext().getRealPath("ImagenPropiedad");
        ruta += "\\" + p.getCodigoPropiedad();
        controladorPropiedad.eliminar(id, ruta);
        sesion.put("mensaje", "Propiedad Eliminada.");
        return SUCCESS;
    }

    public String editar() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        propiedad = controladorPropiedad.getOne(Integer.parseInt(request.getParameter("idPropiedad")));
        this.cargarServicios();
        this.cargarSeleccionados();
        this.cargarImagenesSeleccionadas();
        todosImagenes = propiedad.getImagenes();
        sesion.put("propiedadOriginal", propiedad);
        return SUCCESS;

    }
    private List<ImagenPropiedad> todosImagenes = new ArrayList<>();
    private List<Servicio> todosServicios = new ArrayList<>();
    private List<String> serviciosDefault = new ArrayList<>();
    private List<String> imagenesDefault = new ArrayList<>();

    public String nuevo() {
        this.cargarServicios();
        return "nuevo";
    }

    private void cargarServicios() {
        todosServicios = controladorServicio.getTodos();
    }

    private void cargarSeleccionados() {
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

    private void cargarServiciosINPUT() {
        this.cargarServicios();
        for (String cadaServicioElegido : serviciosElegidos) {
            int id = Integer.parseInt(cadaServicioElegido);
            serviciosDefault.add("" + id);
        }
    }

    private void cargasImagenesINPUT() {
        Propiedad p = controladorPropiedad.getOne(propiedad.getIdPropiedad());
        todosImagenes = p.getImagenes();
        for (ImagenPropiedad s : todosImagenes) {
            imagenesDefault.add("" + s.getIdImagenPropiedad());
        }
//        if (imagenesElegidos.length == 1 && imagenesElegidos[0].equals("false")) {
//            System.out.println(imagenesElegidos[0]);
//
//        } else {
//            for (String cadaImagenElegida : imagenesElegidos) {
//                int id = Integer.parseInt(cadaImagenElegida);
//                imagenesDefault.add("" + id);
//            }
//        }
    }
}
