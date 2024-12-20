package com.syf.blog1.dao;

import com.syf.blog1.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//public interface TypeRepository extends JpaRepository<Type, Long> {
//    @Query("select t from Type t")
//    List<Type> findTop(Pageable pageable);
//}
public interface TypeRepository extends JpaRepository<Type, Long> {
    @Query("select t from Type t left join t.blogs b group by t.id order by count(b) desc")
    List<Type> findTop(Pageable pageable);
}
