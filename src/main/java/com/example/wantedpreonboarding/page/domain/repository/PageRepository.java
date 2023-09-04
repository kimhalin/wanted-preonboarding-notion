package com.example.wantedpreonboarding.page.domain.repository;

import com.example.wantedpreonboarding.page.domain.Page;
import com.example.wantedpreonboarding.page.dto.BreadCrumbsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {

    @Query(value = "SELECT p.title, p.id FROM sub_pages sp \n" +
            "JOIN pages p on sp.parent_page_id = p.id \n" +
            "WHERE sp.sub_page_id = ?1 \n" +
            "ORDER BY sp.depth DESC",
    nativeQuery = true)
    public List<BreadCrumbsDto> findBreadCrumbs(Long id);

    @Query(value = "SELECT sub_page_id FROM sub_pages WHERE parent_page_id = ?1 AND depth = 1",
            nativeQuery = true)
    public List<Long> findSubPages(Long id);

    @Query(value = "SELECT * FROM pages p WHERE id = ?1",
            nativeQuery = true)
    public Page findOnePage(Long id);
}
