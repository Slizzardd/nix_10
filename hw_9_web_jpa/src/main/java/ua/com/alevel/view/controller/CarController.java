package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.CarFacade;
import ua.com.alevel.facade.DriverFacade;
import ua.com.alevel.view.dto.request.CarRequestDto;
import ua.com.alevel.view.dto.response.CarResponseDto;
import ua.com.alevel.view.dto.response.PageData;

@Controller
@RequestMapping("/cars")
public class CarController extends BaseController {

    private final CarFacade carFacade;
    private final DriverFacade driverFacade;
    private Long idToUpdate = 0L;

    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("image", "image", null),
            new HeaderName("car name", "carName", "cars_name"),
            new HeaderName("car number", "carNumber", "car_number"),
            new HeaderName("color", "color", "color"),
            new HeaderName("years of issue", "yearsOfIssue", "years_of_issue"),
            new HeaderName("engine capacity", "engineCapacity", "engine_of_capacity"),
            new HeaderName("details", null, null),
            new HeaderName("update", null, null),
            new HeaderName("delete", null, null)
    };

    public CarController(CarFacade carFacade, DriverFacade driverFacade) {
        this.carFacade = carFacade;
        this.driverFacade = driverFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<CarResponseDto> response = carFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/cars/all");
        model.addAttribute("createNew", "/cars/new");
        model.addAttribute("cardHeader", "All Cars");
        return "pages/car/car_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "cars");
    }

    @GetMapping("/new/{driverId}")
    public String redirectToNewCarPage(Model model, WebRequest request, @PathVariable Long driverId) {
        model.addAttribute("car", new CarRequestDto());
        model.addAttribute("driverId", driverId);
        return "pages/car/car_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("car") CarRequestDto dto) {
        carFacade.create(dto);
        return "redirect:/cars";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("car", carFacade.findById(id));
        model.addAttribute("drivers", driverFacade.findAllByCarId(id));
        return "pages/car/car_details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        carFacade.delete(id);
        return "redirect:/cars";
    }

    @GetMapping("/update/{id}")
    public String update(@ModelAttribute("car") CarRequestDto dto, @PathVariable Long id){
        idToUpdate = id;
        return "pages/car/car_update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("car") CarRequestDto dto) {
        carFacade.update(dto, idToUpdate);
        return "redirect:/cars";
    }
}