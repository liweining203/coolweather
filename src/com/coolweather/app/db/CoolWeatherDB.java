package com.coolweather.app.db;

import java.util.ArrayList;
import java.util.List;

import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB {

	/**
	 * ���ݿ���
	 */
	public static final String DB_NAME="cool_weather";
	/**
	 * ���ݿ�汾
	 */
	public static final int VERSION=1;
	private static CoolWeatherDB coolWeatherDB;
	private SQLiteDatabase db;
	/**
	 * �����췽��˽�л�
	 */
	private CoolWeatherDB (Context context) {
		CoolWeatherOpenHelper dbHelper=new CoolWeatherOpenHelper(context, 
				DB_NAME, null, VERSION);
		db=dbHelper.getWritableDatabase();
		}
	/**
	 * ��ȡcoolWeatherDBʵ��	
	 */
	public synchronized static CoolWeatherDB getInstance(Context context){
		if (coolWeatherDB!=null) {
			coolWeatherDB=new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}
	/**
	 * ��provinceʵ���洢�����ݿ�	
	 */

	public void saveProvince(Province province){
		if (province!=null) {
			ContentValues values=new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("Province", null, values);
		}
	}
	/**
	 * �����ݿ��ȡȫ������ʡ����Ϣ
	 */
	public List<Province> loadProvince(){
		List<Province> listPro=new ArrayList<Province>();
		Cursor cursor=db.query("Province", 
				null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Province province=new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(
						cursor.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(
						cursor.getColumnIndex("province_code")));
				listPro.add(province);
			} while (cursor.moveToNext());	
		}
		return listPro;
		
	}
	/**
	 * ��Cityʵ���洢�����ݿ�
	 */
	public void saveCity(City city){
		if (city!=null) {
			ContentValues values=new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("province_id", city.getProviceId());
			db.insert("City", null, values);
		}
	}
	
	/**
	 * �����ݿ��ȡȫ�����г�����Ϣ
	 */
	public List<City> loadCites(int proviceId){
		List<City> listCity=new ArrayList<City>();
		Cursor cursor=db.query("City", null, "province_id=?", 
				new String[]{String.valueOf(proviceId)}, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				City city=new City();
				city.setCityId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(
						cursor.getColumnIndex("city_name")));
				city.setCityCode(cursor.getString(
						cursor.getColumnIndex("city_code")));
				city.setProviceId(proviceId);
				listCity.add(city);
			} while (cursor.moveToNext());	
		}
		return listCity;
		
	}
	/**
	 * ��County��Ϣ�洢�����ݿ�
	 */
	public void saveCounty(County county){
		if (county!=null) {
			ContentValues values=new ContentValues();
			values.put("county_name", county.getCountyName());
			values.put("county_code", county.getCountyCode());
			values.put("city_id", county.getCityId());
			db.insert("County", null, values);
		}
	}
	
	/**
	 * �����ݿ��ȡĳ�����µ�������Ϣ
	 */
	public List<County> loadCounties(int cityId){
		List<County> listCounty=new ArrayList<County>();
		Cursor cursor=db.query("County", null, "city_id=?", 
				new String[]{String.valueOf(cityId)}, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				County county=new County();
				county.setCountyId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyName(cursor.getString(
						cursor.getColumnIndex("county_name")));
				county.setCountyCode(cursor.getString(
						cursor.getColumnIndex("county_code")));
				county.setCityId(cityId);
				listCounty.add(county);
			} while (cursor.moveToNext());	
		}
		return listCounty;
		
	}
	
}
