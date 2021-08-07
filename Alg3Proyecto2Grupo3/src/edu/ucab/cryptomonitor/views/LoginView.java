package edu.ucab.cryptomonitor.views;

import javax.swing.JFrame;

import edu.ucab.cryptomonitor.app.Response;
import edu.ucab.cryptomonitor.app.Status;
import edu.ucab.cryptomonitor.app.View;
import edu.ucab.cryptomonitor.models.User;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.paaksing.jjango.Database.ObjectDoesNotExist;
import com.paaksing.jjango.Validator.ValidationError;

import java.awt.Font;
import java.awt.Image;

/**
* 
* Ventana de inicio de sesión de usuario
* @author Jian Feng
* @author Arturo Hung
* @author Kleysber Noguera
* 
*/


@SuppressWarnings("serial")

public class LoginView extends View implements ActionListener {  // Inicio de la clase LoginView

    /*
     *   Atributos y/o campos de clase:
     *   
     *   container: El contenedor de la ventana de interfaz gráfica,
     *   			la cual posee en su composición el resto de atributos
     *   			y campos declarados en la clase
     *   
     *   
     *   
     *   iconPath:  El url relativo de un determinado archivo de imagen 
     *              ubicado en el directorio "resources"
     *             
     *   image, newImg, : Atributos empleados para dimensionar una 
     *   imageIcon        etiqueta JLabel como imagen
     *         
     *   
     *   iconView: Atributo empleado para el muestreo del ícono de
     *   		   la ventana gráfica
     *   
     *   labelEmail: Etiqueta que muestra en la ventana el mensaje de
     *   			 "Correo electrónico"
     * 
     *   labelPassword: Etiqueta que muestra en la ventana el mensaje de
     *   			    "Contraseña"
     *   
     *   labelRegister: Etiqueta que muestra en la ventana el mensaje de
     *   			    "¿ No tienes cuenta ? Regístrate aquí"
     *   
     *   labelBackground: Etiqueta que muestra en la ventana 
     *                    la imagen de fondo "login.png"
     *   			      
     *   
     *   labelLogo: Etiqueta que muestra en la ventana el ícono
     *   			"logo.png"
     *   
     *   labelTitle: Etiqueta que muestra en ventana el título de imagen
     *               "letras.png"
     *             
     *   labelWelcome: Etiqueta que muestra en la ventana el mensaje de
     *   			   "¡ Bienvenido a UCABIT !"
     *          
     *   txtEmail: Campo de texto para colocar el correo electrónico
     *   
     *   txtPassword: Campo de texto para colocar la contraseña
     *   
     *   buttonLogin: Botón que al accionar permite el 
     *                redireccionamiento a la clase HomeView 
     * 
     */
	
    private JPanel container;
    private final String iconPath = "resources/logo.png";

    private Image image, newImg;
    private ImageIcon iconView, imageIcon;

    private JLabel labelEmail, labelPassword, labelRegister;
    private JLabel labelBackground, labelLogo, labelTitle;
    private JLabel labelWelcome;
    
    private static JTextField txtEmail;
    private static JPasswordField txtPassword;

    private JButton buttonLogin;
    private JButton buttonExit;

    /**
    * Constructor por defecto de la clase 
    * @param El constructor no posee ningún parámetro
    */    
    
