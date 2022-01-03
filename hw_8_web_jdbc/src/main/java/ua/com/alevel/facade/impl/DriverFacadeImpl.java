package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.DriverFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Driver;
import ua.com.alevel.service.DriverService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.DriverRequestDto;
import ua.com.alevel.view.dto.response.DriverResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DriverFacadeImpl implements DriverFacade {

    private final DriverService driverService;

    public DriverFacadeImpl(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public void create(DriverRequestDto driverRequestDto) {
        Driver driver = new Driver();
        driver.setFirstName(driverRequestDto.getFirstName());
        driver.setLastName(driverRequestDto.getLastName());
        driver.setBalance(driverRequestDto.getBalance());
        driver.setImageUrl(driverRequestDto.getImageUrl());
        driver.setNotes(driverRequestDto.getNotes());
        driverService.create(driver);
    }

    @Override
    public void update(DriverRequestDto driverRequestDto, Long id) {
        Driver driver = driverService.findById(id);
        driver.setFirstName(driverRequestDto.getFirstName());
        driver.setLastName(driverRequestDto.getLastName());
        driver.setBalance(driverRequestDto.getBalance());
        driver.setImageUrl(driverRequestDto.getImageUrl());
        driver.setNotes(driverRequestDto.getNotes());
        driverService.update(driver);
    }

    @Override
    public void delete(Long id) {
        driverService.delete(id);
    }

    @Override
    public DriverResponseDto findById(Long id) {
        return new DriverResponseDto(driverService.findById(id));
    }

    @Override
    public PageData<DriverResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Driver> tableResponse = driverService.findAll(dataTableRequest);

        List<DriverResponseDto> drivers = tableResponse.getItems().stream().
                map(DriverResponseDto::new).
                peek(driverResponseDto -> driverResponseDto.setCarCount((Integer) tableResponse.
                        getOtherParamMap().get(driverResponseDto.getId()))).
                collect(Collectors.toList());

        PageData<DriverResponseDto> pageData = (PageData<DriverResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(drivers);

        return pageData;
    }

    @Override
    public Map<Long, String> findAllByCarId(Long carId) {
        return driverService.finAllByCarId(carId);
    }
}
