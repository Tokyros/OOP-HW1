import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AfekaInstrument {

    private double price;
    private String companyName;

    public AfekaInstrument(Scanner scanner) {
        try {
            setPrice(scanner.nextDouble());
        } catch (InputMismatchException e) {
            throw new InstrumentCreationException("Problem with input file, price must be a double", e);
        }
        scanner.nextLine();
        setCompanyName(scanner.nextLine());
    }

    public AfekaInstrument(double price, String companyName) {
        setPrice(price);
        setCompanyName(companyName);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price must be a positive number!");
        this.price = price;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AfekaInstrument
                && price == ((AfekaInstrument) obj).price
                && companyName.equals(((AfekaInstrument) obj).companyName);
    }

    @Override
    public String toString() {
        return String.format("%-8s %-9s| Price:%8.2f,", companyName, getClass().getCanonicalName(), price);
    }


    public static void main(String[] args) throws FileNotFoundException {
        try {
            ArrayList<AfekaInstrument> instrumentsFromFile = openFileAndLoadInstruments();
            printInstrumentsSummary(instrumentsFromFile);
        } catch (Exception e) {
            printErrorAndTerminateProgram(e);
        }
    }

    protected static void printErrorAndTerminateProgram(Exception e) {
        System.err.println(e.getMessage());
        System.exit(1);
    }

    private static ArrayList<AfekaInstrument> openFileAndLoadInstruments() {
        Scanner inputScanner = getInputScanner();
        ArrayList<AfekaInstrument> instruments = getInstrumentsFromFile(inputScanner);
        inputScanner.close();
        return instruments;
    }

    private static ArrayList<Saxophone> loadSaxophonesFromFile(Scanner inputScanner) {
        ArrayList<Saxophone> saxophones = new ArrayList<>();

        int numOfInstruments = getNumOfInstrumentsToLoad(inputScanner);
        for (int i = 0; i < numOfInstruments; i++) {
            saxophones.add(new Saxophone(inputScanner));
        }

        return saxophones;
    }

    private static ArrayList<Bass> loadBassesFromFile(Scanner inputScanner) {
        ArrayList<Bass> basses = new ArrayList<>();

        int numOfInstruments = getNumOfInstrumentsToLoad(inputScanner);
        for (int i = 0; i < numOfInstruments; i++) {
            basses.add(new Bass(inputScanner));
        }

        return basses;
    }

    private static ArrayList<Flute> loadFlutesFromFile(Scanner inputScanner) {
        ArrayList<Flute> flutes = new ArrayList<>();

        int numOfInstruments = getNumOfInstrumentsToLoad(inputScanner);
        for (int i = 0; i < numOfInstruments; i++) {
            flutes.add(new Flute(inputScanner));
        }

        return flutes;
    }

    private static ArrayList<Guitar> loadGuitarsFromFile(Scanner inputScanner) {
        ArrayList<Guitar> guitars = new ArrayList<>();

        int numOfInstruments = getNumOfInstrumentsToLoad(inputScanner);
        for (int i = 0; i < numOfInstruments; i++) {
            guitars.add(new Guitar(inputScanner));
        }

        return guitars;
    }

    //Reads a single line indicating the number of instruments that follow in the file
    private static int getNumOfInstrumentsToLoad(Scanner inputScanner) {
        try {
            int numOfInstruments = inputScanner.nextInt();
            //The line should only contain the number of instruments, so we can consume it and move to the next line
            inputScanner.nextLine();
            return numOfInstruments;
        } catch (InputMismatchException e) {
            throw new InstrumentCreationException("Number of instruments to load must be an integer", e);
        }
    }


    private static ArrayList<AfekaInstrument> getInstrumentsFromFile(Scanner inputScanner) {
        ArrayList<AfekaInstrument> allInstruments = new ArrayList<>();
        addAllInstruments(loadGuitarsFromFile(inputScanner), allInstruments);
        addAllInstruments(loadBassesFromFile(inputScanner), allInstruments);
        addAllInstruments(loadFlutesFromFile(inputScanner), allInstruments);
        addAllInstruments(loadSaxophonesFromFile(inputScanner), allInstruments);
        System.out.println("Instruments loaded from file successfully!\n");
        return allInstruments;
    }

    private static void printInstrumentsSummary(ArrayList<AfekaInstrument> instruments) {
        int numOfDifferentElements = getNumOfDifferentElements(instruments);
        if (numOfDifferentElements > 0) {
            for (AfekaInstrument instrument : instruments) {
                System.out.println(instrument);
            }
            System.out.println("\nDifferent Instruments: " + numOfDifferentElements);
            System.out.println("\nMost Expensive Instrument:\n" + getMostExpensiveInstrument(instruments));
        } else {
            System.out.println("There are no instruments in the store currently");
        }
    }


    //Prompts the user for a full file path and loops until given path is a valid file. returns an open scanner
    private static Scanner getInputScanner() {
        Scanner filePathInputScanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please enter the full path of the input file");
            String filePath = filePathInputScanner.nextLine();
            try {
                Scanner inputScanner = new Scanner(new File(filePath));
                filePathInputScanner.close();
                return inputScanner;
            } catch (FileNotFoundException e) {
                System.out.println("There was a problem with the file provided, please try again...");
            }
        }
    }

    //Extends the contents of instrumentsArrayTo with the contents of instrumentsArrayFrom
    private static void addAllInstruments(ArrayList<? extends AfekaInstrument> instrumentsArrayFrom, ArrayList<AfekaInstrument> instrumentsArrayTo) {
        for (AfekaInstrument afekaInstrument : instrumentsArrayFrom) {
            instrumentsArrayTo.add(afekaInstrument);
        }
    }

    //Finds most expensive instrument in the list, returns null if the list is empty
    private static AfekaInstrument getMostExpensiveInstrument(ArrayList<AfekaInstrument> afekaInstruments) {
        if (afekaInstruments.size() == 0) return null;

        AfekaInstrument mostExpensive = afekaInstruments.get(0);
        for (int i = 1; i < afekaInstruments.size(); i++) {
            AfekaInstrument currentInstrumentToCheck = afekaInstruments.get(i);
            if (currentInstrumentToCheck.getPrice() > mostExpensive.getPrice())
                mostExpensive = currentInstrumentToCheck;
        }
        return mostExpensive;
    }

    private static int getNumOfDifferentElements(ArrayList<AfekaInstrument> instruments) {
        ArrayList<AfekaInstrument> uniqueInstruments = new ArrayList<>();
        //For each instrument in the list, check if it already exists in the temporary unique instruments list
        for (AfekaInstrument instrument : instruments) {
            boolean found = false;
            for (AfekaInstrument uniqueInstrument : uniqueInstruments) {
                if (uniqueInstrument.equals(instrument)) {
                    //The instrument has been found in the unique list, not need to keep checking
                    found = true;
                    break;
                }
            }
            //If the element has not bee found in the unique list, we add it to the unique list
            if (!found) uniqueInstruments.add(instrument);
        }
        //After iteration, the size of the unique list indicates the number of different instruments in the original list
        return uniqueInstruments.size();
    }

    //Helper function for pretty printing arrays (assuming we cannot use String.join/Arrays.toString)
    protected static String getCommaDelimitedStringFromArray(Object[] arr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            stringBuffer.append(arr[i]);
            if (i < arr.length - 1) stringBuffer.append(", ");
        }
        return stringBuffer.toString();
    }
}
