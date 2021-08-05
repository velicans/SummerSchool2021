package tema_sesiunea9;

public class Cedar extends Pinidae implements Eatable{

    int numberOfSpecies = 10;


    public void getGrowthEnvironment(){
        System.out.println("mountain and Mediterranean");
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
