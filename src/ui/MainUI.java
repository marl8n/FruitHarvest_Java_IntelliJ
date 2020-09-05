package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainUI {
    private JPanel rootPanel;
    private JTable tableFruits;
    private JComboBox comboBoxFruits;
    private JTextField textFieldProductionUnits;
    private JButton btnAddProduction;
    private JTextField textFieldNewFruit;
    private JButton btnNewFruit;
    private JLabel labelTotalFruits;
    private JLabel labelProduction;

    protected Object[][] fruitProductions = new Object[20][2];
    int c = 0;
    int totalFruits = 0;


    public MainUI() {

        createTable();


        btnAddProduction.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(!textFieldProductionUnits.getText().isEmpty() && !comboBoxFruits.getSelectedItem().toString().isEmpty()){
                    addFruitProduction();
                    labelProduction.setText("Procucciones" + c);
                    bubbleSort();
                    setNumberOfUnits();
                    createTable();
                }
            }
        });
        btnNewFruit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!textFieldNewFruit.toString().isEmpty()) {
                    comboBoxFruits.addItem(textFieldNewFruit.getText());
                    textFieldNewFruit.setText("");
                }
            }
        });
    }

    private void addFruitProduction() {
        int idx = -1;
        for (int i = 0; i < c; i++) {
            if(fruitProductions[i][0].toString().equals(comboBoxFruits.getSelectedItem().toString())) {
                idx = i;
            }
        }

        //if ()
        if (idx == -1) {
            fruitProductions[c][0] = comboBoxFruits.getSelectedItem().toString();
            fruitProductions[c][1] = textFieldProductionUnits.getText();
            textFieldProductionUnits.setText("");
            c++;
        }else{
            Integer buffer = Integer.parseInt(fruitProductions[idx][1].toString()) + Integer.parseInt(textFieldProductionUnits.getText());
            fruitProductions[idx][1] = buffer;
        }
    }

    private void bubbleSort(){
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < c-1; j++) {
                for (int k = 0; k < j-1; k++) {
                    if (Integer.parseInt(fruitProductions[j][1].toString()) < Integer.parseInt(fruitProductions[j+1][1].toString())){
                        for (int l = 0; l < 2; l++) {
                            Object buffer = fruitProductions[j][k];
                            fruitProductions[j][k] = fruitProductions[j+1][k];
                            fruitProductions[j+1][k] = buffer;
                        }
                    }
                }
            }
        }
    }

    private void setNumberOfUnits(){
        int numberOfUnits = 0;
        for (int i = 0; i < c; i++) {
            numberOfUnits += Double.parseDouble(fruitProductions[i][1].toString());
        }
        labelTotalFruits.setText("Frutas: " + numberOfUnits);
    }


    public JPanel getRootPanel(){
        return rootPanel;
    }

    private void createTable(){
        tableFruits.setModel(new DefaultTableModel(
                fruitProductions,
                new String[]{"Frutas", "Unidades"}
        ));
    }
}
