package com.test.wcc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "location")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Location {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Location location = (Location) o;
            return Objects.equals(this.postcode, location.postcode) && Objects.equals(this.latitude, location.latitude) && Objects.equals(this.longitude, location.longitude);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(this.postcode, this.latitude, this.longitude);
    }
}
