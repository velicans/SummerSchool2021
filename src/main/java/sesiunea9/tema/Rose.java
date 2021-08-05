package sesiunea9.tema;

public class Rose extends Plant implements Eatable {

    @Override
    public boolean isEatable() {
        return false;
    }

    @Override
    public String toString() {
        return "Rose{" + "\n" +
                "   oxygen_production='" + getOxygenProduction() + '\'' + ",\n" +
                "   isEatable='" + isEatable() + '\'' + "\n" +
                '}';
    }
}
