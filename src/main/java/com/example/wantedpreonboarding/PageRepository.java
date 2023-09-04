package com.example.wantedpreonboarding;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PageRepository extends JpaRepository<Page, Long> {

	@Query(value = "SELECT p.title FROM sub_pages_1 sp "
		+ "JOIN pages_1 p ON sp.parent_page_id = p.id "
		+ "WHERE sp.sub_page_id = :pageId "
		+ "ORDER BY sp.depth DESC",
	nativeQuery = true)
	List<String> findBreadCrumbsByPageId(@Param("pageId") Long pageId);

	@Query(value = "SELECT sub_page_id FROM sub_pages_1 "
		+ "WHERE parent_page_id = :pageId AND depth = 1",
	nativeQuery = true)
	List<Long> findSubPagesByPageId(@Param("pageId") Long pageId);

	@Query(value = "SELECT * FROM pages_1 p "
		+ "WHERE id = :pageId",
	nativeQuery = true)
	Optional<Page> findByPageId(@Param("pageId") Long pageId);
}
