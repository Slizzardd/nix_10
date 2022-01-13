package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.persistence.type.CardType;
import ua.com.alevel.view.dto.request.AccountRequestDto;
import ua.com.alevel.view.dto.response.AccountResponseDto;
import ua.com.alevel.view.dto.response.PageData;

@Controller
@RequestMapping("/accounts")
public class AccountsController extends BaseController{

    private final AccountFacade accountFacade;

    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("card number", "card_number", "cardNumber"),
            new HeaderName("balance", "balance", "balance"),
            new HeaderName("cardType", "cardType", "cardType"),
            new HeaderName("transaction count", "transactionCount", "transactionCount"),
            new HeaderName("details", null, null),
            new HeaderName("update", null, null),
            new HeaderName("delete", null, null)
    };

    public AccountsController(AccountFacade accountFacade) {
        this.accountFacade = accountFacade;
    }


    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<AccountResponseDto> response = accountFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("pageData", response);
        model.addAttribute("createUrl", "/accounts/all");
        model.addAttribute("createNew", "/accounts/new");
        model.addAttribute("cardHeader", "All Users");
        return "pages/accounts/accounts_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "accounts");
    }

    @GetMapping("/new")
    public String redirectToNewAccountPage(Model model, WebRequest request, @RequestParam Long userId) {
        model.addAttribute("account", new AccountRequestDto(userId));
        model.addAttribute("types", CardType.values());
        model.addAttribute("userId", userId);
        return "pages/accounts/accounts_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("account") AccountRequestDto dto) {
        accountFacade.create(dto);
        return "redirect:/accounts";
    }
}
