package hust.soict.hedspi.aims.store;
import java.util.ArrayList;

import hust.soict.hedspi.aims.exception.DuplicateMediaException;
import hust.soict.hedspi.aims.media.Media;

public class Store {
	private ArrayList<Media> itemsInStore = new ArrayList<>();
	
	public void addMedia(Media media) throws DuplicateMediaException {
		if (!itemsInStore.contains(media)) {
			itemsInStore.add(media);
			System.out.println("The media '" + media.getTitle() + "' has been added to the store.");
		} else {
			throw new DuplicateMediaException("This media is already in the store.");
		}
	}
	
	public void removeMedia(Media media) {
		if (itemsInStore.contains(media)) {
			itemsInStore.remove(media);
			System.out.println("The media '" + media.getTitle() + "' has been removed from the store.");
		}
		else 
			System.out.println("The media is not found in store.");
	}
	
	public Media searchByTitle(String title) {
		for (Media m : itemsInStore) {
			if (m.getTitle().equalsIgnoreCase(title)) {
				return m;
			}
		}
		return null;
	}
	public void printStore() {
		System.out.println("***************** STORE *****************");
        int i = 1;
        for (Media media : itemsInStore) {
            System.out.println(i + ". " + media.toString());
            i++;
        }
        System.out.println("*****************************************");
    }
	
	public ArrayList<Media> getItemsInStore() {
		return itemsInStore;
	}
}
	
