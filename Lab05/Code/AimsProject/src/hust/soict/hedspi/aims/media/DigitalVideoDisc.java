package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {
	
	public DigitalVideoDisc() {
		
	}
	
	public DigitalVideoDisc(String title) {
        this(title, "Unknown Category", "Unknown Director", 0, 0.0f);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        this(title, category, "Unknown Director", 0, cost);
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        this(title, category, director, 0, cost);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, director, length, cost);
    }

	@Override
	public String toString() {
		return "DVD - " + getTitle() + " - " + getCategory() + " - " + getDirector() + " - " + getLength() + ": " + getCost() + "$";
	}
	
	public boolean isMatch(String title) {
		if (title == null || getTitle() == null) return false;
		return this.getTitle().toLowerCase().contains(title.toLowerCase());
	}
	
	@Override
	public void play() throws PlayerException {
		if (this.getLength() <= 0) {
			throw new PlayerException("ERROR: DVD length is non-positive!");
		}
		else {
			System.out.println("Playing DVD: " + this.getTitle());
			System.out.println("DVD Length: " + this.getLength());
		}
	}
}
