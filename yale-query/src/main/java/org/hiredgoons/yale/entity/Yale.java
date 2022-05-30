package org.hiredgoons.yale.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(schema = "astro_catalog", name="yale")
public class Yale implements Serializable {

    private Integer id;
    private String name;
    private Integer hdId;
    private Integer adsId;
    private String variable;
    private String ra;
    private String dec;
    private BigDecimal visualMagnitude;
    private BigDecimal bvMagnitude;
    private String spectralTypeFull;
    private String spectralType;
    private String spectralTypeSub;
    private String intensity;
    private String mtWilson;
    private String noteFlag;

    @Id
    @Column(name="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="hd_id")
    public Integer getHdId() {
        return hdId;
    }

    public void setHdId(Integer hdId) {
        this.hdId = hdId;
    }

    @Column(name="ads_id")
    public Integer getAdsId() {
        return adsId;
    }

    public void setAdsId(Integer adsId) {
        this.adsId = adsId;
    }

    @Column(name="variable")
    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    @Column(name="ra")
    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    @Column(name="dec")
    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    @Column(name="visual_magnitude")
    public BigDecimal getVisualMagnitude() {
        return visualMagnitude;
    }

    public void setVisualMagnitude(BigDecimal visualMagnitude) {
        this.visualMagnitude = visualMagnitude;
    }

    @Column(name="bv_magnitude")
    public BigDecimal getBvMagnitude() {
        return bvMagnitude;
    }

    public void setBvMagnitude(BigDecimal bvMagnitude) {
        this.bvMagnitude = bvMagnitude;
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

    @Column(name="mt_wilson")
    public String getMtWilson() {
        return mtWilson;
    }

    public void setMtWilson(String mtWilson) {
        this.mtWilson = mtWilson;
    }

    @Column(name="note_flag")
    public String getNoteFlag() {
        return noteFlag;
    }

    public void setNoteFlag(String noteFlag) {
        this.noteFlag = noteFlag;
    }
}
