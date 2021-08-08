package sesiunea9.TEMA_ANDREI_PAVEN;

// Ex. 1.

abstract class  Plant {

    // Mandatory attribute for every plant object
    private int oxygenProduction;

    public Plant(int oxygenProduction) {
        this.oxygenProduction = oxygenProduction;
        System.out.println("Oxygen production is: " + oxygenProduction);
    }

}
