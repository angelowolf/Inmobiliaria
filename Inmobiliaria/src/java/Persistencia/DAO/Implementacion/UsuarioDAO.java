/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.DAO.Implementacion;

import Persistencia.DAO.Interface.IUsuario;
import Persistencia.DAO.Util.GenericDAO;
import Persistencia.Modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Angelo
 */
public class UsuarioDAO extends GenericDAO<Usuario, Integer> implements IUsuario {

    public Usuario buscar(String username) {
        Session session = getHibernateTemplate();
        List<Usuario> objetos = new ArrayList<Usuario>();
        try {
            String sql = "from Usuario where nick = :nick";
            objetos = session.createQuery(sql).setParameter("nick", username).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        if (!objetos.isEmpty()) {
            return objetos.get(0);
        } else {
            return null;
        }
    }

    public Usuario buscarEmail(String email) {
        Session session = getHibernateTemplate();
        List<Usuario> objetos = new ArrayList<Usuario>();
        try {
            String sql = "from Usuario where email = :email";
            objetos = session.createQuery(sql).setParameter("email", email).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        if (!objetos.isEmpty()) {
            return objetos.get(0);
        } else {
            return null;
        }
    }

}
