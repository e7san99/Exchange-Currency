package com.company;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class Main {
    private static JPanel panel;
    private static JFrame frame;
    private static JLabel bg;
    private static JButton usaBtn,iraqBtn,exchangeBtn, reset;
    private static boolean flag;
    private static JTextField iqPrice;
    private static double nrx;

    public static void main(String[] args) {

        //panelAndFrame
        panelAndFrame();

        //oneDollar
        oneDollarBackground();

        //Exchange
        exchange();

        //IRAQ
        JTextField iraqField = iraqCollection();

        //USA
        JTextField usaField = usaCollection();

        //price
        JTextField usp = usPrice();

        //programming
        ActionListener actionListener = getActionListener(iraqField, usaField, usp);

        //reset
        reset(iraqField, usaField, actionListener);

        frame.setVisible(true);
    }

    private static void panelAndFrame() {
        panel = new JPanel();
        panel.setLayout(null);

        frame = new JFrame();
        frame.setTitle("Exchange Money");
        frame.setSize(612,290);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
    }

        private static void oneDollarBackground() {
            ImageIcon imageIcon = new ImageIcon("C:\\Users\\LENOVO\\IdeaProjects\\Exchange Currency\\src\\com\\company\\oneDollar.jpg");
            bg = new JLabel(imageIcon);
            frame.add(bg);
            Border yellowLine = BorderFactory.createMatteBorder(2,2,2,2,Color.yellow);
            bg.setBorder(yellowLine);
        }

    private static void exchange() {
        exchangeBtn = new JButton();
        exchangeBtn.setBorder(null);
        exchangeBtn.setContentAreaFilled(false);
        exchangeBtn.setBounds(275,115,25,25);
        bg.add(exchangeBtn);

        ImageIcon exchangeImg = new ImageIcon("C:\\Users\\LENOVO\\IdeaProjects\\Exchange Currency\\src\\com\\company\\exchange.png");
        Image image3 = exchangeImg.getImage();
        Image size3 = image3.getScaledInstance(25,25,Image.SCALE_SMOOTH);
        ImageIcon scaled3 = new ImageIcon(size3);
        exchangeBtn.setIcon(scaled3);
    }

    private static JTextField iraqCollection() {
        iraqBtn = new JButton();
        iraqBtn.setBorder(null);
        iraqBtn.setContentAreaFilled(false);
        iraqBtn.setBounds(320,110,35,35);
        bg.add(iraqBtn);

        ImageIcon iraqImg = new ImageIcon("C:\\Users\\LENOVO\\IdeaProjects\\Exchange Currency\\src\\com\\company\\iraq.png");
        Image image2 = iraqImg.getImage();
        Image size2 = image2.getScaledInstance(35,35,Image.SCALE_SMOOTH);
        ImageIcon scaled2 = new ImageIcon(size2);
        iraqBtn.setIcon(scaled2);

        return setTextField(360,110,75,35,Color.green);
    }

    private static JTextField usaCollection() {
        usaBtn = new JButton();
        usaBtn.setBorder(null);
        usaBtn.setContentAreaFilled(false);
        usaBtn.setBounds(220,110,35,35);
        bg.add(usaBtn);

        ImageIcon usaImg = new ImageIcon("C:\\Users\\LENOVO\\IdeaProjects\\Exchange Currency\\src\\com\\company\\usa.png");
        Image image1 = usaImg.getImage();
        Image size1 = image1.getScaledInstance(35,35,Image.SCALE_SMOOTH);
        ImageIcon scaled1 = new ImageIcon(size1);
        usaBtn.setIcon(scaled1);

        return setTextField(140,110,75,35,Color.red);
    }

    private static JTextField usPrice() {
        iqPrice = new JTextField();
        iqPrice = setTextField(400,30,90,25,Color.BLUE);
        iqPrice.setForeground(Color.RED);
        bg.add(iqPrice);
        return iqPrice;
    }

    private static ActionListener getActionListener(JTextField iraqField, JTextField usaField, JTextField usp) {
        ActionListener actionListener = e ->{
            try {
                nrx = Double.parseDouble((usp.getText()));
                if (flag) {
                    iraqField.setText(String.format("%.2f", (Double.parseDouble(usaField.getText()) * nrx ) / 100));
                    iraqBtn.setBounds(320, 110, 35, 35);
                    usaBtn.setBounds(220, 110, 35, 35);
                    usaField.setBounds(140, 110, 75, 35);
                    iraqField.setBounds(360, 110, 75, 35);
                    iraqField.setEnabled(true);
                    usaField.setEnabled(false);
                } else {
                    usaField.setText(String.format("%.2f",(Double.parseDouble(iraqField.getText()) / nrx ) * 100));
                    usaBtn.setBounds(320, 110, 35, 35);
                    iraqBtn.setBounds(220, 110, 35, 35);
                    iraqField.setBounds(140, 110, 75, 35);
                    usaField.setBounds(360, 110, 75, 35);
                    iraqField.setEnabled(false);
                    usaField.setEnabled(true);
                }
            }catch (Throwable ex){
                JOptionPane.showMessageDialog(null,"Enter a Currency");
            }
            reset.setEnabled(true);
            flag = !flag;
        };
        exchangeBtn.addActionListener(actionListener);
        return actionListener;
    }

    private static void reset(JTextField iraqField, JTextField usaField, ActionListener actionListener) {
        reset = new JButton("reset");
        reset.setBounds(245,180,90,20);
        reset.addActionListener(e->{
            reset.setEnabled(true);
            if (!iraqField.getText().isEmpty() || !usaField.getText().isEmpty()) {
                iraqField.setText(null);
                usaField.setText(null);
                iqPrice.setText(null);
                reset.setEnabled(false);
                usaField.setEnabled(false);
                iraqField.setEnabled(true);
            }
        });
        panel.add(reset);
        bg.add(reset);
        reset.setEnabled(false);    //reset aka click le bkay dway awa false abetawa
        usaField.setEnabled(false); //kate reset fieldy USA esh naka
        reset.addActionListener(actionListener);
        usdConst(); //$100Constant
    }

    public static JTextField setTextField(int x, int y, int wid, int high, Color select ){
        JTextField textField = new JTextField();
        textField.setBounds(x,y,wid,high);
        textField.setFont(new Font("Tahoma",Font.ITALIC,20));
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        Border red = BorderFactory.createMatteBorder(2,2,2,2, select);
        textField.setBorder(red);
        bg.add(textField);
        return textField;
    }

    private static void usdConst() {
        JLabel usdConst100 = new JLabel("$100 USD = ");
        usdConst100.setFont(new Font("Tahoma",Font.BOLD,18));
        usdConst100.setForeground(Color.BLUE);
        usdConst100.setBounds(290,30,120,20);
        bg.add(usdConst100);
        Border borderResult = BorderFactory.createMatteBorder(2,2,0,0,Color.RED);
        usdConst100.setBorder(borderResult);
    }

}
