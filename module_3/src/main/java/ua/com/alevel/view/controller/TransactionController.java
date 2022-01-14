package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.TransactionFacade;
import ua.com.alevel.view.dto.request.TransactionRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.TransactionResponseDto;

@Controller
@RequestMapping("/transactions")
public class TransactionController extends BaseController{

    private final TransactionFacade transactionFacade;

    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("amount", "amount", "amount"),
            new HeaderName("income", null, null),
            new HeaderName("appointment  ", null, null),
            new HeaderName("details", null, null),
    };

    public TransactionController(TransactionFacade transactionFacade) {
        this.transactionFacade = transactionFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<TransactionResponseDto> response = transactionFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("pageData", response);
        model.addAttribute("createUrl", "/transactions/all");
        model.addAttribute("createNew", "/transactions/new");
        model.addAttribute("cardHeader", "All Transactions");
        return "pages/transactions/transactions_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "transactions");
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("transaction", transactionFacade.findById(id));
        model.addAttribute("account", transactionFacade.findById(id).getMapAccount());
        return "pages/transactions/transaction_details";
    }

    @GetMapping("/new")
    public String redirectToNewAccountPage(Model model, WebRequest request, @RequestParam String cardNumber) {
        model.addAttribute("transaction", new TransactionRequestDto(cardNumber));
//        model.addAttribute("types", CardType.values());
//        model.addAttribute("userId", userId);
        return "pages/transactions/transaction_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("transaction") TransactionRequestDto dto) throws Exception {
        transactionFacade.create(dto);
        return "redirect:/transactions";
    }
}
