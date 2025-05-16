import java.util.ArrayList;

public class Cart {

	public static final int MAX_NUMBERS_ORDERED = 20;
	private ArrayList<DigitalVideoDisc> itemsOrdered;
	private int qtyOrdered = 0;
	public Cart() {
		itemsOrdered = new ArrayList<>();
	}
	public void addDigitalVideoDisc(DigitalVideoDisc disc) {
		if (qtyOrdered < MAX_NUMBERS_ORDERED) {
			itemsOrdered.add(disc);
			qtyOrdered++;
			System.out.println("The disc has been added.");
		}
		else {
			System.out.println("The cart is almost full.");
		}
	}
	// 14.1 : An array parameter
	public void addDVD1(DigitalVideoDisc[] dvdlist) {
		if (qtyOrdered < MAX_NUMBERS_ORDERED) {
			for (DigitalVideoDisc disc : dvdlist) {
				addDigitalVideoDisc(disc);
			}
			System.out.println("The dvd list has been added.");
		}
		else {
			System.out.println("The cart is almost full.");
		}
	}
	// 14.1 : An arbitrary number of arguments 
	public void addDVD2(DigitalVideoDisc... dvds) {
        for (DigitalVideoDisc disc : dvds) {
            addDigitalVideoDisc(disc);
        }
    }
	// 14.2 : Two parameters
	public void addDVD3(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
		if(qtyOrdered < MAX_NUMBERS_ORDERED) {
			addDigitalVideoDisc(dvd1);
			addDigitalVideoDisc(dvd2);
			System.out.println("The disc " + dvd1.getTitle()  + " has been added.");
			System.out.println("The disc " + dvd2.getTitle()  + " has been added.");
		}
		else {
			System.out.println("The cart is almost full.");
		}
	}
	public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
		if (qtyOrdered == 0) {
			System.out.println("The cart is empty.");
		}
		else {
			for (DigitalVideoDisc dvd : itemsOrdered) {
				if (dvd == disc) {
					itemsOrdered.remove(dvd);
					qtyOrdered--;
					System.out.println("The disc '"+ disc.getTitle()+"' has been removed.");
					return;
				}
			}
			System.out.println("The disc '"+ disc.getTitle()+"' is not found in the cart.");
		}
	}
	public float totalCost() {
		float total = 0;
		for (DigitalVideoDisc disc : itemsOrdered) {
			total += disc.getCost();
		}
		return total;
	}
	public void display() {
		int i = 1;
		for (DigitalVideoDisc disc : itemsOrdered) {
			System.out.println(i + "\t" + disc.getTitle() + "\t" + disc.getCost());
			i++;
		}
		System.out.println(" \tTotal Cost\t" + totalCost());
	}

}
