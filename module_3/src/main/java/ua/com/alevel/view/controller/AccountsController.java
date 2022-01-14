package ua.com.alevel.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.persistence.type.CardType;
import ua.com.alevel.util.MediaTypeUtils;
import ua.com.alevel.view.dto.request.AccountRequestDto;
import ua.com.alevel.view.dto.response.AccountResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/accounts")
public class AccountsController extends BaseController {

    private final AccountFacade accountFacade;
    private Long idToUpdate = 0L;

    @Autowired
    private ServletContext servletContext;

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
        return "redirect:/users";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("account", accountFacade.findById(id));
        model.addAttribute("user", accountFacade.findUserByAccountId(id));
        model.addAttribute("transactions", accountFacade.findAllTransactionByAccountId(id));
        return "pages/accounts/account_details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        accountFacade.delete(id);
        return "redirect:/accounts";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @ModelAttribute("account") AccountRequestDto dto, @PathVariable Long id) {
        model.addAttribute("types", CardType.values());
        idToUpdate = id;
        return "pages/accounts/account_update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("account") AccountRequestDto dto) {
        accountFacade.update(dto, idToUpdate);
        return "redirect:/accounts";
    }

    @GetMapping("/getTransactions")
    public String findAllCarsByDriverId(@RequestParam Long accountId, Model model) {
        model.addAttribute("transactions", accountFacade.findAllTransactionByAccountId(accountId));
        model.addAttribute("account", accountFacade.findById(accountId));
        return "pages/accounts/transactions_by_account";
    }

    @GetMapping("/accountStatement")
    public ResponseEntity<InputStreamResource> getAccountStatement(@RequestParam Long accountId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        accountFacade.cardStatement(accountId);
        final String DIRECTORY = "D:\\code\\nix_10\\module_3\\src\\main\\resources\\files";
        final String DEFAULT_FILE_NAME = "data.csv";
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, DEFAULT_FILE_NAME);
        File file = new File(DIRECTORY + "/" + DEFAULT_FILE_NAME);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length())
                .body(resource);
    }
}