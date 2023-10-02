package global.citytech.moneyexchange.transaction.service.borrower.returntransactionamount;

import global.citytech.moneyexchange.constraints.StatusAndRoleEnum;
import global.citytech.moneyexchange.exception.CustomException;
import global.citytech.moneyexchange.response.CustomResponse;
import global.citytech.moneyexchange.transaction.repository.cashdetails.CashDetails;
import global.citytech.moneyexchange.transaction.repository.cashdetails.CashDetailsRepository;
import global.citytech.moneyexchange.transaction.repository.transaction.Transaction;
import global.citytech.moneyexchange.transaction.repository.transaction.TransactionRepository;
import global.citytech.moneyexchange.user.repository.UserRepository;
import global.citytech.moneyexchange.user.repository.Users;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

public class ReturnTransactionAmountServiceImpl implements ReturnTransactionAmountService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private CashDetailsRepository cashDetailsRepository;

    @Inject
    public ReturnTransactionAmountServiceImpl(UserRepository userRepository, CashDetailsRepository cashDetailsRepository,
                                              TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.cashDetailsRepository = cashDetailsRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public CustomResponse returnTransaction(ReturnTransactionAmountRequest request) {
        Optional<CashDetails> cashDetails = cashDetailsRepository.findById(request.getCashId());
        List<Transaction> transactionDetails = transactionRepository.findByBorrowerId(cashDetails.get().getBorrowerId());
        Optional<Users> lender = userRepository.findById(cashDetails.get().getLenderId());
        Optional<Users> borrower =userRepository.findById(cashDetails.get().getBorrowerId());

        if (cashDetails.isEmpty()) {
            throw new CustomException("Cash detail id not found");
        }
        if (transactionDetails.isEmpty()) {
            throw new CustomException("Borrower id or lender id not matched");
        }
        if(lender.isEmpty()){
            throw new CustomException("User not found");
        }
        if(borrower.isEmpty()){
            throw new CustomException("User not found");
        }

        double lenderAmount = cashDetails.get().getTotalLenderAvailableAmount();
        double borrowerAmount = cashDetails.get().getBorrowerAmountToPay();
        CashDetails updateCashDetails = new CashDetails();
        Transaction updateTransaction = transactionDetails.get(0);

        this.validateRequest(request);

        updateCashDetails.setId(request.getCashId());
        updateCashDetails.setBorrowerId(request.getBorrowerId());
        updateCashDetails.setLenderId(request.getLenderId());
        updateCashDetails.setBorrowerAmountToPay(borrowerAmount - request.getReturningAmount());
        updateCashDetails.setTotalLenderAvailableAmount(lenderAmount + request.getReturningAmount());

        if (borrower.isPresent()) {
            Users userUpdate = borrower.get();
            userUpdate.setAvailableBalance(borrowerAmount-request.getReturningAmount());
            userRepository.update(userUpdate);
        }
        if(lender.isPresent()){
            Users userUpdate = lender.get();
            userUpdate.setAvailableBalance(userUpdate.getAvailableBalance() + request.getReturningAmount());
            userRepository.update(userUpdate);
        }

        if (borrowerAmount == request.getReturningAmount()) {
            updateCashDetails.setStatus(StatusAndRoleEnum.COMPLETE.name());
            updateTransaction.setTransactionStatus(StatusAndRoleEnum.COMPLETE.name());
            transactionRepository.update(updateTransaction);
        }

        if (borrowerAmount > request.getReturningAmount()) {
            updateCashDetails.setStatus(StatusAndRoleEnum.PARTIAL_PAID.name());
            updateTransaction.setTransactionStatus(StatusAndRoleEnum.PARTIAL_PAID.name());
            transactionRepository.update(updateTransaction);
        }

        if (borrowerAmount < request.getReturningAmount()) {
            throw new CustomException("Returning Amount is more than actual initiated money");
        }

        cashDetailsRepository.update(updateCashDetails);
        return new CustomResponse("Successfully completed payment", true);

    }

    public void validateRequest(ReturnTransactionAmountRequest request) {
        Optional<CashDetails> cashDetails = cashDetailsRepository.findById(request.getCashId());
        List<Transaction> lenderTransaction = transactionRepository.findByLenderId(request.getLenderId());
        List<Transaction> borrowerTransaction = transactionRepository.findByBorrowerId(request.getBorrowerId());
        List<CashDetails> lenderCashDetails = cashDetailsRepository.findByLenderId(request.getLenderId());
        List<CashDetails> borrowerCashDetails = cashDetailsRepository.findByBorrowerId(request.getBorrowerId());

        if (cashDetails.isEmpty()) {
            throw new CustomException("Cash Details Not Found");
        }
        if (borrowerTransaction.isEmpty() && borrowerCashDetails.isEmpty()) {
            throw new CustomException("Borrower not found. Enter borrower id properly");
        }
        if (lenderTransaction.isEmpty() && lenderCashDetails.isEmpty()) {
            throw new CustomException("Lender Not Found. Enter lender id properly");
        }
        if (cashDetails.get().getBorrowerAmountToPay() <= 0) {
            throw new CustomException("No amount left to be paid");
        }

    }

}
