package hust.soict.hedspi.aims.media;
import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
	private List<String> authors = new ArrayList<>();
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(String title) {
        super(title);
    }
	
	public Book(String title, String category, float cost) {
        super(title, category, cost);
    }
	
	public Book(String title, String category, float cost, List<String> authors) {
        super(title, category, cost);
        if (authors != null) {
            this.authors = authors;
        }
    }
	
	public void addAuthor(String name) {
		if (!authors.contains(name)) {
			authors.add(name);
			System.out.println("Author '" + name + "' has been added.");
		}
		else
			System.out.println("Author " + name + " already exists.");
	}
	public void removeAuthor(String name) {
		if (authors.contains(name)) {
			authors.remove(name);
			System.out.println("Author '" + name + "' has been removed.");
		}
		else
			System.out.println("Author '" + name + "' is not found.");
	}
	
	public List<String> getAuthors() {
		return authors;
	}
	
	@Override
	public String toString() {
	    return "Book - " + getTitle() + " - " + getCategory() + ": " + getCost() + "$";
	}

}
