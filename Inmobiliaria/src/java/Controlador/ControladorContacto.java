/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Persistencia.DAO.Implementacion.ContactoDAO;
import Persistencia.Modelo.Contacto;
import org.apache.commons.lang.WordUtils;

/**
 *
 * @author Angelo
 */
public class ControladorContacto {

    private final ContactoDAO contactoDAO;

    public ControladorContacto() {
        contactoDAO = new ContactoDAO();
    }

    /**
     * Guarda un contacto.
     *
     * @param o El contacto.
     */
    public void guardar(Contacto o) {
        o.setDireccion(WordUtils.capitalize(o.getDireccion()));
        contactoDAO.guardar(o);
    }

    /**
     * Actualiza el contacto. Convierte a mayuscula la primera letra de la
     * direccion y el nombre.
     *
     * @param o El contacto.
     */
    public void actualizar(Contacto o) {
        o.setDireccion(WordUtils.capitalize(o.getDireccion()));
        o.setNombre(WordUtils.capitalize(o.getNombre()));
        contactoDAO.actualizar(o);
    }

    /**
     * Elimina el contacto.
     *
     * @param o El contacto.
     */
    public void eliminar(Contacto o) {
        contactoDAO.eliminar(o);
    }

    /**
     * Elimina el contacto.
     *
     * @param id El id del contacto.
     */
    public void eliminar(int id) {
        Contacto m = new Contacto();
        m.setIdContacto(id);
        contactoDAO.eliminar(m);
    }

    /**
     * Retorna el ultimo contacto.
     *
     * @return El contacto.
     */
    public Contacto getOne() {
        return contactoDAO.contacto();
    }

}
