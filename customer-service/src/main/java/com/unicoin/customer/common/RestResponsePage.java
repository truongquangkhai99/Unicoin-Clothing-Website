package com.unicoin.customer.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class RestResponsePage<T> {
    private Pagination pagination;
    private List<T> content;

    public RestResponsePage(List<T> content, int page, int size, int totalOfElements, int totalPages) {
        this.content = content;
        Pagination pagination = new Pagination();
        pagination.currentPage = page;
        pagination.size = size;
        pagination.totalPages = totalPages;
        pagination.totalOfElements = totalOfElements;
        this.pagination = pagination;
    }

    public RestResponsePage(List<T> content, int page, int size, long totalOfElements, int totalPages) {
        this.content = content;
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
