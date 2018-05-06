package com.pangpang.jei.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/** 
* @author  : lijingwei
* @version ：2017年11月15日 下午6:59:04 
*/

@Entity
@Table(name = "male")
public class MaleEntity extends PersonEntity implements Serializable {

	private static final long serialVersionUID = 5059638007112417865L;

	@Column(name="male")
	private String male;

	@Override
	public String toString() {
		String person = super.toString();
		return person + " MaleEntity [male=" + male + "]";
	}

	public String getMale() {
		return male;
	}

	public void setMale(String male) {
		this.male = male;
	}
	
}
