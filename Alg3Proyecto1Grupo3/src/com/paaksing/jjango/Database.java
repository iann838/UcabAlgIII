package com.paaksing.jjango;

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

    private static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();

    @SuppressWarnings("serial")
    public static class ObjectDoesNotExist extends Exception { 
        public ObjectDoesNotExist(String errorMessage) {
            super(errorMessage);
        }
    }

    private static String parsePath(String path, long id) {
        return path + "/" + Long.toString(id) + ".json";
    }

    private static void updateIndex(String path, List<Long> index) throws IOException {
        index.sort(Long::compare);
        Files.createDirectories(Paths.get(path));
        FileWriter writer = new FileWriter(path + "/index.json");
        gson.toJson(index, writer);
        writer.flush();
    }

    private static List<Long> getIndex(String path) throws IOException {
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
        Type ListInteger = new TypeToken<ArrayList<Long>>(){}.getType();
        List<Long> index = gson.fromJson(bufferedReader, ListInteger);
        bufferedReader.close();
        return index;
    }

    public static BufferedReader get(String path, long id) throws ObjectDoesNotExist, IOException {
        List<Long> index = getIndex(path);
        if (!index.contains(id)) {
            throw new ObjectDoesNotExist("id: " + Long.toString(id));
        }
        return new BufferedReader(new FileReader(parsePath(path, id)));
    }

    public static long put(String path, long id, Model o) { try {
        List<Long> index = getIndex(path);
        o.setId(id);
        if (!index.contains(id)) {
            index.add(id);
            updateIndex(path, index);
        }
        FileWriter writer = new FileWriter(parsePath(path, id));
        gson.toJson(o, writer);
        writer.close();
        return id;
    } catch ( Exception e ) { throw new RuntimeException( e ); } }

    public static boolean delete(String path, long id) throws IOException {
        List<Long> index = getIndex(path);
        if (index.contains(id)) {
            index.remove(id);
        }
        File json = new File(parsePath(path, id));
        return json.delete();
    }

    public static <T extends Model> long create(String path, T o) { try {
        List<Long> index = getIndex(path);
        long id;
        if (index.size() > 0) {
            id = index.get(index.size() - 1) + 1;
        } else {
            id = 0;
        }
        o.setId(id);
        index.add(id);
        updateIndex(path, index);
        FileWriter writer = new FileWriter(parsePath(path, id));
        gson.toJson(o, writer);
        writer.close();
        return id;
    } catch ( Exception e ) { throw new RuntimeException( e ); } }

    public static List<BufferedReader> all(String path) throws IOException {
        List<Long> index = getIndex(path);
        List<BufferedReader> buffers = new ArrayList<BufferedReader>();
        for (long id: index) {
            try {
                buffers.add(get(path, id));
            } catch (ObjectDoesNotExist e) { }
        }
        return buffers;
    }

}