    public LoginView() {

    	// Configuración general de la ventana
    	
        LoginView self = this;
        setBounds(100, 100, 984, 633);
        setTitle("UCABIT - Ventana de Inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iconView = new ImageIcon(iconPath);
        setIconImage(iconView.getImage());
        setResizable(false);

        // Etiqueta de fondo
        
        labelBackground = new JLabel("");
        labelBackground.setBounds(414, 0, 580, 598);
        
        imageIcon = new ImageIcon("resources/login.png");
        image = imageIcon.getImage(); // Conversion del icono en imagen
        newImg = image.getScaledInstance(580, 598, java.awt.Image.SCALE_SMOOTH); // Se escala la imagen
        imageIcon = new ImageIcon(newImg); // Creacion de nueva instancia

        labelBackground = new JLabel(imageIcon);
        labelBackground.setBounds(398, 0, 580, 598);

        // Etiqueta de logo
        
        labelLogo = new JLabel("");
        labelLogo.setBounds(73, 59, 110, 108);

        imageIcon = new ImageIcon("resources/logo.png");
        image = imageIcon.getImage(); // Conversion del icono en imagen
        newImg = image.getScaledInstance(110, 108, java.awt.Image.SCALE_SMOOTH); // Se escala la imagen
        imageIcon = new ImageIcon(newImg); // Creacion de nueva instancia

        labelLogo = new JLabel(imageIcon);
        labelLogo.setBounds(47, 35, 110, 108);

        // Etiqueta de título
        
        labelTitle = new JLabel("");
        labelTitle.setBounds(73, 59, 211, 60);

        imageIcon = new ImageIcon("resources/letras.png");
        image = imageIcon.getImage(); // Conversion del icono en imagen
        newImg = image.getScaledInstance(211, 60, java.awt.Image.SCALE_SMOOTH); // Se escala la imagen
        imageIcon = new ImageIcon(newImg); // Creacion de nueva instancia

        labelTitle = new JLabel(imageIcon);
        labelTitle.setBounds(164, 62, 211, 60); 

        // Etiqueta de bienvenida
        
        labelWelcome = new JLabel("\u00A1 Bienvenido a UCABIT !");
        labelWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        labelWelcome.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelWelcome.setBounds(23, 170, 379, 25);

        // Etiqueta de correo electrónico 
        
        labelEmail = new JLabel("Correo electr\u00F3nico");
        labelEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelEmail.setBounds(47, 221, 211, 25);
        
        // Campo de texto de correo electrónico
        
        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtEmail.setBounds(47, 259, 306, 40);

        // Campo de texto de correo electrónico
        
        labelPassword = new JLabel("Contrase\u00F1a");
        labelPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelPassword.setBounds(47, 320, 139, 26);

        // Campo de texto de contraseña
        
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtPassword.setBounds(47, 355, 306, 40);

        // Botón de inicio de sesión
        
        buttonLogin = new JButton("Login");
        buttonLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
        buttonLogin.setBounds(47, 425, 139, 40);
        buttonLogin.addActionListener(self);

        // Botón de salida
        
        buttonExit = new JButton("Salir");
        buttonExit.setFont(new Font("Tahoma", Font.BOLD, 20));
        buttonExit.setBounds(214, 425, 139, 40);
        buttonExit.addActionListener(self);

        // Etiqueta de registro
        
        labelRegister = new JLabel(
                "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>\u00BF No tienes cuenta ? Reg\u00EDstrate aqu\u00ED</h1>\r\n</html>");
        labelRegister.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelRegister.setForeground(new Color(0, 0, 139));
        labelRegister.setHorizontalAlignment(SwingConstants.CENTER);
        labelRegister.setBounds(95, 488, 211, 60);

        labelRegister.addMouseListener(new MouseAdapter() {
        	
            @Override
            
            /* Sobreescritura del método MouseClicked
             * por la instancia a la clase MouseAdapter
             */
                  
            public void mouseClicked(MouseEvent e) {
                response = new Response(RegisterView.class, Status.REDIRECT);
                dispatchEvent(new WindowEvent(self, WindowEvent.WINDOW_CLOSING));
            }
        });

        // Contenedor de la ventana
        
        container = new JPanel();
        container.setBorder(new EmptyBorder(5, 5, 5, 5));
        container.setBackground(new Color(140, 160, 206));
        setContentPane(container);
        container.setLayout(null);
        container.add(labelBackground);
        container.add(labelLogo);
        container.add(labelTitle);
        container.add(labelWelcome);
        container.add(labelEmail);
        container.add(txtEmail);
        container.add(labelPassword);
        container.add(txtPassword);
        container.add(buttonLogin);
        container.add(buttonExit);
        container.add(labelRegister);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    	// "Botón Login"
        if (e.getSource() == buttonLogin) {

        	
        // Condición: Los campos de texto txtEmail y txtPassword no pueden ser vacíos
        	
            if (!((txtEmail.getText().equals("")) || (String.valueOf(txtPassword.getPassword()).equals("")))) {
                try {
                    if (User.authenticate(txtEmail.getText(), String.valueOf(txtPassword.getPassword()))) {
                        try {
                            User user = User.objects.first("email", txtEmail.getText());
                            user.setPassword(String.valueOf(txtPassword.getPassword()));
                            user.setLastLoggedIP(InetAddress.getLocalHost().toString());
                            user.save();
                            request.setUser(user);
                        } catch (ObjectDoesNotExist | ValidationError e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(this, "Correo electrónico y/o contraseña invalidos",
                                    "Mensaje Emergente", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        response = new Response(HomeView.class, Status.REDIRECT);
                        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                        return;
                    }
                    JOptionPane.showMessageDialog(this, "Correo electrónico y/o contraseña invalidos",
                            "Mensaje Emergente", JOptionPane.ERROR_MESSAGE);

                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(this, "Error en accesso a database", "Mensaje Emergente",
                            JOptionPane.ERROR_MESSAGE);
                }
            } 
            
            // Condición No Cumplida: Ventana Emergente (Advertencia - Campos de textos vacíos)
            else {
                JOptionPane.showMessageDialog(this, "Debe ingresar todos los datos de acceso", "Mensaje Emergente",
                        JOptionPane.WARNING_MESSAGE);
            }

        }

     // Si se presiona el botón buttonExit
        if (e.getSource() == buttonExit) {

            Object[] options = { "Si", "No" };

            int pos = JOptionPane.showOptionDialog(null, "¿ Desea salir de la aplicación ?", "Ventana Emergente",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, // No usa un icono personalizado
                    options, // Botones del arreglo options
                    options[0]); // Titulo por defecto del botón

            if (pos == JOptionPane.YES_OPTION) {
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }

        }

    }

    
    /**
     * Limpieza de datos de acceso
     *
     * @param   El método no recibe ningún tipo de parámetro
     * @return  El método no devuelve ningún resultado
     */
    
    public static void cleanLogin() {
    	
        /*  Atributso estáticos txtEmail y txtPassword del tipo JTextField 
         *  son puestos en su estado por defecto (vacío)
    	 */
    	
        txtEmail.setText(null);    
        txtPassword.setText(null);
    }
    
}  // Cierre de la clase LoginView
