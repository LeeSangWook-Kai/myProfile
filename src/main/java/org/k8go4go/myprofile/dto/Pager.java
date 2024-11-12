package org.k8go4go.myprofile.dto;


import lombok.Data;

@Data
public class Pager {
    private int page;
    private int size;
    private int offset;
    private String searchType;
    private String keyword;

    public Pager() {
        this(1, 10);
    }

    public Pager(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getOffset() {
        this.offset = (page - 1) * 10;
        return offset;
    }

    public String[] getSearchTypes() {
        return searchType == null ? new String[]{} : searchType.split("");
    }

    @Override
    public String toString() {
        return "Pager{" +
                "page=" + page +
                ", size=" + size +
                ", offset=" + offset +
                ", searchType='" + searchType + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}