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
        return GernalSearch;
    }

    @JsonIgnore
    public void setGernalSearch(String gernalSearch) {
        GernalSearch = gernalSearch;
    }

    @JsonProperty("IP")
    private String IP;

    @JsonProperty("GernalSearch")
    private String GernalSearch;

    public SearchCriteria(String DNS, int PORT, String IP,String GernalSearch ) {
        this.DNS = DNS;
        this.PORT = PORT;
        this.IP = IP;
        this.GernalSearch = GernalSearch;
    }
    public SearchCriteria() {

    }

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