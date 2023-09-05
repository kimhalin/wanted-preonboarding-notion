package com.example.wantedpreonboarding.page.presentation;

import com.example.wantedpreonboarding.page.dto.PageDto;
import com.example.wantedpreonboarding.page.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pages")
@RequiredArgsConstructor
public class PageController {
    private final PageService pageService;

    @GetMapping("/{id}")
    public ResponseEntity<PageDto> getPage(@PathVariable("id") Long id) throws Exception{
        PageDto response = pageService.getPage(id);
        return ResponseEntity.ok(response);
    }
}
