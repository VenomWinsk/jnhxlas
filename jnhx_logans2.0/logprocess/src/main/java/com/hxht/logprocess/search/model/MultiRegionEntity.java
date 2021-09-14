package com.hxht.logprocess.search.model;

import lombok.Data;

@Data
public class MultiRegionEntity {
    String emailName = "";
    int countryCount = 0;
    int IPCount = 0;
    int countryRealCount = 0;
    String countries = "";

    public MultiRegionEntity(String emailName, int countryCount, int IPCount, int countryRealCount) {
        this.emailName = emailName;
        this.countryCount = countryCount;
        this.IPCount = IPCount;
        this.countryRealCount = countryRealCount;
        //this.countries = _countries;
    }
}
