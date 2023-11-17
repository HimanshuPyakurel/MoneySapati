package global.citytech.moneyexchange.platform.utils;

import global.citytech.moneyexchange.platform.constraints.StatusAndRoleEnum;
import global.citytech.moneyexchange.platform.exception.CustomException;
import global.citytech.moneyexchange.transaction.repository.cashdetails.CashDetails;
import global.citytech.moneyexchange.transaction.repository.cashdetails.CashDetailsRepository;
import global.citytech.moneyexchange.transaction.repository.transaction.Transaction;
import global.citytech.moneyexchange.transaction.repository.transaction.TransactionRepository;
import global.citytech.moneyexchange.user.repository.UserRepository;
import global.citytech.moneyexchange.user.repository.Users;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Singleton
public class SendEmailNotification {
    public final CashDetailsRepository cashDetailsRepository;
    public final TransactionRepository transactionRepository;
    public final UserRepository userRepository;

    @Inject
    public SendEmailNotification(CashDetailsRepository cashDetailsRepository, TransactionRepository transactionRepository, UserRepository userRepository) {
        this.cashDetailsRepository = cashDetailsRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Scheduled(initialDelay = "10s", fixedDelay = "4h")
    public void checkTimeFrameExceed(){
        List<Transaction> requestsList= transactionRepository.findByTransactionStatus(StatusAndRoleEnum.UNPAID.name());
        LocalDateTime currentTime= LocalDateTime.now();

        for(Transaction request: requestsList){
            List<CashDetails> cashDetailsList=  cashDetailsRepository.findByBorrowerId(request.getBorrowerId());
            LocalDateTime timeLimit = cashDetailsList.get(0).getApprovedAt().plusDays(request.getBorrowingPeriod());

            if (currentTime.isAfter(timeLimit) && !request.getTransactionStatus().equals(StatusAndRoleEnum.COMPLETE.name())) {
                Optional<Users> borrowerDetails = userRepository.findById(cashDetailsList.get(0).getBorrowerId());
                Users borrower= borrowerDetails.get();
                borrower.setCheckBlacklist(StatusAndRoleEnum.BLACKLIST.name());
                userRepository.update(borrower);
            }
        }
    }

    @Scheduled(initialDelay = "10s", fixedDelay = "4h")
    public  void emailNotification() {
        List<Users> adminDetail = userRepository.findByUserRole(StatusAndRoleEnum.ADMIN);
        List<Transaction> requestsList = transactionRepository.findByTransactionStatus(StatusAndRoleEnum.UNPAID.name());

        if (adminDetail.isEmpty()) {
            throw new CustomException(400,"Admin not found");
        }
        String adminEmail = adminDetail.get(0).getEmail();
        String messageForFiveDaysRemaining = "Hey, You only have five days to return the amount. Return the amount fast...";
        String messageForOneDayRemaining = "Hey, You only have one days to return the amount. Return the amount fast...";
        String messageForTimeComplete = "Your time limit is already completed so you are blacklisted. Enjoy...";
        String subject = "Notification To Pay Your Loan";

        for (Transaction request : requestsList) {
            Optional<CashDetails> transactionDetail = cashDetailsRepository.findByBorrowerIdIn(request.getBorrowerId());
            LocalDateTime timeLimit = transactionDetail.get().getApprovedAt().plusDays(request.getBorrowingPeriod());
            LocalDateTime timeToNotifyForFiveDays = timeLimit.minusDays(5);
            LocalDateTime timeToNotifyForOneDay = timeLimit.minusDays(1);
            LocalDateTime currentTime = LocalDateTime.now();
            int borrowerId = transactionDetail.get().getBorrowerId();
            Optional<Users> borrowerDetails = userRepository.findById(borrowerId);
            String emailSentToBorrower = borrowerDetails.get().getEmail();

            if (currentTime.isAfter(timeLimit) && !request.getTransactionStatus().equals("PAID")) {
                EmailUtil.sendEmail(adminEmail, emailSentToBorrower, messageForTimeComplete, subject);
            }
            else if (currentTime.isAfter(timeToNotifyForOneDay) && !request.getTransactionStatus().equals(StatusAndRoleEnum.COMPLETE.name())) {
                EmailUtil.sendEmail(adminEmail, emailSentToBorrower, messageForOneDayRemaining, subject);
            }
            else if (currentTime.isAfter(timeToNotifyForFiveDays) && !request.getTransactionStatus().equals(StatusAndRoleEnum.COMPLETE.name())) {
                EmailUtil.sendEmail(adminEmail, emailSentToBorrower, messageForFiveDaysRemaining, subject);
            }

        }
    }
}
