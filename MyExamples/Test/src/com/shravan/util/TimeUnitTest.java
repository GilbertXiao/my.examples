package com.shravan.util;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class TimeUnitTest {

	protected Object getHome()

	{
		Object cachedHome = null;
		boolean cacheHome = true;
		boolean lookupHomeOnStartup = false;

		if ((!cacheHome) || ((lookupHomeOnStartup) && (!isHomeRefreshable()))) {
			System.out.println("Condition met");
			return (cachedHome != null) ? cachedHome : lookup();
		}

		synchronized (TimeUnitTest.class) {
			if (cachedHome == null) {
				cachedHome = lookup();
				getCreateMethod(cachedHome);

			}
			return cachedHome;
		}
	}

	private Object getCreateMethod(Object cachedHome) {
		return new Object();
	}

	private Object lookup() {
		return new Object();
	}

	protected boolean isHomeRefreshable() {
		return false;
	}

	public static void main(String[] args) {
		new TimeUnitTest().getHome();
	}

	public static void main_1(String[] args) {
		long currentTimeInMilliseconds = 1379357549899l;
		long startTimeInMilliseconds = 1379319748770l;
		long timeoutInterval = 600000l;
		long res = currentTimeInMilliseconds - startTimeInMilliseconds;
		System.out.println("currentTimeInMilliseconds:"
				+ logTime(currentTimeInMilliseconds));
		System.out.println("startTimeInMilliseconds:"
				+ logTime(startTimeInMilliseconds));
		System.out.println("timeoutInterval:" + logTime(timeoutInterval));

		System.out.println("Result: " + logTime(res));

		// System.out.println("currentTimeInMilliseconds:"+DurationFormatUtils.formatDuration(currentTimeInMilliseconds,"y:M:d:H:m:s:S"));
		// System.out.println("startTimeInMilliseconds:"+DurationFormatUtils.formatDurationHMS(startTimeInMilliseconds));
		// System.out.println("timeoutInterval:"+DurationFormatUtils.formatDurationHMS(timeoutInterval));

	}

	private static String logTime(long currentTimeInMilliseconds) {
		return String.format("%d min",
				MILLISECONDS.toMinutes(currentTimeInMilliseconds));
	}
}
