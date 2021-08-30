package sesiunea9.Temasesiunea9;

public class Pinidae extends Plant  {
        public int numberOfSpecies = 50;

        public Pinidae(int oxygenProduction) {
            super(oxygenProduction);
        }

        String getGrowthEnvironment () {
            return "mountain";
        }

        public boolean isEatable() {
            return false;
        }
}
