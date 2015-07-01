package com.zbq.beads;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.Calendar;

public class Util {


    public static Point screenPoint = null;

	public Util() {
	}

    public static String getVersionName(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageInfo("", 0);
			return packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "nuknow";
		}
	}



	public static int getScreenBrightness(Context context) {
		int nowBrightnessValue = 0;

		try {
			nowBrightnessValue = Settings.System.getInt(
					context.getContentResolver(),
					Settings.System.SCREEN_BRIGHTNESS);
		} catch (Exception e) {
			e.printStackTrace();

			nowBrightnessValue = 200;
		}

		return nowBrightnessValue;
	}


	
	public static int getWidthPixels(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		return dm.widthPixels;
	}

	public static int getHeightPixels(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();

		return dm.heightPixels;
	}

    public static Point getScreenPoint(Context paramContext) {
        if(screenPoint==null){
            screenPoint = new Point(paramContext.getResources().getDisplayMetrics().widthPixels,paramContext.getResources().getDisplayMetrics().heightPixels);
        }
        return screenPoint;
    }

    public static float dip2px(Context context, float dpValue) {
        return TypedValue.applyDimension(1, dpValue, context.getResources()
                .getDisplayMetrics());
    }

	public static int getMinWidth(Context context) {
		return Math.min(getWidthPixels(context), getHeightPixels(context));
	}

	
	public static boolean isOrientationLandscape(Context context) {
		return getWidthPixels(context) > getHeightPixels(context);
	}

	
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);

			byte[] md = mdTemp.digest();

			int j = md.length;
			char str[] = new char[j * 2];

			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}

			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	
	public static void putStringToDefaultPreferences(Context context,
			String key, String value) {
		PreferenceManager.getDefaultSharedPreferences(context).edit()
				.putString(key, value).commit();
	}

	public static void putIntToDefaultPreferences(Context context, String key,
			int value) {
		PreferenceManager.getDefaultSharedPreferences(context).edit()
				.putInt(key, value).commit();
	}

	public static void putBooleanToDefaultPreferences(Context context,
			String key, Boolean value) {
		PreferenceManager.getDefaultSharedPreferences(context).edit()
				.putBoolean(key, value).commit();
	}

	public static String getStringFromDefaultPreferences(Context context,
			String key, String defValue) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getString(key, defValue);
	}

	public static int getIntFromDefaultPreferences(Context context, String key,
			int defValue) {
		return PreferenceManager.getDefaultSharedPreferences(context).getInt(
                key, defValue);
	}

	public static Boolean getBooleanFromDefaultPreferences(Context context,
			String key, Boolean defValue) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(key, defValue);
	}

    public static void RunOnUIThread(Runnable runnable) {
        RunOnUIThread(runnable, 0);
    }

    public static void RunOnUIThread(Runnable runnable, long delay) {
        if(BeadsApplication.applicationHandler==null){
            return;
        }
        if (delay == 0) {
            BeadsApplication.applicationHandler.post(runnable);
        } else {
            BeadsApplication.applicationHandler.postDelayed(runnable, delay);
        }
    }

    public static void CancelRunOnUIThread(Runnable runnable) {
        BeadsApplication.applicationHandler.removeCallbacks(runnable);
    }

    /**
     * google maps的脚本里代码
     */
    private static double EARTH_RADIUS = 6378.137;
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     */
    public static double GetDistance(double lat1, double lng1, double lat2, double lng2)
    {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    public static boolean isToday(long time) {
        final Calendar current = Calendar.getInstance();
        final Calendar today = Calendar.getInstance();
        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        current.setTimeInMillis(time);
        if (current.after(today)) {
            return true;
        } else {
            return false;
        }
    }

    //获取手机状态栏高度
    public static int getStatusBarHeight(Context context){
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }
}
