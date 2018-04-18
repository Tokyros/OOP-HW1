//Unchecked exception thrown when an invalid parameter was used to create an AfekaInstrument instance
public class InstrumentCreationException extends RuntimeException {
    public InstrumentCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InstrumentCreationException(String message) {
        super(message);
    }
}
