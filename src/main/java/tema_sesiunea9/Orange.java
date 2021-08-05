package tema_sesiunea9;

public class Orange  extends Plant implements Fructiferous,Eatable{

    int age;
    int height;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void getTimeToHarvest() {
        System.out.println("Time to harvest is " + timeToHarvest);
    }

    public void isEatable() {
        if (itIsEatable == true) {

            System.out.println("It is eatable");

        }else{

            System.out.println("It is not eatable");
        }
    }

}
