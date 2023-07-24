package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.repository.IExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    IExpenseRepo expenseRepo ;

    public String addExpense(Expense expense) {
        expenseRepo.save(expense);
        return "Added Successfully" ;
    }

    public String updateExpense(Expense expense) {
        Optional<Expense> existingExpense = expenseRepo.findById(expense.getId());
        if (existingExpense.isPresent()){
            Expense expense1 = existingExpense.get();
            expense1.setTitle(expense.getTitle());
            expense1.setDescription(expense.getDescription());
            expense1.setPrice(expense.getPrice());
            expenseRepo.save(expense1);
            return "updated Successfully" ;

        }
        else {
            return "Id not found" ;
        }
    }

    public List<Expense> getExpense(String email , Long userId) {
        List<Expense> expenses = expenseRepo.findByUsersId(userId) ;
        return expenses ;
    }

    public String deleteExpense(Long expenseId) {
        expenseRepo.deleteById(expenseId);
        return "Expense Deleted Successfully" ;
    }

    public List<Expense> getExpenseByDateRange(LocalDate startDate, LocalDate endDate, Long userId) {
        return expenseRepo.findByDateBetweenAndUsersId(startDate, endDate, userId);
    }
}
