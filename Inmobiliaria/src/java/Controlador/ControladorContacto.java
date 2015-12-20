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

    public void guardar(Contacto o) {
        o.setDireccion(WordUtils.capitalize(o.getDireccion()));
        contactoDAO.guardar(o);
    }

    public void actualizar(Contacto o) {
        o.setDireccion(WordUtils.capitalize(o.getDireccion()));
        contactoDAO.actualizar(o);
    }

    public void eliminar(Contacto o) {
        contactoDAO.eliminar(o);
    }

    public void eliminar(int id) {
        Contacto m = new Contacto();
        m.setIdContacto(id);
        contactoDAO.eliminar(m);
    }

    public Contacto getOne(int id) {
        return contactoDAO.buscar(id);
    }

}
