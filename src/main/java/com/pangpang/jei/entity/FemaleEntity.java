package com.pangpang.jei.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/** 
* @author  : lijingwei
* @version ：2017年11月16日 上午10:06:03 
*/

@Entity
@Table(name = "female")
public class FemaleEntity extends PersonEntity implements Serializable {

	private static final long serialVersionUID = 5059638007112417865L;

	@Column(name="female")
	private String female;

	@Override
	public String toString() {
		String person = super.toString();
		return person + " FemaleEntity [female=" + female + "]";
	}
	
}