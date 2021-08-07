package edu.ucab.cryptomonitor.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.paaksing.jjango.Validator.InsecurePassword;
import com.paaksing.jjango.Validator.InvalidEmail;
import com.paaksing.jjango.Validator.UniqueConstraintFailed;
import com.paaksing.jjango.Validator.ValidationError;

import edu.ucab.cryptomonitor.app.Response;
import edu.ucab.cryptomonitor.app.Status;
import edu.ucab.cryptomonitor.app.View;
import edu.ucab.cryptomonitor.models.User;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

@SuppressWarnings("serial")
public class RegisterView extends View implements ActionListener {

    private JPanel container;

    private JLabel labelUsername, labelEmail, labelPassword;
    private JLabel labelConfirm, labelLogin;
    private JLabel labelBackground, labelRegister;

    private static JTextField txtEmail;
    private static JTextField txtPassword;
    private static JTextField txtUsername;
    private static JPasswordField txtConfirm;

    private JButton buttonRegister;

    private Image image, newImg;
    private ImageIcon iconView, imageIcon;
    private final String iconPath = "resources/logo.png";
    private JLabel labelLogo;

    public RegisterView() {

        RegisterView self = this;
        setBounds(100, 100, 995, 700);
        setTitle("UCABIT - Ventana de Registro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iconView = new ImageIcon(iconPath);
        setIconImage(iconView.getImage());
        setResizable(false);

        labelBackground = new JLabel("");
        labelBackground.setBounds(337, 0, 652, 720);

        imageIcon = new ImageIcon("resources/registrarFondo.png");
        image = imageIcon.getImage();
        newImg = image.getScaledInstance(652, 720, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);

        labelBackground = new JLabel(imageIcon);
        labelBackground.setBounds(337, 0, 652, 720);

        labelLogo = new JLabel("");
        labelLogo.setBounds(94, 28, 122, 122);

        imageIcon = new ImageIcon("resources/logo.png");
        image = imageIcon.getImage();
        newImg = image.getScaledInstance(122, 122, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);

        labelLogo = new JLabel(imageIcon);
        labelLogo.setBounds(109, 28, 122, 122);

        labelRegister = new JLabel("Registro de Usuario");
        labelRegister.setHorizontalAlignment(SwingConstants.CENTER);
        labelRegister.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelRegister.setBounds(48, 175, 246, 33);

        labelUsername = new JLabel("Username");
        labelUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
        labelUsername.setBounds(48, 218, 173, 19);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtUsername.setBounds(48, 249, 246, 31);

        labelEmail = new JLabel("Correo electr\u00F3nico");
        labelEmail.setFont(new Font("Tahoma", Font.BOLD, 18));
        labelEmail.setBounds(48, 290, 190, 19);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtEmail.setBounds(48, 319, 246, 33);

        labelPassword = new JLabel("Contrase\u00F1a");
        labelPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
        labelPassword.setBounds(48, 362, 144, 18);

        txtPassword = new JTextField();
        txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtPassword.setBounds(48, 390, 246, 33);

        labelConfirm = new JLabel("Confirmar contrase\u00F1a");
        labelConfirm.setFont(new Font("Tahoma", Font.BOLD, 18));
        labelConfirm.setBounds(48, 433, 235, 19);

        txtConfirm = new JPasswordField();
        txtConfirm.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtConfirm.setBounds(48, 462, 246, 33);

        buttonRegister = new JButton("Registrar");
        buttonRegister.setFont(new Font("Tahoma", Font.BOLD, 20));
        buttonRegister.setBounds(79, 519, 184, 45);
        buttonRegister.addActionListener(self);

        labelLogin = new JLabel(
                "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>\u00BF Ya tienes cuenta ? Inicie sesi\u00F3n aqu\u00ED</h1>\r\n</html>");
        labelLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelLogin.setForeground(new Color(0, 0, 139));
        labelLogin.setHorizontalAlignment(SwingConstants.CENTER);
        labelLogin.setBounds(67, 583, 213, 40);
        labelLogin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                response = new Response(LoginView.class, Status.REDIRECT);
                dispatchEvent(new WindowEvent(self, WindowEvent.WINDOW_CLOSING));
            }
        });

        container = new JPanel();
        container.setBackground(new Color(140, 160, 206));
        container.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(container);
        container.setLayout(null);
        container.add(labelBackground);
        container.add(labelLogo);
        container.add(labelRegister);
        container.add(labelUsername);
        container.add(txtUsername);
        container.add(labelEmail);
        container.add(txtEmail);
        container.add(labelPassword);
        container.add(txtPassword);
        container.add(labelConfirm);
        container.add(txtConfirm);
        container.add(buttonRegister);
        container.add(labelLogin);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!((txtUsername.getText().equals("")) || (txtEmail.getText().equals(""))
                || (txtPassword.getText().equals("")) || (String.valueOf(txtConfirm.getPassword()).equals("")))
                && (txtPassword.getText().equals(String.valueOf(txtConfirm.getPassword())))) {

            User user = new User(txtUsername.getText(), txtEmail.getText(), txtPassword.getText());
            try {
                user.save();
                JOptionPane.showMessageDialog(this,
                        String.format("Usuario %s registrado exitosamente", user.getUsername()), "Mensaje Emergente",
                        JOptionPane.INFORMATION_MESSAGE);
                response = new Response(LoginView.class, Status.REDIRECT);
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

    public static void cleanRegister() {
        txtUsername.setText(null);
        txtEmail.setText(null);
        txtPassword.setText(null);
        txtConfirm.setText(null);
    }
}
