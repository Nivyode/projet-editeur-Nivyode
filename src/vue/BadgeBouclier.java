package vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BadgeBouclier extends AnchorPane{
	public BadgeBouclier() {
    	
		this.setPrefHeight(200);
		this.setPrefWidth(166);
		this.setStyle("-fx-background-color: #65350F;");
		
    	ImageView background = new ImageView();
    	background.setImage(new Image("vue/images/badge/badge.png"));
    	this.getChildren().add(background);
		
    	ImageView symbole = new ImageView();
    	symbole.setImage(new Image("vue/images/badge/templiers.png"));
    	symbole.setLayoutX(40);
    	symbole.setLayoutY(40);
    	this.getChildren().add(symbole);
	
	}

}
