package commande;

import modele.Animal;
import vue.VuePimpMyHero;

public class CommandeModifierAnimal extends Commande {
	Animal animal;
	
	public CommandeModifierAnimal(Animal animal) {
		this.animal = animal;
	}

	@Override
	public void executer() {
		VuePimpMyHero.getInstance().ajouterAnimal(animal);
		
	}

	@Override
	public void annuler() {
		VuePimpMyHero.getInstance().supprimerAsset(animal.getId());
	}
}