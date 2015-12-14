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

}
