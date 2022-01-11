package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.CarFacade;
import ua.com.alevel.facade.DriverFacade;
import ua.com.alevel.view.dto.request.DriverRequestDto;
import ua.com.alevel.view.dto.response.DriverResponseDto;
import ua.com.alevel.view.dto.response.PageData;

@Controller
@RequestMapping("/drivers")
public class DriverController extends BaseController {

    private final CarFacade carFacade;
    private Long idToUpdate = 0L;
    private final DriverFacade driverFacade;
    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("image", null, null),
            new HeaderName("first name", "firstName", "firstName"),
            new HeaderName("last name", "lastName", "lastName"),
            new HeaderName("car count", null, null),
            new HeaderName("balance", "balance", "balance"),
            new HeaderName("details", null, null),
            new HeaderName("update", null, null),
            new HeaderName("delete", null, null)
    };

    public DriverController(CarFacade carFacade, DriverFacade driverFacade) {
        this.carFacade = carFacade;
        this.driverFacade = driverFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<DriverResponseDto> response = driverFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/drivers/all");
        model.addAttribute("createNew", "/drivers/new");
        model.addAttribute("cardHeader", "All Drivers");
        return "pages/driver/driver_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "drivers");
    }

    @GetMapping("/new")
    public String redirectToNewDriverPage(Model model) {
        model.addAttribute("driver", new DriverRequestDto());
        return "pages/driver/driver_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("driver") DriverRequestDto dto) {
        driverFacade.create(dto);
        return "redirect:/drivers";
    }

    @GetMapping("/update/{id}")
    public String update(@ModelAttribute("driver") DriverRequestDto dto, @PathVariable Long id) {
        idToUpdate = id;
        return "pages/driver/driver_update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("driver") DriverRequestDto dto) {
        driverFacade.update(dto, idToUpdate);
        return "redirect:/drivers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        driverFacade.delete(id);
        return "redirect:/drivers";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("driver", driverFacade.findById(id));
        model.addAttribute("cars", driverFacade.findCarsByDriverId(id));
        return "pages/driver/driver_details";
    }
}
