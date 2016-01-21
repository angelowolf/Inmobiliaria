/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.DAO.Implementacion;

import Persistencia.DAO.Interface.IImagenPropiedad;
import Persistencia.DAO.Util.GenericDAO;
import Persistencia.Modelo.ImagenPropiedad;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Angelo
 */
public class ImagenPropiedadDAO extends GenericDAO<ImagenPropiedad, Integer> implements IImagenPropiedad {

    @Override
    public List<ImagenPropiedad> listar() {
        Session session = getHibernateTemplate();
        List<ImagenPropiedad> objetos = new ArrayList();
        Transaction tx = null;
        try {

            //   tx = session.beginTransaction();
            objetos = session.createQuery("from ImagenPropiedad").list();
            //   tx.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return objetos;
    }

    public List<ImagenPropiedad> listar(int idPropiedad) {
        Session session = getHibernateTemplate();
        List<ImagenPropiedad> objetos = new ArrayList<ImagenPropiedad>();
        try {
            String sql = "from ImagenPropiedad where id_propiedad = :idPropiedad";
            objetos = session.createQuery(sql).setParameter("idPropiedad", idPropiedad).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return objetos;
    }

    public void eliminarTodos(int id) {
        Session session = getHibernateTemplate();
        try {
            session.createSQLQuery("delete from ImagenPropiedad where id_propiedad = :id").setParameter("id", id).executeUpdate();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Devuelve en la posicion 0 la cantidad de archivos y en la posicion 1 el
     * tamaño que ocupan estos en disco.
     *
     * @return
     */
    public List<Object[]> getArchivoTamañoDisponible() {
        Session session = getHibernateTemplate();
        List<Object[]> resultado = null;
        try {
            String hql = "select count(*), sum(i.size) from ImagenPropiedad i";
            resultado = session.createQuery(hql).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return resultado;
    }

}
