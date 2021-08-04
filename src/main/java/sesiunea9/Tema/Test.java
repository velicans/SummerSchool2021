package Tema;

public class Test {
    public static void main(String[] args){
        Orange orangeTree = new Orange(10, 5, "August", true , 50);
        Cedar cedarTree = new Cedar(200,false);

        System.out.println("The number of species for the created cedar tree is " + cedarTree.getNumberOfSpecies() + ".");
        System.out.println("The growth environment for the created cedar tree is " + cedarTree.getGrowthEnvironment() + ".");

        Orange rose = new Orange(30, 10, "March", false, 35);
        rose.setColor("red");
        System.out.println("The new created Orange object has the qualities of a Rose, with the following stats:");
        System.out.println("Oxygen Production of the Rose being " + rose.getOxygenProduction() + ".");
        System.out.println("The rose... ");
        rose.isEatable();
        System.out.println("The rose's age is " + rose.getAge() + ".");
        System.out.println("The rose's height is " + rose.getHeight() + ".");
        System.out.println("The rose's time of harvest is " + rose.getTimeToHarvest() + ".");
        System.out.println("The rose's color is " + rose.getColor() + ". This is the difference between the rose and the orange.");
        System.out.println("Thank you for reaching here.");
    }
}
