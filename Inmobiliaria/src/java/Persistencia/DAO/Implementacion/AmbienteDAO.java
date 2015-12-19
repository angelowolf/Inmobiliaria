/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.DAO.Implementacion;

import Persistencia.DAO.Interface.IAmbiente;
import Persistencia.DAO.Util.GenericDAO;
import Persistencia.Modelo.Ambiente;
import Persistencia.Modelo.Servicio;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Angelo
 */
public class AmbienteDAO extends GenericDAO<Ambiente, Integer> implements IAmbiente {

    @Override
    public List<Ambiente> listar() {
        Session session = getHibernateTemplate();
        List<Ambiente> objetos = new ArrayList<Ambiente>();
        Transaction tx = null;
        try {

            //   tx = session.beginTransaction();
            objetos = session.createQuery("from Ambiente").list();
            //   tx.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return objetos;
    }

    public List<Ambiente> buscar(String nombre) {
        Session session = getHibernateTemplate();
        List<Ambiente> objetos = new ArrayList<Ambiente>();
        try {
            String sql = "from Ambiente where nombre = :nombre";
            objetos = session.createQuery(sql).setParameter("nombre", nombre).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return objetos;
    }

    public List<Ambiente> ambienteEnUso(int id) {

        Session session = getHibernateTemplate();
        List<Ambiente> objetos = new ArrayList<Ambiente>();
        try {
            String sql = "select * from Ambiente s inner join ambientes_propiedades sp ON s.id_ambiente = sp.id_ambiente WHERE s.id_ambiente LIKE :id ";
            objetos = session.createSQLQuery(sql).addEntity(Servicio.class).setParameter("id", id).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return objetos;
    }
}
