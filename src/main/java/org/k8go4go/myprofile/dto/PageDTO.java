package org.k8go4go.myprofile.dto;


import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageDTO {
    private final int PAPENUM_SIZE = 5;
    private int startPage;
    private int endPage;

    private boolean prev, next;
    private long total;

    private Pager pager;

    private List<Integer> pageNumbers;

    public PageDTO(Pager pager, long total) {
        this.total = total;
        this.pager = pager;


        this.endPage = (int)Math.ceil((double) pager.getPage() / PAPENUM_SIZE) * PAPENUM_SIZE;
        this.startPage = this.endPage - (PAPENUM_SIZE - 1);
        int realEnd = (int)(Math.ceil((double)total/pager.getSize()));

        if(this.endPage > realEnd) {
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;

        this.pageNumbers = IntStream.rangeClosed(startPage, endPage).boxed().collect(Collectors.toList());
    }
}
