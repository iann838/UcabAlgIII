package edu.ucab.cryptomonitor.views;

import javax.swing.JFrame;

import edu.ucab.cryptomonitor.app.Response;
import edu.ucab.cryptomonitor.app.Status;
import edu.ucab.cryptomonitor.app.View;
import edu.ucab.cryptomonitor.models.Currency;
import edu.ucab.cryptomonitor.models.User;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")

public class HomeView extends View implements ActionListener {

    class ColumnColorRenderer extends DefaultTableCellRenderer {
        Color backgroundColor, foregroundColor;

        public ColumnColorRenderer(Color foregroundColor) {
            super();
            this.foregroundColor = foregroundColor;
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            cell.setForeground(foregroundColor);
            return cell;
        }
    }

    private JPanel container;
    private JScrollPane pane;

    private JTable cryptoTable;
    private JTableHeader header;

    private JLabel labelUsername, labelLogo, labelTitle;

    private JButton buttonLogout;
    private JButton buttonPortfolio;
    private JButton buttonCalculator;

    private Image image, newImg;
    private ImageIcon iconView, imageIcon;
    private final String iconPath = "resources/logo.png";
    private JButton buttonUserChange;
    private JLabel labelUpdate;
    private JButton btnHtextalignCenter;
    private JLabel labelLastLoggedIP;

    public HomeView() {

        HomeView self = this;
        setTitle("UCABIT - Ventana Privada");
        setResizable(false);
//		setUndecorated(true);
//		getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iconView = new ImageIcon(iconPath);
        setIconImage(iconView.getImage());
        setBounds(100, 100, 1304, 680);

        labelLogo = new JLabel("");
        labelLogo.setBounds(35, 320, 110, 110);

        imageIcon = new ImageIcon("resources/logo.png");
        image = imageIcon.getImage(); // Conversion del icono en imagen
        newImg = image.getScaledInstance(110, 110, java.awt.Image.SCALE_SMOOTH); // Se escala la imagen
        imageIcon = new ImageIcon(newImg); // Creacion de nueva instancia

        labelLogo = new JLabel(imageIcon);
        labelLogo.setBounds(70, 47, 110, 110); // setBounds (x,y,width,height)

        labelTitle = new JLabel("");
        labelTitle.setBounds(155, 339, 3270, 76);

        imageIcon = new ImageIcon("resources/letras.png");
        image = imageIcon.getImage(); // Conversion del icono en imagen
        newImg = image.getScaledInstance(270, 76, java.awt.Image.SCALE_SMOOTH); // Se escala la imagen
        imageIcon = new ImageIcon(newImg); // Creacion de nueva instancia

        labelTitle = new JLabel(imageIcon);
        labelTitle.setBounds(192, 67, 270, 76); // setBounds (x,y,width,height)

        cryptoTable = new JTable();
        cryptoTable.setModel(new DefaultTableModel(
                new Object[][] { { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null }, },
                new String[] {
                        "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>Rank</h1>\r\n</html>",
                        "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>Etiqueta</h1>\r\n</html>",
                        "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>Moneda</h1>\r\n</html>",
                        "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>Precio</h1>\r\n</html>",
                        "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>1h %</h1>\r\n</html>",
                        "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>24h %</h1>\r\n</html>",
                        "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>7d %</h1>\r\n</html>",
                        "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>30d %</h1>\r\n</html>",
                        "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>Market Cap</h1>\r\n</html>" })

        {
            boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, false };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });

        DefaultTableCellRenderer numericRenderer = new DefaultTableCellRenderer();
        numericRenderer.setHorizontalAlignment(JLabel.RIGHT);

