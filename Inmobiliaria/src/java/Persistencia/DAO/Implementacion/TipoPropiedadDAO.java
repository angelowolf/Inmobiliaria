/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.DAO.Implementacion;

import Persistencia.DAO.Interface.ITipoPropiedad;
import Persistencia.DAO.Util.GenericDAO;
import Persistencia.Modelo.TipoPropiedad;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Angelo
 */
public class TipoPropiedadDAO extends GenericDAO<TipoPropiedad, Integer> implements ITipoPropiedad {

    @Override
    public List<TipoPropiedad> listar() {
        Session session = getHibernateTemplate();
        List<TipoPropiedad> objetos = new ArrayList<TipoPropiedad>();
        Transaction tx = null;
        try {

            //   tx = session.beginTransaction();
            objetos = session.createQuery("from TipoPropiedad").list();
            //   tx.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return objetos;
    }

    public List<TipoPropiedad> buscar(String nombre) {
        Session session = getHibernateTemplate();
        List<TipoPropiedad> objetos = new ArrayList<TipoPropiedad>();
        try {
            String sql = "from TipoPropiedad where nombre = :nombre";
            objetos = session.createQuery(sql).setParameter("nombre", nombre).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return objetos;
    }

    public List<TipoPropiedad> tipoPropiedadEnUso(int id) {

        Session session = getHibernateTemplate();
        List<TipoPropiedad> objetos = new ArrayList<TipoPropiedad>();
        try {
            String sql = "select * from TipoPropiedad s inner join propiedad sp ON s.id_tipoPropiedad = sp.id_tipoPropiedad WHERE s.id_tipoPropiedad LIKE :id ";
            objetos = session.createSQLQuery(sql).addEntity(TipoPropiedad.class).setParameter("id", id).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return objetos;
    }
}
