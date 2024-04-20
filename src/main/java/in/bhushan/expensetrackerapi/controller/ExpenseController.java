package in.bhushan.expensetrackerapi.controller;

import in.bhushan.expensetrackerapi.entity.Expense;
import in.bhushan.expensetrackerapi.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/expenses")
    public Expense createExpense(@RequestBody Expense expense){
        return expenseService.createExpense(expense);
    }

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(){
        return expenseService.getAllExpenses();
    }

//    @GetMapping("/expenses")
//    public List<Expense> getAllExpenses(Pageable page){
//        return expenseService.getAllExpenses(page).toList();
//    }

    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable("id") Long id){
        return expenseService.getExpenseById(id);
    }

    @PutMapping("/expenses/{id}")
    public Expense updateExpenseById(@PathVariable("id") Long id,@RequestBody Expense expense){
        return expenseService.updateExpenseById(id,expense);
    }
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses/{id}")
    public void deleteById(@PathVariable("id") Long id){
        expenseService.deleteById(id);
    }

    @DeleteMapping("/expenses")
    public void deleteAllExpenses(){
        expenseService.deleteAllExpenses();
    }
}
