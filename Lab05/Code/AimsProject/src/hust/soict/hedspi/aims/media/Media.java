package hust.soict.hedspi.aims.media;
import java.util.Comparator;

public abstract class Media implements Comparable<Media>{
	private int id;
	private String title;
	private String category;
	private float cost;
	private static int nbMedia = 0;

	public Media() {
		// TODO Auto-generated constructor stub
	}
	
	public Media(String title) {
        this.title = title;
        this.id = ++nbMedia;
    }
	
	public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
        this.id = ++nbMedia;
    }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	
	public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();
    
    @Override
    public int compareTo(Media other) {
    	if (other == null) {
    		throw new NullPointerException("Cannot compare to null!.\n");
    	}
        int titleComparison = this.getTitle().compareToIgnoreCase(other.getTitle());
        if (titleComparison != 0) {
            return titleComparison;
        } else {
            return Float.compare(this.getCost(), other.getCost());
        }
    } 
    
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true; 
	    if (!(o instanceof Media)) return false;
	    Media other = (Media) o;
	    if (this.title == null || other.title == null) return false;
	    return this.title.equalsIgnoreCase(other.getTitle()) && Float.compare(this.cost,  other.getCost()) == 0;
	}

}
