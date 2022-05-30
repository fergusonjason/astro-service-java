package org.hiredgoons.common.dto;

import java.io.Serializable;

public abstract class CatalogQuery<T> implements Serializable {

    private T name;
    private String spectralType;
    private String spectralTypeSub;
    private String luminosity;
    int page;
    int size;

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public String getSpectralType() {
        return spectralType;
    }

    public void setSpectralType(String spectralType) {
        this.spectralType = spectralType;
    }

    public String getSpectralTypeSub() {
        return spectralTypeSub;
    }

    public void setSpectralTypeSub(String spectralTypeSub) {
        this.spectralTypeSub = spectralTypeSub;
    }

    public String getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(String luminosity) {
        this.luminosity = luminosity;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
