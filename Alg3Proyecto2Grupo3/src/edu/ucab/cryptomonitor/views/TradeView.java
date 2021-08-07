package edu.ucab.cryptomonitor.views;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.paaksing.jjango.Database.ObjectDoesNotExist;
import com.paaksing.jjango.Validator.ValidationError;

import edu.ucab.cryptomonitor.app.View;
import edu.ucab.cryptomonitor.models.Currency;
import edu.ucab.cryptomonitor.models.WalletBalance;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import edu.ucab.cryptomonitor.others.DoubleJTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class TradeView extends View implements ActionListener {
    
    long currencyId;
    Currency currency;
    private JLabel labelLogo, labelTitle, lblBuyCurrencyAmount, lblSellCurrencyAmount, lblCurrencyBalanceText, lblUSDTBalance, lblCurrencyBalance;
    private JPanel container;
    private JButton buttonBuy, btnSell, btnReturn;
    private Image image, newImg;
    private ImageIcon iconView, imageIcon;
    private DoubleJTextField txtBuyPrice, txtSellPrice, txtBuyCurrencyAmount, txtSellCurrencyAmount, txtBuyUSDTAmount, txtSellUSDTAmount;
    private final String iconPath = "resources/logo.png";

    public TradeView(long currencyId) {
        this.currencyId = currencyId;
        setTitle("UCABIT - Trade");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        iconView = new ImageIcon(iconPath);
        setIconImage(iconView.getImage());
        setBounds(100, 100, 800, 530);

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
        labelLogo.setBounds(65, 10, 110, 110); // setBounds (x,y,width,height)

        labelTitle = new JLabel("");
        labelTitle.setBounds(155, 339, 3270, 76);

        imageIcon = new ImageIcon("resources/letras.png");
        image = imageIcon.getImage(); // Conversion del icono en imagen
        newImg = image.getScaledInstance(270, 76, java.awt.Image.SCALE_SMOOTH); // Se escala la imagen
        imageIcon = new ImageIcon(newImg);
        container.add(labelLogo);

        JLabel labelBuy = new JLabel("Comprar:");
        labelBuy.setHorizontalAlignment(SwingConstants.LEFT);
        labelBuy.setFont(new Font("Tahoma", Font.BOLD, 22));
        labelBuy.setBounds(32, 135, 336, 33);
        container.add(labelBuy);
        
        JLabel lblVender = new JLabel("Vender:");
        lblVender.setHorizontalAlignment(SwingConstants.LEFT);
        lblVender.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblVender.setBounds(419, 135, 336, 33);
        container.add(lblVender);
        
        txtBuyPrice = new DoubleJTextField();
        txtBuyPrice.setHorizontalAlignment(SwingConstants.CENTER);
        txtBuyPrice.setBounds(32, 209, 336, 33);
        txtBuyPrice.setEditable(false);
        container.add(txtBuyPrice);
        
        txtSellPrice = new DoubleJTextField();
        txtSellPrice.setHorizontalAlignment(SwingConstants.CENTER);
        txtSellPrice.setBounds(419, 209, 336, 33);
        txtSellPrice.setEditable(false);
        container.add(txtSellPrice);
        
        JLabel labelBuyPrice = new JLabel("Precio actual:");
        labelBuyPrice.setHorizontalAlignment(SwingConstants.LEFT);
        labelBuyPrice.setFont(new Font("Tahoma", Font.BOLD, 17));
        labelBuyPrice.setBounds(32, 178, 216, 33);
        container.add(labelBuyPrice);
        
        JLabel labelSellPrice = new JLabel("Precio actual:");
        labelSellPrice.setHorizontalAlignment(SwingConstants.LEFT);
        labelSellPrice.setFont(new Font("Tahoma", Font.BOLD, 17));
        labelSellPrice.setBounds(419, 178, 216, 33);
        container.add(labelSellPrice);
        
        txtBuyCurrencyAmount = new DoubleJTextField();
        txtBuyCurrencyAmount.setHorizontalAlignment(SwingConstants.CENTER);
        txtBuyCurrencyAmount.setBounds(32, 282, 336, 33);
        container.add(txtBuyCurrencyAmount);
        
        txtSellCurrencyAmount = new DoubleJTextField();
        txtSellCurrencyAmount.setHorizontalAlignment(SwingConstants.CENTER);
        txtSellCurrencyAmount.setBounds(419, 282, 336, 33);
        container.add(txtSellCurrencyAmount);
        
        lblBuyCurrencyAmount = new JLabel("Monto Undefined:");
        lblBuyCurrencyAmount.setHorizontalAlignment(SwingConstants.LEFT);
        lblBuyCurrencyAmount.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblBuyCurrencyAmount.setBounds(32, 252, 216, 33);
        container.add(lblBuyCurrencyAmount);
        
        lblSellCurrencyAmount = new JLabel("Monto Undefined:");
        lblSellCurrencyAmount.setHorizontalAlignment(SwingConstants.LEFT);
        lblSellCurrencyAmount.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblSellCurrencyAmount.setBounds(419, 252, 216, 33);
        container.add(lblSellCurrencyAmount);
        
        JLabel lblBuyUSDTAmount = new JLabel("Monto USDT:");
        lblBuyUSDTAmount.setHorizontalAlignment(SwingConstants.LEFT);
        lblBuyUSDTAmount.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblBuyUSDTAmount.setBounds(32, 325, 216, 33);
        container.add(lblBuyUSDTAmount);
        
        JLabel lblSellUSDTAmount = new JLabel("Monto USDT:");
        lblSellUSDTAmount.setHorizontalAlignment(SwingConstants.LEFT);
        lblSellUSDTAmount.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblSellUSDTAmount.setBounds(419, 325, 216, 33);
        container.add(lblSellUSDTAmount);
        
        txtBuyUSDTAmount = new DoubleJTextField();
        txtBuyUSDTAmount.setHorizontalAlignment(SwingConstants.CENTER);
        txtBuyUSDTAmount.setBounds(32, 356, 336, 33);
        txtBuyUSDTAmount.setEditable(false);
        container.add(txtBuyUSDTAmount);
        
        txtSellUSDTAmount = new DoubleJTextField();
        txtSellUSDTAmount.setHorizontalAlignment(SwingConstants.CENTER);
        txtSellUSDTAmount.setBounds(419, 356, 336, 33);
        txtSellUSDTAmount.setEditable(false);
        container.add(txtSellUSDTAmount);
        
        JLabel labelUSDTBalanceTxt = new JLabel("Saldo USDT:");
        labelUSDTBalanceTxt.setHorizontalAlignment(SwingConstants.LEFT);
        labelUSDTBalanceTxt.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelUSDTBalanceTxt.setBounds(203, 27, 131, 33);
        container.add(labelUSDTBalanceTxt);
        
        lblCurrencyBalanceText = new JLabel("Saldo UDF:");
        lblCurrencyBalanceText.setHorizontalAlignment(SwingConstants.LEFT);
        lblCurrencyBalanceText.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblCurrencyBalanceText.setBounds(203, 59, 131, 33);
        container.add(lblCurrencyBalanceText);
        
        lblUSDTBalance = new JLabel("Undefined");
        lblUSDTBalance.setHorizontalAlignment(SwingConstants.LEFT);
        lblUSDTBalance.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblUSDTBalance.setBounds(332, 27, 300, 33);
        container.add(lblUSDTBalance);
        
        lblCurrencyBalance = new JLabel("Undefined");
        lblCurrencyBalance.setHorizontalAlignment(SwingConstants.LEFT);
        lblCurrencyBalance.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblCurrencyBalance.setBounds(332, 59, 300, 33);
        container.add(lblCurrencyBalance);
        
        buttonBuy = new JButton("Comprar");
        buttonBuy.setFont(new Font("Tahoma", Font.BOLD, 20));
        buttonBuy.setBounds(32, 411, 336, 55);
        buttonBuy.addActionListener(this);
        container.add(buttonBuy);
        
        btnSell = new JButton("Vender");
        btnSell.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnSell.setBounds(419, 411, 336, 55);
        btnSell.addActionListener(this);
        container.add(btnSell);
        
        btnReturn = new JButton("Regresar");
        btnReturn.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnReturn.setBounds(642, 27, 113, 65);
        btnReturn.addActionListener(this);
        container.add(btnReturn);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                isClosed = true;
            }
        });
    }

    public void updateBalance() {
        try {
            List<WalletBalance> balances = WalletBalance.objects.filter("user", request.getUser().getId());
            for (WalletBalance balance: balances) {
                if (balance.getCurrency() == currencyId) {
                    this.lblCurrencyBalance.setText(Double.toString(balance.getBalance()));
                }
                if (balance.getCurrency() == Currency.objects.first("tag", "USDT").getId())
                    this.lblUSDTBalance.setText(Double.toString(balance.getBalance()));
            }
        } catch (IOException | ObjectDoesNotExist e) {
            JOptionPane.showMessageDialog(this, "Error en accesso a database.");
        }
    }
    
    @Override
    public void postInit() {
        TradeView self = this;
        try {
            self.currency = Currency.objects.get(self.currencyId);
            self.lblBuyCurrencyAmount.setText("Monto " + currency.getTag() + ":");
            self.lblSellCurrencyAmount.setText("Monto " + currency.getTag() + ":");
            self.lblCurrencyBalanceText.setText("Saldo " + currency.getTag() + ":");
            self.updateBalance();
        } catch (ObjectDoesNotExist | IOException e) {
            JOptionPane.showMessageDialog(self, "Error en accesso a database.");
        }
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                while (!isClosed) {
                    try {
                        self.currency = Currency.objects.get(self.currencyId);
                        self.txtBuyPrice.setText(String.format("%.2f", currency.getPrice()));
                        self.txtSellPrice.setText(String.format("%.2f", currency.getPrice()));
                        if (!self.txtBuyCurrencyAmount.getText().isEmpty())
                            self.txtBuyUSDTAmount.setText(String.format("%.2f", currency.getPrice() * Double.valueOf(self.txtBuyCurrencyAmount.getText())));
                        else
                            self.txtBuyUSDTAmount.setText("");
                        if (!self.txtSellCurrencyAmount.getText().isEmpty())
                            self.txtSellUSDTAmount.setText(String.format("%.2f", currency.getPrice() * Double.valueOf(self.txtSellCurrencyAmount.getText())));
                        else
                            self.txtSellUSDTAmount.setText("");
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(self, "Error en accesso a database.");
                    }
                }
                
            }
        }).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnReturn) {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        if (e.getSource() == this.buttonBuy) {
            if (this.txtBuyCurrencyAmount.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad.");
                return;
            }
            double amountCurrency = Double.valueOf(this.txtBuyCurrencyAmount.getText());
            double amountUSDT = this.currency.getPrice() * amountCurrency;
            this.txtBuyCurrencyAmount.setText("");
            try {
                List<WalletBalance> balances;
                balances = WalletBalance.objects.filter("user", request.getUser().getId());
                WalletBalance currencyBalance = null;
                WalletBalance USDTBalance = null;
                for (WalletBalance balance: balances) {
                    if (balance.getCurrency() == currencyId) {
                        currencyBalance = balance;
                    }
                    if (balance.getCurrency() == Currency.objects.first("tag", "USDT").getId()) {
                        USDTBalance = balance;
                    }
                }
                if (USDTBalance.getBalance() - amountUSDT < 0) {
                    JOptionPane.showMessageDialog(this, "Saldo insuficiente.");
                    return;
                }
                USDTBalance.setBalance(USDTBalance.getBalance() - amountUSDT);
                USDTBalance.save();
                this.lblUSDTBalance.setText(Double.toString(USDTBalance.getBalance()));
                currencyBalance.setBalance(currencyBalance.getBalance() + amountCurrency);
                currencyBalance.setUsdtInversion(currencyBalance.getUsdtInversion() + amountUSDT);
                currencyBalance.save();
                this.lblCurrencyBalance.setText(Double.toString(currencyBalance.getBalance()));
            } catch (IOException | ObjectDoesNotExist | ValidationError e1) {
                JOptionPane.showMessageDialog(this, "Error en accesso a database.");
            }
        }
        if (e.getSource() == this.btnSell) {
            if (this.txtSellCurrencyAmount.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad.");
                return;
            }
            double amountCurrency = Double.valueOf(this.txtSellCurrencyAmount.getText());
            double amountUSDT = this.currency.getPrice() * amountCurrency;
            this.txtSellCurrencyAmount.setText("");
            try {
                List<WalletBalance> balances;
                balances = WalletBalance.objects.filter("user", request.getUser().getId());
                WalletBalance currencyBalance = null;
                WalletBalance USDTBalance = null;
                for (WalletBalance balance: balances) {
                    if (balance.getCurrency() == currencyId) {
                        currencyBalance = balance;
                    }
                    if (balance.getCurrency() == Currency.objects.first("tag", "USDT").getId()) {
                        USDTBalance = balance;
                    }
                }
                if (currencyBalance.getBalance() - amountCurrency < 0) {
                    JOptionPane.showMessageDialog(this, "Saldo insuficiente.");
                    return;
                }
                currencyBalance.setUsdtInversion(currencyBalance.getUsdtInversion() - (amountCurrency / currencyBalance.getBalance()) * currencyBalance.getUsdtInversion());
                currencyBalance.setBalance(currencyBalance.getBalance() - amountCurrency);
                currencyBalance.save();
                this.lblCurrencyBalance.setText(Double.toString(currencyBalance.getBalance()));
                USDTBalance.setBalance(USDTBalance.getBalance() + amountUSDT);
                USDTBalance.save();
                this.lblUSDTBalance.setText(Double.toString(USDTBalance.getBalance()));
            } catch (IOException | ObjectDoesNotExist | ValidationError e1) {
                JOptionPane.showMessageDialog(this, "Error en accesso a database.");
            }
        }
    }
}
