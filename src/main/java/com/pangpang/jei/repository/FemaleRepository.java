package com.pangpang.jei.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pangpang.jei.entity.FemaleEntity;

/** 
* @author  : lijingwei
* @version ：2017年11月16日 上午11:19:12 
*/

@Repository
public interface FemaleRepository extends JpaRepository<FemaleEntity, Long>, JpaSpecificationExecutor<FemaleEntity>{

}
