package org.hiredgoons.gliese.entity;

import org.hiredgoons.common.dto.Positional;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(schema = "astro_catalog", name= "gliese")
@SuppressWarnings("unused")
public class Gliese implements Positional, Serializable {

    private Integer id;
    private String name;
    private String component;
    private String rightAscension;
    private String declination;
    private BigDecimal properMotion;
    private BigDecimal properMotionDir;
    private BigDecimal radialVelocity;
    private String spectralTypeFull;
    private String spectralType;
    private String spectralTypeSub;
    private String luminosityClass;
    private BigDecimal visualMagnitude;
    private BigDecimal absoluteMagnitude;
    private BigDecimal parallax;
    private BigDecimal bvColor;
    private Integer hdNumber;
    private BigDecimal distance;

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="component")
    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
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

    @Column(name="proper_motion")
    public BigDecimal getProperMotion() {
        return properMotion;
    }

    public void setProperMotion(BigDecimal properMotion) {
        this.properMotion = properMotion;
    }

    @Column(name="proper_motion_dir")
    public BigDecimal getProperMotionDir() {
        return properMotionDir;
    }

    public void setProperMotionDir(BigDecimal properMotionDir) {
        this.properMotionDir = properMotionDir;
    }

    @Column(name="radial_velocity")
    public BigDecimal getRadialVelocity() {
        return radialVelocity;
    }

    public void setRadialVelocity(BigDecimal radialVelocity) {
        this.radialVelocity = radialVelocity;
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

    @Column(name="luminosity_class")
    public String getLuminosityClass() {
        return luminosityClass;
    }

    public void setLuminosityClass(String luminosityClass) {
        this.luminosityClass = luminosityClass;
    }

    @Column(name="visual_magnitude")
    public BigDecimal getVisualMagnitude() {
        return visualMagnitude;
    }

    public void setVisualMagnitude(BigDecimal visualMagnitude) {
        this.visualMagnitude = visualMagnitude;
    }

    @Column(name="absolute_magnitude")
    public BigDecimal getAbsoluteMagnitude() {
        return absoluteMagnitude;
    }

    public void setAbsoluteMagnitude(BigDecimal absoluteMagnitude) {
        this.absoluteMagnitude = absoluteMagnitude;
    }

    @Column(name="parallax")
    public BigDecimal getParallax() {
        return parallax;
    }

    public void setParallax(BigDecimal parallax) {
        this.parallax = parallax;
    }

    @Column(name="bv_color")
    public BigDecimal getBvColor() {
        return bvColor;
    }

    public void setBvColor(BigDecimal bvColor) {
        this.bvColor = bvColor;
    }

    @Column(name="hd_number")
    public Integer getHdNumber() {
        return hdNumber;
    }

    public void setHdNumber(Integer hdNumber) {
        this.hdNumber = hdNumber;
    }

    @Transient
    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }
}
