/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Persistencia.DAO.Implementacion.AmbienteDAO;
import Persistencia.Modelo.Ambiente;
import java.util.List;
import org.apache.commons.lang.WordUtils;

/**
 *
 * @author Angelo
 */
public class ControladorAmbiente {

    private final AmbienteDAO ambienteDAO;

    public ControladorAmbiente() {
        ambienteDAO = new AmbienteDAO();
    }

    public void guardar(Ambiente o) {
        o.setNombre(WordUtils.capitalize(o.getNombre()));
        ambienteDAO.guardar(o);
    }

    public void actualizar(Ambiente o) {
        o.setNombre(WordUtils.capitalize(o.getNombre()));
        ambienteDAO.actualizar(o);
    }

    public void eliminar(Ambiente o) {
        ambienteDAO.eliminar(o);
    }

    public List<Ambiente> getTodos() {
        return ambienteDAO.listar();
    }

    public void eliminar(int id) {
        Ambiente m = new Ambiente();
        m.setIdAmbiente(id);
        ambienteDAO.eliminar(m);
    }

    public Ambiente getOne(int id) {
        return ambienteDAO.buscar(id);
    }

    public boolean existe(Ambiente o) {
        o.setNombre(WordUtils.capitalize(o.getNombre()));
        List<Ambiente> lista = ambienteDAO.buscar(o.getNombre());
        for (Ambiente m : lista) {
            if (m.getNombre().equals(o.getNombre())) {
                return m.getIdAmbiente() != o.getIdAmbiente();
            }
        }
        return false;
    }

    public Ambiente getOne(String serviciosElegido) {
        serviciosElegido = Soporte.mayusculaPrimeraLetra(serviciosElegido);
        return ambienteDAO.buscar(serviciosElegido).get(0);
    }

    public boolean ambienteEnUso(int id) {
        return !ambienteDAO.ambienteEnUso(id).isEmpty();
    }

}
