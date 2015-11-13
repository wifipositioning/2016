package com.wifipositioning.utils.db.parser;

import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * 
 * 数据源解析接口
 * 
 * @author liuyujie
 *
 */
public interface IDataSourceParser {
	
//	/**
//	 * 生成解析器实例对象
//	 * 
//	 * @return 数据源解析器对象
//	 */
//	public static Object getDataSourceParser(){
//		return null;
//	}

	/**
	 * 解析数据源配置文件
	 * 
	 * @return 列表形式存储的配置文件中解析出的配置<code>Element</code>
	 * @throws DocumentException
	 */
	public List<Element> parseDataSourceCfg() throws DocumentException;
	
	/**
	 * 配置数据源
	 * 
	 * @param dataSourceCfg 配置项
	 */
	public void setDataSourceCfg(List<Element> dataSourceCfg);
}
