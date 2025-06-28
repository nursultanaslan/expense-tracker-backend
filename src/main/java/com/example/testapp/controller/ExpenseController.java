package com.example.testapp.controller;


import com.example.testapp.service.ExpenseService;
import com.example.testapp.model.Expense;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController (ExpenseService expenseService){
        this.expenseService = expenseService;
    }


    @PostMapping   //bir harcama ekler
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense){
        Expense createdExpense = expenseService.createExpense(expense);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
    }

    @PutMapping("/{id}")   //id'si verilen harcamayı günceller
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expense){
        Expense updated = expenseService.updateExpense(id, expense);
        return ResponseEntity.ok(updated);
    }

    @GetMapping   //tüm harcamaları getirir
    public List<Expense> getAllExpenses(){
        return expenseService.getAllExpenses();
    }

    @GetMapping("{id}")  //id'ye gore harcama getirir
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id){
        Expense expense = expenseService.getExpenseById(id);
        return ResponseEntity.ok(expense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id){
       expenseService.deleteExpense(id);
       return ResponseEntity.noContent().build();
    }

}
