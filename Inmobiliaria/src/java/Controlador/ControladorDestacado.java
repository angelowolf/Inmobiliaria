/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Persistencia.DAO.Implementacion.DestacadoDAO;
import Persistencia.Modelo.Destacado;
import Persistencia.Modelo.Imagen;
import Persistencia.Modelo.Propiedad;
import Soporte.Archivo;
import Soporte.SingletonRuta;
import java.io.File;
import java.util.List;
import org.apache.commons.lang.WordUtils;

/**
 *
 * @author Angelo
 */
public class ControladorDestacado {

    private final DestacadoDAO destacadoDAO;

    public ControladorDestacado() {
        destacadoDAO = new DestacadoDAO();
    }

    /**
     * Guarda una propiedad destacada, crea las imagenes en el disco.
     *
     * @param destacado La propiedad destacada a crear.
     * @param upload El archivo de la imagen a guardar.
     * @param uploadFileName El nombre del archivo.
     * @return EL id de la propiedad destacada creada.
     */
    public int guardar(Destacado destacado, File upload, String uploadFileName) {
        int id = destacado.getPropiedad().getIdPropiedad();
        String ruta = SingletonRuta.getInstancia().getSTORAGE_PATH() + "ImagenDestacada/id_propiedad_" + id;
        Imagen im = new Imagen();
        im.setRuta(Archivo.crearImagen(ruta, upload, uploadFileName));
        im.setSize(upload.length());
        destacado.setImagen(im);
        ControladorImagen ci = new ControladorImagen();
        ci.guardar(im);
        destacado.setNombre(WordUtils.capitalize(destacado.getNombre()));
        return destacadoDAO.guardar(destacado);
    }

    /**
     * Si guardaImagen es false se eliminara la imagen acutal y se creara la
     * nueva,si es true solo se actualizaran los campos de la propiedad
     * destacada sin modificar a la imagen.
     *
     * @param guardaImagen Indicara si se eliminara una imagen o no.
     * @param destacadoCambiado La propiedad destacada a modificar.
     * @param upload El nuevo archivo a crear.
     * @param uploadFileName El nombre del archivo con el q sera creado.
     */
    public void actualizar(boolean guardaImagen, Destacado destacadoCambiado, File upload, String uploadFileName) {
        boolean flag = false;
        ControladorDestacado cd = new ControladorDestacado();
        ControladorImagen ci = new ControladorImagen();
        Destacado destacadoOriginal = cd.getOne(destacadoCambiado.getIdDestacado());
        if (!guardaImagen) {
            Imagen temp = this.getOne(destacadoCambiado.getIdDestacado()).getImagen();
            //elimino del disco
            String ruta = SingletonRuta.getInstancia().getSTORAGE_PATH() + "ImagenDestacada/id_propiedad_" + destacadoOriginal.getPropiedad().getIdPropiedad();
            Archivo.delete(ruta);
            //elimino de la bd
            ci.eliminar(temp.getIdImagen());
            //creo el nuevo
            int idNuevaPropiedad = destacadoCambiado.getPropiedad().getIdPropiedad();
            ruta = SingletonRuta.getInstancia().getSTORAGE_PATH() + "ImagenDestacada/id_propiedad_" + idNuevaPropiedad;
            Imagen imagen = new Imagen();
            imagen.setRuta(Archivo.crearImagen(ruta, upload, uploadFileName));
            imagen.setSize(upload.length());
            destacadoCambiado.setImagen(imagen);
            ci.guardar(imagen);
            flag = true;
        }
        //si esto cambia tengo q renombrar la carpeta
        if (destacadoOriginal.getPropiedad().getIdPropiedad() != destacadoCambiado.getPropiedad().getIdPropiedad() && !flag) {
            String rutaOriginal = SingletonRuta.getInstancia().getSTORAGE_PATH() + "ImagenDestacada/id_propiedad_" + destacadoOriginal.getPropiedad().getIdPropiedad();
            String rutaCambiada = SingletonRuta.getInstancia().getSTORAGE_PATH() + "ImagenDestacada/id_propiedad_" + destacadoCambiado.getPropiedad().getIdPropiedad();
            Archivo.renombrarCarpeta(rutaOriginal, rutaCambiada);
            String rutamodificada = destacadoOriginal.getImagen().getRuta().replaceFirst("id_propiedad_" + destacadoOriginal.getPropiedad().getIdPropiedad(), "id_propiedad_" + destacadoCambiado.getPropiedad().getIdPropiedad());
            destacadoCambiado.getImagen().setRuta(rutamodificada);
            ci.actualizar(destacadoCambiado.getImagen());
        }
        destacadoCambiado.setNombre(WordUtils.capitalize(destacadoCambiado.getNombre()));
        destacadoDAO.actualizar(destacadoCambiado);
    }

    /**
     * Devuelve una lista con todas las propiedades destacadas que existen.
     *
     * @return La lista
     */
    public List<Destacado> getTodos() {
        return destacadoDAO.listar();
    }

    /**
     * Elimina la propiedad destacada, y la imagen que posea tanto en disco como
     * en bd.
     *
     * @param id De la propiedad destacada.
     */
    public void eliminar(int id) {
        Destacado d = this.getOne(id);
        ControladorImagen ci = new ControladorImagen();
        Archivo.delete(d.getImagen().getRuta());
        ci.eliminar(d.getImagen().getIdImagen());
        destacadoDAO.eliminar(d);
    }

    /**
     * Elimina la propiedad destacada, y la imagen que posea tanto en disco como
     * en bd.
     *
     * @param d La propiedad destacada.
     */
    public void eliminar(Destacado d) {
        ControladorImagen ci = new ControladorImagen();
        Archivo.delete(d.getImagen().getRuta());
        ci.eliminar(d.getImagen().getIdImagen());
        destacadoDAO.eliminar(d);
    }

    /**
     * Busca en la bd la propiedad destacada.
     *
     * @param id EL id de la propiedad destacada.
     * @return La propiedad destacada.
     */
    public Destacado getOne(int id) {
        return destacadoDAO.buscar(id);
    }

    /**
     * Verifica si la propiedad destacada ya existe, tiene algun problema en la
     * validacion.
     *
     * @param destacado
     * @return True si una propiedad ya posee una propiedad destacada.
     */
    public boolean existe(Destacado destacado) {
        ControladorPropiedad controladorPropiedad = new ControladorPropiedad();
        Propiedad p = controladorPropiedad.getOneCodigoPropiedad(destacado.getPropiedad().getCodigoPropiedad());
        if (p == null) {
            return false;
        }
        List<Destacado> lista = destacadoDAO.buscarIdPropiedad(p.getIdPropiedad());
        Destacado m;
        if (!lista.isEmpty()) {
            m = lista.get(0);
            if (m.getPropiedad().getCodigoPropiedad() == destacado.getPropiedad().getCodigoPropiedad()) {
                return m.getIdDestacado() != destacado.getIdDestacado();
            }
        }
        return false;
    }
}
