package exceptions;

public class MaximumTransactionLimitExceed extends AccountTransactionException {
    public MaximumTransactionLimitExceed(String message) {
        super(message);
    }
}