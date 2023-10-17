package vue;

import architecture.Vue;

import java.util.ArrayList;
import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;
import controleur.ControleurPimpMyHero;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import modele.Animal.ANIMAL;
import modele.Assets;

public class VuePimpMyHero extends Vue {

    protected ControleurPimpMyHero controleur;

    protected static VuePimpMyHero instance = null;
    List<String> boutons;
    ColorPicker cp;
    ImageView pushedBouton;

    public static VuePimpMyHero getInstance() {
        if(null==instance)instance = new VuePimpMyHero();
        return VuePimpMyHero.instance;
    };

    private VuePimpMyHero()
    {
        super("personnage.fxml", VuePimpMyHero.class, 1294,743);
        super.controleur = this.controleur = new ControleurPimpMyHero();
        Logger.logMsg(Logger.INFO, "new VuePimpMyHero()");
        boutons = new ArrayList<String>();
        boutons.add("#bouton-selection-casque");
        boutons.add("#bouton-selection-armure");
        boutons.add("#bouton-selection-cape");
        boutons.add("#bouton-selection-bottes");
        boutons.add("#bouton-selection-animal");
        boutons.add("#bouton-selection-background");
        boutons.add("#bouton-telechargement");
        boutons.add("#bouton-refaire");
        boutons.add("#bouton-annuler");
        for(int boutonChoix = 1; boutonChoix < 6; boutonChoix++) {
        	boutons.add("#bouton-choix-" + boutonChoix);
        }
        cp = (ColorPicker)lookup("#colorpicker");
        
    }
	public List<String> getBoutons() {
		return boutons;
	}  

