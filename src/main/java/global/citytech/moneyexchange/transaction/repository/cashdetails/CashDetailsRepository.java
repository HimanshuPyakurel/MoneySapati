package global.citytech.moneyexchange.transaction.repository.cashdetails;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import java.util.Optional;

@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CashDetailsRepository extends CrudRepository<CashDetails,Integer> {
    Optional<CashDetails> findByBorrowerId(int borrowerId);
    Optional<CashDetails> findByLenderId(int lenderId);
}
