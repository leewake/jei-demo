package com.pangpang.jei.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
* @author  : lijingwei
* @version ：2017年11月15日 下午6:50:33 
*/

@Entity
@Table(name="person")
public class PersonEntity implements Serializable{
	
	private static final long serialVersionUID = -1260543643198573086L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;

}
