package hust.soict.hedspi.aims;

import java.util.Scanner;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.DuplicateMediaException;
import hust.soict.hedspi.aims.exception.LimitExceededException;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.*;

public class AIMS {
	static Scanner scanner = new Scanner(System.in);
	static Store store = new Store();
	static Cart cart = new Cart();
	
	public static void showMenu()  {
		int choice;
		do {
			System.out.println("\nAIMS: ");
			System.out.println("--------------------------------");
			System.out.println("1. View store");
			System.out.println("2. Update store");
			System.out.println("3. See current cart");
			System.out.println("0. Exit");
			System.out.println("--------------------------------");
			System.out.println("Please choose a number: 0-1-2-3");
			choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
				case 1: 
					store.printStore();
					storeMenu();
					break;
				case 2:
					updateStore();
					break;
				case 3:
					cart.printCart();
					cartMenu();
					break;
				case 0:
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Invalid choice! Please enter again.");
			}
		} while (choice != 0);
	}
	public static void storeMenu()  {
		int choice;
        do {
            System.out.println("\nOptions: ");
            System.out.println("--------------------------------");
            System.out.println("1. See a media’s details");
            System.out.println("2. Add a media to cart");
            System.out.println("3. Play a media");
            System.out.println("4. See current cart");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3-4 ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
            	case 1:
            		seeMediaDetails();
            		break;
            	case 2:
            		addMediaToCart();
            		break;
            	case 3:
            		playMedia();
            		break;
            	case 4:
            		cart.printCart();
            		cartMenu();
            		break;
            	case 0:
            		System.out.println("Returning to main menu...");
            		break;
                default:
                	System.out.println("Invalid choice! Please enter again.");
            }
        } while (choice != 0);
    }
	public static void seeMediaDetails() {
		System.out.print("Enter the title of media: ");
		String title = scanner.nextLine();
		Media media = store.searchByTitle(title);
		if (media != null) {
			System.out.println(media.toString());
			mediaDetailsMenu(media);
		}
		else
			System.out.println("The media '" + title + "' is not found.");
	}
	public static void mediaDetailsMenu(Media media)  {
		int choice;
		do {
			System.out.println("\nOptions: ");
			System.out.println("--------------------------------");
			System.out.println("1. Add to cart");
			System.out.println("2. Play");
			System.out.println("0. Back");
			System.out.println("--------------------------------");
			System.out.println("Please choose a number: 0-1-2");
			
			choice = scanner.nextInt();
			scanner.nextLine();
			
			switch (choice) {
				case 1:
					try {
						cart.addMedia(media);
					} catch (LimitExceededException e) {
						System.err.println("ERROR: " + e.getMessage());
						e.printStackTrace();
					}
					break;
				case 2:
					if (media instanceof Playable) {
						try {
							((Playable) media).play();
						} catch (PlayerException e) {
							System.err.println("ERROR: " + e.getMessage());
							System.err.println(e.toString());
							e.printStackTrace();
						}
                    } 
					else 
                        System.out.println("This media cannot be played.");
                    break;
				case 0:
					System.out.println("Returning to store menu...");
					break;
				default: 
					System.out.println("Invalid choice! Please enter again.");
			}
		} while (choice != 0);
	}
	public static void addMediaToCart()  {
		System.out.println("Enter the title of media: ");
		String title = scanner.nextLine();
		Media media = store.searchByTitle(title);
		
		if (media != null) {
			try {
				cart.addMedia(media);
			} catch (LimitExceededException e) {
				System.err.println("ERROR: " + e.getMessage());
				e.printStackTrace();
			}
		}
		else 
			System.out.println("The media is not found in store.");
	}
	public static void playMedia() {
		System.out.println("Enter the title of media: ");
		String title = scanner.nextLine();
		Media media = store.searchByTitle(title);
		
		if (media != null) {
			if (media instanceof Playable) {
				try {
					((Playable) media).play();
				} catch (PlayerException e) {
					System.err.println("ERROR: " + e.getMessage());
					System.err.println(e.toString());
					e.printStackTrace();
				}
            } else {
                System.out.println("This media cannot be played.");
            }
		}
		else 
			System.out.println("The media is not found in store.");
	}
	public static void updateStore() {
		int choice;
        System.out.println("\nUpdate Store:");
        System.out.println("--------------------------------");
        System.out.println("1. Add a media");
        System.out.println("2. Remove a media");
        System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2");
        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addMediaToStore();
                break;
            case 2:
                System.out.println("Enter the title of media:");
                String title = scanner.nextLine();
                Media media = store.searchByTitle(title);
                if (media == null)
                    System.out.println("Media '" + title + "' is not found in store.");
                else {
                    store.removeMedia(media);
                    System.out.println("Media '" + title + "' has been removed from the store.");
                }
                break;
            case 0:
                System.out.println("Back to Main Menu...");
                break;
            default:
                System.out.println("Invalid choice! Please enter again.");
        }
	}
	public static void addMediaToStore() {
		int choice;
		do {
			System.out.println("\nAdd Media:");
	        System.out.println("1. Add a Book");
	        System.out.println("2. Add a CD");
	        System.out.println("3. Add a DVD");
	        System.out.println("0. Back");
	        System.out.println("--------------------------------");
	        System.out.println("Please choose a number: 0-1-2-3");
	        
	        choice = scanner.nextInt();
	        scanner.nextLine();
	        switch(choice) {
		        case 0:
	                break;
	            case 1:
	                System.out.println("Enter the title:");
	                String bookTitle = scanner.nextLine();
	                System.out.println("Enter the category:");
	                String bookCategory = scanner.nextLine();
	                System.out.println("Enter the cost:");
	                float bookCost = scanner.nextFloat();
	                scanner.nextLine();
	                System.out.println("Enter the authors (separated by spaces):");
	                String authors = scanner.nextLine();              
	                Book book = new Book(bookTitle, bookCategory, bookCost);
	                if (!authors.trim().isEmpty()) {
	                    String[] authorsArray = authors.trim().split("\\s+");
	                    for (String author : authorsArray) {
	                        book.addAuthor(author);
	                    }
	                }
	                try {
	                    store.addMedia(book);
	                } catch (DuplicateMediaException e) {
	                    System.err.println("ERROR: " + e.getMessage());
	                }

	                break;
		        case 2: 
		        	System.out.println("Enter the title:");
	                String CDtitle = scanner.nextLine();
	                System.out.println("Enter the category:");
	                String CDcategory = scanner.nextLine();
	                System.out.println("Enter the cost:");
	                float CDcost = scanner.nextFloat();
	                scanner.nextLine();
	                System.out.println("Enter the artist:");
	                String CDartist = scanner.nextLine();
	                System.out.println("Enter the director:");
	                String CDdirector = scanner.nextLine();
	                System.out.println("Enter the length:");
	                int CDlength = scanner.nextInt();
	                scanner.nextLine();
	                
	                CompactDisc cd = new CompactDisc(CDtitle, CDcategory, CDcost, CDdirector, CDlength, CDartist);
	                
	                System.out.println("Enter number of tracks:");
	                int numTracks = scanner.nextInt();
	                scanner.nextLine();
	                for (int i = 0; i < numTracks; i++) {
	                    System.out.println("Track " + (i + 1) + ":");
	                    System.out.print("Title: ");
	                    String trackTitle = scanner.nextLine();
	                    System.out.print("Length: ");
	                    int trackLength = scanner.nextInt();
	                    scanner.nextLine();
	                    Track track = new Track(trackTitle, trackLength);
	                    cd.addTrack(track);
	                }
	                
	                try {
	                    store.addMedia(cd);
	                } catch (DuplicateMediaException e) {
	                    System.err.println("ERROR: " + e.getMessage());
	                }

	                break;
		        case 3: 
		        	System.out.println("Enter the title:");
	                String dvdTitle = scanner.nextLine();
	                System.out.println("Enter the category:");
	                String dvdCategory = scanner.nextLine();
	                System.out.println("Enter the cost:");
	                float dvdCost = scanner.nextFloat();
	                scanner.nextLine();
	                System.out.println("Enter the director:");
	                String dvdDirector = scanner.nextLine();
	                System.out.println("Enter the length:");
	                int dvdLength = scanner.nextInt();
	                scanner.nextLine();

	                DigitalVideoDisc dvd = new DigitalVideoDisc(dvdTitle, dvdCategory, dvdDirector, dvdLength, dvdCost);
	                try {
	                    store.addMedia(dvd);
	                } catch (DuplicateMediaException e) {
	                    System.err.println("ERROR: " + e.getMessage());
	                }

	                System.out.println("DVD added to store!");
	                break;
		        default:
	                System.out.println("Invalid choice! Please enter again.");
	       }
		} while (choice != 0);
	}
	
	public static void cartMenu() {
		int choice;
        do {
        	System.out.println("\nOptions: ");
        	System.out.println("--------------------------------");
        	System.out.println("1. Filter media in cart");
        	System.out.println("2. Sort media in cart");
        	System.out.println("3. Remove media from cart");
        	System.out.println("4. Play a media");
        	System.out.println("5. Place order");
        	System.out.println("0. Back");
        	System.out.println("--------------------------------");
        	System.out.println("Please choose a number: 0-1-2-3-4-5");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    filterCart();
                    break;
                case 2:
                    sortCart();
                    break;
                case 3:
                    removeMediaFromCart();
                    break;
                case 4:
                    playMediaInCart();
                    break;
                case 5:
                    cart.placeOrder();
                    break;
                case 0:
                    System.out.println("Back to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter again.");
            }
        } while (choice != 0);
	}
	
	public static void filterCart() {
		System.out.println("\nFilter by:");
		System.out.println("--------------------------------");
        System.out.println("1. Title");
        System.out.println("2. ID");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
        
        int option = scanner.nextInt();
        scanner.nextLine();
        
        if (option == 0) {
        	return;
        }
        else if (option == 1) {
            System.out.println("Enter title:");
            String title = scanner.nextLine();
            cart.filterByTitle(title);
        } 
        else if (option == 2) {
            System.out.println("Enter ID:");
            int id = scanner.nextInt();
            scanner.nextLine();
            cart.filterById(id);
        } 
        else {
            System.out.println("Invalid option.");
        }
	}
	
	public static void sortCart() {
        System.out.println("\nSort by:");
        System.out.println("1. Title");
        System.out.println("2. Cost");
        int option = scanner.nextInt();
        scanner.nextLine();
        if (option == 1) {
            cart.sortMediaByTitle();
        } else if (option == 2) {
            cart.sortMediaByCost();
        } else {
            System.out.println("Invalid option.");
        }
    }

    public static void removeMediaFromCart() {
        System.out.println("Enter title of media:");
        String title = scanner.nextLine();
        Media media = cart.searchByTitle(title);
        if (media != null) {
            cart.removeMedia(media);
        } else {
            System.out.println("The media is not found in cart!");
        }
    }

    public static void playMediaInCart()  {
        System.out.println("Enter title of media:");
        String title = scanner.nextLine();
        Media media = cart.searchByTitle(title);
        if (media != null && media instanceof Playable) {
            try {
            	((Playable) media).play();
            } catch (PlayerException e) {
            	System.err.println("ERROR: " + e.getMessage());
            	System.err.println(e.toString());
            	e.printStackTrace();
            }
        } else {
            System.out.println("Media is not found or cannot be played!");
        }
    }
    
    public static void main(String[] args)  {
    	try {
            addData();
        } catch (DuplicateMediaException e) {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        showMenu();
    }
    private static void addData() throws DuplicateMediaException {
    	try {
            store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
            store.addMedia(new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 121, 24.95f));
            store.addMedia(new DigitalVideoDisc("Inception", "Sci-Fi Action", "Christopher Nolan", 148, 22.50f));

            Book book1 = new Book("The Hobbit", "Fantasy", 15.99f);
            book1.addAuthor("J.R.R. Tolkien");
            store.addMedia(book1);

            Book book2 = new Book("1984", "Dystopian", 12.50f);
            book2.addAuthor("George Orwell");
            store.addMedia(book2);

            CompactDisc cd1 = new CompactDisc("Thriller", "Pop", 10.99f, "Quincy Jones (Producer)",0, "Michael Jackson");
            cd1.addTrack(new Track("Thriller", 6));
            cd1.addTrack(new Track("Billie Jean", 5));
            cd1.addTrack(new Track("Beat It", 4));
            store.addMedia(cd1);

            CompactDisc cd2 = new CompactDisc("Back in Black", "Hard Rock", 11.50f, "Robert John Lange (Producer)",0, "AC/DC");
            cd2.addTrack(new Track("Hells Bells", 5));
            cd2.addTrack(new Track("Back in Black", 4));
            store.addMedia(cd2);

        } catch (DuplicateMediaException e) {
            System.err.println("Duplicate media detected: " + e.getMessage());
        }

        System.out.println("Sample data loaded.\n");
    }

}
