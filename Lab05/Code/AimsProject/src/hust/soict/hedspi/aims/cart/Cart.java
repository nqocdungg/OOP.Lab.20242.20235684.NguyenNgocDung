package hust.soict.hedspi.aims.cart;
import java.util.ArrayList;

import hust.soict.hedspi.aims.exception.LimitExceededException;
import hust.soict.hedspi.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;

public class Cart {

	public static final int MAX_NUMBERS_ORDERED = 20;
	private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();
	
	public void addMedia(Media media) throws LimitExceededException {
		if (media == null) {
			System.err.println("Cannot add null media.");
			return;
		}
		if (itemsOrdered.size() < MAX_NUMBERS_ORDERED) {
			if (itemsOrdered.contains(media))
				System.out.println("This media is already in the cart.");
			else {
				itemsOrdered.add(media);
				System.out.println("The media " + media.getTitle() + " has been added.");
			}
		}
		else {
			throw new LimitExceededException("ERROR: The number of media has reached its limit.");
		}
	}

	public void removeMedia(Media media) {
		if (itemsOrdered.isEmpty())
			System.out.println("The cart is empty.");
		else {
			if (itemsOrdered.remove(media))
				System.out.println("The media " + media.getTitle()+ " has been removed.");
			else 
				System.out.println("The media " + media.getTitle()+ " is not found.");
		}
	}
	
	public float totalCost() {
		float total = 0;
		for (Media media : itemsOrdered) {
			total += media.getCost();
		}
		return total;
	}
	
	public void printCart() {
		System.out.println("***********************CART***********************");
		int i = 1;
		for (Media media : itemsOrdered) {
			System.out.println(i + ". " + media.toString());
			i++;
		}
		System.out.println("Total Cost: " + totalCost() + "$");
		System.out.println("***************************************************");
	}
	
	public void sortMediaByTitle() {
	    Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
	    System.out.println("Cart has been sorted by Title.");
	}

	public void sortMediaByCost() {
	    Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
	    System.out.println("Cart has been sorted by Cost.");
	}
	
	public void filterById(int id) {
		boolean flag = false;
		for (Media m : itemsOrdered) {
			if (m.getId() == id) {
				System.out.println(m.toString());
				flag = true;
				break;
			}
		}
		if (!flag)
			System.out.println("No media found with ID " + id);
	}
	
	public void filterByTitle(String title) {
		boolean flag = false;
		for (Media m : itemsOrdered) {
			if (m.getTitle().toLowerCase().contains(title.toLowerCase())) {
				System.out.println(m.toString());
				flag = true;
			}
		}
		if (!flag)
			System.out.println("No media found with title '" + title + "'.");
	}

	public Media searchByTitle(String title) {
		for (Media m : itemsOrdered) {
			if (m.getTitle().equalsIgnoreCase(title)) {
				return m;
			}
		}
		return null;
	}
	
	public void placeOrder() {
	    if (itemsOrdered.isEmpty()) {
	        System.out.println("Cart is already empty. No order placed.");
	    } else {
	        itemsOrdered.clear();
	        System.out.println("An order is created successfully!");
	    }
	}
	
	public ObservableList<Media> getItemsOrdered() {
		return itemsOrdered;
	}
	
	/* 
	public void printCart() {
		System.out.println("***********************CART***********************");
		System.out.println("Ordered Items:");
		int i = 1; 
		for (DigitalVideoDisc disc : itemsOrdered) {
			System.out.println(i + ". " + disc.toString());
			i++;
		}
		System.out.println("Total cost: " + totalCost());
		System.out.println("***************************************************");
	}
	*/
}
