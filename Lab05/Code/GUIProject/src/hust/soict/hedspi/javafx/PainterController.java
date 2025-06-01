package hust.soict.hedspi.javafx;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;

public class PainterController {
	@FXML
    private Pane drawingAreaPane;
	
    @FXML
    private RadioButton penRadioButton;

    @FXML
    private RadioButton eraserRadioButton;

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
    	Color color = Color.PINK;
    	
    	if (eraserRadioButton.isSelected()) {
    		color = Color.WHITE;
    	}
    	Circle newCircle = new Circle(event.getX(), event.getY(), 4);
    	newCircle.setFill(color);
    	drawingAreaPane.getChildren().add(newCircle);
    	
    	
    }

    @FXML
    void clearButtonPressed(MouseEvent event) {
    	drawingAreaPane.getChildren().clear();
    }
    


}
