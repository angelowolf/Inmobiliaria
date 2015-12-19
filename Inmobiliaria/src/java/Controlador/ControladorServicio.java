/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Persistencia.DAO.Implementacion.ServicioDAO;
import Persistencia.Modelo.Servicio;
import java.util.List;

/**
 *
 * @author Angelo
 */
public class ControladorServicio {

    private final ServicioDAO servicioDAO;

    public ControladorServicio() {
        servicioDAO = new ServicioDAO();
    }

    public void guardar(Servicio o) {
        o.setNombre(Soporte.mayusculaPrimeraLetra(o.getNombre()));
        servicioDAO.guardar(o);
    }

    public void actualizar(Servicio o) {
        o.setNombre(Soporte.mayusculaPrimeraLetra(o.getNombre()));
        servicioDAO.actualizar(o);
    }

    public void eliminar(Servicio o) {
        servicioDAO.eliminar(o);
    }

    public List<Servicio> getTodos() {
        return servicioDAO.listar();
    }

    public void eliminar(int id) {
        Servicio m = new Servicio();
        m.setIdServicio(id);
        servicioDAO.eliminar(m);
    }

    public Servicio getOne(int id) {
        return servicioDAO.buscar(id);
    }

    public boolean existe(Servicio o) {
        o.setNombre(Soporte.mayusculaPrimeraLetra(o.getNombre()));
        List<Servicio> lista = servicioDAO.buscar(o.getNombre());
        for (Servicio m : lista) {
            if (m.getNombre().equals(o.getNombre())) {
                return m.getIdServicio() != o.getIdServicio();
            }
        }
        return false;
    }

    public Servicio getOne(String serviciosElegido) {
        serviciosElegido = Soporte.mayusculaPrimeraLetra(serviciosElegido);
        return servicioDAO.buscar(serviciosElegido).get(0);
    }

    public boolean servicioEnUso(int id) {
        return !servicioDAO.servicioEnUso(id).isEmpty();
    }

}
