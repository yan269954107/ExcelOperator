package com.yanxinwei.exceloperator.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GsonUtil {

	private static final Gson sGson = new GsonBuilder().registerTypeAdapter(Date.class,
            new JsonDeserializer<Date>() {
        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            String dateString = json.getAsString().replace("Z", "+0000");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            return format.parse(dateString, new ParsePosition(0));
        }
    }).create();

    public static <T> T jsonToBean(String jsonStr, Class<T> clazz){
        if (sGson != null){
        	return sGson.fromJson(jsonStr, clazz);
        }
        return null;
    }
    
    public static <T> T jsonToBean(String jsonStr, Type type){
        if (sGson != null){
        	return sGson.fromJson(jsonStr, type);
        }
        return null;
    }
    
    public static String toJson(Object bean) {
    	return sGson.toJson(bean);
    }
	
}
