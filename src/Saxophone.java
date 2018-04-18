import java.util.Scanner;

public class Saxophone extends WindInstrument {

    public Saxophone(Scanner scanner) {
        super(scanner);
    }

    @Override
    protected boolean validateMaterial(String material) {
        return material.equals("Metal");
    }

    @Override
    protected String getMaterialErrorMessage() {
        return "Saxophones must be made from Metal";
    }
}
