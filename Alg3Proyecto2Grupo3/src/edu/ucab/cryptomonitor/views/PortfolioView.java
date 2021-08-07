package edu.ucab.cryptomonitor.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.paaksing.jjango.Database.ObjectDoesNotExist;
import com.paaksing.jjango.Validator.ValidationError;

import edu.ucab.cryptomonitor.app.Response;
import edu.ucab.cryptomonitor.app.Status;
import edu.ucab.cryptomonitor.app.View;
import edu.ucab.cryptomonitor.models.Currency;
import edu.ucab.cryptomonitor.models.WalletBalance;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PortfolioView extends View implements ActionListener {
    
    private JLabel labelLogo, labelTitle;
    private JPanel container;
    private JScrollPane pane;
    private JTable cryptoTable;
    private JTableHeader header;
    private JButton buttonRegresar, btnResetAll, btnAddCurrency, btnDeleteCurrency, btnTrade;

    private Image image, newImg;
    private ImageIcon iconView, imageIcon;
    private final String iconPath = "resources/logo.png";
    private JLabel labelUSDTText;
    private JLabel lblUSDT;

    public PortfolioView() {
//        PortfolioView self = this;
        setTitle("UCABIT - Portafolio");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iconView = new ImageIcon(iconPath);
        setIconImage(iconView.getImage());
        setBounds(100, 100, 1304, 630);

        container = new JPanel();
        container.setBackground(new Color(140, 160, 206));
        container.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(container);
        container.setLayout(null);

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

        container.add(labelTitle);
        container.add(labelLogo);

        cryptoTable = new JTable();
        cryptoTable.setModel(new DefaultTableModel(
            new Object[][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
            },
            new String[] {
                "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>Moneda</h1>\r\n</html>",
                "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>Etiqueta</h1>\r\n</html>",
                "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>Precio USDT</h1>\r\n</html>",
                "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>Inversi\u00F3n</h1>\r\n</html>",
                "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>Inversi\u00F3n USDT</h1>\r\n</html>",
                "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>Profit USDT</h1>\r\n</html>",
                "<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>Profit %</h1>\r\n</html>",
            }
        ) {
            boolean[] columnEditables = new boolean[] {
                false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        DefaultTableCellRenderer numericRenderer = new DefaultTableCellRenderer();
        numericRenderer.setHorizontalAlignment(JLabel.RIGHT);
        cryptoTable.getColumnModel().getColumn(0).setResizable(false);
        cryptoTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        cryptoTable.getColumnModel().getColumn(1).setPreferredWidth(20);
        cryptoTable.getColumnModel().getColumn(1).setResizable(false);
        cryptoTable.getColumnModel().getColumn(2).setResizable(false);
        cryptoTable.getColumnModel().getColumn(2).setPreferredWidth(57);
        cryptoTable.getColumnModel().getColumn(2).setCellRenderer(numericRenderer);
        cryptoTable.getColumnModel().getColumn(3).setResizable(false);
        cryptoTable.getColumnModel().getColumn(3).setPreferredWidth(75);
        cryptoTable.getColumnModel().getColumn(3).setCellRenderer(numericRenderer);
        cryptoTable.getColumnModel().getColumn(4).setPreferredWidth(75);
        cryptoTable.getColumnModel().getColumn(4).setResizable(false);
        cryptoTable.getColumnModel().getColumn(4).setCellRenderer(numericRenderer);
        cryptoTable.getColumnModel().getColumn(5).setResizable(false);
        cryptoTable.getColumnModel().getColumn(5).setPreferredWidth(75);
        cryptoTable.getColumnModel().getColumn(5).setCellRenderer(numericRenderer);
        cryptoTable.getColumnModel().getColumn(6).setResizable(false);
        cryptoTable.getColumnModel().getColumn(6).setPreferredWidth(40);
        cryptoTable.getColumnModel().getColumn(6).setCellRenderer(numericRenderer);

        cryptoTable.setShowVerticalLines(false);
        cryptoTable.setRowHeight(20);
        cryptoTable.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cryptoTable.setDefaultEditor(Object.class, null);
        cryptoTable.setShowVerticalLines(false);
        cryptoTable.setBounds(22, 230, 1200, 200);

        header = cryptoTable.getTableHeader();
        header.setPreferredSize(new Dimension(1209, 50));
        header.setOpaque(false);
        header.setBackground(new Color(163, 192, 255));
        header.setForeground(new Color(0, 0, 0));
        header.setEnabled(false);

        pane = new JScrollPane(cryptoTable);
        pane.setBounds(45, 195, 1209, 253);
        pane.setViewportBorder(null);
        pane.setBorder(BorderFactory.createEmptyBorder());
        container.add(pane);
        
        labelUSDTText = new JLabel("Saldo USDT:");
        labelUSDTText.setHorizontalAlignment(SwingConstants.LEFT);
        labelUSDTText.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelUSDTText.setBounds(541, 85, 131, 33);
        container.add(labelUSDTText);
        
        lblUSDT = new JLabel("Undefined");
        lblUSDT.setHorizontalAlignment(SwingConstants.LEFT);
        lblUSDT.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblUSDT.setBounds(674, 85, 580, 33);
        container.add(lblUSDT);
        
        buttonRegresar = new JButton("Regresar");
        buttonRegresar.setFont(new Font("Tahoma", Font.BOLD, 20));
        buttonRegresar.setBounds(90, 490, 165, 67);
        buttonRegresar.addActionListener(this);
        container.add(buttonRegresar);
        
        btnResetAll = new JButton("Reset All");
        btnResetAll.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnResetAll.setBounds(311, 490, 165, 67);
        btnResetAll.addActionListener(this);
        container.add(btnResetAll);
        
        btnAddCurrency = new JButton("<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>Agregar criptomoneda</h1>\r\n</html>");
        btnAddCurrency.setVerticalAlignment(SwingConstants.TOP);
        btnAddCurrency.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnAddCurrency.setBounds(529, 490, 201, 67);
        btnAddCurrency.addActionListener(this);
        container.add(btnAddCurrency);
        
        btnDeleteCurrency = new JButton("<html>\r\n<style>\r\nh1 {text-align: center; font-size:14px }\r\n</style>\r\n<h1>Eliminar criptomoneda</h1>\r\n</html>");
        btnDeleteCurrency.setVerticalAlignment(SwingConstants.TOP);
        btnDeleteCurrency.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnDeleteCurrency.setBounds(783, 490, 201, 67);
        btnDeleteCurrency.addActionListener(this);
        container.add(btnDeleteCurrency);

        btnTrade = new JButton("Trade");
        btnTrade.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnTrade.setBounds(1043, 490, 165, 67);
        btnTrade.addActionListener(this);
        container.add(btnTrade);

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
    public void postInit() {
        try {
            WalletBalance usdtBalance = null;
            List<WalletBalance> balances = WalletBalance.objects.filter("user", this.request.getUser().getId());
            Currency usdtCurrency = Currency.objects.first("tag", "USDT");
            
            for (WalletBalance balance: balances) {
                if (balance.getCurrency() == usdtCurrency.getId())
                    usdtBalance = balance;
            }
            if (usdtBalance == null) {
                usdtBalance = new WalletBalance(this.request.getUser().getId(), usdtCurrency.getId(), 500, 0);
                usdtBalance.save();
            }
            this.lblUSDT.setText(Double.toString(usdtBalance.getBalance()));
        } catch (IOException | ObjectDoesNotExist | ValidationError e) {
            JOptionPane.showMessageDialog(this, "Error en accesso a database.");
        }
        PortfolioView self = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isClosed) {                    
                    try {
                        List<WalletBalance> balances = WalletBalance.objects.filter("user", request.getUser().getId());
                        int i = 0;
                        for (WalletBalance balance: balances) {
                            if (balance.getCurrency() == Currency.objects.first("tag", "USDT").getId()) {
                                self.lblUSDT.setText(Double.toString(balance.getBalance()));
                                continue;
                            }
                            if (i > 9)
                                break;
                            Currency currency = Currency.objects.get(balance.getCurrency());
                            cryptoTable.getModel().setValueAt(currency.getName(), i, 0);
                            cryptoTable.getModel().setValueAt(currency.getTag(), i, 1);
                            cryptoTable.getModel().setValueAt(String.format("%.2f", currency.getPrice()), i, 2);
                            cryptoTable.getModel().setValueAt(String.format("%.8f", balance.getBalance()), i, 3);
                            cryptoTable.getModel().setValueAt(String.format("%.2f", balance.getUsdtInversion()), i, 4);
                            cryptoTable.getModel().setValueAt(String.format("%.2f", balance.getBalance() * currency.getPrice() - balance.getUsdtInversion()), i, 5);
                            if (balance.getUsdtInversion() != 0.0) {                                
                                cryptoTable.getModel().setValueAt(String.format("%.5f", (balance.getBalance() * currency.getPrice() - balance.getUsdtInversion()) / balance.getUsdtInversion() * 100) + "%", i, 6);
                            } else {
                                cryptoTable.getModel().setValueAt(String.format("%.5f", 0.0) + "%", i, 6);                                
                            }
                            i++;
                        }
                        while (i < 10) {
                            cryptoTable.getModel().setValueAt("", i, 0);
                            cryptoTable.getModel().setValueAt("", i, 1);
                            cryptoTable.getModel().setValueAt("", i, 2);
                            cryptoTable.getModel().setValueAt("", i, 3);
                            cryptoTable.getModel().setValueAt("", i, 4);
                            cryptoTable.getModel().setValueAt("", i, 5);
                            cryptoTable.getModel().setValueAt("", i, 6);
                            i++;
                        }
                        TimeUnit.SECONDS.sleep(1);
                    } catch (IOException | ObjectDoesNotExist | InterruptedException e) {
                        JOptionPane.showMessageDialog(self, "Error en accesso a database.");
                    }
                }
            }
        }).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.buttonRegresar) {
            response = new Response(HomeView.class, Status.REDIRECT);
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        if (e.getSource() == this.btnAddCurrency) {
            try {
                List<WalletBalance> balances = WalletBalance.objects.filter("user", request.getUser().getId());
                ArrayList<Long> activeCurrencies = new ArrayList<Long>();
                for (WalletBalance balance: balances) {
                    activeCurrencies.add(balance.getCurrency());
                }
                if (balances.size() > 10) {
                    JOptionPane.showMessageDialog(this, "Solo puede añadir 10 monedas.");
                    return;
                }
                List<Currency> currencies = Currency.objects.all();
                ArrayList<String> currencyOptions = new ArrayList<String>();
                for (Currency currency : currencies) {
                    if (currency.getRank() > 30 || activeCurrencies.contains(currency.getId()))
                        continue;
                    currencyOptions.add(currency.getName() + " -- " + currency.getTag());
                }
                @SuppressWarnings({ "rawtypes", "unchecked" })
                JComboBox comboCurrencies = new JComboBox(currencyOptions.toArray());
                JOptionPane optionPane = new JOptionPane("Selecciona la moneda a agregar",
                        JOptionPane.PLAIN_MESSAGE,
                        JOptionPane.NO_OPTION, null, new Object[] {}, null);
                optionPane.add(comboCurrencies);

                int chosen = JOptionPane.showOptionDialog(this, optionPane, "Seleccionar moneda",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (chosen == JOptionPane.CANCEL_OPTION || chosen == JOptionPane.CLOSED_OPTION)
                    return;
                String currencyName = comboCurrencies.getSelectedItem().toString().split(" -- ")[0];
                WalletBalance newBalance = new WalletBalance(this.request.getUser().getId(), Currency.objects.first("name", currencyName).getId(), 0, 0);
                newBalance.save();
            } catch (IOException | ObjectDoesNotExist | ValidationError e1) {
                JOptionPane.showMessageDialog(this, "Error en accesso a database.");
            }
            
        }
        if (e.getSource() == btnDeleteCurrency) {
            try {
                List<WalletBalance> balances = WalletBalance.objects.filter("user", request.getUser().getId());
                int row = this.cryptoTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar una moneda.");
                    return;
                }
                String value = this.cryptoTable.getModel().getValueAt(row, 0).toString();
                Currency currency = Currency.objects.first("name", value);
                for (WalletBalance balance: balances) {
                    if (balance.getCurrency() == currency.getId()) {
                        int res = JOptionPane.showConfirmDialog(this, String.format("Desea eliminar %s ?", currency.getName()), "Warning", JOptionPane.YES_NO_OPTION);
                        if (res == JOptionPane.YES_OPTION)
                            balance.delete();
                    }
                }
            } catch (IOException | ObjectDoesNotExist e1) {
                JOptionPane.showMessageDialog(this, "Error en accesso a database.");
            }
        }
        if (e.getSource() == btnTrade) {
            try {
                List<WalletBalance> balances = WalletBalance.objects.filter("user", request.getUser().getId());
                int row = this.cryptoTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar una moneda.");
                    return;
                }
                String value = this.cryptoTable.getModel().getValueAt(row, 0).toString();
                Currency currency = Currency.objects.first("name", value);
                for (WalletBalance balance: balances) {
                    if (balance.getCurrency() == currency.getId()) {
                        TradeView trade = new TradeView(currency.getId());
                        trade.setRequest(request);
                        trade.postInit();
                        trade.setVisible(true);
                    }
                }
            } catch (IOException | ObjectDoesNotExist e1) {
                JOptionPane.showMessageDialog(this, "Error en accesso a database.");
            }
        }
        if (e.getSource() == btnResetAll) {
            try {
                List<WalletBalance> balances = WalletBalance.objects.filter("user", request.getUser().getId());
                String value = JOptionPane.showInputDialog(this, "Valor USDT a resetear:");
                double usdtValue = Double.parseDouble(value);
                
                for (WalletBalance balance: balances) {
                    balance.setBalance(0);
                    if (balance.getCurrency() == Currency.objects.first("tag", "USDT").getId())
                        balance.setBalance(usdtValue);
                    balance.setUsdtInversion(0);
                    balance.save();
                }
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(this, "Input invalido.");                
            } catch (Exception e1) {                
                JOptionPane.showMessageDialog(this, "Error en accesso a database.");
            }
        }
        
    }
    
}
