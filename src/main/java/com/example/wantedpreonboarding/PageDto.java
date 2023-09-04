package com.example.wantedpreonboarding;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PageDto {
    private Long id;
    private String title;
    private String content;
    private List<Long> subPages;
    private List<String> breadCrumbs;

    public static PageDto of(Page page, List<Long> subPages, List<String> breadCrumbs) {
        return PageDto.builder()
            .id(page.getId())
            .title(page.getTitle())
            .content(page.getContent())
            .subPages(subPages)
            .breadCrumbs(breadCrumbs)
            .build();
    }
}
