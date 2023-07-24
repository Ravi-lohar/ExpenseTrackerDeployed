package com.example.ExpenseTracker.cotroller;

import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.model.User;
import com.example.ExpenseTracker.model.dto.SignInInput;
import com.example.ExpenseTracker.model.dto.SignUpOutput;
import com.example.ExpenseTracker.service.AuthenticationService;
import com.example.ExpenseTracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
public class UserController {
    @Autowired
    UserService userService ;

    @Autowired
    AuthenticationService authenticationService ;

    @PostMapping("user/signup")
    public SignUpOutput signUpUser(@Valid @RequestBody User user)
    {
        return userService.signUpUser(user);
    }

    @PostMapping("user/signIn")
    public String sigInUser(@RequestBody @Valid SignInInput signInInput)
    {
        return userService.signInUser(signInInput);
    }

    @DeleteMapping("user/signOut/{email}/{token}")
    public String sigOutUser(@Valid @PathVariable String email, @Valid @PathVariable String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.sigOutUser(email);
        }
        else {
            return "Sign out not allowed for non authenticated user.";
        }

    }

    @PostMapping("expense/{email}/{token}")
    public String addExpense(@Valid @PathVariable String email, @PathVariable String token , @Valid @RequestBody Expense expense)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.addExpense(expense) ;

        }
        else {
            return "Not allowed for non authenticated user.";
        }

    }

    @PutMapping("expense/{email}/{token}")
    public String updateExpense(@Valid @PathVariable String email, @PathVariable String token , @Valid @RequestBody Expense expense)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.updateExpense(expense) ;

        }
        else {
            return "Not allowed for non authenticated user.";
        }

    }

    @GetMapping("expense/{email}/{token}/{userId}")
    public List<Expense> getExpenses(@Valid @PathVariable String email, @PathVariable String token , @PathVariable Long userId)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.getexpenses(email , userId) ;

        }
        else {
            return null ;
        }
    }

    @DeleteMapping("expense/{email}/{token}/{expenseId}")
    public String deleteExpense(@PathVariable String email, @PathVariable String token , @PathVariable Long expenseId)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.deleteExpenses(expenseId) ;

        }
        else {
            return "Not Allowed for unauthenticated User" ;
        }
    }

    @GetMapping("expense/{email}/{token}/{userId}/{startDate}/{endDate}")
    public List<Expense> getExpensesByDateRange(@Valid @PathVariable String email, @PathVariable String token , @PathVariable Long userId , @PathVariable  LocalDate startDate, @PathVariable LocalDate endDate) {
            if(authenticationService.authenticate(email,token)) {
                return userService.getExpensesByDateRange(startDate, endDate, userId);
            }
            else {
                return null ;
            }
    }


}