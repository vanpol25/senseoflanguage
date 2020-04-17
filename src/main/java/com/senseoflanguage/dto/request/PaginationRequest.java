package com.senseoflanguage.dto.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class PaginationRequest implements Serializable {

    @NotNull
    @Min(value = 1, message = "Min 1 post on page")
    @Max(value = 100, message = "Max 20 post on page")
    private Integer size;
    @Min(value = 0, message = "Page start with 0")
    @NotNull
    private Integer page;
    private Sort.Direction direction;
    private String field;

    public PaginationRequest() {
    }

    public Pageable toPageable() {
        PageRequest pageRequest;
        if (direction != null && field != null) {
            pageRequest = PageRequest.of(page, size, direction, field);
        } else if (direction != null) {
            pageRequest = PageRequest.of(page, size, direction, "id");
        } else if (field != null) {
            pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, field);
        } else {
            pageRequest = PageRequest.of(page, size, Sort.Direction.DESC, "id");
        }
        return pageRequest;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaginationRequest that = (PaginationRequest) o;
        return Objects.equals(size, that.size) &&
                Objects.equals(page, that.page) &&
                direction == that.direction &&
                Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, page, direction, field);
    }

    @Override
    public String toString() {
        return "PaginationRequest{" +
                "size=" + size +
                ", page=" + page +
                ", direction=" + direction +
                ", field='" + field + '\'' +
                '}';
    }
}
