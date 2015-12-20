/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.DAO.Implementacion;

import Persistencia.DAO.Interface.IContacto;
import Persistencia.DAO.Util.GenericDAO;
import Persistencia.Modelo.Contacto;
import java.util.List;

/**
 *
 * @author Angelo
 */
public class ContactoDAO extends GenericDAO<Contacto, Integer> implements IContacto {

    @Override
    public List<Contacto> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
