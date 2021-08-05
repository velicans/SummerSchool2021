package sesiunea9.tema;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Orange extends Plant implements Fructiferous,Eatable {

    private int age;
    private int height;

    @Override
    public String getTimeToHarvest() {
        return "August";
    }

    @Override
    public boolean isEatable() {
        return true;
    }

    @Override
    public String toString() {
        return "Orange{\n" +
                "   age=" + age + ",\n" +
                "   height=" + height + ",\n" +
                "   oxygen_production=" + getOxygenProduction() + ",\n" +
                "   isEatable='" + isEatable() + '\'' + "\n" +
                '}';
    }
}
