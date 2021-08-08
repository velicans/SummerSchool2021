package sesiunea9.TemaSimonaMakkos;

public class Rose extends Plant implements Eatable,Fructiferous{
    public static String color;

    public String getColor(){
        return color;
    }

    public Rose(){
    }

    public  Rose (String color){
        this.color = color;
    }

    @Override
    public boolean isEatable() {
        return false;
    }

    @Override
    public String getTimeToHarvest() {
        return "june";
    }
}
