package edu.ucab.cryptomonitor.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.paaksing.jjango.Validator.InsecurePassword;
import com.paaksing.jjango.Validator.InvalidEmail;
import com.paaksing.jjango.Validator.UniqueConstraintFailed;
import com.paaksing.jjango.Validator.ValidationError;

import edu.ucab.cryptomonitor.app.Response;
import edu.ucab.cryptomonitor.app.Status;
import edu.ucab.cryptomonitor.app.View;
import edu.ucab.cryptomonitor.models.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

/**
*
* @author Jian Feng
* @author Arturo Hung
* @author Kleysber Noguera
* 
*/

@SuppressWarnings("serial")
public class UserChangeView extends View implements ActionListener { // Inicio de la clase UserChangView

    /*
     *   Atributos y/o campos de clase:
     *   
     *   container: El contenedor de la ventana de interfaz gráfica,
     *   			la cual posee en su composición el resto de atributos
     *   			y campos declarados en la clase
     *   
     *   labelBackground: Etiqueta que muestra en la ventana 
     *                    la imagen de fondo "userbg.png"
     *                    
     *   buttonModify: Botón que al accionar permite el
     *                 guardado de modificaciones de datos de acceso
     *                 
     *   buttonHome: Botón que al accionar permite el redireccionamiento
     *               a la clase HomeView
     *   
     *   image, newImg, : Atributos empleados para dimensionar una 
     *   imageIcon        etiqueta JLabel como imagen
     *   
     *   iconView: Atributo empleado para el muestreo del ícono de
     *   		   la ventana gráfica
     *   
     *   iconPath:  El url relativo de un determinado archivo de imagen 
     *   
     *   txtUsername: Campo de texto para colocar el nombre de usuario
     *    
     *   txtEmail: Campo de texto para colocar el correo electrónico
     *   
     *   txtPassword: Campo de texto para colocar la contraseña
     */	
	
    private JPanel container;
    private JLabel labelBackground;
    private JButton buttonModify, buttonHome;
    private Image image, newImg;
    private ImageIcon iconView, imageIcon;
    private final String iconPath = "resources/logo.png";
    private JTextField txtUsername;
    private JTextField txtEmail;
    private JTextField txtPassword;
    private JPasswordField txtConfirm;

