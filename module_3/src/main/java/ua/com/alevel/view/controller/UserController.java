package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.view.dto.request.UserRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.UserResponseDto;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController{

    private final UserFacade userFacade;
    private Long idToUpdate = 0L;

    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("first name", "first_name", "firstName"),
            new HeaderName("last name", "last_name", "lastName"),
            new HeaderName("age", "age", "age"),
            new HeaderName("account count", "accountCount", "accountCount"),
            new HeaderName("details", null, null),
            new HeaderName("update", null, null),
            new HeaderName("delete", null, null)
    };

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<UserResponseDto> response = userFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("pageData", response);
        model.addAttribute("createUrl", "/users/all");
        model.addAttribute("createNew", "/users/new");
        model.addAttribute("cardHeader", "All Users");
        return "pages/users/users_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "users");
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("user", userFacade.findById(id));
        model.addAttribute("accounts", userFacade.findAccountsByUserId(id));
        return "pages/users/user_details";
    }

    @GetMapping("/update/{id}")
    public String update(@ModelAttribute("user") UserRequestDto dto, @PathVariable Long id) {
        idToUpdate = id;
        return "pages/users/user_update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") UserRequestDto dto) {
        userFacade.update(dto, idToUpdate);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userFacade.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/new")
    public String redirectToNewUserPage(Model model, WebRequest request) {
        model.addAttribute("user", new UserRequestDto());
        return "pages/users/user_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("car") UserRequestDto dto) {
        userFacade.create(dto);
        return "redirect:/users";
    }

    @GetMapping("/getAccounts")
    public String findAllCarsByDriverId(@RequestParam Long userId, Model model) {
        model.addAttribute("accounts", userFacade.findAllAccountsByUserId(userId));
        model.addAttribute("user", userFacade.findById(userId));
        return "pages/users/accounts_by_user";
    }
}
