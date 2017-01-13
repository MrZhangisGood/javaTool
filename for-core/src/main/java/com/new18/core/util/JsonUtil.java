package com.new18.core.util;

import com.fasterxml.jackson.module.hibernate.HibernateModule;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: JSON 数据转换
 * @author zhanglm@joyplus.com.cn 
 * @date 2015年3月12日 上午11:16:52   
 * @version 1.0
 */
public class JsonUtil {

    private static final Logger logger = Logger.getLogger(JsonUtil.class);
    
    /**
     * 默认日期格式
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    
    /**
     * 利用json字符串转换并填充实体对象
     * @param jsonString json字符串
     * @param pojoClass 转换目标对象类型
     * @return object 返回转换后目标对象
     */
    @SuppressWarnings("deprecation")
    public static Object json2Object(String jsonString, Class<? extends Object> pojoClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        objectMapper.getDeserializationConfig().setDateFormat(df);
        //objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

        if (VTool.isEmpty(jsonString)){
            return null;
        }
        try {
            Object pojoValue  = objectMapper.readValue(jsonString, pojoClass);
            return pojoValue;
        } catch (JsonParseException e) {
            logger.error(e);
        } catch (JsonMappingException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }
    

    /**
     * 对象转JSON
     * @param obj 源对象
     * @return String 目标JSON
     */
    @SuppressWarnings("deprecation")
    public static String object2Json(Object obj){
        JsonGenerator gen = null;
        StringWriter writer = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            DateFormat df = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
            objectMapper.getSerializationConfig().setDateFormat(df);
            //objectMapper.disable(SerializationConfig.Feature.WRITE_NULL_PROPERTIES);
            objectMapper.registerModule(new HibernateModule());
            writer = new StringWriter();
            gen = objectMapper.getJsonFactory().createJsonGenerator(writer);
            gen.writeObject(obj);
            String json = writer.toString();
            return json;
        }catch(Exception e){
            logger.error(e);
        }finally{
            try{
                if(gen != null){
                    gen.flush();
                    gen.close();
                }
                if( writer != null){
                    writer.close();
                }
            }catch(Exception e){
                logger.error(e);
            }
        }
        return null;
    }
    /**
     *
     * @Description: 将json字符串转换为list
     * @param <T>
     * @param jsonString json字符串
     * @param pojoClass list中放的类型，如BaseEntity.class
     * @return
     */
    public static  <T> List<T> json2List(String jsonString, Class<T> pojoClass) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            @SuppressWarnings("unchecked")
            T pojoValue = (T) JSONObject.toBean(jsonObject, pojoClass);
            list.add(pojoValue);
        }
        return list;
    }
}
