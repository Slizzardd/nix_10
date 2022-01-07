package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.CarRequestDto;
import ua.com.alevel.view.dto.response.CarResponseDto;

import java.util.Map;

public interface CarFacade extends BaseFacade<CarRequestDto, CarResponseDto> {

    Map<Long, String> findByDriverId(Long id);
}
