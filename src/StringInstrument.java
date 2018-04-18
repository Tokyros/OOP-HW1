import java.util.InputMismatchException;
import java.util.Scanner;

public class StringInstrument extends AfekaInstrument {

    private int numOfStrings = 6;

    public StringInstrument(double price, String companyName, int numOfStrings) {
        super(price, companyName);
        setNumOfStrings(numOfStrings);
    }

    public StringInstrument(Scanner scanner) {
        super(scanner);
        try {
            setNumOfStrings(scanner.nextInt());
            scanner.nextLine();
        } catch (InputMismatchException e) {
            throw new InstrumentCreationException("Expected int for number of string and got something else", e);
        }
    }

    public int getNumOfStrings() {
        return numOfStrings;
    }

    public void setNumOfStrings(int numOfStrings) {
        if (numOfStrings <= 0) throw new IllegalArgumentException("Number of strings must be positive");
        this.numOfStrings = numOfStrings;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringInstrument && super.equals(obj) && this.numOfStrings == ((StringInstrument) obj).numOfStrings;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Number of strings:%3d|", numOfStrings);
    }
}
