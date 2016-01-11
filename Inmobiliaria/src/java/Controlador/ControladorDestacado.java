/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Acciones.PropiedadAction;
import Persistencia.DAO.Implementacion.DestacadoDAO;
import Persistencia.Modelo.Destacado;
import Persistencia.Modelo.Imagen;
import Soporte.Archivo;
import Soporte.SingletonRuta;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
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
            ci.eliminarImagen(ruta);
            //elimino de la bd
            ci.eliminar(temp.getIdImagen());
            //creo el nuevo
            int idNuevaPropiedad = destacadoCambiado.getPropiedad().getIdPropiedad();
            ruta = SingletonRuta.getInstancia().getSTORAGE_PATH() + "ImagenDestacada/id_propiedad_" + idNuevaPropiedad;
            Imagen imagen = new Imagen();
            imagen.setRuta(Archivo.crearImagen(ruta, upload, uploadFileName));
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
        ci.eliminarImagen(d.getImagen().getRuta());
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
        ci.eliminarImagen(d.getImagen().getRuta());
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
}
