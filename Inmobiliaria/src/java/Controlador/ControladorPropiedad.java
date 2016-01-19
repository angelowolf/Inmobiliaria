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

    /**
     * Guarda una propiedad, crea las imagenes que tendra en el disco.
     *
     * @param propiedad La propiedad a guardar.
     * @param serviciosElegidos Los servicios que tendra.
     * @param ambientesElegidos Los ambientes que tendra.
     * @param imagenFileName El nombre de las distintas imagenes.
     * @param imagen Las imagenes.
     * @return El id de la propiedad creada.
     */
    public int guardar(Propiedad propiedad, String[] serviciosElegidos, String[] ambientesElegidos,
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

    /**
     * Actualiza los datos de una propiedad.
     *
     * @param p La propiedad.
     */
    public void actualizar(Propiedad p) {
        propiedadDAO.actualizar(p);
    }

    /**
     * Actualiza los datos de una propiedad y crea las nuevas imagenes que
     * tendra, tambien elimina las imagenes que el usuario decidio eliminar.
     *
     * @param propiedad La propiedad a actualizar con sus datos.
     * @param todosImagenes Todas las imagenes que posee la propiedad(actuales).
     * @param imagenesElegidos Las imagenes elegidas para guardar.
     * @param ambientesElegidos Los ambientes elegidos para guardar.
     * @param serviciosElegidos Los servicios elegidos para guardar-
     * @param sesion La sesion actual
     * @param imagen Las imagenes nuevas.
     * @param imagenFileName El nombre de las nuevas imagenes.
     */
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

    /**
     * Busca todas las propiedades que existen.
     *
     * @return Las propiedades.
     */
    public List<Propiedad> getTodos() {
        return propiedadDAO.listar();
    }

    /**
     * Elimina una propiedad y sus imagenes del disco.
     *
     * @param id El id de la propiedad.
     */
    public void eliminar(int id) {
        Propiedad p = getOne(id);
        ControladorImagenPropiedad cip = new ControladorImagenPropiedad();
        cip.eliminarTodos(p.getIdPropiedad());
        propiedadDAO.eliminar(p);
        Archivo.delete(SingletonRuta.getInstancia().getSTORAGE_PATH() + "ImagenPropiedad/codigo_" + p.getCodigoPropiedad());
    }

    /**
     * Elimina una propiedad y sus imagenes del disco.
     *
     * @param p La propiedad a eliminar.
     */
    public void eliminar(Propiedad p) {
        ControladorImagenPropiedad cip = new ControladorImagenPropiedad();
        cip.eliminarTodos(p.getIdPropiedad());
        propiedadDAO.eliminar(p);
        Archivo.delete(SingletonRuta.getInstancia().getSTORAGE_PATH() + "ImagenPropiedad/codigo_" + p.getCodigoPropiedad());
    }

    /**
     * Busca una propiedad.
     *
     * @param id El id de la propiedad.
     * @return La propiedad.
     */
    public Propiedad getOne(int id) {
        return propiedadDAO.buscar(id);
    }

    /**
     * Busca todas las propiedades que estan marcadas como oportunidades.
     *
     * @return Las propiedades.
     */
    public List<Propiedad> getOportunidades() {
        return propiedadDAO.listarOportunidades();
    }

    /**
     * Busca propiedades por distintos criterios.
     *
     * @param idTipoPropiedad El id del tipo de propiedad.
     * @param habitacion Cantidad de habitaciones.
     * @param bano Cantidad de baños.
     * @return Las propiedades.
     */
    public List<Propiedad> buscar(int idTipoPropiedad, int habitacion, int bano) {
        return propiedadDAO.buscar(idTipoPropiedad, habitacion, bano);
    }

    /**
     * Verifica si la propiedad esta siendo utilizada por otra entidad, como
     * propiedad destacada.
     *
     * @param id El id de la propiedad.
     * @return True si esta siendo utilizada.
     */
    public boolean propiedadEnUso(int id) {
        return !propiedadDAO.propiedadEnUso(id).isEmpty();
    }

    /**
     * Verifica si el codigo de la propiedad esta ya en uso...
     *
     * @param propiedad La propiedad
     * @return True si el codigo ya esta siendo utilizado.
     */
    public boolean existe(Propiedad propiedad) {
        List<Propiedad> lista = propiedadDAO.buscarCodigo(propiedad.getCodigoPropiedad());
        for (Propiedad m : lista) {
            if (m.getCodigoPropiedad() == propiedad.getCodigoPropiedad()) {
                return m.getIdPropiedad() != propiedad.getIdPropiedad();
            }
        }
        return false;
    }

    /**
     * Busca una propiedad por su codigo.
     *
     * @param codigoPropiedad El codigo.
     * @return La propiedad.
     */
    public Propiedad getOneCodigoPropiedad(int codigoPropiedad) {
        List<Propiedad> lp = propiedadDAO.buscarCodigo(codigoPropiedad);
        if (!lp.isEmpty()) {
            return lp.get(0);
        } else {
            return null;
        }
    }

    /**
     * Busca todas las propiedades que no estan destacadas.
     *
     * @return Las propiedades.
     */
    public List<Propiedad> getTodosSinDestacar() {
        return propiedadDAO.todosSinDestacar();
    }
}
