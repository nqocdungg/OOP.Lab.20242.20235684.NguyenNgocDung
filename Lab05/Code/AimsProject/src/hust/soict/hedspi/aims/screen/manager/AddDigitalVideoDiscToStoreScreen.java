package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.exception.DuplicateMediaException;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfTitle, tfCategory, tfDirector, tfLength, tfCost;

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store);
        setTitle("Add DVD To Store");
    }

    @Override
    protected JPanel createCenter() {
    	JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel field = new JPanel(new GridLayout(5, 2, 10, 10));

        tfTitle = new JTextField();
        tfCategory = new JTextField();
        tfDirector = new JTextField();
        tfLength = new JTextField();
        tfCost = new JTextField();

        field.add(new JLabel("Title:")); field.add(tfTitle);
        field.add(new JLabel("Category:")); field.add(tfCategory);
        field.add(new JLabel("Director:")); field.add(tfDirector);
        field.add(new JLabel("Length (minutes):")); field.add(tfLength);
        field.add(new JLabel("Cost:")); field.add(tfCost);

        JButton btnAddDVD = new JButton("Add DVD");
        btnAddDVD.addActionListener(new AddDVDButtonListener());
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnAddDVD);

        center.add(field);
        center.add(Box.createVerticalStrut(20));
        center.add(btnPanel);

        return center;
    }

    private class AddDVDButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String title = tfTitle.getText().trim();
                String category = tfCategory.getText().trim();
                String director = tfDirector.getText().trim();
                int length = Integer.parseInt(tfLength.getText().trim());
                float cost = Float.parseFloat(tfCost.getText().trim());

                DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
                try {
                    store.addMedia(dvd);
                } catch (DuplicateMediaException e1) {
                    System.err.println("ERROR: " + e1.getMessage());
                }


                JOptionPane.showMessageDialog(null, "DVD added successfully!");

                tfTitle.setText("");
                tfCategory.setText("");
                tfDirector.setText("");
                tfLength.setText("");
                tfCost.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid value. Please enter again!");
            }
        }
    }
}