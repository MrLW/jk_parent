package jk_web.fastjson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 测试FastJson解析null值的问题
 *  QuoteFieldNames———-输出key时是否使用双引号,默认为true 
	WriteMapNullValue——–是否输出值为null的字段,默认为false 
	WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null 
	WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null 
	WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null 
	WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
 * @author lw
 */
public class FastJsonTest {

	public static void main(String[] args) {
		// 1、map集合
		Map<String, Object> map = new HashMap<>();
		map.put("a", "李");
		map.put("b", null);
		map.put("c", "");
		map.put(null, "");
		map.put(null, null);
		// WriteMapNullValue——–是否输出值为null的字段,默认为false
		// {null:null,"a":"李","b":null,"c":""}
		// String json =
		// JSON.toJSONString(map,SerializerFeature.WriteMapNullValue);
		String json = JSON.toJSONString(map, SerializerFeature.WriteNullNumberAsZero);
		System.out.println(json);
		
		// 2、List集合
		List<User> list = new ArrayList<>(); 
		User u1 = new User("1", null, "22");
		User u2 = new User("1", "", "22");
		User u3 = new User("1", "cc", "22");
		list.add(u1);
		list.add(u2);
		list.add(u3);
		String json2 = JSON.toJSONString(list,SerializerFeature.WriteMapNullValue);
		System.out.println(json2);
	}
}
