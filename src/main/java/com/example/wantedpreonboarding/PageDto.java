package com.example.wantedpreonboarding;

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

    private List<Long> breadCrumbs;
}
