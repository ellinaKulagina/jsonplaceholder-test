package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoModel geo;
}
