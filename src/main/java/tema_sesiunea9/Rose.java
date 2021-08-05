package tema_sesiunea9;

public class Rose extends Plant implements Fructiferous,Eatable{

    String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void getTimeToHarvest() {
        System.out.println("Best time to harvest is " + timeToHarvest);
    }

    @Override
    public void isEatable() {
        if (itIsEatable == true) {

            System.out.println("It is eatable");

        }else{

            System.out.println("It is not eatable");
        }
    }
}
