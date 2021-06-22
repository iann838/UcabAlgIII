package com.paaksing.java.django;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Database {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @SuppressWarnings("serial")
    public static class ObjectDoesNotExist extends Exception { 
        public ObjectDoesNotExist(String errorMessage) {
            super(errorMessage);
        }
    }

    private static String parsePath(String path, int id) {
        return path + "/" + Integer.toString(id) + ".json";
    }

    private static void updateIndex(String path, List<Integer> index) throws IOException {
        index.sort(Integer::compare);
        Files.createDirectories(Paths.get(path));
        FileWriter writer = new FileWriter(path + "/index.json");
        gson.toJson(index, writer);
        writer.flush();
    }

    private static List<Integer> getIndex(String path) throws IOException {
        File json = new File(path + "/index.json");
        Files.createDirectories(Paths.get(path));
        if (!json.exists()) {
            json.createNewFile();
            List<Integer> emptyList = new ArrayList<Integer>();
            FileWriter writer = new FileWriter(path + "/index.json");
            gson.toJson(emptyList, writer);
            writer.close();
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path + "/index.json"));
        Type ListInteger = new TypeToken<ArrayList<Integer>>(){}.getType();
        List<Integer> index = gson.fromJson(bufferedReader, ListInteger);
        bufferedReader.close();
        return index;
    }

    public static BufferedReader get(String path, int id) throws ObjectDoesNotExist, IOException {
        List<Integer> index = getIndex(path);
        if (!index.contains(id)) {
            throw new ObjectDoesNotExist("id: " + Integer.toString(id));
        }
        return new BufferedReader(new FileReader(parsePath(path, id)));
    }

    public static int put(String path, int id, Model o) { try {
        List<Integer> index = getIndex(path);
        o.id = id;
        if (!index.contains(id)) {
            index.add(id);
            updateIndex(path, index);
        }
        FileWriter writer = new FileWriter(parsePath(path, id));
        gson.toJson(o, writer);
        writer.close();
        return id;
    } catch ( Exception e ) { throw new RuntimeException( e ); } }

    public static <T extends Model> int create(String path, T o) { try {
        List<Integer> index = getIndex(path);
        int id;
        if (index.size() > 0) {
            id = index.get(index.size() - 1) + 1;
        } else {
            id = 0;
        }
        o.id = id;
        index.add(id);
        updateIndex(path, index);
        FileWriter writer = new FileWriter(parsePath(path, id));
        gson.toJson(o, writer);
        writer.close();
        return id;
    } catch ( Exception e ) { throw new RuntimeException( e ); } }

    public static List<BufferedReader> all(String path) throws IOException {
        List<Integer> index = getIndex(path);
        List<BufferedReader> buffers = new ArrayList<BufferedReader>();
        for (int id: index) {
            try {
                buffers.add(get(path, id));
            } catch (ObjectDoesNotExist e) { }
        }
        return buffers;
    }

}
