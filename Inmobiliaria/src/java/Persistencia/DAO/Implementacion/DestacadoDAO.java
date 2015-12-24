/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.DAO.Implementacion;

import Persistencia.DAO.Interface.IDestacado;
import Persistencia.DAO.Util.GenericDAO;
import Persistencia.Modelo.Destacado;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Angelo
 */
public class DestacadoDAO extends GenericDAO<Destacado, Integer> implements IDestacado {

    @Override
    public List<Destacado> listar() {
        Session session = getHibernateTemplate();
        List<Destacado> objetos = new ArrayList<Destacado>();
        try {

            //   tx = session.beginTransaction();
            objetos = session.createQuery("from Destacado").list();
            //   tx.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return objetos;
    }

}
