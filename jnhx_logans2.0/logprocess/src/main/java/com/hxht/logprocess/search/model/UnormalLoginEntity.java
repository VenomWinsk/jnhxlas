package com.hxht.logprocess.search.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UnormalLoginEntity {

    String req_ip = "";
    String country = "";
    String city = "";
    int emailCount = 0;
    List<EmailResult> emailList = new ArrayList<>();

    public UnormalLoginEntity(String req_ip, String country, String city) {
        this.req_ip = req_ip;
        this.country = country;
        this.city = city;
    }

    public void addEmail(EmailResult result) {
        emailList.add(result);
        emailCount = emailList.size();
    }

    @Data
    public static class EmailResult {
        private String emailName;
        private int successCount = 0;
        private int failCount = 0;

        public EmailResult(String emailName, int successCount, int failCount) {
            this.emailName = emailName;
            this.successCount = successCount;
            this.failCount = failCount;
        }
    }
}
