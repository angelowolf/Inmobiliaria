/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.DAO.Implementacion;

import Persistencia.DAO.Interface.IPropiedad;
import Persistencia.DAO.Util.GenericDAO;
import Persistencia.Modelo.Propiedad;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Angelo
 */
public class PropiedadDAO extends GenericDAO<Propiedad, Integer> implements IPropiedad {

    @Override
    public List<Propiedad> listar() {
        Session session = getHibernateTemplate();
        List<Propiedad> objetos = new ArrayList();
        Transaction tx = null;
        try {

            //   tx = session.beginTransaction();
            objetos = session.createQuery("from Propiedad").list();
            //   tx.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return objetos;
    }

    public List<Propiedad> listarOportunidades() {
        Session session = getHibernateTemplate();
        List<Propiedad> objetos = new ArrayList<Propiedad>();
        try {
            String sql = "from Propiedad where oportunidad = 1";
            objetos = session.createQuery(sql).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return objetos;
    }

    public List<Propiedad> buscar(int idTipoPropiedad, int habitacion, int bano) {
        Session session = getHibernateTemplate();
        List<Propiedad> objetos = new ArrayList<Propiedad>();
        boolean tp = false;
        boolean h = false;
        boolean b = false;
        try {
            String sql = "from Propiedad WHERE 1=1 ";
            if (idTipoPropiedad > 0) {
                sql += "and idTipoPropiedad = :idTipoPropiedad ";
                tp = true;
            }
            if (habitacion > 0) {
                sql += "and habitacion = :habitacion ";
                h = true;
            }
            if (bano > 0) {
                sql += "and bano = :bano";
                b = true;
            }
            Query sqlq = session.createQuery(sql);
            if (tp) {
                sqlq.setParameter("idTipoPropiedad", idTipoPropiedad);
            }
            if (h) {
                sqlq.setParameter("habitacion", habitacion);
            }
            if (b) {
                sqlq.setParameter("bano", bano);
            }
            objetos = sqlq.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return objetos;
    }

    public List<Propiedad> propiedadEnUso(int id) {
    
        Session session = getHibernateTemplate();
        List<Propiedad> objetos = new ArrayList<Propiedad>();
        try {
            String sql = "select * from Propiedad s inner join destacado sp ON s.id_propiedad = sp.id_propiedad WHERE s.id_propiedad LIKE :id ";
            objetos = session.createSQLQuery(sql).addEntity(Propiedad.class).setParameter("id", id).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return objetos;
    }

    public List<Propiedad> buscarCodigo(int codigoPropiedad) {
        Session session = getHibernateTemplate();
        List<Propiedad> objetos = new ArrayList<Propiedad>();
        try {
            String sql = "from Propiedad where codigoPropiedad = :codigoPropiedad";
            objetos = session.createQuery(sql).setParameter("codigoPropiedad", codigoPropiedad).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return objetos;
    }

}
