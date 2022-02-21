package sesiunea9.Tema.AndreiMarc;

abstract class  Plant {
    private int oxygenProduction;

    public Plant(int oxygenProduction) {
         System.out.println("Plant constructor with parameter called.");
        this.oxygenProduction = oxygenProduction;
    }
}
