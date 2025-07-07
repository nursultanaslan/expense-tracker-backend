package com.example.testapp.controller;


import com.example.testapp.service.ExpenseService;
import com.example.testapp.model.Expense;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController (ExpenseService expenseService){
        this.expenseService = expenseService;
    }


    @PostMapping("/expense")   //bir harcama ekler
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense){
        Expense createdExpense = expenseService.createExpense(expense);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
    }

    @PutMapping("/expense/{id}")   //id'si verilen harcamayı günceller
    public ResponseEntity<Expense> updateExpense(@PathVariable("id") Long id, @RequestBody Expense expense){
        Expense updated = expenseService.updateExpense(id, expense);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/all-expenses")   //tüm harcamaları getirir
    public List<Expense> getAllExpenses(){
        return expenseService.getAllExpenses();
    }

    @GetMapping("/all-expenses/{id}")  //id'ye gore harcama getirir
    public ResponseEntity<Expense> getExpenseById(@PathVariable("id") Long id){
        Expense expense = expenseService.getExpenseById(id);
        return ResponseEntity.ok(expense);
    }

    @GetMapping("/all-expenses/date")       //tarihe gore harcama getir.
    public ResponseEntity<List<Expense>> getExpenseByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        List<Expense> expenses;

        if(date != null){
            expenses = expenseService.getExpenseByDate(date);
        }else {
            expenses = expenseService.getAllExpenses();
        }
        return ResponseEntity.ok(expenses);
    }

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable("id") Long id){
       expenseService.deleteExpense(id);
       return ResponseEntity.noContent().build();
    }

}
