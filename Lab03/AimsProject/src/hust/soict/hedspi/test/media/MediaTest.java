package hust.soict.hedspi.test.media;

import hust.soict.hedspi.aims.media.*;

import java.util.ArrayList;
import java.util.List;

public class MediaTest {
    public static void main(String[] args) {
        List<Media> mediaList = new ArrayList<>();

        DigitalVideoDisc dvd = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        Book book = new Book("Effective Java", "Programming", 45.0f);
        CompactDisc cd = new CompactDisc("Hybrid Theory", "Music", 20.0f, "Don Gilmore", 37, "Linkin Park");

        mediaList.add(dvd);
        mediaList.add(book);
        mediaList.add(cd);

        System.out.println("----- Printing media information -----");
        for (Media media : mediaList) {
            System.out.println(media.toString());
        }
    }
}
