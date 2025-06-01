package hust.soict.hedspi.test.screen.customer.cart;



import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.DuplicateMediaException;
import hust.soict.hedspi.aims.exception.LimitExceededException;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.screen.customer.controller.CartController;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestCartScreen extends Application {
	
    private static Cart cart;
    private static Store store;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        final String CART_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Cart.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CART_FXML_FILE_PATH));
        CartController cartController = new CartController(store, cart);
        fxmlLoader.setController(cartController);
        Parent root = fxmlLoader.load();
        
        primaryStage.setTitle("Cart");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }
    
    public static void main(String[] args) throws LimitExceededException, DuplicateMediaException {
        cart = new Cart();
        store = new Store();
        
        DigitalVideoDisc dvd4 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd5 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        
        Book book4 = new Book("The Hobbit", "Fantasy", 15.99f);
        book4.addAuthor("J.R.R. Tolkien");
        
        CompactDisc cd5 = new CompactDisc("Thriller", "Pop", 10.99f, "Quincy Jones (Producer)", 0, "Michael Jackson");
        cd5.addTrack(new Track("Thriller", 6));
        cd5.addTrack(new Track("Billie Jean", 5));
        
        cart.addMedia(dvd4);
        cart.addMedia(dvd5);
        cart.addMedia(book4);
        cart.addMedia(cd5);
        
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
		
        
        System.out.println("CartController initialized with " + cart.getItemsOrdered().size() + " items");

        
        launch(args);
    }
}
