package commande;

import java.util.ArrayList;
import java.util.List;

import modele.Animal;
import modele.Hero;
import vue.VuePimpMyHero;

public class CommandeModifierAnimal extends Commande {
	Animal animal;
	List<Animal> animaux =  new ArrayList<>();
	
	public CommandeModifierAnimal(Animal animal) {
		this.animal = animal;
		
	}

	@Override
	public void executer() {
		VuePimpMyHero.getInstance().ajouterAnimal(this.animal);
		if (Hero.getInstance().getAnimals() != null)
			animaux = Hero.getInstance().getAnimals();
		animaux.add(this.animal);
		Hero.getInstance().setAnimals(animaux);
	}

	@Override
	public void annuler() {
		VuePimpMyHero.getInstance().supprimerAsset(this.animal.getId());
		if (Hero.getInstance().getAnimals() != null)
			animaux = Hero.getInstance().getAnimals();
		animaux.remove(this.animal);
		Hero.getInstance().setAnimals(animaux);
	}
}