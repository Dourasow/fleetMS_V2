package com.fleetms.accounts.controller;

import com.fleetms.accounts.model.Transaction;
import com.fleetms.accounts.services.TransactionService;
import com.fleetms.accounts.services.TransactionStatusService;
import com.fleetms.accounts.services.TransactionTypeService;
import com.fleetms.hr.services.EmployeeService;
import com.fleetms.settings.services.ClientService;
import com.fleetms.settings.services.ContactService;
import com.fleetms.settings.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionStatusService transactionStatusService;
    @Autowired
    private TransactionTypeService transactionTypeService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private EmployeeService employeeService;

    public Model addModelAttributes(Model model){
        model.addAttribute("transactionStatuses", transactionStatusService.findAll());
        model.addAttribute("transactionTypes", transactionTypeService.findAll());
        model.addAttribute("contacts", contactService.getAll());
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        return model;
    }

    @GetMapping("/accounts/transactions")
    public String  getAll(Model model){
        List<Transaction> transactions =   transactionService.findAll();
        model.addAttribute("transactions", transactions);
        addModelAttributes(model);
        return "/accounts/transactions";
    }

    @GetMapping("/accounts/transactionAdd")
    public String addTransaction(Model model){
        addModelAttributes(model);
        return "accounts/transactionAdd";
    }

    //The op parameter is either Edit or Details
    @GetMapping("/accounts/transaction/{op}/{id}")
    public String editTransaction(@PathVariable Integer id, @PathVariable String op, Model model){
        Transaction transaction = transactionService.findById(id);
        model.addAttribute("transaction", transaction);
        addModelAttributes(model);
        return "/accounts/transaction"+ op;
    }

    @PostMapping("/accounts/transactions")
    public String save(Transaction transaction){
        transactionService.save(transaction);
        return "redirect:/accounts/transactions";
    }

    @RequestMapping(value = "/accounts/transaction/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public  String delete(@PathVariable Integer id){
        transactionService.delete(id);
        return "redirect:/accounts/transactions";
    }
}
