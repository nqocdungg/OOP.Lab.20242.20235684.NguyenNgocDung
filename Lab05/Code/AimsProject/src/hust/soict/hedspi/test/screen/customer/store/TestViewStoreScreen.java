package hust.soict.hedspi.test.screen.customer.store;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.DuplicateMediaException;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.screen.customer.controller.ViewStoreController;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestViewStoreScreen extends Application {
	private static Store store;
	private static Cart cart;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
		ViewStoreController viewStoreController = new ViewStoreController(store, cart);
		fxmlLoader.setController(viewStoreController);
		Parent root = fxmlLoader.load();
		
		primaryStage.setTitle("Store");
		primaryStage.setScene(new Scene(root, 1100, 768));
		primaryStage.show();
	}
	
	public static void main(String[] args) throws DuplicateMediaException {
		store = new Store();
		cart = new Cart();
		
		// DVD
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
		DigitalVideoDisc dvd4 = new DigitalVideoDisc("Inception", "Sci-Fi", "Christopher Nolan", 120, 29.95f);
		DigitalVideoDisc dvd5 = new DigitalVideoDisc("Interstellar", "Sci-Fi", "Christopher Nolan", 165, 34.95f);
		DigitalVideoDisc dvd6 = new DigitalVideoDisc("Coco", "Animation", "Lee Unkrich", 105, 21.50f);

		// Books
		Book book1 = new Book("The Hobbit", "Fantasy", 15.99f);
		book1.addAuthor("J.R.R. Tolkien");
		Book book2 = new Book("1984", "Dystopian", 12.50f);
		book2.addAuthor("George Orwell");
		Book book3 = new Book("Clean Code", "Programming", 35.00f);
		book3.addAuthor("Robert C. Martin");
		Book book4 = new Book("Sapiens", "History", 22.00f);
		book4.addAuthor("Yuval Noah Harari");

		// CDs
		CompactDisc cd1 = new CompactDisc("Thriller", "Pop", 10.99f, "Quincy Jones", 0, "Michael Jackson");
		cd1.addTrack(new Track("Thriller", 6));
		cd1.addTrack(new Track("Billie Jean", 5));
		cd1.addTrack(new Track("Beat It", 4));

		CompactDisc cd2 = new CompactDisc("Back in Black", "Hard Rock", 11.50f, "Robert John Lange", 0, "AC/DC");
		cd2.addTrack(new Track("Hells Bells", 5));
		cd2.addTrack(new Track("Back in Black", 4));

		CompactDisc cd3 = new CompactDisc("Divide", "Pop", 13.00f, "Ed Sheeran", 0, "Ed Sheeran");
		cd3.addTrack(new Track("Shape of You", 4));
		cd3.addTrack(new Track("Perfect", 4));
		cd3.addTrack(new Track("Castle on the Hill", 5));

		// Add to store
		store.addMedia(dvd1);
		store.addMedia(dvd2);
		store.addMedia(dvd3);
		store.addMedia(dvd4);
		store.addMedia(dvd5);
		store.addMedia(dvd6);
		store.addMedia(book1);
		store.addMedia(book2);
		store.addMedia(book3);
		store.addMedia(book4);
		store.addMedia(cd1);
		store.addMedia(cd2);
		store.addMedia(cd3);
		
		launch(args);
	}
	
}
