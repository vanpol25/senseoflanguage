package com.senseoflanguage.dto.request;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PaginationRequest implements Serializable {

    @NotNull
    @Min(value = 1, message = "Min 1 post on page")
    @Max(value = 100, message = "Max 100 post on page")
    private Integer size;
    @Min(value = 0, message = "Page start with 0")
    @NotNull
    private Integer page;
    private Sort.Direction direction;
    private String field;

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

}

