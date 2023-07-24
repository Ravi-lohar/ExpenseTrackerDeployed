package com.example.ExpenseTracker.repository;

import com.example.ExpenseTracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IExpenseRepo extends JpaRepository<Expense , Long> {
    List<Expense> findByUsersId(Long userId);
    List<Expense> findByDateBetweenAndUsersId(LocalDate startDate, LocalDate endDate, Long usersId);
}
