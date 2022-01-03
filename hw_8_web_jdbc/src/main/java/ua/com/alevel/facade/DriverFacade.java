package ua.com.alevel.facade;

import org.springframework.stereotype.Service;
import ua.com.alevel.view.dto.request.DriverRequestDto;
import ua.com.alevel.view.dto.response.DriverResponseDto;

import java.util.Map;

@Service
public interface DriverFacade extends BaseFacade<DriverRequestDto, DriverResponseDto> {

    Map<Long, String> findAllByCarId(Long carId);
}
