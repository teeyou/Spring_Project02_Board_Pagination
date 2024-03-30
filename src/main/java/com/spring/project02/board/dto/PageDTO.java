package com.spring.project02.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class PageDTO {
    private int page; //현재 페이지
    private int maxPage; //총 페이지
    private int startPage; //현재 화면 기준 시작페이지
    private int endPage; //현재 화면 기준 마지막페이지
}
