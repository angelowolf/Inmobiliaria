/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.DAO.Implementacion;

import Persistencia.DAO.Interface.ITipoMoneda;
import Persistencia.DAO.Util.GenericDAO;
import Persistencia.Modelo.TipoMoneda;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Angelo
 */
public class TipoMonedaDAO extends GenericDAO<TipoMoneda, Integer> implements ITipoMoneda {

    @Override
    public List<TipoMoneda> listar() {
        Session session = getHibernateTemplate();
        List<TipoMoneda> objetos = new ArrayList<TipoMoneda>();
        Transaction tx = null;
        try {

            //   tx = session.beginTransaction();
            objetos = session.createQuery("from TipoMoneda").list();
            //   tx.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return objetos;
    }
    public List<TipoMoneda> buscar(String nombre) {
        Session session = getHibernateTemplate();
        List<TipoMoneda> objetos = new ArrayList<TipoMoneda>();
        try {
            String sql = "from TipoMoneda where nombre = :nombre";
            objetos = session.createQuery(sql).setParameter("nombre", nombre).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return objetos;
    }

    public List<TipoMoneda> tipoMonedaEnUso(int id) {

        Session session = getHibernateTemplate();
        List<TipoMoneda> objetos = new ArrayList<TipoMoneda>();
        try {
            String sql = "select * from TipoMoneda s inner join propiedad sp ON s.id_tipoMoneda = sp.tipoMoneda WHERE s.id_tipoMoneda LIKE :id ";
            objetos = session.createSQLQuery(sql).addEntity(TipoMoneda.class).setParameter("id", id).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return objetos;
    }

}
