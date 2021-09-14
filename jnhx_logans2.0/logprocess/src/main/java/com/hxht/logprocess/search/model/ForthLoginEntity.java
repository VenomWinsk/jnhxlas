package com.hxht.logprocess.search.model;

import lombok.Data;

@Data
public class ForthLoginEntity {

    String req_ip = "";
    String country = "";
    String city = "";
    String email = "";
//    double emailCount = 0;
    int successCount = 0;
    int failCount = 0;
//    List<EmailResult> emailList;


    public ForthLoginEntity(String req_ip, String country, String city, String email, int successCount, int failCount) {
        this.req_ip = req_ip;
        this.country = country;
        this.city = city;
        this.email = email;
//        this.emailCount = emailCount;
        this.successCount = successCount;
        this.failCount = failCount;
    }
}
