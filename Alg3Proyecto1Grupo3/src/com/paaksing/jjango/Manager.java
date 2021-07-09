package com.paaksing.jjango;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paaksing.jjango.Database.ObjectDoesNotExist;

public class Manager<T extends Model> {

    private String path;
    private Class<T> owner;
    private static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();
    public Validator<Manager<T>, T> validator;

    public Manager(String dir, Class<T> classOfT) {
        path = dir;
        owner = classOfT;
        validator = new Validator<Manager<T>, T>(this);
    }

    public T get(long id) throws ObjectDoesNotExist, IOException {
        BufferedReader buffer = Database.get(path, id);
        T object = gson.fromJson(buffer, owner);
        buffer.close();
        return object;
    }

    public List<T> all() throws IOException {
        List<BufferedReader> buffers = Database.all(path);
        List<T> objects = new ArrayList<T>();
        for (BufferedReader buffer: buffers) {
            T object = gson.fromJson(buffer, owner);
            objects.add(object);
            buffer.close();
        }
        return objects;
    }

    @SuppressWarnings("unchecked")
    public <E> T first(String key, E value) throws ObjectDoesNotExist, IOException {
        List<T> objects = all();
        for (T object: objects) {
            try {
                Class<?> c = object.getClass();
                Field f = c.getDeclaredField(key);
                f.setAccessible(true);
                E foundValue = (E) f.get(object);
                if (foundValue == value || foundValue.equals(value))
                    return object;
            } catch (Exception e) { }
        }
        throw new ObjectDoesNotExist(key + ": " + value.toString());
    }

    @SuppressWarnings("unchecked")
    public <E> List<T> filter(String key, E value) throws IOException {
        List<T> objects = all();
        List<T> filtered = new ArrayList<T>();
        for (T object: objects) {
            try {
                Class<?> c = object.getClass();
                Field f = c.getDeclaredField(key);
                f.setAccessible(true);
                E foundValue = (E) f.get(object);
                if (foundValue == value || foundValue.equals(value))
                    filtered.add(object);
            } catch (Exception e) { }
        }
        return filtered;
    }
    
    public long create(T o) {
        return Database.create(path, o);
    }

    public long put(long id, T o) {
        return Database.put(path, id, o);
    }

    public boolean delete(long id) throws IOException {
        return Database.delete(path, id);
    }
    
    public boolean drop() throws IOException {
        boolean allDeleted = true;
        for (T o: this.all()) {            
            if (!Database.delete(path, o.getId()))
                allDeleted = false;
        }
        return allDeleted;
    }

}