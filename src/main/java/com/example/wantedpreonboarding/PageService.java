package com.example.wantedpreonboarding;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

@Service
public class PageService {
    private final DriverManagerDataSource dataSource =
            new DriverManagerDataSource("jdbc:mysql://localhost:13306/notion", "root", "root");
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    public PageDto getPage(Long id) {
        try{
            String breadCrumbsSql = "SELECT parent_page_id FROM sub_pages WHERE sub_page_id = ? ORDER BY depth ASC";
            String subPageSql = "SELECT sub_page_id FROM sub_pages WHERE parent_page_id = ? AND depth = 1";
            String getPageSql = "SELECT * FROM pages p WHERE id = ?";
            List<Long> breadCrumbs = jdbcTemplate.query(breadCrumbsSql, (ResultSet rs, int rowNum) -> {
                return rs.getLong("parent_page_id");
            }, id);
            List<Long> subpages = jdbcTemplate.query(subPageSql, (ResultSet rs, int rowNum) -> {
                return rs.getLong("sub_page_id");
            }, id);
            Page page = jdbcTemplate.queryForObject(getPageSql, (ResultSet rs, int rowNum) -> {
                return new Page(rs.getLong("id"), rs.getString("title"), rs.getString("content"));
            }, id);

            return PageDto.builder()
                    .id(page.getId())
                    .title(page.getTitle())
                    .content(page.getContent())
                    .breadCrumbs(breadCrumbs)
                    .subPages(subpages).build();
        }
        catch (Exception e){
            return null;
        }
    }
}
