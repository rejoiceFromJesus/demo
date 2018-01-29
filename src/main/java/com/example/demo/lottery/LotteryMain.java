package com.example.demo.lottery;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LotteryMain {

	public static void main(String[] args) {
		
		DayOfWeek[] buyDays = {DayOfWeek.TUESDAY, DayOfWeek.THURSDAY, DayOfWeek.SUNDAY};
		List<DayOfWeek> buyDayList = Arrays.asList(buyDays);
		int totouCount = 30;
		LocalDate startDate = LocalDate.parse("2017-12-31");
		for(int j = 0; j < totouCount;) {
			
			if(buyDayList.contains(startDate.getDayOfWeek())) {
				List<Integer> numbers = new ArrayList<>();
				Random random = new Random();
				for(int i = 0; i < 6;) {
					Integer red = random.nextInt(33)+1;
					if(!numbers.contains(red)) {
						numbers.add(red);
						i++;
					}
				}
				Collections.sort(numbers);
				numbers.add(random.nextInt(16)+1);
				String numberStr = numbers.toString().replace("[", "").replace("]", "");
				System.out.println(startDate.format(DateTimeFormatter.ISO_DATE)+"      "+numberStr);
				j++;
			}
			
			startDate = startDate.plusDays(1);
		}
		}
		
	
	
}
