import java.util.Scanner;

public class Guitar extends StringInstrument {
    //Constants
    private static final int MINIMUM_ELECTRIC_STRING_NUM = 6;
    private static final int MAXIMUM_ELECTRIC_STRING_NUM = 8;
    private static final int CLASSIC_ACOUSTIC_STRING_NUM = 6;
    private static final String ELECTRIC = "Electric";
    private static final String ACOUSTIC = "Acoustic";
    private static final String CLASSIC = "Classic";

    //Properties
    private String guitarType;
    private String[] possibleGuitarTypes = new String[]{ELECTRIC, ACOUSTIC, CLASSIC};

    //Constructors
    public Guitar(double price, String companyName, int numOfStrings, String guitarType) {
        super(price, companyName, numOfStrings);
        setGuitarType(guitarType);
    }

    public Guitar(Scanner scanner) {
        super(scanner);
        setGuitarType(scanner.nextLine());
    }

    //Methods
    public String getGuitarType() {
        return guitarType;
    }

    public void setGuitarType(String guitarType) {
        if (!isTypeOfGuitarValid(guitarType)) throw new IllegalArgumentException(getGuitarTypeErrorMessage());
        this.guitarType = guitarType;
        //If the type is changed, we make sure the number of strings still matches
        validateNumberOfStrings(getNumOfStrings());
    }

    private String getGuitarTypeErrorMessage() {
        return String.format("Guitar type must be one of - %s", getCommaDelimitedStringFromArray(possibleGuitarTypes));
    }

    //Throws an exception if the guitarType and numOfStrings match. if the guitarType is not set, numOfStrings should just be positive;
    @Override
    protected void validateNumberOfStrings(int numOfStrings){
        //If a guitarType has yet to be specified, we do not add extra validation logic for Guitar class
        if (guitarType == null) {
            super.validateNumberOfStrings(numOfStrings);
            return;
        }

        switch (guitarType) {
            case ELECTRIC:
                if (!(MINIMUM_ELECTRIC_STRING_NUM <= numOfStrings && numOfStrings <= MAXIMUM_ELECTRIC_STRING_NUM)) {
                    throw new IllegalArgumentException(String.format("%s number of strings is a number between %d and %d", guitarType, MINIMUM_ELECTRIC_STRING_NUM, MAXIMUM_ELECTRIC_STRING_NUM));
                }
                break;
            case CLASSIC:
            case ACOUSTIC:
                if (numOfStrings != CLASSIC_ACOUSTIC_STRING_NUM)
                    throw new IllegalArgumentException(String.format("%s Guitars have %d strings not %d", guitarType, CLASSIC_ACOUSTIC_STRING_NUM, numOfStrings));
                break;
        }

    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Guitar && guitarType.equals(((Guitar) obj).getGuitarType()) && super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString() + " Type: " + guitarType;
    }

    private boolean isTypeOfGuitarValid(String guitarType) {
        for (String possibleGuitarType : this.possibleGuitarTypes) {
            if (possibleGuitarType.equals(guitarType)) return true;
        }
        return false;
    }
}
