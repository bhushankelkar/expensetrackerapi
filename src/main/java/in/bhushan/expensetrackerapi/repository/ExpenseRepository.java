package in.bhushan.expensetrackerapi.repository;

import in.bhushan.expensetrackerapi.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.sql.Date;
@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    //Behind the scenes the JPA will run select * from tbl_expenses where category = ?
    Page<Expense> findByCategory(String category, Pageable page);

    //Behind the scenes JPA will run select * from tbl_expenses where name like '%keyword%'
    Page<Expense> findByNameContaining(String keyword,Pageable page);

    //select * from tbl_expenses where date between 'startDate' AND 'endDate'
    Page<Expense> findByDateBetween(Date startDate,Date endDate,Pageable page);

}
