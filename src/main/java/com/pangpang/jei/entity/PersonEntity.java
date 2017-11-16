package com.pangpang.jei.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/** 
* @author  : lijingwei
* @version ：2017年11月15日 下午6:50:33 
*/

@Entity
@Table(name="person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PersonEntity implements Serializable{
	
	private static final long serialVersionUID = -1260543643198573086L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "sex")
	private String sex;

	@Override
	public String toString() {
		return "PersonEntity [id=" + id + ", name=" + name + ", sex=" + sex + "]";
	}

}
