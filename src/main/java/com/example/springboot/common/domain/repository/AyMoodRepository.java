package com.example.springboot.common.domain.repository;

import com.example.springboot.common.domain.entity.AyMood;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述：说说repository
 * @author Ay
 * @date   2017/12/02
 */
public interface AyMoodRepository extends JpaRepository<AyMood,String> {

}
