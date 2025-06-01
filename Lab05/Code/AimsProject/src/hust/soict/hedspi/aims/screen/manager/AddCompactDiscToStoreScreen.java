package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.exception.DuplicateMediaException;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
	private JTextField tfTitle;
	private JTextField tfCategory;
	private JTextField tfArtist;
	private JTextField tfCost;
	private JTextField tfTrackTitle;
	private JTextField tfTrackLength;
	private JTextArea tracksArea;
	private ArrayList<Track> tracks = new ArrayList<>();

	public AddCompactDiscToStoreScreen(Store store) {
		super(store);
		setTitle("Add CD To Store");
	}

	@Override
	protected JPanel createCenter() {
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		center.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		JPanel field = new JPanel(new GridLayout(8, 2, 10, 10));

		tfTitle = new JTextField();
		tfCategory = new JTextField();
		tfArtist = new JTextField();
		tfCost = new JTextField();
		tfTrackTitle = new JTextField();
		tfTrackLength = new JTextField();

		field.add(new JLabel("Title:"));     
		field.add(tfTitle);
		
		field.add(new JLabel("Category:"));  
		field.add(tfCategory);
		
		field.add(new JLabel("Artist:"));    
		field.add(tfArtist);
		
		field.add(new JLabel("Cost:"));      
		field.add(tfCost);
		
		field.add(new JLabel("Track Information"));
		field.add(new JLabel());
		field.add(new JLabel("Track Title:")); 
		field.add(tfTrackTitle);
		field.add(new JLabel("Track Length (minutes):")); 
		field.add(tfTrackLength);
		field.add(new JLabel(""));
		JButton btnAddTrack = new JButton("Add Track");
		btnAddTrack.addActionListener(new AddTrackButtonListener());
		JPanel btnAddTrackPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnAddTrackPanel.add(btnAddTrack);
		field.add(btnAddTrackPanel);
		

		tracksArea = new JTextArea(10, 30);
		tracksArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(tracksArea);

		JPanel listPanel = new JPanel(new BorderLayout());
		listPanel.add(new JLabel("Tracks Added:"), BorderLayout.NORTH);
		listPanel.add(scrollPane, BorderLayout.CENTER);

		center.add(field);
		center.add(Box.createVerticalStrut(10));
		center.add(listPanel);

		JButton btnAddCD = new JButton("Add CD");
		btnAddCD.addActionListener(new AddCDButtonListener());
		JPanel btnPanel = new JPanel();
		btnPanel.add(btnAddCD);
		
		center.add(Box.createVerticalStrut(10));
		center.add(btnPanel);

		return center;
	}

	private class AddTrackButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String title = tfTrackTitle.getText().trim();
			String len = tfTrackLength.getText().trim();
			if (title.isEmpty() || len.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter information for both title and length of track.");
				return;
			}
			try {
				int length = Integer.parseInt(len);
				Track track = new Track(title, length);
				tracks.add(track);
				tracksArea.append("Track: " + title + " - Length: " + length + "m\n");
				tfTrackTitle.setText("");
				tfTrackLength.setText("");
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Invalid value for track length. Please enter again!");
			}
		}
	}

	private class AddCDButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String title = tfTitle.getText().trim();
				String category = tfCategory.getText().trim();
				String artist = tfArtist.getText().trim();
				float cost = Float.parseFloat(tfCost.getText().trim());

				CompactDisc cd = new CompactDisc(title, category, cost, artist);
				for (Track t : tracks) {
					cd.addTrack(t);
				}
				
				try {
				    store.addMedia(cd);
				} catch (DuplicateMediaException e1) {
				    System.err.println("ERROR: " + e1.getMessage());
				}


				JOptionPane.showMessageDialog(null, "CD added successfully!");

				tfTitle.setText("");
				tfCategory.setText("");
				tfArtist.setText("");
				tfCost.setText("");
				tfTrackTitle.setText("");
				tfTrackLength.setText("");
				tracksArea.setText("");
				tracks.clear();
				
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Invalid value for cost. Please enter again!");
			}
		}
	}
}
