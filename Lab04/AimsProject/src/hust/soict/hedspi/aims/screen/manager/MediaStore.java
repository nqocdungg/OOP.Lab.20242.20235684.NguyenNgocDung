package hust.soict.hedspi.aims.screen.manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*;

public class MediaStore extends JPanel{
	private Media media;
	
	public MediaStore(Media media) {
		this.media = media;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel(media.getTitle());
		title.setFont(new Font("Arial", Font.PLAIN, 15));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel cost = new JLabel("" + media.getCost() + " $");
		cost.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel container = new JPanel();
		container.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		if (media instanceof Playable) {
			JButton playButton = new JButton("Play");
			playButton.addActionListener(e -> {
		        JDialog dialog = new JDialog();
		        dialog.setTitle("Play Media");
		        dialog.setSize(300, 150);
		        dialog.setModal(true);
		        dialog.setLocationRelativeTo(null);
		        
		        dialog.setLayout(new BorderLayout());

		        JLabel label = new JLabel("Playing: " + media.getTitle(), SwingConstants.CENTER);
		        label.setFont(new Font("Arial", Font.ITALIC, 20));
		        dialog.add(label);

		        dialog.setVisible(true);
		    });
			container.add(playButton);		
		}
		
		this.add(Box.createVerticalGlue());
		this.add(title);
		this.add(cost);
		this.add(Box.createVerticalGlue());
		this.add(container);
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
}
