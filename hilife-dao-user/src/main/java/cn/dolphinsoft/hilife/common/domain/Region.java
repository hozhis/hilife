package cn.dolphinsoft.hilife.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REGION")
public class Region {

    @Id
    @Column(name = "REGION_ID")
    private Integer regionId;

    @Column(name = "AREANAME")
    private String areaname;

    @Column(name = "PARENT_ID")
    private Integer parentId;

    @Column(name = "SHORT_NAME")
    private String shortName;

    @Column(name = "LONGITUDE")
    private String longitude;

    @Column(name = "LATITUDE")
    private String latitude;

    @Column(name = "LEVEL")
    private Integer level;

    @Column(name = "CITY_PY")
    private String cityPy;

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCityPy() {
        return cityPy;
    }

    public void setCityPy(String cityPy) {
        this.cityPy = cityPy;
    }

}
