package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.*;

public class StoreManagerScreen extends JFrame {
	private Store store;
	
	public StoreManagerScreen(Store store) {
		this.store = store;
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(createNorth(), BorderLayout.NORTH);
		cp.add(createCenter(), BorderLayout.CENTER);
		
		setTitle("Store");
		setSize(1024, 768);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

	JPanel createNorth() {
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		north.add(createMenuBar());
		north.add(createHeader());
		return north;
	}
	
	JMenuBar createMenuBar() {
		JMenu menu = new JMenu("Options");
		
		JMenuItem viewStoreItem = new JMenuItem("View Store");
		viewStoreItem.addActionListener(e -> {
			new StoreManagerScreen(store);
			this.dispose();
		});
		menu.add(viewStoreItem);
		
		JMenu smUpdateStore = new JMenu("Update Store");
		
		JMenuItem addBookItem = new JMenuItem("Add Book");
		addBookItem.addActionListener(e -> {
		    new AddBookToStoreScreen(store);
		    this.dispose();
		});
        smUpdateStore.add(addBookItem);
		
        JMenuItem addCDItem = new JMenuItem("Add CD");
        addCDItem.addActionListener(e -> {
            new AddCompactDiscToStoreScreen(store);
            this.dispose();
        });
        smUpdateStore.add(addCDItem);
		
        JMenuItem addDVDItem = new JMenuItem("Add DVD");
        addDVDItem.addActionListener(e -> {
            new AddDigitalVideoDiscToStoreScreen(store);
            this.dispose();
        });
		smUpdateStore.add(addDVDItem);
		
		menu.add(smUpdateStore);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuBar.add(menu);
		
		return menuBar;
		
	}
	
	JPanel createHeader() {
		JPanel header = new JPanel();
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		
		JLabel title = new JLabel("AIMS");
		title.setFont(new Font("Arial", Font.BOLD, 50));
		title.setForeground(Color.PINK);
		
		header.add(Box.createRigidArea(new Dimension(10,10)));
		header.add(title);
		header.add(Box.createHorizontalGlue());
		header.add(Box.createRigidArea(new Dimension(10,10)));
		
		return header;
	}
	
	JPanel createCenter() {
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(3,3,2,2));
		
		ArrayList<Media> mediaInStore = store.getItemsInStore();
		for (Media media : mediaInStore) {
			MediaStore cell = new MediaStore(media);
			center.add(cell);
		}
		
		return center;
	}
	
	public static void main(String[] args) {
		Store store = new Store();
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
		Book book1 = new Book("The Hobbit", "Fantasy", 15.99f);
	    book1.addAuthor("J.R.R. Tolkien");
	    Book book2 = new Book("1984", "Dystopian", 12.50f);
	    book2.addAuthor("George Orwell");
	    Book book3 = new Book("Clean Code", "Programming", 35.00f);
	    book3.addAuthor("Robert C. Martin");
	    CompactDisc cd1 = new CompactDisc("Thriller", "Pop", 10.99f, "Quincy Jones (Producer)", 0, "Michael Jackson");
	    cd1.addTrack(new Track("Thriller", 6));
	    cd1.addTrack(new Track("Billie Jean", 5));
	    cd1.addTrack(new Track("Beat It", 4));
	    CompactDisc cd2 = new CompactDisc("Back in Black", "Hard Rock", 11.50f, "Robert John Lange (Producer)", 0, "AC/DC");
	    cd2.addTrack(new Track("Hells Bells", 5));
	    cd2.addTrack(new Track("Back in Black", 4));
	    CompactDisc cd3 = new CompactDisc("Divide", "Pop", 13.00f, "Ed Sheeran", 0, "Ed Sheeran");
	    cd3.addTrack(new Track("Shape of You", 4));
	    cd3.addTrack(new Track("Perfect", 4));
	    cd3.addTrack(new Track("Castle on the Hill", 5));
	    
		store.addMedia(dvd1);
		store.addMedia(dvd2);
		store.addMedia(dvd3);
		store.addMedia(book1);
		store.addMedia(book2);
		store.addMedia(book3);
		store.addMedia(cd1);
		store.addMedia(cd2);
		store.addMedia(cd3);
		
		new StoreManagerScreen(store);
	}
}
