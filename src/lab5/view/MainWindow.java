package lab5.view;

import lab5.MyTableModel;
import lab5.data.Corvette;
import lab5.data.Fleet;
import lab5.data.Sailboat;
import lab5.data.Steamboat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainWindow extends JFrame {
    private JTable jTable;
    private MyTableModel myTableModel;
    private JButton sellButton;
    private JSplitPane jSplitPane;
    private JButton addButton;
    private JLabel balance;
    private JButton addBalance;
    public MainWindow(){

        super("MainWindow");
        jTable = new JTable();
        myTableModel = new MyTableModel(new Fleet());
        sellButton = new JButton("Продать корабль");
        addButton = new JButton("Купить корабль");
        jSplitPane = new JSplitPane();
        balance = new JLabel(String.format("Баланс - %s",myTableModel.getBalance()));
        addBalance = new JButton("Добавить денег");


        jTable.setModel(myTableModel);
        JScrollPane jScrollPane = new JScrollPane(jTable);


        addBalance.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myTableModel.setBalance(myTableModel.getBalance()+500);
                balance.setText("Баланс - "+ myTableModel.getBalance());
            }
        });

        sellButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    myTableModel.delete(jTable.getSelectedRow());
                    balance.setText("Баланс - " + myTableModel.getBalance());
                } catch (IndexOutOfBoundsException ex) {
                    JDialog jDialog = new JDialog(MainWindow.this, "Выберите Корабль", true);
                    jDialog.setSize(250,20);
                    jDialog.setLocationRelativeTo(null);
                    jDialog.setVisible(true);
                }

            }
        });
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog jDialog = new JDialog(MainWindow.this, "Купить корабль", true);
                JPanel jPanel = new JPanel(new GridLayout(4, 2, 2, 2));
                JTextField nameField = new JTextField();
                String[] types = {"Парусник", "Пароход", "Корвет"};
                JComboBox typeField = new JComboBox(types);
                JTextField featuresField = new JTextField();

                jPanel.add(new JLabel("Название корабля"));
                jPanel.add(nameField);
                jPanel.add(new JLabel("Тип корабля"));
                jPanel.add(typeField);
                JLabel features = new JLabel("Введите кол-во мачт");
                JButton okButton = new JButton("ОК");

                typeField.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        switch ((String)typeField.getSelectedItem()){
                            case "Парусник":
                                features.setText("Введите кол-во мачт");
                                break;
                            case "Пароход":
                                features.setText("Введите кол-во тонн угля");
                                break;
                            case "Корвет":
                                features.setText("Введите кол-во пушек");
                                break;
                            default:
                                break;
                        }


                    }
                });
                okButton.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch ((String)typeField.getSelectedItem()){
                            case "Парусник":
                                if (myTableModel.getBalance() < 300){
                                    JDialog jDialog = new JDialog(MainWindow.this, "Недостаточно средств", true);
                                    jDialog.setSize(250,20);
                                    jDialog.setLocationRelativeTo(null);
                                    jDialog.setVisible(true);
                                    return;
                                }
                                if (parseInt(featuresField.getText()) != -1){
                                    Sailboat sailboat = new Sailboat(parseInt(featuresField.getText()), nameField.getText());
                                    myTableModel.add(sailboat);
                                    break;
                                } else {
                                    featuresField.setText("");
                                    return;
                                }
                            case "Пароход":
                                if (myTableModel.getBalance() < 400){
                                    JDialog jDialog = new JDialog(MainWindow.this, "Недостаточно средств", true);
                                    jDialog.setSize(250,20);
                                    jDialog.setLocationRelativeTo(null);
                                    jDialog.setVisible(true);
                                    return;
                                }
                                if (parseInt(featuresField.getText()) != -1){
                                    Steamboat steamboat = new Steamboat(parseInt(featuresField.getText()), nameField.getText());
                                    myTableModel.add(steamboat);
                                    break;
                                }else {
                                    featuresField.setText("");
                                    return;
                                }
                            case "Корвет":
                                if (myTableModel.getBalance() < 500){
                                    JDialog jDialog = new JDialog(MainWindow.this, "Недостаточно средств", true);
                                    jDialog.setSize(250,20);
                                    jDialog.setLocationRelativeTo(null);
                                    jDialog.setVisible(true);
                                    break;
                                }
                                if (parseInt(featuresField.getText()) != -1){
                                    Corvette corvette = new Corvette(parseInt(featuresField.getText()), nameField.getText());
                                    myTableModel.add(corvette);
                                    return;
                                } else {
                                    featuresField.setText("");
                                    return;
                                }

                            default:
                                break;
                        }
                        balance.setText("Баланс - "+ myTableModel.getBalance());
                        jDialog.setVisible(false);
                    }
                });



                jPanel.add(features);
                jPanel.add(featuresField);
                jPanel.add(okButton);


                jDialog.add(jPanel);
                jDialog.pack();
                jDialog.setLocationRelativeTo(null);
                jDialog.setVisible(true);
            }
        });





        JPanel jPanel = new JPanel(new GridLayout(10, 1, 2, 2));

        jPanel.add(addButton);
        jPanel.add(sellButton);
        jPanel.add(addBalance);
        jPanel.add(balance);


        jSplitPane.setTopComponent(jScrollPane);
        jSplitPane.setBottomComponent(jPanel);
        this.add(jSplitPane);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);



    }
    private int parseInt(String str){
        try{
            if (Integer.parseInt(str) < 0){
                throw new IllegalArgumentException();
            }
            return Integer.parseInt(str);
        } catch (NumberFormatException ex){
            JDialog jDialog = new JDialog(MainWindow.this, "Введите число", true);
            jDialog.setSize(250,20);
            jDialog.setLocationRelativeTo(null);
            jDialog.setVisible(true);

            return -1;
        } catch (IllegalArgumentException exx){
            JDialog jDialog = new JDialog(MainWindow.this, "Введите положительное число", true);
            jDialog.setSize(250,20);
            jDialog.setLocationRelativeTo(null);
            jDialog.setVisible(true);
            return -1;
        }
    }
}
