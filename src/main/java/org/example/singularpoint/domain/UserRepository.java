package org.example.singularpoint.domain;

import org.example.singularpoint.infra.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDO, Long> {

    // NOTE 需要根据JPA的规范来写方法名
    List<UserDO> findByNameContaining(String name);
    List<UserDO> findByBirthDayBetween(LocalDate start, LocalDate end);

    // NOTE JPQL语法
    @Query("select u from UserDO u where u.name like %?1%")
    List<UserDO> findByNameLike_JPQL_1(String name);
    @Query("select u from UserDO u where u.name like %:name%")
    List<UserDO> findByNameLike_JPQL_2(String name);
    @Modifying
    @Transactional
    @Query("delete from UserDO u where u.name = :name")
    int deleteByName_JPQL(String name);

    // NOTE SQL语法
    @Query(value = "select * from user where name like %?1%", nativeQuery = true)
    List<UserDO> findByNameLike_SQL_1(String name);
    @Query(value = "select * from user where name like %:name%", nativeQuery = true)
    List<UserDO> findByNameLike_SQL_2(String name);
    @Modifying
    @Transactional
    @Query(value = "delete from user where name = :name", nativeQuery = true)
    int deleteByName_SQL(String name);

}
