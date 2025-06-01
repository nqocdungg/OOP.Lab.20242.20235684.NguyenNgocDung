package hust.soict.hedspi.aims.media;
import java.util.ArrayList;

import hust.soict.hedspi.aims.exception.PlayerException;

public class CompactDisc extends Disc implements Playable {
	private String artist;
	private ArrayList<Track> tracks = new ArrayList<>()
			;
	public CompactDisc() {
		// TODO Auto-generated constructor stub
	}

	public String getArtist() {
		return artist;
	}
	
    public CompactDisc(String title) {
        this(title, "Unknown Category", 0.0f, "Unknown Director", 0, "Unknown Artist");
    }

    public CompactDisc(String title, String category, float cost) {
        this(title, category, cost, "Unknown Director", 0, "Unknown Artist");
    }

    public CompactDisc(String title, String category, float cost, String artist) {
        this(title, category, cost, "Unknown Director", 0, artist);
    }

    public CompactDisc(String title, String category, float cost, String director, int length, String artist) {
        super(title, category, director, length, cost);
        this.artist = artist;
    }
    public ArrayList<Track> getTracks() {
        return this.tracks;
    }

	
	public void addTrack(Track track) {
		if (tracks.contains(track)) {
			System.out.println("Track '" + track.getTitle() + "' already exists.");
		}
		else {
			tracks.add(track);
			System.out.println("Track '" + track.getTitle() + "' has been added.");
		}
	}
	public void removeTrack(Track track) {
		if (tracks.contains(track)) {
			tracks.remove(track);
			System.out.println("Track '" + track.getTitle() + "' has been removed.");
		}
		else 
			System.out.println("Track '" + track.getTitle() + "' not found.");
	}
	
	@Override
	public void play() throws PlayerException {
		if (this.getLength() <= 0) {
			throw new PlayerException("ERROR: CD length is non-positive!");
		}
		else {
			System.out.println("Playing CD: " + this.getTitle());
			System.out.println("Artist: " + this.getArtist());
			
			for (Track track:tracks) {
				try {
					track.play();
				}
				catch (PlayerException e) {
					String errorMessage = "Track \"" + track.getTitle() + "\" cannot be played.\n" + e.getMessage();
					System.err.println(errorMessage);
					throw new PlayerException(errorMessage);
				}
			}

//			
//			if (tracks.isEmpty()) {
//				System.out.println("This CD has no tracks to play.");
//				return;
//			}
//			System.out.println("*** Playing Tracks ***");
//			for (Track track : tracks) {
//				track.play();
//			}
//			System.out.println("*** Finished playing CD ***");
		}
		
	}
	@Override
	public String toString() {
	    return "CD - " + getTitle() + " - " + getCategory() + " - " + getArtist() + ": " + getCost() + "$";
	}
	
	@Override
	public int getLength() {
		int totalLength = 0;
		for (Track track : tracks) {
			totalLength += track.getLength();
		}
		return totalLength;
	}
	
}
