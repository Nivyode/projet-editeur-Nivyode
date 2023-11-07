package commande;

import modele.Hero;
import modele.Hero.BACKGROUND;

public class CommandeModifierBackground extends Commande{
	
	BACKGROUND ancienBackground;
	BACKGROUND nouveauBackground;
	
	public CommandeModifierBackground(BACKGROUND ancienBackground, BACKGROUND nouveauBackground) {
		this.ancienBackground = ancienBackground;
		this.nouveauBackground = nouveauBackground;
	}
	

	@Override
	public void executer() {
		Hero.getInstance().setBackgroundActuel(nouveauBackground);
		
	}

	@Override
	public void annuler() {
		
	}
	
}