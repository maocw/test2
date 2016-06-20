package com.maocw.QRcode;

public final class GPSutil {
		   
	/** 
	* 地球半径：6378.137KM 
	*/ 
	private static double EARTH_RADIUS = 6378.137;  

	private static double rad(double d) {  
	      return d * Math.PI / 180.0;  
	}  
    /** 
     * 得到两点间的距离 米 
     *  
     * @param lat1 
     * @param lng1 
     * @param lat2 
     * @param lng2 
     * @return 
     */ 
    public static double getDistanceOfMeter(double lat1, double lng1,  
            double lat2, double lng2) {  
        double radLat1 = rad(lat1);  
        double radLat2 = rad(lat2);  
        double a = radLat1 - radLat2;  
        double b = rad(lng1) - rad(lng2);  
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)  
                + Math.cos(radLat1) * Math.cos(radLat2)  
                * Math.pow(Math.sin(b / 2), 2)));  
        distance = distance * EARTH_RADIUS;  
        distance = Math.round(distance * 10000) / 10;  
        return distance;  
    }  
}
