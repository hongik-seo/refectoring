package com.freeboard.freeboard_jpa.repository;

import com.freeboard.freeboard_jpa.domain.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface Repository extends JpaRepository<Entity,Long> {

    @Query(value = "SELECT num.nextval FROM dual", nativeQuery = true)
    public Long getNextValMySequence();


    @Query(value="SELECT * FROM freeboard f WHERE f.title LIKE %?1%",
            countQuery = "SELECT COUNT(f.num) FROM freeboard f WHERE f.title LIKE %?1%",
            nativeQuery=true
    )
    public Page<Entity> searchByTitleLike(@Param(value = "keyword") String keyword,Pageable pageable);


    @Query(value="SELECT * FROM freeboard f WHERE f.num LIKE %?1%",
            countQuery = "SELECT COUNT(f.num) FROM freeboard f WHERE f.num LIKE %?1%",
            nativeQuery=true
    )
    public Page<Entity> searchByNumLike(@Param(value = "keyword") String keyword,Pageable pageable);

}
