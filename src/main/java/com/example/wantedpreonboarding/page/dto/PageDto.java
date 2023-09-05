package com.example.wantedpreonboarding.page.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PageDto {
    private Long id;
    private String title;
    private String content;

    private List<Long> subPages;
    private List<BreadCrumbsDto> breadCrumbs;
}
