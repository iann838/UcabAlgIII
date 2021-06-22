package com.paaksing.java.django;

import com.paaksing.java.django.Validator.ValidationError;

public abstract class Model {

    public int id = Integer.MIN_VALUE;
    public static transient Manager<Model> objects;
    public abstract <T extends Model> Manager<T> getObjects();

    public void clean() throws ValidationError { }

    public int save() throws ValidationError {
        clean();
        if (id == Integer.MIN_VALUE)
            return getObjects().create(this);
        return getObjects().put(id, this);
    }

}
