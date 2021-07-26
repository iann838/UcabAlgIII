package edu.ucab.taller6;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import edu.ucab.taller6.models.Shape;
import edu.ucab.taller6.serializers.ShapeDeserializer;
import edu.ucab.taller6.serializers.ShapeDeserializer.ShapeNotFound;
import edu.ucab.taller6.serializers.ShapeSerializer;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

@SuppressWarnings("serial")
public class Main extends JFrame {

    private JPanel contentPane;
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JTextField txtNombreArchivoSalida;
    private JLabel lblArchivoEntradaSeleccionado;
    private JLabel lblDirSalidaSeleccionado;
    private JLabel lblResultado;
    private String outputDirPath;
    private Gson gson;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1038, 610);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Shape.class, new ShapeDeserializer());
        gsonBuilder.registerTypeAdapter(Shape.class, new ShapeSerializer());
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        gson = gsonBuilder.create();

        inputTextArea = new JTextArea();
        inputTextArea.setEditable(false);
        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);
        inputScrollPane.setBounds(10, 106, 494, 457);
        contentPane.add(inputScrollPane);
        inputTextArea.setColumns(10);

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
        outputScrollPane.setBounds(520, 106, 494, 457);
        outputTextArea.setColumns(10);
        contentPane.add(outputScrollPane);
        
        JButton btnSeleccionarEntrada = new JButton("Seleccionar archivo de entrada");
        btnSeleccionarEntrada.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnSeleccionarEntrada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JSON", "json");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(getParent());
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    if (!lblArchivoEntradaSeleccionado.getText().equals(chooser.getSelectedFile().getAbsolutePath().replace('\\', '/')))
                        lblResultado.setText("Direcciones de archivos ha cambiado, reprocesamiento es requerido.");
                    lblArchivoEntradaSeleccionado.setText(chooser.getSelectedFile().getAbsolutePath().replace('\\', '/'));
                }
            }
        });
        btnSeleccionarEntrada.setBounds(124, 16, 202, 21);
        contentPane.add(btnSeleccionarEntrada);
        
        JLabel lblNewLabel = new JLabel("Archivo de entrada:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel.setBounds(10, 19, 140, 17);
        contentPane.add(lblNewLabel);
        
        JLabel lblArchivoDeSalida = new JLabel("Archivo de salida:");
        lblArchivoDeSalida.setVerticalAlignment(SwingConstants.TOP);
        lblArchivoDeSalida.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblArchivoDeSalida.setBounds(10, 48, 140, 17);
        contentPane.add(lblArchivoDeSalida);
        
        JButton btnSeleccionarDirSalida = new JButton("Seleccionar directorio de salida");
        btnSeleccionarDirSalida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = chooser.showOpenDialog(getParent());
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    outputDirPath = chooser.getSelectedFile().getAbsolutePath();
                    if (!lblArchivoEntradaSeleccionado.getText().equals((outputDirPath + txtNombreArchivoSalida.getText()).replace('\\', '/')))
                        lblResultado.setText("Direcciones de archivos ha cambiado, reprocesamiento es requerido.");
                    lblDirSalidaSeleccionado.setText((outputDirPath + txtNombreArchivoSalida.getText()).replace('\\', '/'));
                }
            }
        });
        btnSeleccionarDirSalida.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnSeleccionarDirSalida.setBounds(111, 45, 202, 21);
        contentPane.add(btnSeleccionarDirSalida);
        
        txtNombreArchivoSalida = new JTextField();
        txtNombreArchivoSalida.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {updateFieldState();}
                @Override
                public void removeUpdate(DocumentEvent e) {updateFieldState();}
                @Override
                public void changedUpdate(DocumentEvent e) {updateFieldState();}
                protected void updateFieldState() {
                    if (lblDirSalidaSeleccionado != null && outputDirPath != null)
                        lblDirSalidaSeleccionado.setText(outputDirPath + txtNombreArchivoSalida.getText());
                }
        });
        txtNombreArchivoSalida.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtNombreArchivoSalida.setText("/out.json");
        txtNombreArchivoSalida.setBounds(323, 46, 96, 19);
        contentPane.add(txtNombreArchivoSalida);
        txtNombreArchivoSalida.setColumns(10);
        
        JButton btnProcesar = new JButton("Procesar");
        btnProcesar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (lblArchivoEntradaSeleccionado.getText().length() == 0) {
                    lblResultado.setText("Direccion del archivo de entrada es requerido.");
                } else if (lblDirSalidaSeleccionado.getText().length() == 0) {
                    lblResultado.setText("Direccion del archivo de salida es requerido.");                    
                } else {
                    try {
                        BufferedReader input = new BufferedReader(new FileReader(lblArchivoEntradaSeleccionado.getText()));
                        java.lang.reflect.Type ShapeList = new TypeToken<ArrayList<Shape>>(){}.getType();
                        ArrayList<Shape> shapes = gson.fromJson(input, ShapeList);
                        input.close();

                        BufferedReader inputText = new BufferedReader(new FileReader(lblArchivoEntradaSeleccionado.getText()));
                        inputTextArea.setText(inputText.lines().collect(Collectors.joining(System.lineSeparator())));
                        inputText.close();

                        for (Shape shape: shapes)
                            shape.calculateArea();

                        Files.createDirectories(Paths.get(outputDirPath));
                        FileWriter output = new FileWriter(lblDirSalidaSeleccionado.getText());
                        JsonWriter jsonWriter = gson.newJsonWriter(output);
                        jsonWriter.setIndent("    ");
                        JsonElement jsonElement = gson.toJsonTree(shapes);
                        gson.toJson(jsonElement, jsonWriter);
                        output.close();
                        lblResultado.setText("Archivo de salida generado exitosamente.");

                        BufferedReader outputText = new BufferedReader(new FileReader(lblDirSalidaSeleccionado.getText()));
                        outputTextArea.setText(outputText.lines().collect(Collectors.joining(System.lineSeparator())));
                        outputText.close();
                    } catch (FileNotFoundException e1) {
                        lblResultado.setText("Archivos inexistentes y/o restringidos.");
                    } catch (IOException e1) {
                        lblResultado.setText("Error en operaciones de archivos.");
                    } catch (ShapeNotFound e1) {
                        lblResultado.setText("Figura de tipo " + e1.getMessage() + " no es reconocida.");
                    } catch (JsonParseException | NullPointerException | NumberFormatException e1) {
                        lblResultado.setText("Archivo de entrada no valido.");
                    }
                }
            }
        });
        btnProcesar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnProcesar.setBounds(10, 75, 131, 21);
        contentPane.add(btnProcesar);
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnSalir.setBounds(883, 76, 131, 21);
        contentPane.add(btnSalir);
        
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputTextArea.setText("");
                outputTextArea.setText("");
            }
        });
        btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnLimpiar.setBounds(742, 76, 131, 21);
        contentPane.add(btnLimpiar);
        
        lblArchivoEntradaSeleccionado = new JLabel("");
        lblArchivoEntradaSeleccionado.setVerticalAlignment(SwingConstants.TOP);
        lblArchivoEntradaSeleccionado.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblArchivoEntradaSeleccionado.setBounds(336, 19, 678, 17);
        contentPane.add(lblArchivoEntradaSeleccionado);
        
        lblDirSalidaSeleccionado = new JLabel("");
        lblDirSalidaSeleccionado.setVerticalAlignment(SwingConstants.TOP);
        lblDirSalidaSeleccionado.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblDirSalidaSeleccionado.setBounds(429, 48, 585, 17);
        contentPane.add(lblDirSalidaSeleccionado);
        
        lblResultado = new JLabel("");
        lblResultado.setVerticalAlignment(SwingConstants.BOTTOM);
        lblResultado.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblResultado.setBounds(151, 76, 581, 17);
        contentPane.add(lblResultado);
    }
}