        cryptoTable.getColumnModel().getColumn(0).setResizable(false);
        cryptoTable.getColumnModel().getColumn(0).setPreferredWidth(34);
        cryptoTable.getColumnModel().getColumn(1).setResizable(false);
        cryptoTable.getColumnModel().getColumn(1).setPreferredWidth(32);
        cryptoTable.getColumnModel().getColumn(2).setResizable(false);
        cryptoTable.getColumnModel().getColumn(2).setPreferredWidth(133);
        cryptoTable.getColumnModel().getColumn(3).setResizable(false);
        cryptoTable.getColumnModel().getColumn(3).setCellRenderer(numericRenderer);
        cryptoTable.getColumnModel().getColumn(4).setResizable(false);
        cryptoTable.getColumnModel().getColumn(4).setPreferredWidth(57);
        cryptoTable.getColumnModel().getColumn(4).setCellRenderer(numericRenderer);
        cryptoTable.getColumnModel().getColumn(5).setPreferredWidth(59);
        cryptoTable.getColumnModel().getColumn(5).setResizable(false);
        cryptoTable.getColumnModel().getColumn(5).setCellRenderer(numericRenderer);
        cryptoTable.getColumnModel().getColumn(6).setResizable(false);
        cryptoTable.getColumnModel().getColumn(6).setPreferredWidth(58);
        cryptoTable.getColumnModel().getColumn(6).setCellRenderer(numericRenderer);
        cryptoTable.getColumnModel().getColumn(7).setResizable(false);
        cryptoTable.getColumnModel().getColumn(7).setPreferredWidth(58);
        cryptoTable.getColumnModel().getColumn(7).setCellRenderer(numericRenderer);
        cryptoTable.getColumnModel().getColumn(8).setResizable(false);
        cryptoTable.getColumnModel().getColumn(8).setPreferredWidth(106);
        cryptoTable.getColumnModel().getColumn(8).setCellRenderer(numericRenderer);
        cryptoTable.setShowVerticalLines(false);
        cryptoTable.setRowHeight(20);
        cryptoTable.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cryptoTable.setDefaultEditor(Object.class, null);
        cryptoTable.setShowVerticalLines(false);
        cryptoTable.setBounds(22, 132, 1200, 480);

        header = cryptoTable.getTableHeader();
        header.setPreferredSize(new Dimension(1209, 50));
        header.setOpaque(false);
        header.setBackground(new Color(163, 192, 255));
        header.setForeground(new Color(0, 0, 0));
        header.setEnabled(false);

        labelUsername = new JLabel("Usuario: Undefined");
        labelUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelUsername.setHorizontalAlignment(SwingConstants.LEFT);
        labelUsername.setBounds(505, 47, 749, 33);

        buttonLogout = new JButton("Logout");
        buttonLogout.setFont(new Font("Tahoma", Font.BOLD, 20));
        buttonLogout.setBounds(63, 531, 165, 67);
        buttonLogout.addActionListener(self);

        buttonPortfolio = new JButton(
                "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>Acceder Portafolio</h1>\r\n</html>");
        buttonPortfolio.setVerticalAlignment(SwingConstants.TOP);
        buttonPortfolio.setFont(new Font("Tahoma", Font.BOLD, 20));
        buttonPortfolio.setBounds(516, 532, 201, 67);
        buttonPortfolio.addActionListener(self);

        buttonUserChange = new JButton(
                "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>Modificar datos de acceso</h1>\r\n</html>");
        buttonUserChange.setVerticalAlignment(SwingConstants.TOP);
        buttonUserChange.setFont(new Font("Tahoma", Font.BOLD, 20));
        buttonUserChange.setBounds(274, 531, 201, 67);
        buttonUserChange.addActionListener(self);

        buttonCalculator = new JButton(
                "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>Calculadora de conversi\u00F3n</h1>\r\n</html>");
        buttonCalculator.setVerticalAlignment(SwingConstants.TOP);
        buttonCalculator.setFont(new Font("Tahoma", Font.BOLD, 20));
        buttonCalculator.setBounds(762, 531, 210, 67);
        buttonCalculator.addActionListener(self);

        labelUpdate = new JLabel("\u00DAltima actualizaci\u00F3n: Undefined");
        labelUpdate.setHorizontalAlignment(SwingConstants.LEFT);
        labelUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelUpdate.setBounds(505, 113, 749, 33);

