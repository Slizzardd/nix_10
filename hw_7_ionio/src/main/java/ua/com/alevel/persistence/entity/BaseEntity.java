package ua.com.alevel.persistence.entity;

import lombok.Data;

@Data
public abstract class BaseEntity {
    private Integer id;
    private Boolean available;

    public BaseEntity() {
        this.available = true;
    }
}
