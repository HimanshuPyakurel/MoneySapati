package global.citytech.moneyexchange.transaction.service.lender.rejectborrower;

import global.citytech.moneyexchange.platform.constraints.StatusAndRoleEnum;
import global.citytech.moneyexchange.platform.exception.CustomException;
import global.citytech.moneyexchange.platform.response.CustomResponse;
import global.citytech.moneyexchange.transaction.repository.cashdetails.CashDetails;
import global.citytech.moneyexchange.transaction.repository.cashdetails.CashDetailsRepository;
import global.citytech.moneyexchange.transaction.repository.transaction.Transaction;
import global.citytech.moneyexchange.transaction.repository.transaction.TransactionRepository;
import global.citytech.moneyexchange.user.repository.UserRepository;
import global.citytech.moneyexchange.user.repository.Users;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class RejectBorrowerServiceImpl implements RejectBorrowerService{

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private CashDetailsRepository cashDetailsRepository;


    @Inject
    public RejectBorrowerServiceImpl(TransactionRepository transactionRepository,
                                      UserRepository userRepository, CashDetailsRepository cashDetailsRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.cashDetailsRepository=cashDetailsRepository;
    }

    @Override
    public CustomResponse rejectTransaction(RejectBorrowerRequest request) {
        Optional<Transaction> transactionEntities = transactionRepository.findById(request.getTransactionId());

        if(transactionEntities.isPresent()){
            Transaction transaction = transactionEntities.get();
            transaction.setId(request.getTransactionId());;
            transaction.setBorrowerId(request.getBorrowerId());
            transaction.setLenderId(request.getLenderId());
            transaction.setTransactionStatus(StatusAndRoleEnum.REJECTED.name());

            transactionRepository.update(transaction);
            this.CashDetailsUpdate(request,transaction);

            return new CustomResponse("Successfully approved",true);
        }
        else {throw new CustomException(400,"Transaction id not present");}
    }
    private void CashDetailsUpdate(RejectBorrowerRequest request, Transaction transaction) {
        CashDetails updateCashDetails = new CashDetails();
        Optional<Users> lender = userRepository.findById(transaction.getLenderId());

        this.validateCashDetails(transaction);

        // transaction details must be updated in Users table

        Random random=new Random();
        updateCashDetails.setId(random.nextInt(10000));

        double interestRateAmount = transaction.getInterestRate()/100 * transaction.getRequestAmount() * transaction.getBorrowingPeriod()/365;

        updateCashDetails.setBorrowerId(transaction.getBorrowerId());
        updateCashDetails.setLenderId(transaction.getLenderId());

        updateCashDetails.setBorrowerAmountToPay(Math.floor(transaction.getRequestAmount()+interestRateAmount));

        updateCashDetails.setStatus(StatusAndRoleEnum.REJECTED.name());
        updateCashDetails.setTotalLenderAvailableAmount(lender.get().getAvailableBalance()-transaction.getRequestAmount());

        cashDetailsRepository.save(updateCashDetails);
    }
    private void validateCashDetails(Transaction transaction){
        Optional<Users> lender = userRepository.findById(transaction.getLenderId());
        Optional<Users> borrower =userRepository.findById(transaction.getBorrowerId());
        Optional<Transaction> transactionLender = transactionRepository.findById(transaction.getLenderId());
        Optional<Transaction> transactionBorrower = transactionRepository.findById(transaction.getLenderId());
        List<CashDetails> cashDetailsBorrower = cashDetailsRepository.findByBorrowerId(transaction.getBorrowerId());

        if(lender.isEmpty()){
            throw new CustomException(400,"Invalid Lender id. Please check id number.");
        }
        if(borrower.isEmpty()){
            throw new CustomException(400,"Invalid Borrower id. Please check id number.");
        }
        if (!("LENDER".equalsIgnoreCase(lender.get().getUserRole().name()))){
            throw new CustomException(400,"Lender id must be belong to lender user only");
        }
        if (!("BORROWER".equalsIgnoreCase(borrower.get().getUserRole().name()))){
            throw new CustomException(400,"Borrower id must be belong to borrower user only");
        }
        if(transactionLender.isPresent() || transactionBorrower.isPresent()){
            throw new CustomException(400,"Lender and borrower is already present");
        }
        if(!cashDetailsBorrower.isEmpty() && "UNPAID".equalsIgnoreCase(cashDetailsBorrower.get(0).getStatus())){
            throw new CustomException(400,"Borrower status is unpaid.");
        }
        if(!cashDetailsBorrower.isEmpty() && "PARTIAL_PAID".equalsIgnoreCase(cashDetailsBorrower.get(0).getStatus())){
            throw new CustomException(400,"Borrower status is partially paid");
        }
        if(!cashDetailsBorrower.isEmpty() && "REJECTED".equalsIgnoreCase(cashDetailsBorrower.get(0).getStatus())){
            throw new CustomException(400,"Borrower is already rejected");
        }

    }


}
