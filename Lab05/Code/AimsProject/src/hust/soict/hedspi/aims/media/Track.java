package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class Track implements Playable {
	private String title;
	private int length;
	
	public Track() {
		// TODO Auto-generated constructor stub
	}

	public Track(String title, int length) {
		this.title = title;
		this.length = length;
	}

	public String getTitle() {
		return title;
	}

	public int getLength() {
		return length;
	}
	
	@Override
	public void play() throws PlayerException {
		if (this.getLength() <= 0) {
			throw new PlayerException("ERROR: Track length is non-positive!");
		}
		else {
			System.out.println("Playing Track: "+ this.getTitle());
			System.out.println("Track Length: "+ this.getLength());
		}
	}
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Track)) return false;
        Track other = (Track) o;
        if (this.title == null || other.title == null) return false;
        return this.title.equalsIgnoreCase(other.title) && this.length == other.length;
    }
}
