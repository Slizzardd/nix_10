package ua.com.alevel.facade.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.facade.CarFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Car;
import ua.com.alevel.service.CarService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.CarRequestDto;
import ua.com.alevel.view.dto.response.CarResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CarFacadeImpl implements CarFacade {

    private final CarService carService;

    public CarFacadeImpl(CarService carService) {
        this.carService = carService;
    }

    @Override
    public void create(CarRequestDto carRequestDto) {
        Car car = new Car();
        car.setCarName(carRequestDto.getCarName());
        car.setColor(carRequestDto.getColor());
        car.setEngineCapacity(carRequestDto.getEngineCapacity());
        car.setYearsOfIssue(carRequestDto.getYearsOfIssue());
        car.setImageUrl(carRequestDto.getImageUrl());
        car.setCarNumber(carRequestDto.getCarNumber());
        try {
            carService.create(car, carRequestDto.getDriverId());
        } catch (NullPointerException e) {
            throw new EntityNotFoundException("Driver not found!");
        }
    }

    @Override
    public void update(CarRequestDto carRequestDto, Long id) {
        Car car = carService.findById(id);
        car.setCarName(carRequestDto.getCarName());
        car.setColor(carRequestDto.getColor());
        car.setEngineCapacity(carRequestDto.getEngineCapacity());
        car.setYearsOfIssue(carRequestDto.getYearsOfIssue());
        car.setImageUrl(carRequestDto.getImageUrl());
        car.setCarNumber(carRequestDto.getCarNumber());
        carService.update(car);
    }

    @Override
    public void delete(Long id) {
        carService.delete(id);
    }

    @Override
    public CarResponseDto findById(Long id) {
        return new CarResponseDto(carService.findById(id));
    }

    @Override
    public PageData<CarResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        String driverId = request.getParameter("carId");
        if (StringUtils.isNotBlank(driverId)) {
            dataTableRequest.getQueryParam().put("carId", driverId);
        }
        DataTableResponse<Car> tableResponse = carService.findAll(dataTableRequest);
        List<CarResponseDto> cars = tableResponse.getItems().stream().
                map(CarResponseDto::new).
                collect(Collectors.toList());

        PageData<CarResponseDto> pageData = (PageData<CarResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(cars);
        return pageData;
    }

    @Override
    public Map<Long, String> findDriversByCarId(Long carId) {
        return carService.findDriversByCarId(carId);
    }
}
