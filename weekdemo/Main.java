package com.michael.weekdemo;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		int currentYear = 2019;
		List<Week> weeks = WeekHelper.getWeeksByYear(currentYear);
		System.out.println("��ǰѡ������:" + currentYear);
		for(Week week:weeks){
			System.out.println(week.toString());
		}
	}

}
