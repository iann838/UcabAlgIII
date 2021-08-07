package edu.ucab.cryptomonitor.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.paaksing.jjango.Database.ObjectDoesNotExist;

import edu.ucab.cryptomonitor.app.Response;
import edu.ucab.cryptomonitor.app.Status;
import edu.ucab.cryptomonitor.app.View;
import edu.ucab.cryptomonitor.models.Currency;
import edu.ucab.cryptomonitor.others.DoubleJTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.JComboBox;

/**
*
* @author Jian Feng
* @author Arturo Hung
* @author Kleysber Noguera
* 
*/

@SuppressWarnings("serial")
public class CalculatorView extends View implements ActionListener {

    /*
     *   Atributos y/o campos de clase:
     *   
     *   container: El contenedor de la ventana de interfaz gráfica,
     *   			la cual posee en su composición el resto de atributos
     *   			y campos declarados en la clase
     *   
     *   labelBackground: Etiqueta que muestra en la ventana 
     *                    la imagen de fondo "calculadora.png"
     *                    
     *   labelLogo: Etiqueta que muestra en la ventana el ícono
     *   			"logo.png"
     *   
     *   labelTitle: Etiqueta que muestra en ventana el título de imagen
     *               "letras.png"
     *               
     *   buttonCalculate: Botón que al accionar permite el 
     *            	      cálculo de conversión entre criptomonedas 
     *                
     *   buttonClear: Botón que al accionar permite la 
     *                limpieza de los datos ingresados en la calculadora
     *                
     *   buttonReturn: Botón que al accionar permite el 
     *                 redireccionamiento a la clase HomeView 
     *                 
     *   comoboBase, comboChange: Cuadros desplegables de criptomonedas
     *   						  (0-60)}
     *   
     *   labelAmount: Etiqueta que muestra en ventana el mensaje 
     *                de "Cantidad"
     *                
     *   txtAmount: Campo de texto para colocar la cantidad 
     *              que se desea convertir entre criptomonedas
     *              
     *   labelResult: Etiqueta que muestra en ventana el mensaje 
     *                de "Resultado"
     *                
     *   txtResult: Campo de texto para mostrar el resultado
     *              de la conversión
     *              
     *   currencyOptions: Colección de datos empleada para 
     *                    la archivación del rango de cripotmonedas
     *                    
     */
	
	
    private JPanel container;

    private JLabel labelBackground, labelLogo;
    private JLabel labelTitle;
    private JButton buttonCalculate, buttonClear, buttonReturn;

    @SuppressWarnings("rawtypes")
    private static JComboBox comboBase, comboChange;

    private Image image, newImg;
    private ImageIcon iconView, imageIcon;
    private final String iconPath = "resources/logo.png";

    private JLabel labelAmount;
    private JLabel labelCryptoBase;
    private JLabel labelCryptoChange;
    private JLabel labelResult;
    
    private static DoubleJTextField txtAmount;

    private static JTextField txtResult;
    private ArrayList<String> currencyOptions;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    
    /**
    * Constructor por defecto de la clase 
    * @param El constructor no posee ningún parámetro
    */ 
    
