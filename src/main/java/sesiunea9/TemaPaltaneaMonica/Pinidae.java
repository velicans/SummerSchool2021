package sesiunea9.TemaPaltaneaMonica;

//A Pinidae class that extends Plant with a public field numberOfSpecies equal to 50, with the method getGrowthEnvironment that returns mountain
public class Pinidae extends Plant {
   public int numberOfSpecies = 50;

   public Pinidae(int oxygenProduction) {
      super(oxygenProduction);
   }

   public String getGrowthEnvironment () {
         return "mountain";
      }
}
