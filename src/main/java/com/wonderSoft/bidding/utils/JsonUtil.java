package com.wonderSoft.bidding.utils;



import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class JsonUtil
{
	private static final Gson googleJson = new Gson();
	

	public static Gson getGoogleJson()
	{
		return googleJson;
	}
	
	/**
	 * 直接获取
	 * 
	 * @param <T>
	 * @param json
	 * @param class1
	 * @return
	 */
	public static <T> Object jsonToObject(String json, Class<T> class1)
	{
		return googleJson.fromJson(json, class1);
	}
	
	/**
	 * 对象转换成json 字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String objectToJson(Object object)
	{
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new MyDateSerializer())
                .create();
		return gson.toJson(object);
	}
	
	
	/**
	 * 将json字符串反序列化为对象,不区分注解
	 * @param jsonStr
	 * @param classOfT
	 * @return
	 */
	public static <T> T fromJson(String jsonStr,Type type){
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new MyDateDeserializer())
		                             .create();
		return (T)gson.fromJson(jsonStr, type);
	}
	

	/**
	 * date类型序列化器
	 * @author jiweibin
	 *
	 */
	private static class MyDateSerializer implements JsonSerializer<Date> {

		@Override
		public JsonElement serialize(Date src, Type typeOfSrc,
									 JsonSerializationContext context) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return new JsonPrimitive(sdf.format(src));
		}
		
	}
	
	/**
	 * date类型反序列化器
	 * @author jiweibin
	 *
	 */
	private static class MyDateDeserializer implements JsonDeserializer<Date>{
	
		@Override
		public Date deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(json.getAsString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}
	
	public static String getValueFromJson(String json,String key)
	{
		String value = null;
		JsonParser parser = new JsonParser();
		JsonElement jsonEl = parser.parse(json);
		JsonObject jsonObj = null;
		jsonObj = jsonEl.getAsJsonObject();
		JsonElement valueTemp= jsonObj.get(key);
		if(valueTemp.isJsonNull())
		{
			value = "";
		}
		else
		{
			
			value = valueTemp.getAsString();
		}
		return value;
	}
	

	// 从json中获得列表
	public static String getValueFromJsonList(String json, String key)
	{

		String value = null;
		JsonParser parser = new JsonParser();
		JsonElement jsonEl = parser.parse(json);
		JsonObject jsonObj = null;
		jsonObj = jsonEl.getAsJsonObject();
		JsonElement valueTemp= jsonObj.get(key);
		if(valueTemp.isJsonNull())
		{
			value = "";
		}
		else
		{
			
			value = valueTemp.getAsJsonArray().toString();
		}
		return value;
	}
	
}
