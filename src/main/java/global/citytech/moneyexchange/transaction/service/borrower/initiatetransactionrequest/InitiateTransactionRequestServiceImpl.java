package global.citytech.moneyexchange.transaction.service.borrower.initiatetransactionrequest;

import global.citytech.moneyexchange.platform.constraints.StatusAndRoleEnum;
import global.citytech.moneyexchange.platform.exception.CustomException;
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

    private void validateRequest(InitiateTransactionRequest validateRequest) {
        Optional<Users> borrower = userRepository.findById(validateRequest.getBorrowerId());
        Optional<Users> lender = userRepository.findById(validateRequest.getLenderId());
        List<Transaction> previousBorrower = transactionRepository.findByBorrowerId(validateRequest.getBorrowerId());

        if("BLACKLIST".equalsIgnoreCase(borrower.get().getCheckBlacklist())){
            throw new CustomException(400,"Borrower is blacklisted");
        }
        if("BLACKLIST".equalsIgnoreCase(lender.get().getCheckBlacklist())){
            throw new CustomException(400,"Lender is blacklisted");
        }

        if (borrower.isEmpty() ) {
            throw new CustomException(400,"Borrower not found. Enter borrower id properly");
        }
        if (lender.isEmpty()) {
            throw new CustomException(400,"Lender Not Found. Enter lender id properly");
        }
        if(lender.get().getCheckStatus().equals(StatusAndRoleEnum.PENDING.name())){
            throw  new CustomException(400,"Lender has pending status. It must be verified first");
        }
        if(lender.get().getCheckStatus().equals(StatusAndRoleEnum.REJECTED.name())){
            throw  new CustomException(400,"Lender has Rejected status. It must be verified first");
        }
        if(borrower.get().getCheckStatus().equals(StatusAndRoleEnum.PENDING.name())) {
            throw new CustomException(400,"Borrower has pending status. It must be verified first");
        }
        if(borrower.get().getCheckStatus().equals(StatusAndRoleEnum.REJECTED.name())) {
            throw new CustomException(400,"Borrower has Rejected status. It must be verified first");
        }
        if (!("LENDER".equalsIgnoreCase(lender.get().getUserRole().name()))) {
            throw new CustomException(400,"Lender id must be of lender role user");
        }
        if (!("BORROWER".equalsIgnoreCase(borrower.get().getUserRole().name()))) {
            throw new CustomException(400,"Borrower id must be of borrower role User");
        }
        if(!previousBorrower.isEmpty() && "PENDING".equalsIgnoreCase(previousBorrower.get(0).getTransactionStatus())){
            throw new CustomException(400,"Borrower have already initiated amounts with Lender");
        }
        if(!previousBorrower.isEmpty() && "UNPAID".equalsIgnoreCase(previousBorrower.get(0).getTransactionStatus())){
            throw new CustomException(400,"Borrower status is still unpaid");
        }
        if(!previousBorrower.isEmpty() && "PARTIAL_PAID".equalsIgnoreCase(previousBorrower.get(0).getTransactionStatus())){
            throw new CustomException(400,"Borrower status is still partial paid");
        }
        if(!previousBorrower.isEmpty() && "REJECTED".equalsIgnoreCase(previousBorrower.get(0).getTransactionStatus())){
            throw new CustomException(400,"Borrower is rejected");
        }
        if(validateRequest.getRequestAmount() <= 200){
            throw new CustomException(400,"Requested Amount must be greater than 200");
        }


    }

}
