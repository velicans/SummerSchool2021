package sesiunea9.Temasesiunea9;
// - more details requried for class Rose ?
class Rose extends Plant implements Eatable {
    public Rose(int oxygenProduction) {
        super (oxygenProduction);
    }

    public boolean isEatable() {
        return false;
    }
}