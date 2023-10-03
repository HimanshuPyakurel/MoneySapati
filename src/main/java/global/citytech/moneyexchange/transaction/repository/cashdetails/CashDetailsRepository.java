package global.citytech.moneyexchange.transaction.repository.cashdetails;

import global.citytech.moneyexchange.transaction.repository.cashdetails.CashDetails;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CashDetailsRepository extends CrudRepository<CashDetails,Integer> {
    List<CashDetails> findByBorrowerId(int borrowerId);
    List<CashDetails> findByLenderId(int lenderId);
    List<CashDetails> findByStatus(String status);
}
