package org.hiredgoons.hd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(schema = "astro_catalog", name = "hd")
public class Hd {

    private Integer id;
    private Integer hdId;
    private String dm;
    private BigDecimal photovisualMagnitude;
    private BigDecimal photographicMaginitude;
    private String spectralTypeFull;
    private String spectralType;
    private String spectralTypeSub;
    private String intensity;
    private String rightAscension;
    private String declination;

    @Id
    @Column(name="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="hd_id")
    public Integer getHdId() {
        return hdId;
    }

    public void setHdId(Integer hdId) {
        this.hdId = hdId;
    }

    @Column(name="dm")
    public String getDm() {
        return dm;
    }

    public void setDm(String dm) {
        this.dm = dm;
    }

    @Column(name="photovisual_magnitude")
    public BigDecimal getPhotovisualMagnitude() {
        return photovisualMagnitude;
    }

    public void setPhotovisualMagnitude(BigDecimal photovisualMagnitude) {
        this.photovisualMagnitude = photovisualMagnitude;
    }

    @Column(name="photographic_magnitude")
    public BigDecimal getPhotographicMaginitude() {
        return photographicMaginitude;
    }

    public void setPhotographicMaginitude(BigDecimal photographicMaginitude) {
        this.photographicMaginitude = photographicMaginitude;
    }

    @Column(name="spectral_type_full")
    public String getSpectralTypeFull() {
        return spectralTypeFull;
    }

    public void setSpectralTypeFull(String spectralTypeFull) {
        this.spectralTypeFull = spectralTypeFull;
    }

    @Column(name="spectral_type")
    public String getSpectralType() {
        return spectralType;
    }

    public void setSpectralType(String spectralType) {
        this.spectralType = spectralType;
    }

    @Column(name="spectral_type_sub")
    public String getSpectralTypeSub() {
        return spectralTypeSub;
    }

    public void setSpectralTypeSub(String spectralTypeSub) {
        this.spectralTypeSub = spectralTypeSub;
    }

    @Column(name="intensity")
    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    @Column(name="ra")
    public String getRightAscension() {
        return rightAscension;
    }

    public void setRightAscension(String rightAscension) {
        this.rightAscension = rightAscension;
    }

    @Column(name="dec")
    public String getDeclination() {
        return declination;
    }

    public void setDeclination(String declination) {
        this.declination = declination;
    }
}
