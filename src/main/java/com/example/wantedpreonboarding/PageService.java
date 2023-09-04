package com.example.wantedpreonboarding;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PageService {

    private final PageRepository pageRepository;

    public PageDto getPage(Long id) {
        Page page = pageRepository.findByPageId(id)
            .orElseThrow(() -> new EntityNotFoundException("page not found."));

        List<Long> subPages = pageRepository.findSubPagesByPageId(id);

        List<String> breadCrumbs = pageRepository.findBreadCrumbsByPageId(id);

        return PageDto.of(page, subPages, breadCrumbs);
    }
}
