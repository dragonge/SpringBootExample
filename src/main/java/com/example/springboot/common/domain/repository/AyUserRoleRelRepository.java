package com.example.springboot.common.domain.repository;

import com.example.springboot.common.domain.entity.AyUserRoleRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述：用户角色关联Repository
 * @author Ay
 * @date   2017/10/14.
 */
public interface AyUserRoleRelRepository extends JpaRepository<AyUserRoleRel,String> {

    List<AyUserRoleRel> findByUserId(@Param("userId")String userID);
}

