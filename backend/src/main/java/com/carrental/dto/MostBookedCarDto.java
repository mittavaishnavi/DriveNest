
package com.carrental.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MostBookedCarDto {
    private Long id;
    private String brand;
    private String model;
    private BigDecimal pricePerDay;
    private String imageUrl;
    private Long bookingCount;
}