/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Persistencia.DAO.Implementacion.TipoMonedaDAO;
import Persistencia.Modelo.TipoMoneda;
import java.util.List;
import org.apache.commons.lang.WordUtils;

/**
 *
 * @author Angelo
 */
public class ControladorTipoMoneda {
    private final TipoMonedaDAO tipoMonedaDAO;

    public ControladorTipoMoneda() {
        tipoMonedaDAO = new TipoMonedaDAO();
    }

    public void guardar(TipoMoneda o) {
        o.setNombre(WordUtils.capitalize(o.getNombre()));
        tipoMonedaDAO.guardar(o);
    }

    public void actualizar(TipoMoneda o) {
         o.setNombre(WordUtils.capitalize(o.getNombre()));
        tipoMonedaDAO.actualizar(o);
    }

    public void eliminar(TipoMoneda o) {
        tipoMonedaDAO.eliminar(o);
    }

    public List<TipoMoneda> getTodos() {
        return tipoMonedaDAO.listar();
    }

    public void eliminar(int id) {
        TipoMoneda m = new TipoMoneda();
        m.setIdTipoMoneda(id);
        tipoMonedaDAO.eliminar(m);
    }

    public TipoMoneda getOne(int id) {
        return tipoMonedaDAO.buscar(id);
    }

    public boolean existe(TipoMoneda o) {
         o.setNombre(WordUtils.capitalize(o.getNombre()));
        List<TipoMoneda> lista = tipoMonedaDAO.buscar(o.getNombre());
        for (TipoMoneda m : lista) {
            if (m.getNombre().equals(o.getNombre())) {
                return m.getIdTipoMoneda() != o.getIdTipoMoneda();
            }
        }
        return false;
    }

    public TipoMoneda getOne(String serviciosElegido) {
        serviciosElegido = Soporte.mayusculaPrimeraLetra(serviciosElegido);
        return tipoMonedaDAO.buscar(serviciosElegido).get(0);
    }

    public boolean tipoMonedaEnUso(int id) {
        return !tipoMonedaDAO.tipoMonedaEnUso(id).isEmpty();
    }
}
