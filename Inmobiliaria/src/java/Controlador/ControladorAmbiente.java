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

    /**
     * Guarda un ambiente.
     *
     * @param o El ambiente a guardar.
     */
    public void guardar(Ambiente o) {
        o.setNombre(WordUtils.capitalize(o.getNombre()));
        ambienteDAO.guardar(o);
    }

    /**
     * Actualiza un ambiente.
     *
     * @param o El ambiente a actualizar.
     */
    public void actualizar(Ambiente o) {
        o.setNombre(WordUtils.capitalize(o.getNombre()));
        ambienteDAO.actualizar(o);
    }

    /**
     * Eilimina un ambiente.
     *
     * @param o El ambiente a eliminar.
     */
    public void eliminar(Ambiente o) {
        ambienteDAO.eliminar(o);
    }

    /**
     * Retorna todos los ambientes que existen.
     *
     * @return Los ambientes.
     */
    public List<Ambiente> getTodos() {
        return ambienteDAO.listar();
    }

    /**
     * Eilimina un ambiente.
     *
     * @param id El id del ambiente a eliminar.
     */
    public void eliminar(int id) {
        Ambiente m = new Ambiente();
        m.setIdAmbiente(id);
        ambienteDAO.eliminar(m);
    }

    /**
     * Retorna un ambiente segun su ID.
     *
     * @param id El id del ambiente.
     * @return EL ambiente.
     */
    public Ambiente getOne(int id) {
        return ambienteDAO.buscar(id);
    }

    /**
     * Verifica si un ambiente ya existe, verifica si el nombre del ambiente por
     * el que se pregunta ya esta almacenado o no.
     *
     * @param o El ambiente a consultar.
     * @return True si existe.
     */
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

    /**
     * Busca un ambiente por su nombre.
     *
     * @param nombre El nombre del ambiente.
     * @return El ambiente encontrado.
     */
    public Ambiente getOne(String nombre) {
        nombre = WordUtils.capitalize(nombre);
        return ambienteDAO.buscar(nombre).get(0);
    }

    /**
     * Verifica si el ambiente esta siendo utilizado por alguna otra entidad
     * (Propiedades).
     *
     * @param id El id del ambiente.
     * @return True si esta siendo utilizado.
     */
    public boolean ambienteEnUso(int id) {
        return !ambienteDAO.ambienteEnUso(id).isEmpty();
    }

}
