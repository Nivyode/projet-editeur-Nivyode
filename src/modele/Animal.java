package modele;


import java.util.ArrayList;

public class Animal {
	public enum ANIMAL{ANIMAL1, ANIMAL2, ANIMAL3, ANIMAL4, ANIMAL5}
	protected ANIMAL animal;
	protected double x,y;

	protected String id;
	
	public Animal(){
		super();
	}
	
	public Animal(ANIMAL animal) {
		super();
		this.animal = animal;
	}
	public Animal(ANIMAL animal,double coordX, double coordY, String id) {
		super();
		this.animal = animal;
		this.x = coordX;
		this.y = coordY;
		this.id = id;
	}
	
	public ANIMAL getAnimal() {
		return animal;
	}
	public String getId() {
		return id;
	}

	public void setAnimal(ANIMAL animal) {
		this.animal = animal;
	}
	
	public String toString() {
		return animal.toString() + "<coordX>" + x + "</coordX>" + "<coordY>" + y + "</coordY>";
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
