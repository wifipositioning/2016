package com.wifipositioning.utils.db.parser.impl;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wifipositioning.utils.db.parser.AbstractDataSourceParser;
import com.wifipositioning.utils.type.db.DbType;
import com.wifipositioning.utils.type.source.SourceType;

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

	private static final String INITIAL_POOL_SIZE = "initialpoolsize";
	private static final String MIN_POOL_SIZE = "minpoolsize";
	private static final String MAX_POOL_SIZE = "maxpoolsize";
	
	private int initialpoolsize = 5;
	private int minPoolSize = 1;
	private int maxPoolSize = 10;
	
	public C3P0DataSourceParser(){
		this(DbType.MYSQL);
	}
	
	public C3P0DataSourceParser(DbType dbType){
		
		this.dbType = dbType;
		this.sourceType = SourceType.C3P0;
		this.flagTag = sourceType.getTypeText() + "/" + dbType.getTypeText();
		
		try {
			initDataSource();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public List<Element> parseDataSourceCfg() throws DocumentException{
		InputStream dataSourceStream = C3P0DataSourceParser.class.getResourceAsStream(DATA_SOURCE_FILE_NAME);
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(dataSourceStream);
		Element dataSourceELement = (Element)document.selectSingleNode(ROOT_TAG + "/" + flagTag);
		System.out.println(flagTag);
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
	
	public static void main(String[] args) {
		C3P0DataSourceParser c3p0 = new C3P0DataSourceParser(DbType.ORACLE);
		System.out.println(c3p0.getUrl());
	}
}
