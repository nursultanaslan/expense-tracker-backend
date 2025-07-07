package com.example.testapp.service;


import com.example.testapp.model.Expense;
import com.example.testapp.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;  //Constructor injection

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }


    public Expense createExpense(Expense expense) {  //bir harcama ekleriz
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {        //bütün harcamaları getirsin
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {    //id'ye gore harcama getirsin
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No data was found for the given ID: " + id));
    }

    public List<Expense> getExpenseByDate(LocalDate date){    //tarihe göre harcama getirsin.
        return expenseRepository.findByDate(date);
    }

    public Expense updateExpense(Long id, Expense updatedExpense) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));

        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setCategory(updatedExpense.getCategory());
        existingExpense.setDescription(updatedExpense.getDescription());
        existingExpense.setDate(updatedExpense.getDate());

        return expenseRepository.save(existingExpense);
    }

    public void deleteExpense(Long id){
        Expense expense = expenseRepository.findById(id).
                orElseThrow(() -> new RuntimeException("The given id was not found: " + id));
        expenseRepository.delete(expense);

    }
}
