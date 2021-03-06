/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.DAO.Implementacion;

import Persistencia.DAO.Interface.IServicio;
import Persistencia.DAO.Util.GenericDAO;
import Persistencia.Modelo.Servicio;
import java.util.List;
import org.hibernate.Session;
import java.util.ArrayList;
import org.hibernate.Transaction;

/**
 *
 * @author Angelo
 */
public class ServicioDAO extends GenericDAO<Servicio, Integer> implements IServicio {

    @Override
    public List<Servicio> listar() {
        Session session = getHibernateTemplate();
        List<Servicio> objetos = new ArrayList<Servicio>();
        Transaction tx = null;
        try {

            //   tx = session.beginTransaction();
            objetos = session.createQuery("from Servicio").list();
            //   tx.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return objetos;
    }

    public List<Servicio> buscar(String nombre) {
        Session session = getHibernateTemplate();
        List<Servicio> objetos = new ArrayList<Servicio>();
        try {
            String sql = "from Servicio where nombre = :nombre";
            objetos = session.createQuery(sql).setParameter("nombre", nombre).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return objetos;
    }

    public List<Servicio> servicioEnUso(int id) {

        Session session = getHibernateTemplate();
        List<Servicio> objetos = new ArrayList<Servicio>();
        try {
            String sql = "select * from Servicio s inner join servicios_propiedades sp ON s.id_servicio = sp.id_servicio WHERE s.id_servicio LIKE :id ";
            objetos = session.createSQLQuery(sql).addEntity(Servicio.class).setParameter("id", id).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return objetos;
    }

}
