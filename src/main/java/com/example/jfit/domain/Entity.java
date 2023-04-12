package com.example.jfit.domain;

import java.io.Serializable;

public class Entity<ID> implements Serializable{

    /**
     * id of entity
     */
    private ID id;

    /**
     * returns entity's id
     * @return id
     */
    public ID getId() { return id; }

    /**
     * sets a new id for the entity
     * @param id - new id of entity
     */
    public void setId(ID id) { this.id = id; }

}