package global.citytech.moneyexchange.borrower.service.requestamount.impl;

import global.citytech.moneyexchange.borrower.dto.BorrowerDto;
import global.citytech.moneyexchange.borrower.model.Borrower;
import global.citytech.moneyexchange.borrower.model.RequestedMoneyEnum;
import global.citytech.moneyexchange.borrower.repository.BorrowerRepository;
import global.citytech.moneyexchange.borrower.service.requestamount.RequestAmountService;
import jakarta.inject.Inject;
import java.util.Optional;

public class RequestAmountServiceImp implements RequestAmountService {

    private final BorrowerRepository borrowerRepository;

    @Inject
    public RequestAmountServiceImp(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    @Override
    public Borrower createTransaction(BorrowerDto borrowerDto) {

        Optional<Borrower> borrowerFromDatabase = borrowerRepository.findById(borrowerDto.getUsers().getId());
        Borrower borrower=new Borrower();

        if (borrowerFromDatabase.isPresent()){
            borrower.setRequestAmount(borrowerDto.getRequestAmount());
            borrower.setRequestedMoneyStatus(RequestedMoneyEnum.PENDING);

            return borrowerRepository.save(borrower);
        }
        else throw new IllegalArgumentException("Invalid");
    }
}
