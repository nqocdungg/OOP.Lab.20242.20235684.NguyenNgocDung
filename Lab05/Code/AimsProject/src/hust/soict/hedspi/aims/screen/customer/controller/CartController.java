package hust.soict.hedspi.aims.screen.customer.controller;

import java.io.IOException;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CartController {
	private Cart cart;
	private Store store;

	public CartController(Store store, Cart cart) {
		this.cart = cart;
		this.store = store;
	}
	
	@FXML
    private TableColumn<Media, Integer> colMediaId;

    @FXML
    private Label costLabel;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private TableColumn<Media,String> colMediaTitle;

    @FXML
    private TableColumn<Media,String> colMediaCategory;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;
    
    @FXML
    private TextField tfFilter;

    
    @FXML
    void btnPlayPressed() {
    	Media media = tblMedia.getSelectionModel().getSelectedItem();

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

    @FXML
    void btnRemovePressed() {
    	Media media = tblMedia.getSelectionModel().getSelectedItem();
    	if (media != null) {
	    	cart.removeMedia(media);
	    	updateCost();
    	}
    }


    @FXML
    void btnViewStorePressed(ActionEvent event) {
    	try {
    		final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
    		fxmlLoader.setController(new ViewStoreController(store, cart));
    		
    		Parent root = fxmlLoader.load();
    		
    		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    		stage.setScene(new Scene(root));
    		stage.setTitle("Store");
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Cart");
            alert.setHeaderText(null);
            alert.setContentText("Your cart is empty. Please add items before placing an order.");
            alert.showAndWait();
            return;
        }

        cart.getItemsOrdered().clear();
        tblMedia.setItems(cart.getItemsOrdered());
        updateCost();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Your order has been placed successfully!");
        alert.showAndWait();
    }


    
    @FXML
    public void initialize() {
    	colMediaId.setCellValueFactory(new PropertyValueFactory<Media,Integer>("id"));
    	colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media,String>("title"));
    	colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media,String>("category"));
    	colMediaCost.setCellValueFactory(new PropertyValueFactory<Media,Float>("cost"));
    	
    	tblMedia.setItems(cart.getItemsOrdered());
    	
    	btnPlay.setVisible(false);
    	btnRemove.setVisible(false);
    	
    	tblMedia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Media>() {
    		@Override
    		public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
    			updateButtonBar(newValue);
    		}
    	});
    	
    	updateCost();
    	
    	tfFilter.textProperty().addListener(new ChangeListener<String>() {
    		@Override
    		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			showFilteredMedia(newValue);
    		}
    	});

    }
	private void updateButtonBar(Media media) {
		// TODO Auto-generated method stub
		if (media == null) {
			btnPlay.setVisible(false);
			btnRemove.setVisible(false);
		} else {
			btnRemove.setVisible(true);
	
			if (media instanceof Playable) {
				btnPlay.setVisible(true);;
			} else {
				btnPlay.setVisible(false);
			}
		}
	}
	private void updateCost() {
	    costLabel.setText(String.format("%.2f $", cart.totalCost()));
	}
	
	private void showFilteredMedia(String filter) {
	    if (filter == null || filter.isEmpty()) {
	        tblMedia.setItems(cart.getItemsOrdered());
	        updateCost();
	        return;
	    }
	    
	    if (radioBtnFilterId.isSelected()) {
	        try {
	            int id = Integer.parseInt(filter);
	            tblMedia.setItems(cart.getItemsOrdered().filtered(media -> media.getId() == id));
	        } catch (NumberFormatException e) {
	            tblMedia.setItems(cart.getItemsOrdered());
	        }
	    } else if (radioBtnFilterTitle.isSelected()) {
	        String lowerFilter = filter.toLowerCase();
	        tblMedia.setItems(cart.getItemsOrdered().filtered(media -> media.getTitle().toLowerCase().contains(lowerFilter)));
	    }
	    updateCost();
	}

}
