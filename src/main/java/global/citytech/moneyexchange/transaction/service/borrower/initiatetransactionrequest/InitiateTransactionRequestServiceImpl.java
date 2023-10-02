package global.citytech.moneyexchange.transaction.service.borrower.initiatetransactionrequest;

import global.citytech.moneyexchange.constraints.StatusAndRoleEnum;
import global.citytech.moneyexchange.exception.CustomException;
import global.citytech.moneyexchange.transaction.repository.cashdetails.CashDetailsRepository;
import global.citytech.moneyexchange.transaction.repository.transaction.Transaction;
import global.citytech.moneyexchange.transaction.repository.transaction.TransactionRepository;
import global.citytech.moneyexchange.user.repository.UserRepository;
import global.citytech.moneyexchange.user.repository.Users;
import jakarta.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class InitiateTransactionRequestServiceImpl implements InitiateTransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CashDetailsRepository cashDetailsRepository;

    @Inject
    public InitiateTransactionRequestServiceImpl(TransactionRepository transactionRepository,
                                                 UserRepository userRepository,CashDetailsRepository cashDetailsRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.cashDetailsRepository = cashDetailsRepository;
    }

    @Override
    public Transaction initiateTransaction(InitiateTransactionRequest request) {
        Transaction transaction = new Transaction();

        this.validateRequest(request);
        Random random=new Random();
        transaction.setId(random.nextInt(10000));
        transaction.setBorrowerId(request.getBorrowerId());
        transaction.setLenderId(request.getLenderId());
        transaction.setRequestAmount(request.getRequestAmount());
        transaction.setTransactionStatus(StatusAndRoleEnum.PENDING.name());
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setInterestRate(request.getInterestRate());
        transaction.setBorrowingPeriod(request.getBorrowingPeriod());
        return transactionRepository.save(transaction);
    }

    public void validateRequest(InitiateTransactionRequest validateRequest) {
        Optional<Users> borrower = userRepository.findById(validateRequest.getBorrowerId());
        Optional<Users> lender = userRepository.findById(validateRequest.getLenderId());
        List<Transaction> previousBorrower = transactionRepository.findByBorrowerId(validateRequest.getBorrowerId());

        if (borrower.isEmpty() ) {
            throw new CustomException("Borrower not found. Enter borrower id properly");
        }
        if (lender.isEmpty()) {
            throw new CustomException("Lender Not Found. Enter lender id properly");
        }
        if(lender.get().getCheckStatus().equals("Pending")){
            throw  new CustomException("Lender has pending status. It must be verified first");
        }
        if(lender.get().getCheckStatus().equals("Rejected")){
            throw  new CustomException("Lender has Rejected status. It must be verified first");
        }
        if(borrower.get().getCheckStatus().equals("Pending")) {
            throw new CustomException("Borrower has pending status. It must be verified first");
        }
        if(borrower.get().getCheckStatus().equals("Rejected")) {
            throw new CustomException("Borrower has Rejected status. It must be verified first");
        }
        if (!("LENDER".equalsIgnoreCase(lender.get().getUserRole().name()))) {
            throw new CustomException("Lender id must be of lender role user");
        }
        if (!("BORROWER".equalsIgnoreCase(borrower.get().getUserRole().name()))) {
            throw new CustomException("Borrower id must be of borrower role User");
        }
        if(!previousBorrower.isEmpty() && "PENDING".equalsIgnoreCase(previousBorrower.get(0).getTransactionStatus())){
            throw new CustomException("Borrower have already initiated amounts with Lender");
        }
        if(!previousBorrower.isEmpty() && "UNPAID".equalsIgnoreCase(previousBorrower.get(0).getTransactionStatus())){
            throw new CustomException("Borrower status is still unpaid");
        }
        if(!previousBorrower.isEmpty() && "PARTIAL_PAID".equalsIgnoreCase(previousBorrower.get(0).getTransactionStatus())){
            throw new CustomException("Borrower status is still partial paid");
        }
        if(!previousBorrower.isEmpty() && "REJECTED".equalsIgnoreCase(previousBorrower.get(0).getTransactionStatus())){
            throw new CustomException("Borrower is rejected");
        }


    }

}
