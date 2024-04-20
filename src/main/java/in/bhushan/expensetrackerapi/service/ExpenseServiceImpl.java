package in.bhushan.expensetrackerapi.service;

import in.bhushan.expensetrackerapi.entity.Expense;
import in.bhushan.expensetrackerapi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    @Override
    public Page<Expense> getAllExpenses(Pageable page) {
        return expenseRepository.findAll(page);
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if(expense.isPresent()){
            return expense.get();
        }
        throw new RuntimeException("Expense not found for the id " +id);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if(expense.isPresent()){
            expenseRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Expense not found for the id " + id);
        }
    }

    @Override
    public void deleteAllExpenses() {
        expenseRepository.deleteAll();
    }

    @Override
    public Expense createExpense(Expense expense) {
        if(expense != null){
            return expenseRepository.saveAndFlush(expense);
        }
        else{
            throw new RuntimeException("No expense added");
        }
    }

    @Override
    public Expense updateExpenseById(Long id, Expense expense) {
        Expense existingExpense = expenseRepository.getById(id);
        if(expense.getAmount() != null)
            existingExpense.setAmount(expense.getAmount());
        if(expense.getDate() != null)
            existingExpense.setDate(expense.getDate());
        if(expense.getName() != null && !expense.getName().isBlank())
            existingExpense.setName(expense.getName());
        if(expense.getCategory() != null && !expense.getName().isBlank())
            existingExpense.setCategory(expense.getCategory());
        if(expense.getDescription() != null)
            existingExpense.setDescription(expense.getDescription());
        return expenseRepository.saveAndFlush(existingExpense);
    }
}
