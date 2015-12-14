/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.DAO.Util;

import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.lang.reflect.ParameterizedType;
import org.hibernate.Transaction;

public class GenericDAO<Entity, K extends Serializable> implements IGenericDAO<Entity, K> {

    public Class<Entity> domainClass = getDomainClass();

    protected Class getDomainClass() {
        if (domainClass == null) {
            ParameterizedType thisType = (ParameterizedType) getClass()
                    .getGenericSuperclass();
            domainClass = (Class) thisType.getActualTypeArguments()[0];
        }
        return domainClass;
    }

    protected Session getHibernateTemplate() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    @Override
    public void guardar(Entity t) {
        try {
            getHibernateTemplate().save(t);
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public void actualizar(Entity t) {
        try {
            getHibernateTemplate().merge(t);
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public Entity buscar(K id) {
        Entity returnValue;
        try {
            returnValue = (Entity) getHibernateTemplate().load(domainClass, id);
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
        return returnValue;
    }

    @Override
    public void eliminar(Entity t) {
        try {
            Object temp = getHibernateTemplate().merge(t);
            getHibernateTemplate().delete(temp);
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

}
