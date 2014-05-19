package com.quinn.sortlistview;

public class SortModel {
	private String name; //显示的数据
	private String sortLetters; //显示的数据拼音首字母

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSortLetters() {
		return sortLetters;
	}

	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}

	@Override
	public String toString() {
		return "SortModel [name=" + name + ", sortLetters=" + sortLetters + "]";
	}
}
