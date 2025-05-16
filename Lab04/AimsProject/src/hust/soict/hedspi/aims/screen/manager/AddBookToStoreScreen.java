package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
	private JTextField tfTitle;
	private JTextField tfCategory;
	private JTextField tfCost;
	private JTextField tfAuthor;
	private JTextArea authorsArea;
	private ArrayList<String> authors = new ArrayList<>();
	
	public AddBookToStoreScreen(Store store) {
		super(store);
		setTitle("Add Book To Store");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected JPanel createCenter() {
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		
		JPanel field = new JPanel(new GridLayout(5,2,10,10));
		
		tfTitle = new JTextField();
		tfCategory = new JTextField();
		tfCost = new JTextField();
		tfAuthor = new JTextField();

		field.add(new JLabel("Title:"));     
		field.add(tfTitle);
        field.add(new JLabel("Category:"));  
        field.add(tfCategory);
        field.add(new JLabel("Cost:"));      
        field.add(tfCost);
        field.add(new JLabel("Authors:")); 
        
        JPanel authorInputPanel = new JPanel(new BorderLayout(5, 0));
		authorInputPanel.add(tfAuthor, BorderLayout.CENTER);
		JButton btnAddAuthor = new JButton("Add Author");
		btnAddAuthor.addActionListener(new addAuthorButtonListener());
		authorInputPanel.add(btnAddAuthor, BorderLayout.EAST);
		field.add(authorInputPanel);

		authorsArea = new JTextArea(5, 30);
		authorsArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(authorsArea);
		scrollPane.setPreferredSize(new Dimension(tfTitle.getPreferredSize().width, 10));
		field.add(new JLabel("(Enter one author at a time. Click 'Add Author' to submit.)"));
		field.add(scrollPane);
        
        JButton btnAddBook = new JButton("Add Book");
        btnAddBook.addActionListener(new addBookButtonListener());
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnAddBook);

        center.add(field);
        center.add(Box.createVerticalStrut(10));
        center.add(btnPanel);
        
        return center;
	}
	
	private class addBookButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
                String title = tfTitle.getText().trim();
                String category = tfCategory.getText().trim();
                float cost = Float.parseFloat(tfCost.getText().trim());

                Book book = new Book(title, category, cost);
                for (String author : authors) {
                    book.addAuthor(author);
                }

                store.addMedia(book);
                
                JOptionPane.showMessageDialog(null, "Book added successfully!");

                tfTitle.setText("");
                tfCategory.setText("");
                tfCost.setText("");
                tfAuthor.setText("");
                authorsArea.setText("");
                authors.clear();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid value for cost. Please enter again!");
            }
		}
		
	}
	private class addAuthorButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String author = tfAuthor.getText().trim();
            if (!author.isEmpty()) {
                authors.add(author);
                authorsArea.append(author + "\n");
                tfAuthor.setText("");
            } else {
                JOptionPane.showMessageDialog(AddBookToStoreScreen.this, "Author's name cannot be empty. Please enter again!");
            }
		}
		
	}
	
}
