package com.paaksing.jjango;

import java.io.IOException;

import com.paaksing.jjango.Validator.ValidationError;

public abstract class Model {

    private long id = Integer.MIN_VALUE;
    public static transient Manager<Model> objects;
    public abstract <T extends Model> Manager<T> getObjects();

    public void clean() throws ValidationError { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long save() throws ValidationError {
        clean();
        if (id == Integer.MIN_VALUE)
            return this.getObjects().create(this);
        return this.getObjects().put(this.getId(), this);
    }
    
    public boolean delete() throws IOException {
        return this.getObjects().delete(this.getId());
    }

}