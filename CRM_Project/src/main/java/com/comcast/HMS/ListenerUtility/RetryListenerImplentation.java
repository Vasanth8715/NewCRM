package com.comcast.HMS.ListenerUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListenerImplentation implements IRetryAnalyzer
{
	int count = 0;
	int limitCount = 5;
	public boolean retry(ITestResult result)
	{
		if(count<limitCount)
		{
			count++;
			return true;
		}
		return false;
	}
}
