package com.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class SearchCriteria {
    @JsonProperty("DNS")
    private String DNS;

    @JsonProperty("PORT")
    private Integer PORT;

    @JsonIgnore
    public String getGernalSearch() {
        return GeneralSearch;
    }

    @JsonIgnore
    public void setGernalSearch(String gernalSearch) {
        GeneralSearch = gernalSearch;
    }

    @JsonProperty("IP")
    private String IP;

    @JsonProperty("GeneralSearch")
    private String GeneralSearch;

    public SearchCriteria(String DNS, int PORT, String IP,String GernalSearch ) {
        this.DNS = DNS;
        this.PORT = PORT;
        this.IP = IP;
        this.GeneralSearch = GernalSearch;
    }
    public SearchCriteria() {}

    @JsonIgnore
    public String getDNS() {
        return DNS;
    }

    @JsonIgnore
    public void setDNS(String DNS) {
        this.DNS = DNS;
    }

    @JsonIgnore
    public Integer getPORT() {
        return PORT;
    }

    @JsonIgnore
    public void setPORT(Integer PORT) {
        this.PORT = PORT;
    }

    @JsonIgnore
    public String getIP() {
        return IP;
    }

    @JsonIgnore
    public void setIP(String IP) {
        this.IP = IP;
    }
}