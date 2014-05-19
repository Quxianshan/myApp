package com.quinn.sortlistview;

import java.util.Comparator;

public class PinyinComparator implements Comparator<SortModel>{

	@Override
	public int compare(SortModel lhs, SortModel rhs) {
		// TODO Auto-generated method stub
		if (lhs.getSortLetters().equals("@")
				|| rhs.getSortLetters().equals("#")) {
			return -1;
		} else if (lhs.getSortLetters().equals("#")
				|| rhs.getSortLetters().equals("@")) {
			return 1;
		} else {
			return lhs.getSortLetters().compareTo(rhs.getSortLetters());
		}
	}

}
