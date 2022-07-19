package com.unicoin.order.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


//can co getter va setter de tranh loi no bean serializer
@Getter
@Setter
public class RestResponsePage<T> {
    private Pagination pagination;
    private List<T> data;

    public RestResponsePage(List<T> data, int page, int size, int totalOfElements, int totalPages) {
        this.data = data;
        Pagination pagination = new Pagination();
        pagination.currentPage = page;
        pagination.size = size;
        pagination.totalPages = totalPages;
        pagination.totalOfElements = totalOfElements;
        this.pagination = pagination;
    }
    public RestResponsePage(List<T> data) {
        this.data = data;
    }

    public RestResponsePage(List<T> data, int page, int size, long totalOfElements, int totalPages) {
        this.data = data;
        Pagination pagination = new Pagination();
        pagination.currentPage = page;
        pagination.size = size;
        pagination.totalPages = totalPages;
        pagination.totalOfElements = totalOfElements;
        this.pagination = pagination;
    }


    @Getter
    @Setter
    public class Pagination {
        private int currentPage;
        private int size;
        private int totalPages;
        private long totalOfElements;
    }
}
