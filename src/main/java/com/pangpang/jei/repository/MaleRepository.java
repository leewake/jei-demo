package com.pangpang.jei.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pangpang.jei.entity.MaleEntity;

/** 
* @author  : lijingwei
* @version ：2017年11月15日 下午7:04:57 
*/

@Repository
public interface MaleRepository extends JpaRepository<MaleEntity, Long>, JpaSpecificationExecutor<MaleEntity> {

}
