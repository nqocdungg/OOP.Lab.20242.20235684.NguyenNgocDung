package hust.soict.hedspi.aims.screen.customer.controller;


import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.LimitExceededException;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ItemController {
	private Media media;
	private Cart cart;
	
	@FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlay;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblCost;

    @FXML
    void btnAddToCartClicked()  {
    	if (cart != null && media != null) {
    		try {
				cart.addMedia(media);
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Added Successfully");
	            alert.setHeaderText(null);
	            alert.setContentText("Added \"" + media.getTitle() + "\" to the cart.");
	            alert.showAndWait();
	            
			} catch (LimitExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error Add To Cart");
				alert.setHeaderText("Playback Failed");
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			}
    	}
    }

    @FXML
    void btnPlayClicked() {
        if (media instanceof Playable) {
            try {
                StringBuilder playbackInfo = new StringBuilder();

                if (media instanceof CompactDisc) {
                    CompactDisc cd = (CompactDisc) media;
                    playbackInfo.append("Now playing CD: ").append(cd.getTitle()).append("\n")
                                .append("Artist: ").append(cd.getArtist()).append("\n")
                                .append("Total Length: ").append(cd.getLength()).append(" sec\n\n");

                    if (cd.getLength() <= 0) {
                        throw new PlayerException("ERROR: CD length is non-positive!");
                    }

                    int i = 1;
                    for (Track track : cd.getTracks()) {
                        playbackInfo.append("Track ").append(i++).append(": ")
                                    .append(track.getTitle())
                                    .append(" (").append(track.getLength()).append(" sec)\n");
                    }

                    cd.play(); 

                } else if (media instanceof DigitalVideoDisc) {
                    DigitalVideoDisc dvd = (DigitalVideoDisc) media;
                    if (dvd.getLength() <= 0) {
                        throw new PlayerException("ERROR: DVD length is non-positive!");
                    }

                    playbackInfo.append("Now playing DVD: ").append(dvd.getTitle()).append("\n")
                                .append("Length: ").append(dvd.getLength()).append(" sec");

                    dvd.play();
                } else {
                    ((Playable) media).play();
                    playbackInfo.append("Now playing: ").append(media.getTitle());
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Playing Media");
                alert.setHeaderText(null);
                alert.setContentText(playbackInfo.toString());
                alert.showAndWait();

            } catch (PlayerException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Playing Media");
                alert.setHeaderText("Playback Failed");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Play Media");
            alert.setHeaderText(null);
            alert.setContentText("Selected media cannot be played.");
            alert.showAndWait();
        }
    }

	public void setData(Media media) {
		this.media = media;
		lblTitle.setText(media.getTitle());
		lblCost.setText(media.getCost()+" $");
		if (media instanceof Playable) {
			btnPlay.setVisible(true);
		}
		else {
			btnPlay.setVisible(false);
			HBox.setMargin(btnAddToCart, new Insets(0,0,0,100));
		}
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
}
