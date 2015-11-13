package com.wifipositioning.utils.db.parser.impl;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wifipositioning.utils.db.parser.AbstractDataSourceParser;

/**
 * 
 * C3P0数据源解析器
 * <br/>
 * 将数据源配置文件中的数据源配置项解析出来
 * 
 * @author liuyujie
 *
 */
public class C3P0DataSourceParser extends AbstractDataSourceParser {
	
	/**
	 * 数据源类型标识
	 */
	private static final String FLAG_TAG = "c3p0";

	private static final String INITIAL_POOL_SIZE = "initialpoolsize";
	private static final String MIN_POOL_SIZE = "minpoolsize";
	private static final String MAX_POOL_SIZE = "maxpoolsize";
	
	private int initialpoolsize = 5;
	private int minPoolSize = 1;
	private int maxPoolSize = 10;
	
	private static C3P0DataSourceParser dataSourceParser = null;
	
	private C3P0DataSourceParser(){
		try {
			initDataSource();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized C3P0DataSourceParser getDataSourceParser(){
		if(dataSourceParser == null){
			dataSourceParser = new C3P0DataSourceParser();
		}
		return dataSourceParser;
	}
	
	public List<Element> parseDataSourceCfg() throws DocumentException{
		InputStream dataSourceStream = C3P0DataSourceParser.class.getResourceAsStream(DATA_SOURCE_FILE_NAME);
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(dataSourceStream);
		Element dataSourceELement = (Element)document.selectSingleNode(ROOT_TAG + "/" + FLAG_TAG);
		@SuppressWarnings("unchecked")
		List<Element> dataSourceCfg = dataSourceELement.elements();
		return dataSourceCfg;
	}
	
	public void setDataSourceCfg(List<Element> dataSourceCfg){
		for(Element element : dataSourceCfg){
			
			String tagName = element.getName();
			String textTrim = element.getTextTrim().equals("")? null: element.getTextTrim();
			
			switch (tagName) {
			case DRIVER:
				driver = textTrim;
				break;
			case URL:
				url = textTrim;
				break;
			case USERNAME:
				username = textTrim;
				break;
			case PASSWORD:
				// 原则上密码需要解密处理(MD5+Salt)				
				password = textTrim;
				break;
			case INITIAL_POOL_SIZE:
				initialpoolsize = Integer.parseInt(textTrim);
				break;
			case MIN_POOL_SIZE:
				minPoolSize = Integer.parseInt(textTrim);
				break;
			case MAX_POOL_SIZE:
				maxPoolSize = Integer.parseInt(textTrim);
				break;
			default:
				break;
			}
		}
	}
	
	public int getInitialPoolSize() {
		return initialpoolsize;
	}
	public int getMinPoolSize() {
		return minPoolSize;
	}
	public int getMaxPoolSize() {
		return maxPoolSize;
	}
	
//	public static void main(String[] args) {
//		C3P0DataSourceParser c3p0 = (C3P0DataSourceParser) C3P0DataSourceParser.getDataSourceParser();
//		System.out.println(c3p0.getUrl());
//	}
}
