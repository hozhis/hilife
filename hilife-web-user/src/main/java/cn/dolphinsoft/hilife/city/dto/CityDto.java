package cn.dolphinsoft.hilife.city.dto;

import java.io.Serializable;
import java.util.List;

public class CityDto implements Serializable {

    private static final long serialVersionUID = 1660466797342380044L;

    private String py;

    private List<String> city;

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public List<String> getCity() {
        return city;
    }

    public void setCity(List<String> city) {
        this.city = city;
    }

}
