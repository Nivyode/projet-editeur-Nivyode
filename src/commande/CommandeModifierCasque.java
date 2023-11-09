package commande;

import modele.Hero;
import modele.Assets.ASSETS;
import modele.Hero.BACKGROUND;
import modele.Hero.CASQUE;

public class CommandeModifierCasque extends Commande {
	CASQUE ancienCasque;
	CASQUE nouveauCasque;
	ASSETS itemChoisi;
	int idCasque;
	
	public CommandeModifierCasque(int id, ASSETS itemChoisi ) {
		this.ancienCasque = Hero.getInstance().getCasqueActuel();
		this.nouveauCasque = CASQUE.valueOf("CASQUE" + id);
		this.itemChoisi = itemChoisi;
		this.idCasque = id;	
	}

	@Override
	public void executer() {
		System.out.println("CommandeModifierBackground.executer()");
		Hero.getInstance().setCasqueActuel(this.nouveauCasque);
		vue.VuePimpMyHero.getInstance().changerAsset(this.itemChoisi, this.idCasque);
	}

	@Override
	public void annuler() {
		System.out.println("CommandeModifierBackground.annuler()");
		Hero.getInstance().setCasqueActuel(ancienCasque);
		vue.VuePimpMyHero.getInstance().changerAsset(this.itemChoisi, Integer.parseInt(ancienCasque.toString().substring(6)));			
	}

}
