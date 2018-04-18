import java.util.Scanner;

public class WindInstrument extends AfekaInstrument {

    private final String[] allowedMaterials = new String[]{
            "Metal", "Wood", "Plastic"
    };

    public WindInstrument(Scanner scanner) {
        super(scanner);
        setMaterial(scanner.nextLine());
    }

    public WindInstrument(double price, String companyName, String material) {
        super(price, companyName);
        setMaterial(material);
    }

    private String material;

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        if (!validateMaterial(material)) throw new InstrumentCreationException(getMaterialErrorMessage());
        this.material = material;
    }

    protected boolean validateMaterial(String material) throws IllegalArgumentException{
        for (String allowedMaterial : allowedMaterials) {
            if (allowedMaterial.equals(material)) return true;
        }
        return false;
    }

    protected String getMaterialErrorMessage() {
        return String.format("Material must be one of: %s", getCommaDelimitedStringFromArray(allowedMaterials));
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof WindInstrument && material.equals(((WindInstrument) obj).getMaterial()) && super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Made of: %12s|", material);
    }
}
