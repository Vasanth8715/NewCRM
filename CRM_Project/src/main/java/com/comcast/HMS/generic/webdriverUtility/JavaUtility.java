package com.comcast.HMS.generic.webdriverUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility 
{
	public int getRandomNumber()
	{
		Random random = new Random();
		int randomNumber = random.nextInt(10000);
		
		return randomNumber;
	}
	
	public String getSystemDateYYYYDDMM()
	{
		Date dateObj = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateObj);
		
		return date;
	}
	
	public String getRequiredDateYYYYDDMM(int days)
	{	
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = sim.getCalendar();
	    cal.add(Calendar.DAY_OF_MONTH,days);
	    String reqDate = sim.format(cal.getTime());
		
	    return reqDate;
	    
	}
}
