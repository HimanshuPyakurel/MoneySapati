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
        Optional<Transaction> transactionDetails = transactionRepository.findByBorrowerId(cashDetails.get().getBorrowerId());

        if(cashDetails.isPresent()){
            if(transactionDetails.isPresent()){

                double lenderAmount = cashDetails.get().getLenderAmount();
                double borrowerAmount = cashDetails.get().getBorrowerAmount();
                CashDetails updateCashDetails = new CashDetails();
                Transaction updateTransaction = transactionDetails.get();

                this.validateRequest(request);

                updateCashDetails.setId(request.getCashId());
                updateCashDetails.setBorrowerId(request.getBorrowerId());
                updateCashDetails.setLenderId(request.getLenderId());
                updateCashDetails.setBorrowerAmount(borrowerAmount - request.getReturningAmount());
                updateCashDetails.setLenderAmount(lenderAmount + request.getReturningAmount());

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
                return new CustomResponse("Successfully completed payment",true);

            }
            else throw new CustomException("Borrower id or lender id not matched");
        }
        else throw new CustomException("Cash detail id not found");
    }


    public void validateRequest(ReturnTransactionAmountRequest request) {
        Optional<CashDetails> cashDetails = cashDetailsRepository.findById(request.getCashId());
        Optional<Transaction> lenderTransaction = transactionRepository.findByLenderId(request.getLenderId());
        Optional<Transaction> borrowerTransaction = transactionRepository.findByBorrowerId(request.getBorrowerId());
        Optional<CashDetails> lenderCashDetails = cashDetailsRepository.findByLenderId(request.getLenderId());
        Optional<CashDetails> borrowerCashDetails = cashDetailsRepository.findByBorrowerId(request.getBorrowerId());

        if (cashDetails.isEmpty()) {
            throw new CustomException("Cash Details Not Found");
        }
        if (borrowerTransaction.isEmpty() && borrowerCashDetails.isEmpty()) {
            throw new CustomException("Borrower not found. Enter borrower id properly");
        }
        if (lenderTransaction.isEmpty() && lenderCashDetails.isEmpty()) {
            throw new CustomException("Lender Not Found. Enter lender id properly");
        }
        if (cashDetails.get().getBorrowerAmount()<= 0){
            throw new CustomException("No amount left to be paid");
        }

    }

}