    public CalculatorView() {

    	// Configuración general de la ventana
    	
        CalculatorView self = this;
        setBounds(100, 100, 900, 700);
        setTitle("UCABIT - Ventana de Calculadora de criptomonedas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iconView = new ImageIcon(iconPath);
        setIconImage(iconView.getImage());
        setResizable(false);

        // Etiqueta de fondo
        
        labelBackground = new JLabel("");
        labelBackground.setBounds(395, 0, 502, 849);

        imageIcon = new ImageIcon("resources/calculadora.png");
        image = imageIcon.getImage();
        newImg = image.getScaledInstance(502, 849, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);
        labelBackground = new JLabel(imageIcon);

        labelBackground.setBounds(396, 0, 498, 665);

        // Etiqueta de logo
        
        labelLogo = new JLabel("");
        labelLogo.setBounds(121, 37, 137, 137);

        imageIcon = new ImageIcon("resources/logo.png");
        image = imageIcon.getImage();
        newImg = image.getScaledInstance(137, 137, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);

        labelLogo = new JLabel(imageIcon);
        labelLogo.setBounds(141, 28, 137, 137);

        // Etiqueta de título
        
        labelTitle = new JLabel("Calculadora de conversi\u00F3n");
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
        labelTitle.setBounds(45, 175, 313, 33);

        // Etiqueta de cantidad
        
        labelAmount = new JLabel("Cantidad a convertir:");
        labelAmount.setHorizontalAlignment(SwingConstants.LEFT);
        labelAmount.setFont(new Font("Tahoma", Font.BOLD, 17));
        labelAmount.setBounds(45, 218, 216, 33);

        // Campo de texto de cantidad
        
        txtAmount = new DoubleJTextField();
        txtAmount.setHorizontalAlignment(SwingConstants.CENTER);
        txtAmount.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtAmount.setBounds(45, 264, 301, 33);

        // Etiqueta de criptomoneda base
        
        labelCryptoBase = new JLabel("Criptomoneda base:");
        labelCryptoBase.setHorizontalAlignment(SwingConstants.LEFT);
        labelCryptoBase.setFont(new Font("Tahoma", Font.BOLD, 17));
        labelCryptoBase.setBounds(45, 307, 201, 33);

        try {
            List<Currency> currencies = Currency.objects.all();
            currencyOptions = new ArrayList<String>();
            for (Currency currency : currencies) {
                if (currency.getRank() > 60)
                    continue;
                currencyOptions.add(currency.getName() + " -- " + currency.getTag());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(self, "Error en accesso a database.");
        }

        // Cuadro desplegable de criptomoneda base
        
        comboBase = new JComboBox(currencyOptions.toArray());
        comboBase.setFont(new Font("Tahoma", Font.PLAIN, 20));
        comboBase.setBounds(45, 350, 233, 31);

        // Etiqueta de criptomoneda a convertir
        
        labelCryptoChange = new JLabel("Criptomoneda de cambio:");
        labelCryptoChange.setHorizontalAlignment(SwingConstants.LEFT);
        labelCryptoChange.setFont(new Font("Tahoma", Font.BOLD, 17));
        labelCryptoChange.setBounds(45, 395, 261, 33);

        // Cuadro desplegable de criptomoneda a convertir
        
        comboChange = new JComboBox(currencyOptions.toArray());
        comboChange.setFont(new Font("Tahoma", Font.PLAIN, 20));
        comboChange.setBounds(45, 438, 233, 31);

        // Botón para calcular conversión
        
        buttonCalculate = new JButton("Calcular");
        buttonCalculate.setFont(new Font("Tahoma", Font.BOLD, 16));
        buttonCalculate.setBounds(45, 489, 101, 54);
        buttonCalculate.addActionListener(self);

        // Botón para limpiar los registros de la calculadora
        
        buttonClear = new JButton("Limpiar");
        buttonClear.setFont(new Font("Tahoma", Font.BOLD, 12));
        buttonClear.setBounds(156, 502, 90, 41);
        buttonClear.addActionListener(self);

        // Botón para regresar al área privada
        
        buttonReturn = new JButton("Regresar");
        buttonReturn.setFont(new Font("Tahoma", Font.BOLD, 12));
        buttonReturn.setBounds(256, 502, 90, 41);
        buttonReturn.addActionListener(self);

        // Etiqueta de resultado
        
        labelResult = new JLabel("Resultado:");
        labelResult.setHorizontalAlignment(SwingConstants.LEFT);
        labelResult.setFont(new Font("Tahoma", Font.BOLD, 17));
        labelResult.setBounds(45, 553, 110, 33);

        // Campo de texto resultado
        
        txtResult = new JTextField();
        txtResult.setHorizontalAlignment(SwingConstants.CENTER);
        txtResult.setFont(new Font("Tahoma", Font.BOLD, 20));
        txtResult.setColumns(10);
        txtResult.setBounds(45, 596, 301, 33);
        txtResult.setEditable(false);

        // Contenedor
        
        container = new JPanel();
        container.setBackground(new Color(140, 160, 206));
        container.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(container);
        container.setLayout(null);
        container.add(labelBackground);
        container.add(labelLogo);
        container.add(labelTitle);
        container.add(labelAmount);
        container.add(txtAmount);
        container.add(labelCryptoBase);
        container.add(comboBase);
        container.add(labelCryptoChange);
        container.add(comboChange);
        container.add(buttonCalculate);
        container.add(buttonClear);
        container.add(labelResult);
        container.add(txtResult);
        container.add(buttonReturn);

        /*
         *  Método para evitar la finalización del programa
         *  al presionar la pestaña de cierre de una ventana externa
         */
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (response.getStatus() == Status.GONE) {
                    response = new Response(HomeView.class, Status.REDIRECT);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    	// Botón "Calcular"
        if (e.getSource() == buttonCalculate) {
        	
          if ( !txtAmount.getText().equals("") ) {
              double amount = Double.parseDouble(txtAmount.getText());
              try {
                  Currency currencyFrom = Currency.objects.first("name",
                          comboBase.getSelectedItem().toString().split(" -- ")[0]);
                  Currency currencyTo = Currency.objects.first("name",
                          comboChange.getSelectedItem().toString().split(" -- ")[0]);
                  txtResult.setText(Double.toString(currencyFrom.getPrice() * amount / currencyTo.getPrice()));
              } catch (ObjectDoesNotExist | IOException e1) {
                  JOptionPane.showMessageDialog(this, "Error en accesso a database.");
              }       	  
          }
           else {
        	   JOptionPane.showMessageDialog(this, "Debe introducir una cantidad a convertir", "Mensaje Emergente",
                       JOptionPane.WARNING_MESSAGE);
          }
        	
        }

        // Botón "Limpiar"
        if (e.getSource() == buttonClear) {
            cleanCalculator();
        }

        // Botón "Regresar"
        if (e.getSource() == buttonReturn) {
            response = new Response(HomeView.class, Status.REDIRECT);
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }

    }

    /**
     * Limpieza de la calculadora
     *
     * @param   El método no recibe ningún tipo de parámetro
     * @return  El método no devuelve ningún resultado
     * 
     * 			No obstante, por ser un método vacío,
     *          lleva a cabo la restauración de estados
     *          por defecto de los textField y comboBoxes
     *          indicados (vacío)
     */   
        
    
    public static void cleanCalculator() {
        txtAmount.setText(null);
        txtResult.setText(null);
        comboBase.setSelectedIndex(0);
        comboChange.setSelectedIndex(0);
    }

}
