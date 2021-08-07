package edu.ucab.cryptomonitor.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.ucab.cryptomonitor.app.Response;
import edu.ucab.cryptomonitor.app.Status;
import edu.ucab.cryptomonitor.app.View;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")

public class UserSettingsView extends View implements ActionListener {

	private JPanel container;
	
	private JLabel labelBackground;
	
	private JButton buttonModify, buttonHome;

    private Image image,newImg;
    private ImageIcon iconView, imageIcon;
    private final String iconPath = "resources/logo.png";
	
	UserSettingsView () {
		
		UserSettingsView self = this;
        setBounds(100, 100, 995, 755);
        setTitle("UCABIT - Ventana de Configuración de Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iconView = new ImageIcon (iconPath);
        setIconImage(iconView.getImage());
        setResizable(false);

		labelBackground = new JLabel("");
		labelBackground.setBounds(398, 0, 580, 598);
		
		 imageIcon = new ImageIcon("resources/userbg.png");
		 image = imageIcon.getImage();              
		 newImg = image.getScaledInstance (580, 598,  
				        java.awt.Image.SCALE_SMOOTH);  
		 imageIcon = new ImageIcon(newImg);              
		labelBackground = new JLabel(imageIcon);
		
		labelBackground.setBounds(398, 0, 580, 598);         
        
        buttonModify = new JButton("Cambiar contraseña");
        buttonModify.setFont(new Font("Tahoma", Font.BOLD, 20));
        buttonModify.setBounds(76, 466, 258, 33);
        buttonModify.addActionListener(self);
        
        buttonHome = new JButton("<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>Regresar al \u00E1rea privada</h1>\r\n</html>");
        buttonHome.setVerticalAlignment(SwingConstants.TOP);
        buttonHome.setFont(new Font("Tahoma", Font.BOLD, 20));
        buttonHome.setBounds(98, 600, 219, 61);
        buttonHome.addActionListener(self);
        
        container = new JPanel ();
        container.setBackground(new Color(140, 160, 206));
	    container.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(container);
		container.setLayout(null);
		container.add(labelBackground);  
		container.add(buttonModify);
		container.add(buttonHome); 		
			
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
    	
		if ( e.getSource() == buttonHome ) {
			response = new Response(HomeView.class, Status.REDIRECT);
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		
    }
}
