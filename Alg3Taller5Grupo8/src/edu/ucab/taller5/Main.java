package edu.ucab.taller5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonWriter;

import edu.ucab.taller5.models.Data;
import edu.ucab.taller5.models.Drink;
import edu.ucab.taller5.serializers.DrinkDeserializer;
import edu.ucab.taller5.serializers.DrinkDeserializer.DrinkNotFound;
import edu.ucab.taller5.serializers.DrinkSerializer;

public class Main {

    public static void main(String[] args) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Drink.class, new DrinkDeserializer());
        gsonBuilder.registerTypeAdapter(Drink.class, new DrinkSerializer());
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Gson gson = gsonBuilder.create();
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.print(">> Path del archivo de entrada: ");
            String inputPath = scan.nextLine();
            System.out.print(">> Path del archivo de salida: ");
            String outputPath = scan.nextLine();
            System.out.println();

            try {
                BufferedReader input = new BufferedReader(new FileReader(inputPath));
                Data data = gson.fromJson(input, Data.class);
                data.calculateCompanyTotals();
                input.close();
                FileWriter output = new FileWriter(outputPath);
                JsonWriter jsonWriter = gson.newJsonWriter(output);
                jsonWriter.setIndent("    ");
                JsonElement jsonElement = gson.toJsonTree(data);
                gson.toJson(jsonElement, jsonWriter);
                output.close();
            } catch (FileNotFoundException e) {
                System.out.println("Archivo " + inputPath + " no existe");
            } catch (IOException e) {
                System.out.println("Archivo " + outputPath + " no se pudo abrir");
            } catch (DrinkNotFound e) {
                System.out.println("Bebida de tipo " + e.getMessage() + " no se pudo identificar");                
            } catch (JsonParseException | NullPointerException e) {
                System.out.println("Archivo " + inputPath + " tiene esquema invalido");
            }

            System.out.print(">> Enter para repetir o X para salir: ");
            String next = scan.nextLine().toUpperCase();
            if (next.equals("X")) break;
        }
        
        
        scan.close();
    }

}
