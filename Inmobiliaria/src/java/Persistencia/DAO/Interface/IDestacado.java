/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.DAO.Interface;

import Persistencia.DAO.Util.IGenericDAO;
import Persistencia.Modelo.Destacado;
import java.util.List;

/**
 *
 * @author Angelo
 */
public interface IDestacado extends IGenericDAO<Destacado, Integer> {

    public List<Destacado> listar();
}
