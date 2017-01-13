package com.new18.core.util;

import org.apache.log4j.Logger;

import java.util.Map;

/**
 *
 * @Description: 处理Map 工具类
 * @version 1.0
 * 
 * 
 * <br/>修改map2Bean方法，提升处理性能
 * @version 2.0
 */
public class MapUtil {
	@SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(JsonUtil.class);


    /**
     * 
     * Description: 
     * <br/>将Map中的值转换为Bean对象(通过map中的key对应bean对象中的属性)
     * @param map 键值对，会根据键值到obj中匹配
     * @param obj 需要转换的对象
     * @return
     */
    public static Object map2Bean(Map<?, ?> map, Object obj) {
        if(map == null){
            return null;
        }

        try {
            org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return obj;


        /*BeanMap beanMap = BeanUtils.getBeanMap(obj);

        Iterator<?> iter = map.keySet().iterator();
        Map<String , Object> valueMap = new HashMap<String , Object>();
        String key = null;
        Class<?> cla;
        while( iter.hasNext() ){
            key = iter.next().toString();
            if( beanMap.containsKey(key) ){
                cla = beanMap.getPropertyType(key);
                if(map.get(key) != null){
                    valueMap.put(key, ConvertorHelper.convert(map.get(key), cla) );
                }
            }
        }
        beanMap.putAll(valueMap);
        return obj;*/
    }

}  

