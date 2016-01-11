/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Persistencia.DAO.Implementacion.PropiedadDAO;
import Persistencia.Modelo.Ambiente;
import Persistencia.Modelo.ImagenPropiedad;
import Persistencia.Modelo.Propiedad;
import Persistencia.Modelo.Servicio;
import Soporte.Archivo;
import Soporte.SingletonRuta;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.WordUtils;

/**
 *
 * @author Angelo
 */
public class ControladorPropiedad {

    private final PropiedadDAO propiedadDAO;
    private final ControladorServicio controladorServicio = new ControladorServicio();
    private final ControladorAmbiente controladorAmbiente = new ControladorAmbiente();
    private final ControladorImagenPropiedad controladorImagenPropiedad = new ControladorImagenPropiedad();

    public ControladorPropiedad() {
        propiedadDAO = new PropiedadDAO();
    }

    public int guardar(Propiedad propiedad, List<String> serviciosElegidos, List<String> ambientesElegidos,
            List<String> imagenFileName, List<File> imagen) {
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
        String ruta = SingletonRuta.getInstancia().getSTORAGE_PATH() + "ImagenPropiedad/codigo_" + propiedad.getCodigoPropiedad();
        for (int i = 0; i < imagen.size(); i++) {
            File cadaImagen = imagen.get(i);
            ImagenPropiedad imagenPropiedad = new ImagenPropiedad();
            imagenPropiedad.setRuta(Archivo.crearImagen(ruta, cadaImagen, imagenFileName.get(i)));
            propiedad.addImagenPropiedad(imagenPropiedad);
        }
        propiedad.setDireccion(WordUtils.capitalize(propiedad.getDireccion()));
        return propiedadDAO.guardar(propiedad);
    }

    public void actualizar(Propiedad p) {
        propiedadDAO.actualizar(p);
    }

    public void actualizar(Propiedad propiedad, List<ImagenPropiedad> todosImagenes, String[] imagenesElegidos,
            String[] ambientesElegidos, String[] serviciosElegidos, Map<String, Object> sesion,
            List<File> imagen, List<String> imagenFileName) {
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
                        String rutamodificada = rutaOriginal.replaceAll("codigo_" + original.getCodigoPropiedad(), "codigo_" + propiedad.getCodigoPropiedad());
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
            Archivo.delete(imagenPropiedad.getRuta());
        }
        if (cambioCodigo) {
            String ruta = SingletonRuta.getInstancia().getSTORAGE_PATH() + "ImagenPropiedad/codigo_";
            String rutaOriginal = ruta + codigoOriginal;
            String rutaNueva = ruta + codigoNuevo;
            Archivo.renombrarCarpeta(rutaOriginal, rutaNueva);
        }
        //AGREGO LAS NUEVAS IMAGENES
        String ruta = SingletonRuta.getInstancia().getSTORAGE_PATH() + "ImagenPropiedad/codigo_" + propiedad.getCodigoPropiedad();
        if (imagen != null) {
            for (int i = 0; i < imagen.size(); i++) {
                File cadaImagen = imagen.get(i);
                ImagenPropiedad imagenPropiedad = new ImagenPropiedad();
                imagenPropiedad.setRuta(Archivo.crearImagen(ruta, cadaImagen, imagenFileName.get(i)));
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
        propiedad.setDireccion(WordUtils.capitalize(propiedad.getDireccion()));
        propiedadDAO.actualizar(propiedad);
    }

    public List<Propiedad> getTodos() {
        return propiedadDAO.listar();
    }

    public void eliminar(int id) {
        Propiedad p = getOne(id);
        ControladorImagenPropiedad cip = new ControladorImagenPropiedad();
        cip.eliminarTodos(p.getIdPropiedad());
        propiedadDAO.eliminar(p);
        Archivo.delete(SingletonRuta.getInstancia().getSTORAGE_PATH() + "ImagenPropiedad/codigo_" + p.getCodigoPropiedad());
    }

    public void eliminar(Propiedad p) {
        ControladorImagenPropiedad cip = new ControladorImagenPropiedad();
        cip.eliminarTodos(p.getIdPropiedad());
        propiedadDAO.eliminar(p);
        Archivo.delete(SingletonRuta.getInstancia().getSTORAGE_PATH() + "ImagenPropiedad/codigo_" + p.getCodigoPropiedad());
    }

    public Propiedad getOne(int id) {
        return propiedadDAO.buscar(id);
    }

    public List<Propiedad> getOportunidades() {
        return propiedadDAO.listarOportunidades();
    }

    public List<Propiedad> buscar(int idTipoPropiedad, int habitacion, int bano) {
        return propiedadDAO.buscar(idTipoPropiedad, habitacion, bano);
    }

    public boolean propiedadEnUso(int id) {
        return !propiedadDAO.propiedadEnUso(id).isEmpty();
    }

    public boolean existe(Propiedad propiedad) {
        List<Propiedad> lista = propiedadDAO.buscarCodigo(propiedad.getCodigoPropiedad());
        for (Propiedad m : lista) {
            if (m.getCodigoPropiedad() == propiedad.getCodigoPropiedad()) {
                return m.getIdPropiedad()!= propiedad.getIdPropiedad();
            }
        }
        return false;
    }
}
