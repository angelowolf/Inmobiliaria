/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.DAO.Util;

import java.io.Serializable;

public interface IGenericDAO<Entity, PK extends Serializable> {

    void guardar(Entity t);

    void actualizar(Entity t);

    Entity buscar(PK id);

    void eliminar(Entity t);
}
