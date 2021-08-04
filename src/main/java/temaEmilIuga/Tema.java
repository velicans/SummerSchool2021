package temaEmilIuga;

abstract class Plant {

	int oxygenProduction;

	public Plant(int oxygenProduction) {

		this.oxygenProduction = oxygenProduction;
	}
}

interface Fructiferous {

	public String color = "";

	public String getTimeToHarvest();
}

class Orange extends EatablePlant implements Fructiferous {

	int age;
	int height;
	String timeToHarvest;

	public Orange(int age, int height, String timeToHarvest, int oxygenProduction) {
		super(true, oxygenProduction);
		this.age = age;
		this.height = height;
		this.timeToHarvest = timeToHarvest;

	}

	@Override
	public String getTimeToHarvest() {
		return timeToHarvest;

	}

}

class Pinidae extends Plant {

	public Pinidae(int oxygenProduction) {
		super(oxygenProduction);

	}

	protected int numberOfSpecies = 50;

	public String getGrowthEnvironment() {

		return "Mountain";

	}
}

class Cedar extends Pinidae implements Eatable {
	boolean isEatable;

	public Cedar(int oxygenProduction, boolean isEatable) {
		super(oxygenProduction);
		this.isEatable = isEatable;
		this.numberOfSpecies = 10;
	}

	public String getGrowthEnvironment() {

		return "Mountain and Mediterranean";

	}

	@Override
	public boolean isEatable() {
		return isEatable;

	}

}

interface Eatable {

	public boolean isEatable();
}

class Rose extends Plant implements Eatable {

	public Rose(int oxygenProduction) {
		super(oxygenProduction);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isEatable() {
		// TODO Auto-generated method stub
		return false;
	}

}

class EatablePlant extends Plant implements Eatable {

	boolean eatable;

	public EatablePlant(boolean eatable, int oxygenProduction) {
		super(oxygenProduction);
		this.eatable = eatable;

	}

	@Override
	public boolean isEatable() {
		return true;

	}

}

class Main {

	public static void main(String[] args) {
		Orange tree = new Orange(10, 5, "August", 50);
		Pinidae cedarTree = new Cedar(200, false);
		System.out.println(cedarTree.numberOfSpecies);
		System.out.println(cedarTree.getGrowthEnvironment());
		Rose rose = new Rose(5);

	}
}