    public void activerControles() {
        super.activerControles();
        
        for(String i : boutons) {
        	activerBouton(boutons.indexOf(i));
        }
        activerCP(cp);

        TextField titre = (TextField)lookup("#titre");
        titre.setOnKeyReleased((EventHandler< KeyEvent>) new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent e) {
                System.out.println("MAJ titre");
                controleur.notifierChangementTitre(titre.getText());
            }
        });
        
        
        
		ImageView jardin = (ImageView)lookup("#background");
		jardin.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent clic) {
				double x = clic.getX();
				double y = clic.getY();
				System.out.println("Clic pour ajouter un animal effectue aux coordonnée : (" + x + " , " + y + ")");
				controleur.notifierAjoutAnimal(x, y);
			}});
    }

    public void changerAsset(Assets.ASSETS asset, int elementId) {
        Logger.logMsg(Logger.INFO, "Changer " + asset.toString().toLowerCase() + ": " + elementId);
        String assetString = asset.toString().toLowerCase();

        //Récupérer l'asset s'il existe
        try {
            ImageView assetImage = (ImageView) lookup("#" + assetString);
            assetImage.setImage(new Image("vue/images/" + assetString + "/" + assetString + elementId + ".png"));
            Logger.logMsg(Logger.INFO, "L'asset a été changé");
        } catch (NullPointerException e) {
            Logger.logMsg(Logger.INFO, "L'asset n'existe pas : ajout");
            ajouterAsset(asset, elementId);
        }
    }

    public void ajouterAsset(Assets.ASSETS asset, int elementId) {
        Logger.logMsg(Logger.INFO, "Ajouter " + asset.toString().toLowerCase() + ": " + elementId);
        String assetString = asset.toString().toLowerCase();

        //Création de l'image
        ImageView assetImage = new ImageView();
        assetImage.setImage(new Image("vue/images/" + assetString + "/" + assetString + elementId + ".png"));

        //Récupérer le conteneur
        AnchorPane conteneur = (AnchorPane) lookup("#anchor-personage-pane");

        //Changer la taille de l'image
        assetImage.preserveRatioProperty().set(true);
        assetImage.setFitWidth(controleur.getAssetSize(asset));

        //Taille max disponible en X et Y
        double maxX = conteneur.getWidth() - assetImage.getFitWidth(); // Bon ici je ne peux pas encore récupérer la largeur de l'image parce que je sais pas
        double maxY = conteneur.getHeight() - assetImage.getFitHeight();
        System.out.println("maxX: " + maxX + " maxY: " + maxY);

        double assetPosX = controleur.getAssetPosition(asset).getX();
        double assetPosY = controleur.getAssetPosition(asset).getY();

        if (assetPosX > maxX) assetPosX = maxX;
        if (assetPosY > maxY) assetPosY = maxY;

        //Déplacer l'image

        if (assetPosX < 0) // Valeur négative = centré
            assetImage.xProperty().bind(conteneur.widthProperty().subtract(assetImage.fitWidthProperty()).divide(2));
        else
            assetImage.setX(assetPosX);

        if (assetPosY < 0)
            assetImage.yProperty().bind(conteneur.heightProperty().subtract(assetImage.fitHeightProperty()).divide(2));
        else
            assetImage.setY(assetPosY);

        //Assigner une id
        assetImage.setId(assetString);

        //Ajouter l'image au conteneur
        conteneur.getChildren().add(assetImage);

        if (assetString.equals("background")) {
            recouperBackground();
        }

        reorganiserLayers();
    }

    public void recouperBackground() {
    	ImageView imageView = (ImageView) lookup("#background");
        Pane imagePane = (Pane) lookup("#anchor-personage-pane");

        // Calculer la position horizontale pour centrer le viewport
        double viewportX = (imageView.getImage().getWidth() - imagePane.getWidth()) / 2;

        imageView.viewportProperty().setValue(new Rectangle2D(viewportX, 0, imagePane.getWidth(), imagePane.getHeight()));
    }

    public void reorganiserLayers() {
        for (Assets.ASSETS asset : Assets.ASSETS.values()) {
            String assetString = asset.toString().toLowerCase();

            //Récupérer l'asset s'il existe
            try {
                if (assetString.equals("label")) {
                    Label label = (Label) lookup("#" + assetString);
                    label.toFront();
                }
                else {
                    ImageView assetImage = (ImageView) lookup("#" + assetString);
                    assetImage.toFront();
                }
            } catch (NullPointerException | ClassCastException e) {
                //Logger.logMsg(Logger.INFO, "L'asset n'existe pas");
            }
        }
    }

    public void redimensionnerAsset(Assets.ASSETS asset, double width) {
    	Logger.logMsg(Logger.INFO, "Resize " + asset.toString().toLowerCase() + ": " + width);
        String assetString = asset.toString().toLowerCase();

        //Récupérer l'asset s'il existe
        try {
            ImageView assetImage = (ImageView) lookup("#" + assetString);
            assetImage.preserveRatioProperty().set(true);
            assetImage.setFitWidth(width);
            Logger.logMsg(Logger.INFO, "L'asset a été redimensionné");
        } catch (NullPointerException e) {
            Logger.logMsg(Logger.INFO, "L'asset n'existe pas");
        }
    }

    public void supprimerAsset(Assets.ASSETS asset) {
        Logger.logMsg(Logger.INFO, "Supprimer " + asset.toString().toLowerCase());
        String assetString = asset.toString().toLowerCase();

        //Récupérer l'asset s'il existe
        try {
            AnchorPane conteneur = (AnchorPane) lookup("#anchor-personage-pane");
            conteneur.getChildren().remove(lookup("#" + assetString));
            Logger.logMsg(Logger.INFO, "L'asset a été supprimé");
        } catch (NullPointerException e) {
            Logger.logMsg(Logger.INFO, "L'asset n'a pas pu être supprimé");
        }
    }
        
    private void activerBouton(int idBouton) {
        Button bouton = (Button)lookup(boutons.get(idBouton));
        bouton.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e) {
                System.out.println("activerBouton : Clic sur " + boutons.get(idBouton));
                controleur.notifierSelectionBouton(idBouton);}});
    }
        
        private void activerCP(ColorPicker cp) {
        	cp.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
        		@Override
        		public void handle(ActionEvent e) {
        			System.out.println("Clic sur " + cp.getId());
        			controleur.notifierSelectionColorPicker(cp);
        			System.out.println("La couleur c'est" + cp.getValue());
        		}
        	});
        }

		public void AfficherListe(String assetString , List<String> items) {
			Logger.logMsg(Logger.INFO, "VuePimpMyHero.AfficherListe()");
			
			for(String i:items) {
				int numeroItem = items.indexOf(i);
		        try {
		            ImageView assetImage = (ImageView) lookup("#image-choix-" + (numeroItem +1) );
		            assetImage.setImage(new Image("vue/images/" + assetString + "/" + items.get(numeroItem)));
		            Logger.logMsg(Logger.INFO, "Image affiché");
		        } catch (NullPointerException e) {
		            Logger.logMsg(Logger.INFO, "Image introuvable");
		        }
				
			}
		}

    public void changerTitre(String text) {
        Label label = (Label)lookup("#label");
        label.setText(text);
    }

    public void changerCouleurLabel(Color value) {
        Label label = (Label)lookup("#label");
        label.setTextFill(value);
    }

	public void ajouterAnimal(double x, double y, ANIMAL animalChoisi) {
		
		ImageView animalAjoute = new ImageView();
		
		switch (animalChoisi) {
			case ANIMAL1:
				animalAjoute.setImage(new Image("vue/images/animal/animal1.png"));
				break;
			case ANIMAL2:
				animalAjoute.setImage(new Image("vue/images/animal/animal2.png"));
				break;
			case ANIMAL3:
				animalAjoute.setImage(new Image("vue/images/animal/animal3.png"));
				break;
			case ANIMAL4:
				animalAjoute.setImage(new Image("vue/images/animal/animal4.png"));
				break;	
			case ANIMAL5:
				animalAjoute.setImage(new Image("vue/images/animal/animal5.png"));
				break;	
		}

		animalAjoute.setPreserveRatio(true);
		animalAjoute.setFitHeight(100);
		animalAjoute.setX(x - 15);
		animalAjoute.setY(y - 50);
		
		
		AnchorPane terrain = (AnchorPane)lookup("#terrain-de-creation");
		terrain.getChildren().add(animalAjoute);
	}

    public void ajouterEffetPush(String id) {
        if (pushedBouton != null) pushedBouton.setOpacity(1);

        Button bouton = (Button)lookup(id);
        Pane parent = (Pane) bouton.getParent();

        List<Node> children = parent.getChildren();
        for (Node child : children) {
            if (child instanceof ImageView imageView) {
                imageView.setOpacity(0.5);
                pushedBouton = imageView;
            }
        }
    }
}