        pane = new JScrollPane(cryptoTable);
        pane.setBounds(45, 195, 1209, 292);
        pane.setViewportBorder(null);
        pane.setBorder(BorderFactory.createEmptyBorder());

        container = new JPanel();
        container.setBackground(new Color(140, 160, 206));
        container.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(container);
        container.setLayout(null);
        container.add(pane);
        container.add(labelUsername);
        container.add(labelLogo);
        container.add(labelTitle);
        container.add(buttonLogout);
        container.add(buttonPortfolio);
        container.add(buttonUserChange);
        container.add(buttonCalculator);
        container.add(labelUpdate);

        btnHtextalignCenter = new JButton(
                "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>Consultar inversiones</h1>\r\n</html>");
        btnHtextalignCenter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnHtextalignCenter.setVerticalAlignment(SwingConstants.TOP);
        btnHtextalignCenter.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnHtextalignCenter.setBounds(1015, 531, 210, 67);
        container.add(btnHtextalignCenter);
        
        labelLastLoggedIP = new JLabel("\u00DAltima login IP: Undefined");
        labelLastLoggedIP.setHorizontalAlignment(SwingConstants.LEFT);
        labelLastLoggedIP.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelLastLoggedIP.setBounds(505, 81, 749, 33);
        container.add(labelLastLoggedIP);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttonLogout) {

            Object[] options = { "Si", "No" };

            int pos = JOptionPane.showOptionDialog(null, "¿ Seguro que quiere cerrar la sesión ?", "Ventana Emergente",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (pos == JOptionPane.YES_OPTION) {
                request.setUser(new User());
                response = new Response(LoginView.class, Status.REDIRECT);
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }

        }

        if (e.getSource() == buttonCalculator) {
            response = new Response(CalculatorView.class, Status.REDIRECT);
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }

        if (e.getSource() == buttonUserChange) {
            response = new Response(UserChangeView.class, Status.REDIRECT);
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        
        if (e.getSource() == buttonPortfolio) {
            response = new Response(PortfolioView.class, Status.REDIRECT);
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }

    }

    @Override
    public void postInit() {
        HomeView self = this;
        labelUsername.setText(String.format("Usuario: %s", this.request.getUser().getUsername()));
        labelLastLoggedIP.setText(String.format("\u00DAltima login IP: %s", this.request.getUser().getLastLoggedIP()));
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isClosed) {
                    try {
                        List<Currency> currencies = Currency.objects.all();
                        for (Currency currency : currencies) {
                            if (currency.getRank() > 30)
                                continue;
                            int rowIndex = currency.getRank() - 1;
                            cryptoTable.getModel().setValueAt(currency.getRank(), rowIndex, 0);
                            cryptoTable.getModel().setValueAt(currency.getTag(), rowIndex, 1);
                            cryptoTable.getModel().setValueAt(currency.getName(), rowIndex, 2);
                            cryptoTable.getModel().setValueAt(String.format("%.2f", currency.getPrice()), rowIndex, 3);
                            cryptoTable.getModel().setValueAt(String.format("%.2f", currency.getPrice1hpc()), rowIndex,
                                    4);
                            cryptoTable.getModel().setValueAt(String.format("%.2f", currency.getPrice24hpc()), rowIndex,
                                    5);
                            cryptoTable.getModel().setValueAt(String.format("%.2f", currency.getPrice7dpc()), rowIndex,
                                    6);
                            cryptoTable.getModel().setValueAt(String.format("%.2f", currency.getPrice30dpc()), rowIndex,
                                    7);
                            cryptoTable.getModel().setValueAt(String.format("%.2f", currency.getMarketCap()), rowIndex,
                                    8);
                            labelUpdate.setText(String.format("Última actualización: %s", currency.getLastUpdated()));
                        }
                        TimeUnit.SECONDS.sleep(1);

                    } catch (IOException | InterruptedException e) {
                        JOptionPane.showMessageDialog(self, "Error en accesso a database.");
                    }
                }
            }
        }).start();
    }
}
