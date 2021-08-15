package tema;

class Rose extends Plant implements Eatable, Price, Fructiferous {

     String color;
     String timeToHarvrest;
     int price;


    public Rose(int oxygenProduction, String color, String timeToHarvrest, int price) {
        super(oxygenProduction);
        this.color=color;
        this.timeToHarvrest=timeToHarvrest;
        // TODO Auto-generated constructor stub
        this.price = price;
    }

    @Override
    public boolean isEatable() {
        // TODO Auto-generated method stub
        return false;
    }
    public String getRoseColor(){
        return color;
    }

    @Override
    public String getTimeToHarvest() {
        return timeToHarvrest;
    }

    @Override
    public int getPrice() {
        return price;
    }
}