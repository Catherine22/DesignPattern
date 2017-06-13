package com.catherine.filter.criteria;

import java.util.List;

import com.catherine.filter.Person;

/**
 * 自定义过滤条件来过滤一群物件，自定义过滤器的接口让每个过滤的类别各自实现，过滤器模式着眼于不同过滤条件的交集、并集或其它逻辑运算。
 * 
 * @author Catherine
 *
 */
public interface Criteria {
	public List<Person> meetCriteria(List<Person> persons);
}
