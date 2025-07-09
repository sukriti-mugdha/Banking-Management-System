package exceptions;

public class MaximumAmountTransactionException extends AccountTransactionException {
    public MaximumAmountTransactionException(String message) {
        super(message);
    }
}
