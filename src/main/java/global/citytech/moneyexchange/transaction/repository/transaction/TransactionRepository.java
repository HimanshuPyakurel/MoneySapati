package global.citytech.moneyexchange.transaction.repository.transaction;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
public interface TransactionRepository extends CrudRepository<Transaction,Integer> {
    Optional<Transaction> findByBorrowerId(int borrowerId);
    Optional<Transaction> findByLenderId(int lenderId);
}
