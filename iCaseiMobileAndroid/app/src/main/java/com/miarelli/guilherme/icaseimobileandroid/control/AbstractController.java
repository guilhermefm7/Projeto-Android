package com.miarelli.guilherme.icaseimobileandroid.control;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by guilherme.miarelli on 23/04/2016.
 * Abstract Controller, where has been defined generic methods, which will be used for other controllers
 */
public abstract class AbstractController<T,A> {

    private T entity;
    private A entityService;

    public AbstractController(T entityClass) {
        this.entity = entityClass;
    }

    public AbstractController(T entityClass, A entityService) {
        this.entity = entityClass;
        this.entityService = entityService;
    }

    public AbstractController() {
    }

    /**
     * Method responsible for getting a new entity instantiation
     * @return
     */
    public T getEntity() {
        if(this.entity == null){
            try {
                this.entity =  getTypeParameterClass().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return this.entity;
    }

    /**
     * Method responsible for getting a new entity activity instantiation
     * @return
     */
    public A getEntityService() {
        if(this.entityService == null){
            try {
                this.entityService =  getTypeParameterClassEntity().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return this.entityService;
    }

    /**
     * Method responsible for getting the model type of the controller which is calling it
     * @return
     */
    public Class<T> getTypeParameterClass(){
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        return (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    /**
     * Method responsible for getting the model type of the controller which is calling it
     * @return
     */
    public Class<A> getTypeParameterClassEntity(){
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        return (Class<A>) parameterizedType.getActualTypeArguments()[0];
    }

    public void setEntity(T entityClass) {
        this.entity = entityClass;
    }

    public void setEntityService(A entityService) {
        this.entityService = entityService;
    }

}
