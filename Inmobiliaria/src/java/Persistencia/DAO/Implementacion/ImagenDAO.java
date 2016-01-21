/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.DAO.Implementacion;

import Persistencia.DAO.Interface.IImagen;
import Persistencia.DAO.Util.GenericDAO;
import Persistencia.Modelo.Imagen;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Angelo
 */
public class ImagenDAO extends GenericDAO<Imagen, Integer> implements IImagen {

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
            String hql = "select count(*), sum(i.size) from Imagen i";
            resultado = session.createQuery(hql).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return resultado;
    }

}
