package json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import pojo.Person;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MrLi on 2022/01/19/15:38
 */
public class JsonText {
	//（1）javaBean 和json的转换
	@Test
	public void test1() {
		Person person = new Person("Tony", 23);
		Gson gson = new Gson();

		String personGson = gson.toJson(person);
		System.out.println(personGson);

		Person person1 = gson.fromJson(personGson, Person.class);
		System.out.println(person1);
	}

	//（2）List 和json的转换
	@Test
	public void test2() {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("Tony", 23));
		personList.add(new Person("Tom", 32));
		personList.add(new Person("Ami", 12));
		personList.add(new Person("Lily", 15));

		Gson gson = new Gson();
		String listJson = gson.toJson(personList);
		System.out.println(listJson);

		List<Person> personList1 = gson.fromJson(listJson, new PersonListType().getType());
		System.out.println(personList1);
	}

	//（3）Map 和json的转换
	@Test
	public void test3() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("person1", new Person("Tony", 12));
		map.put("person2", new Person("Tom", 12));
		map.put("person3", new Person("Smith", 12));
		map.put("person4", new Person("Lily", 12));

		Gson gson = new Gson();
		String mapJson = gson.toJson(map);
		System.out.println(mapJson);

		Map<String, Object> map1 = gson.fromJson(mapJson, new PersonMapType().getType());
		//匿名内部类的形式
		Map<String, Object> map2 = gson.fromJson(mapJson, new TypeToken<Map<String, Object>>(){}.getType());

		System.out.println(map1);
		System.out.println(map2);
	}
}
