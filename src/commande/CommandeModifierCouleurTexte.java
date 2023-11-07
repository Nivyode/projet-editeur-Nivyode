package commande;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import modele.Hero;
import vue.VuePimpMyHero;

public class CommandeModifierCouleurTexte extends Commande{
	Color ancienneCouleur;
	Color nouvelleCouleur;
	
	public CommandeModifierCouleurTexte(Color nouvelleCouleur){
		this.ancienneCouleur = Hero.getInstance().getCouleurNom();
		this.nouvelleCouleur = nouvelleCouleur;
	}

	@Override
	public void executer() {
		System.out.println("CommandeModifierCouleurTexte.executer()");
		VuePimpMyHero.getInstance().changerCouleurLabel(nouvelleCouleur);
		Hero.getInstance().setCouleurNom(nouvelleCouleur);

	}

	@Override
	public void annuler() {
		System.out.println("CommandeModifierCouleurTexte.annuler()");
		VuePimpMyHero.getInstance().changerCouleurLabel(ancienneCouleur);
		Hero.getInstance().setCouleurNom(ancienneCouleur);
		// TODO Auto-generated method stub
		
	}

}
