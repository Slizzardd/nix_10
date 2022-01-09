package ua.com.alevel.view.dto.request;

public class RelationRequestDto extends RequestDto {

    private Long driverId;
    private Long carId;

    public RelationRequestDto(Long driverId, Long carId) {
        this.driverId = driverId;
        this.carId = carId;
    }

    public RelationRequestDto(Long driverId) {
        this.driverId = driverId;
    }

    public RelationRequestDto() {
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }
}
