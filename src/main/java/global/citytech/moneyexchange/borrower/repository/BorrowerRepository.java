package global.citytech.moneyexchange.borrower.repository;

import global.citytech.moneyexchange.borrower.model.Borrower;
import io.micronaut.data.repository.CrudRepository;

public interface BorrowerRepository extends CrudRepository<Borrower,Integer> {
}
