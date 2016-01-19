/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Persistencia.DAO.Implementacion.TipoPropiedadDAO;
import Persistencia.Modelo.TipoPropiedad;
import java.util.List;
import org.apache.commons.lang.WordUtils;

/**
 *
 * @author Angelo
 */
public class ControladorTipoPropiedad {

    private final TipoPropiedadDAO tipoPropiedadDAO;

    public ControladorTipoPropiedad() {
        tipoPropiedadDAO = new TipoPropiedadDAO();
    }

    public void guardar(TipoPropiedad o) {
        o.setNombre(WordUtils.capitalize(o.getNombre()));
        tipoPropiedadDAO.guardar(o);
    }

    public void actualizar(TipoPropiedad o) {
        o.setNombre(WordUtils.capitalize(o.getNombre()));
        tipoPropiedadDAO.actualizar(o);
    }

    public void eliminar(TipoPropiedad o) {
        tipoPropiedadDAO.eliminar(o);
    }

    public List<TipoPropiedad> getTodos() {
        return tipoPropiedadDAO.listar();
    }

    public void eliminar(int id) {
        TipoPropiedad m = new TipoPropiedad();
        m.setIdTipoPropiedad(id);
        tipoPropiedadDAO.eliminar(m);
    }

    public TipoPropiedad getOne(int id) {
        return tipoPropiedadDAO.buscar(id);
    }

    /**
     * Verifica si el nombre del tipo de propiedad ya existe.
     *
     * @param o
     * @return True si el nombre ya esta en uso.
     */
    public boolean existe(TipoPropiedad o) {
        o.setNombre(WordUtils.capitalize(o.getNombre()));
        List<TipoPropiedad> lista = tipoPropiedadDAO.buscar(o.getNombre());
        for (TipoPropiedad m : lista) {
            if (m.getNombre().equals(o.getNombre())) {
                return m.getIdTipoPropiedad() != o.getIdTipoPropiedad();
            }
        }
        return false;
    }

    public TipoPropiedad getOne(String nombre) {
        nombre = WordUtils.capitalize(nombre);
        return tipoPropiedadDAO.buscar(nombre).get(0);
    }

    /**
     * Verifica si alguna propiedad usa este tipo de propiedad.
     *
     * @param id
     * @return True si esta siendo utilizado.
     */
    public boolean tipoPropiedadEnUso(int id) {
        return !tipoPropiedadDAO.tipoPropiedadEnUso(id).isEmpty();
    }
}
