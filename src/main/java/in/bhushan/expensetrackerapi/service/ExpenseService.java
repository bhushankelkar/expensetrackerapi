package in.bhushan.expensetrackerapi.service;

import in.bhushan.expensetrackerapi.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ExpenseService {

    List<Expense> getAllExpenses();
    Page<Expense> getAllExpenses(Pageable page);
    Expense getExpenseById(Long id);

    void deleteById(Long id);

    void deleteAllExpenses();

    Expense createExpense(Expense expense);

    Expense updateExpenseById(Long id, Expense expense);
}
