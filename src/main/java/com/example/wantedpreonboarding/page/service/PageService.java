package com.example.wantedpreonboarding.page.service;


import com.example.wantedpreonboarding.page.domain.Page;
import com.example.wantedpreonboarding.page.domain.repository.PageRepository;
import com.example.wantedpreonboarding.page.dto.BreadCrumbsDto;
import com.example.wantedpreonboarding.page.dto.PageDto;
import com.example.wantedpreonboarding.page.exception.PageNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PageService {

    private final PageRepository pageRepository;

    public PageDto getPage(Long id){
        try {
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
        catch (Exception e){
            throw new PageNotFoundException();
        }
    }

}
