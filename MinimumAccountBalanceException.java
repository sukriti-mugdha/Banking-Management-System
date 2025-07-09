package exceptions;

public class MinimumAccountBalanceException extends AccountTransactionException {
    public MinimumAccountBalanceException(String message) {
        super(message);
    }
}
