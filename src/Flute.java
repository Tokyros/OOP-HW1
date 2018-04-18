import java.util.Scanner;


public class Flute extends WindInstrument {

    public Flute(double price, String companyName, String material, String type) {
        super(price, companyName, material);
        setType(type);
    }

    public Flute(Scanner scanner) {
        super(scanner);
        setType(scanner.nextLine());
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Flute && type.equals(((Flute) obj).getType()) && super.equals(obj);
    }

    @Override
    public String toString() {
        return String.format("%s Type: %s", super.toString(), type);
    }
}