    public UserChangeView() {

        UserChangeView self = this;
        setBounds(100, 100, 995, 700);
        setTitle("UCABIT - Ventana de Configuración de Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iconView = new ImageIcon(iconPath);
        setIconImage(iconView.getImage());
        setResizable(false);

        // Etiqueta de fondo
        
        labelBackground = new JLabel("");
        labelBackground.setBounds(395, 0, 596, 672);

        imageIcon = new ImageIcon("resources/userbg.png");
        image = imageIcon.getImage();
        newImg = image.getScaledInstance(596, 672, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);
        labelBackground = new JLabel(imageIcon);

        labelBackground.setBounds(395, 0, 596, 672);

        // Botón de modificación de datos de acceso (guardado de cambios)
        
        buttonModify = new JButton("Guardar");
        buttonModify.setFont(new Font("Tahoma", Font.BOLD, 20));
        buttonModify.setBounds(102, 521, 190, 46);
        buttonModify.addActionListener(self);

        // Botón de regreso al área privada
        
        buttonHome = new JButton("Regresar");
        buttonHome.setFont(new Font("Tahoma", Font.BOLD, 18));
        buttonHome.setBounds(102, 577, 190, 46);
        buttonHome.addActionListener(self);

        // Contenedor
        
        container = new JPanel();
        container.setBackground(new Color(140, 160, 206));
        container.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(container);
        container.setLayout(null);
        container.add(labelBackground);
        container.add(buttonModify);
        container.add(buttonHome);

        // Etiqueta de logo
        
        JLabel labelLogo = new JLabel("");
        labelLogo.setBounds(94, 28, 122, 122);

        imageIcon = new ImageIcon("resources/logo.png");
        image = imageIcon.getImage();
        newImg = image.getScaledInstance(122, 122, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);

        labelLogo = new JLabel(imageIcon);
        labelLogo.setBounds(138, 28, 122, 122);
        container.add(labelLogo);
        
        // Etiqueta de registro
        
        JLabel labelRegister = new JLabel("Modificar Usuario");
        labelRegister.setHorizontalAlignment(SwingConstants.CENTER);
        labelRegister.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelRegister.setBounds(74, 173, 246, 33);
        container.add(labelRegister);
        
        // Etiqueta de nombre de usuario
        
        JLabel labelUsername = new JLabel("Username");
        labelUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
        labelUsername.setBounds(74, 216, 173, 19);
        container.add(labelUsername);
        
        // Campo de nombre de usuario
        
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtUsername.setBounds(74, 248, 246, 31);
        container.add(txtUsername);
        
        // Etiqueta de correo electrónico
        
        JLabel labelEmail = new JLabel("Correo electr\u00F3nico");
        labelEmail.setFont(new Font("Tahoma", Font.BOLD, 18));
        labelEmail.setBounds(74, 289, 190, 19);
        container.add(labelEmail);
        
        // Campo de texto de correo electrónico
        
        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtEmail.setBounds(74, 318, 246, 33);
        container.add(txtEmail);
        
        // Etiqueta de contraseña
        
        JLabel labelPassword = new JLabel("Nueva Contrase\u00F1a");
        labelPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
        labelPassword.setBounds(74, 361, 246, 18);
        container.add(labelPassword);
        
        // Campo de texto de contraseña
        
        txtPassword = new JTextField();
        txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtPassword.setBounds(74, 389, 246, 33);
        container.add(txtPassword);
    
        // Etiqueta de confirmación de nueva contraseña
        
        JLabel labelConfirm = new JLabel("Confirmar contrase\u00F1a");
        labelConfirm.setFont(new Font("Tahoma", Font.BOLD, 18));
        labelConfirm.setBounds(74, 432, 235, 19);
        container.add(labelConfirm);
        
       // Campo de texto de confirmación de nueva contraseña
         
        txtConfirm = new JPasswordField();
        txtConfirm.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtConfirm.setBounds(74, 461, 246, 33);
        container.add(txtConfirm);

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

    	// Botón "Regresar"
        if (e.getSource() == buttonHome) {
            response = new Response(HomeView.class, Status.REDIRECT);
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        
        // Botón "Guardar"
        if (e.getSource() == buttonModify) {

            if (!((txtUsername.getText().equals("")) || (txtEmail.getText().equals(""))
                    || (txtPassword.getText().equals("")) || (String.valueOf(txtConfirm.getPassword()).equals("")))
                    && (txtPassword.getText().equals(String.valueOf(txtConfirm.getPassword())))) {

                User user = this.request.getUser();
                user.setUsername(txtUsername.getText());
                user.setEmail(txtEmail.getText());
                user.setPassword(txtPassword.getText());
                try {
                    user.save();
                    JOptionPane.showMessageDialog(this,
                            String.format("Usuario %s modificado exitosamente", user.getUsername()), "Mensaje Emergente",
                            JOptionPane.INFORMATION_MESSAGE);
                    response = new Response(HomeView.class, Status.REDIRECT);
                    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                } catch (UniqueConstraintFailed ex) {
                    JOptionPane.showMessageDialog(this, "El correo electrónico ya se encuentra registrado",
                            "Mensaje Emergente", JOptionPane.ERROR_MESSAGE);
                } catch (InvalidEmail ex) {
                    JOptionPane.showMessageDialog(this, "Correo electrónico inválido", "Mensaje Emergente",
                            JOptionPane.ERROR_MESSAGE);
                } catch (InsecurePassword ex) {
                    JOptionPane.showMessageDialog(this,
                            "Contraseña insegura (debe contener min. 8 caracteres Mayus, Minus, especiales y numerales)",
                            "Mensaje Emergente", JOptionPane.ERROR_MESSAGE);
                } catch (ValidationError ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }

            }

            else if ((txtUsername.getText().equals("")) || (txtEmail.getText().equals(""))
                    || (txtPassword.getText().equals("")) || (String.valueOf(txtConfirm.getPassword()).equals(""))) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los datos solicitados", "Mensaje Emergente",
                        JOptionPane.WARNING_MESSAGE);
            }

            else {
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Mensaje Emergente",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    /**
     * Colocación de datos de acceso registrados del usuario actual
     * (nombre de usuario y correo electrónico)
     *
     * @param   El método no recibe ningún tipo de parámetro
     * @return  El método no devuelve ningún resultado
     */   
    
    @Override
    public void postInit() {
        txtUsername.setText(this.request.getUser().getUsername());
        txtEmail.setText(this.request.getUser().getEmail());
    }

} // Cierre de la clase UserChangView
