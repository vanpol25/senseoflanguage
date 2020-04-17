package com.senseoflanguage.dto.response;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class PageResponse<T> implements Serializable {

    private Integer totalPages;
    private Long totalElements;
    private List<T> data;

    public PageResponse() {
    }

    public PageResponse(Integer totalPages, Long totalElements, List<T> data) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.data = data;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageResponse<?> that = (PageResponse<?>) o;
        return Objects.equals(totalPages, that.totalPages) &&
                Objects.equals(totalElements, that.totalElements) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalPages, totalElements, data);
    }

    @Override
    public String toString() {
        return "PageResponse{" +
                "totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", data=" + data +
                '}';
    }

}