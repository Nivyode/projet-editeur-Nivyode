package commande;

import modele.Assets;
import modele.Assets.ASSETS;
import modele.Hero;
import modele.Hero.BACKGROUND;

public class CommandeModifierBackground extends Commande{
	
	BACKGROUND ancienBackground;
	BACKGROUND nouveauBackground;
	ASSETS itemChoisi;
	int idBackGround;
	
	public CommandeModifierBackground(int id, ASSETS itemChoisi ) {
		this.ancienBackground = Hero.getInstance().getBackgroundActuel();
		this.nouveauBackground = BACKGROUND.valueOf("BACKGROUND" + id);
		this.itemChoisi = itemChoisi;
		this.idBackGround = id;
	}
	

	@Override
	public void executer() {
		System.out.println("CommandeModifierBackground.executer()");
		Hero.getInstance().setBackgroundActuel(nouveauBackground);
		vue.VuePimpMyHero.getInstance().changerAsset(this.itemChoisi, this.idBackGround);
	}

	@Override
	public void annuler() {
		System.out.println("CommandeModifierBackground.annuler()");
		
	}
	
}