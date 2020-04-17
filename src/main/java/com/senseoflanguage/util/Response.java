package com.senseoflanguage.util;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Response<T> implements Serializable {

    private HttpStatus status;
    private T body;
    private String message;
    private String detail;
    private LocalDateTime dateTime;

    public Response() {
        this(HttpStatus.OK, null, null);
    }

    public Response(HttpStatus status) {
        this(status, null, null);
    }

    public Response(HttpStatus status, T body) {
        this(status, body, null);
    }

    public Response(HttpStatus status, String message) {
        this(status, null, message);
    }

    public Response(HttpStatus status, T body, String message) {
        setStatus(status);
        this.body = body;
        this.message = message;
        this.dateTime = LocalDateTime.now(ZoneOffset.UTC);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", body=" + body +
                ", message='" + message + '\'' +
                ", detail='" + detail + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }

}
