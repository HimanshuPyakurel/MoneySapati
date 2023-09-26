package global.citytech.moneyexchange.transaction.service.lender.approveborrower;

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
import java.util.Random;

public class ApproveBorrowerServiceImpl implements ApproveBorrowerService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private CashDetailsRepository cashDetailsRepository;


    @Inject
    public ApproveBorrowerServiceImpl(TransactionRepository transactionRepository,
                                      UserRepository userRepository, CashDetailsRepository cashDetailsRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.cashDetailsRepository=cashDetailsRepository;
    }
    @Override
    public CustomResponse approvedTransaction(ApprovedTransactionRequest request) {

        Optional<Transaction> transactionEntities = transactionRepository.findById(request.getTransactionId());

//        Transaction transaction=new Transaction();
        if(transactionEntities.isPresent()){
            if(("REJECTED".equalsIgnoreCase(transactionEntities.get().getTransactionStatus()))) {
                throw new CustomException("Rejected borrower");
            }
            else if((transactionEntities.isEmpty() || "COMPLETE".equalsIgnoreCase(transactionEntities.get().getTransactionStatus()))){
                throw new CustomException("Transaction is already completed");
            }
            else {
                Transaction transaction = transactionEntities.get();
                transaction.setId(request.getTransactionId());;
                transaction.setBorrowerId(request.getBorrowerId());
                transaction.setLenderId(request.getLenderId());
                transaction.setTransactionStatus(StatusAndRoleEnum.UNPAID.name());

                transactionRepository.update(transaction);
                this.CashDetailsUpdate(request,transaction);
                return new CustomResponse("Successfully approved",true);
            }
        }
        else {throw new CustomException("Transaction id not present");}
    }

    private void CashDetailsUpdate(ApprovedTransactionRequest request,Transaction transaction) {
        CashDetails updateCashDetails = new CashDetails();
        Optional<Users> lender = userRepository.findById(transaction.getLenderId());

        this.validateCashDetails(transaction);

        // transaction must also be fluctuate in Users table

        Random random=new Random();
        updateCashDetails.setId(random.nextInt(10000));

        double interestRateAmount = transaction.getInterestRate()/100 * transaction.getRequestAmount() * transaction.getBorrowingPeriod()/365;

        updateCashDetails.setBorrowerId(transaction.getBorrowerId());
        updateCashDetails.setLenderId(transaction.getLenderId());

        updateCashDetails.setBorrowerAmount(Math.floor(transaction.getRequestAmount()+interestRateAmount));

        updateCashDetails.setStatus(StatusAndRoleEnum.UNPAID.name());
        updateCashDetails.setLenderAmount(lender.get().getAvailableBalance()-transaction.getRequestAmount());

        cashDetailsRepository.save(updateCashDetails);
    }

    public void validateCashDetails(Transaction transaction){
        Optional<Users> lender = userRepository.findById(transaction.getLenderId());
        Optional<Users> borrower =userRepository.findById(transaction.getBorrowerId());
        Optional<Transaction> transactionLender = transactionRepository.findById(transaction.getLenderId());
        Optional<Transaction> transactionBorrower = transactionRepository.findById(transaction.getLenderId());
        Optional<CashDetails> cashDetailsBorrower = cashDetailsRepository.findByBorrowerId(transaction.getBorrowerId());

        if(lender.isEmpty()){
            throw new CustomException("Invalid Lender id. Please check id number.");
        }
        if(borrower.isEmpty()){
            throw new CustomException("Invalid Borrower id. Please check id number.");
        }
        if (!("Lender".equalsIgnoreCase(lender.get().getUserRole().name()))){
            throw new CustomException("Lender id must be belong to lender user only");
        }
        if (!("Borrower".equalsIgnoreCase(borrower.get().getUserRole().name()))){
            throw new CustomException("Borrower id must be belong to borrower user only");
        }
        if(transactionLender.isPresent() || transactionBorrower.isPresent()){
            throw new CustomException("Lender and borrower is already present");
        }
        if(cashDetailsBorrower.isPresent() && "UNPAID".equalsIgnoreCase(cashDetailsBorrower.get().getStatus())){
            throw new CustomException("Borrower is already present and the status is unpaid. Need to pay all due first");
        }
        if(cashDetailsBorrower.isPresent() && "PARTIAL_PAID".equalsIgnoreCase(cashDetailsBorrower.get().getStatus())){
            throw new CustomException("Borrower is already present and the status is partially paid. Need to pay all due first");
        }

    }

}
