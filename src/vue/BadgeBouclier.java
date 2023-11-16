package vue;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BadgeBouclier extends AnchorPane{
	public BadgeBouclier() {
    	/*
		setCouleur(COULEUR.BRONZE);
		setBadge(BADGE.BASE);
		setSymbole(SYMBOLE.TRIBAL);
		setTexte("Bateau");
		*/
	}
	
	public BadgeBouclier deCouleur(COULEUR type)
	{
		this.setCouleur(type);
		return this;
	}
	public BadgeBouclier avecBadge(BADGE type)
	{
		this.setBadge(type);
		return this;
	}
	
	public BadgeBouclier avecSymbole(SYMBOLE type)
	{
		this.setSymbole(type);
		return this;
	}
	public BadgeBouclier avecTexte(String message)
	{
		this.setTexte(message);
		return this;
	}
	


	
	private void setTexte(String message) {
    	Label texte = new Label();
    	texte.setText(message);
    	texte.setStyle("-fx-font: 40px Tahoma;");
    	texte.setLayoutX(155);
    	texte.setLayoutY(170);
    	this.getChildren().add(texte);
		
	}

	public enum BADGE{BASE,ARRONDI,CERCLE}
	private void setBadge(BADGE badge) {
		ImageView background = new ImageView();
		if(badge == BADGE.BASE) {
			background.setImage(new Image("vue/images/badge/badge.png"));
		}else if(badge == BADGE.ARRONDI) {
			background.setImage(new Image("vue/images/badge/arrondi.png"));
		}else if(badge == BADGE.CERCLE) {
			background.setImage(new Image("vue/images/badge/cercle.png"));
		}
		this.getChildren().add(background);
	}
	
	public enum SYMBOLE{ETOILE, TEMPLIERS,TRIBAL}
	private void setSymbole(SYMBOLE symbole) {
		ImageView imageSymbole = new ImageView();
		if(symbole == SYMBOLE.ETOILE) {
			imageSymbole.setImage(new Image("vue/images/badge/etoile.png"));
		}else if(symbole == SYMBOLE.TEMPLIERS) {
			imageSymbole.setImage(new Image("vue/images/badge/templiers.png"));
		}else if(symbole == SYMBOLE.TRIBAL) {
			imageSymbole.setImage(new Image("vue/images/badge/tribal.png"));
		}
		imageSymbole.setLayoutX(85);
		imageSymbole.setLayoutY(85);
		this.getChildren().add(imageSymbole);
	}
	
	
	public enum COULEUR{CHOCOLAT, CAFE, MARRON,BRONZE,BITUME}
	private void setCouleur(COULEUR couleur) {
		if (couleur == COULEUR.CHOCOLAT) {
			this.setStyle("-fx-background-color: #5a3a22;");
		}else if (couleur == COULEUR.CAFE) {
			this.setStyle("-fx-background-color: #462e01;");
		}else if (couleur == COULEUR.MARRON) {
			this.setStyle("-fx-background-color: #582900;");
		}else if (couleur == COULEUR.BRONZE) {
			this.setStyle("-fx-background-color: #614e1a;");
		}else if (couleur == COULEUR.BITUME) {
			this.setStyle("-fx-background-color: #4e3d28;");
		}
	}
	
	
	

}
