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

@SuppressWarnings("serial")
public class CalculatorView extends View implements ActionListener {

    private JPanel container;

    private JLabel labelBackground, labelLogo;
    private JLabel labelTitle;
    private JButton buttonCalculate, buttonReturn;

    @SuppressWarnings("rawtypes")
    private static JComboBox comboBase, comboChange;

    private Image image, newImg;
    private ImageIcon iconView, imageIcon;
    private final String iconPath = "resources/logo.png";

    private JLabel labelAmount;
    private JLabel labelCryptoBase;
    private JLabel labelCryptoChange;

    private static DoubleJTextField txtAmount;

    private JButton buttonClear;

    private JLabel labelResult;

    private static JTextField txtResult;
    private ArrayList<String> currencyOptions;

    // precio moneda * cantidad / precio;

    /**
     * Instantiates a new calculator view.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public CalculatorView() {

        CalculatorView self = this;
        setBounds(100, 100, 900, 700);
        setTitle("UCABIT - Ventana de Calculadora de criptomonedas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iconView = new ImageIcon(iconPath);
        setIconImage(iconView.getImage());
        setResizable(false);

        labelBackground = new JLabel("");
        labelBackground.setBounds(395, 0, 502, 849);

        imageIcon = new ImageIcon("resources/calculadora.png");
        image = imageIcon.getImage();
        newImg = image.getScaledInstance(502, 849, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);
        labelBackground = new JLabel(imageIcon);

        labelBackground.setBounds(396, 0, 508, 672);

        labelLogo = new JLabel("");
        labelLogo.setBounds(121, 37, 137, 137);

        imageIcon = new ImageIcon("resources/logo.png");
        image = imageIcon.getImage();
        newImg = image.getScaledInstance(137, 137, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);

        labelLogo = new JLabel(imageIcon);
        labelLogo.setBounds(124, 28, 137, 137);

        labelTitle = new JLabel("Calculadora de conversi\u00F3n");
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
        labelTitle.setBounds(45, 175, 313, 33);

        labelAmount = new JLabel("Cantidad a convertir:");
        labelAmount.setHorizontalAlignment(SwingConstants.LEFT);
        labelAmount.setFont(new Font("Tahoma", Font.BOLD, 17));
        labelAmount.setBounds(45, 218, 216, 33);

        txtAmount = new DoubleJTextField();
        txtAmount.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtAmount.setBounds(45, 264, 301, 33);

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

        comboBase = new JComboBox(currencyOptions.toArray());
        comboBase.setFont(new Font("Tahoma", Font.PLAIN, 20));
        comboBase.setBounds(45, 350, 233, 31);

        labelCryptoChange = new JLabel("Criptomoneda de cambio:");
        labelCryptoChange.setHorizontalAlignment(SwingConstants.LEFT);
        labelCryptoChange.setFont(new Font("Tahoma", Font.BOLD, 17));
        labelCryptoChange.setBounds(45, 395, 261, 33);

        comboChange = new JComboBox(currencyOptions.toArray());
        comboChange.setFont(new Font("Tahoma", Font.PLAIN, 20));
        comboChange.setBounds(45, 438, 233, 31);

        buttonCalculate = new JButton("Calcular");
        buttonCalculate.setFont(new Font("Tahoma", Font.BOLD, 16));
        buttonCalculate.setBounds(45, 489, 101, 54);
        buttonCalculate.addActionListener(self);

        buttonClear = new JButton("Limpiar");
        buttonClear.setFont(new Font("Tahoma", Font.BOLD, 12));
        buttonClear.setBounds(156, 502, 90, 41);
        buttonClear.addActionListener(self);

        buttonReturn = new JButton("Regresar");
        buttonReturn.setFont(new Font("Tahoma", Font.BOLD, 12));
        buttonReturn.setBounds(256, 502, 90, 41);
        buttonReturn.addActionListener(self);

        labelResult = new JLabel("Resultado:");
        labelResult.setHorizontalAlignment(SwingConstants.LEFT);
        labelResult.setFont(new Font("Tahoma", Font.BOLD, 17));
        labelResult.setBounds(45, 553, 110, 33);

        txtResult = new JTextField();
        txtResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtResult.setColumns(10);
        txtResult.setBounds(45, 596, 301, 33);
        txtResult.setEditable(false);

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

        if (e.getSource() == buttonCalculate) {
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

        if (e.getSource() == buttonClear) {
            cleanCalculator();
        }

        if (e.getSource() == buttonReturn) {
            response = new Response(HomeView.class, Status.REDIRECT);
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }

    }

    public static void cleanCalculator() {
        txtAmount.setText(null);
        txtResult.setText(null);
        comboBase.setSelectedIndex(0);
        comboChange.setSelectedIndex(0);
    }

}
