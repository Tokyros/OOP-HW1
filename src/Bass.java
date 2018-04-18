import java.util.InputMismatchException;
import java.util.Scanner;

public class Bass extends StringInstrument {

    private static final int MIN_NUM_OF_STRING = 4;
    private static final int MAX_NUM_OF_STRING = 6;
    private boolean fretless;

    public Bass(double price, String companyName, int numOfStrings, boolean isFretless) {
        super(price, companyName, numOfStrings);
        setFretless(isFretless);
    }

    public Bass(Scanner scanner) {
        super(scanner);
        setFretless(scanner);
    }

    private void setFretless(Scanner scanner) {
        try {
            setFretless(scanner.nextBoolean());
        } catch (InputMismatchException ex) {
            throw new InstrumentCreationException("Whether a bass is fretless or not is boolean, any other string than \"True\" or \"False\" is not acceptable", ex);
        }
    }

    public boolean getFretless() {
        return fretless;
    }

    public void setFretless(boolean fretless) {
        this.fretless = fretless;
    }

    @Override
    public void setNumOfStrings(int numOfStrings) {
        if (MIN_NUM_OF_STRING > numOfStrings || numOfStrings > MAX_NUM_OF_STRING)
            throw new InstrumentCreationException(String.format("Bass number of strings is a number between %d and %d", MIN_NUM_OF_STRING, MAX_NUM_OF_STRING));
        super.setNumOfStrings(numOfStrings);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Bass && fretless == ((Bass) obj).fretless && super.equals(obj);
    }

    @Override
    public String toString() {
        return String.format("%s Fretless: %s", super.toString(), fretless ? "Yes" : "No");
    }
}
