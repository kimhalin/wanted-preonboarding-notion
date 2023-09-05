package com.example.wantedpreonboarding.page.service;


import com.example.wantedpreonboarding.page.domain.repository.PageRepository;
import com.example.wantedpreonboarding.page.dto.BreadCrumbsDto;
import com.example.wantedpreonboarding.page.dto.PageDto;
import com.example.wantedpreonboarding.page.domain.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PageService {

    private final PageRepository pageRepository;

    public PageDto getPage(Long id) {
        List<BreadCrumbsDto> breadCrumbs = pageRepository.findBreadCrumbs(id);
        List<Long> subPages = pageRepository.findSubPages(id);
        Page page = pageRepository.findOnePage(id);

        return PageDto.builder()
                .id(page.getId())
                .title(page.getTitle())
                .content(page.getContent())
                .breadCrumbs(breadCrumbs)
                .subPages(subPages).build();
    }

}
