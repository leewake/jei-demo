package com.pangpang.jei.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pangpang.jei.entity.PersonEntity;

/** 
* @author  : lijingwei
* @version ：2017年11月15日 下午7:03:50 
*/

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long>, JpaSpecificationExecutor<PersonEntity> {


}
