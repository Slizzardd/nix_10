package ua.com.alevel.facade;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.Car;
import ua.com.alevel.view.dto.request.DriverRequestDto;
import ua.com.alevel.view.dto.request.RelationRequestDto;
import ua.com.alevel.view.dto.response.DriverResponseDto;

import java.util.List;
import java.util.Map;

@Service
public interface DriverFacade extends BaseFacade<DriverRequestDto, DriverResponseDto> {

    Map<Long, String> findCarsByDriverId(Long driverId);

    List<Car> findAllCarsByDriverId(Long driverId);

    void createRelation(RelationRequestDto dto);
}
