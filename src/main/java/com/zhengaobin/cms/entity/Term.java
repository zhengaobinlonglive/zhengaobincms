package com.zhengaobin.cms.entity;

/**
 * @author 郑奥斌
 *
 * 2019年10月23日
 */
public class Term {
private static final long serialVersionUID = -5879066127875281505L;
	
	Integer id;
	String display_name;
	String unique_name;
	
	
	public Term(String display_name) {
		super();
		this.display_name = display_name;
	}
	
	public Term() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTagname() {
		return display_name;
	}
	public void setTagname(String display_name) {
		this.display_name = display_name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((display_name == null) ? 0 : display_name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Term other = (Term) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (display_name == null) {
			if (other.display_name != null)
				return false;
		} else if (!display_name.equals(other.display_name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Tag [id=" + id + ", display_name=" + display_name + "]";
	}
}
