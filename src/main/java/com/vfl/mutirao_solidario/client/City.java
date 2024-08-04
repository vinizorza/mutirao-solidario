package com.vfl.mutirao_solidario.client;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class City {
    private String id;
    private String name;
    private Double latitude;
    private Double longitude;
}
