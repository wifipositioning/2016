package com.wifipositioning.utils.db.parser;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
 * 数据源解析器
 * <br/>
 * 将数据源配置文件中的数据源配置项解析出来
 * 
 * @author liuyujie
 *
 */
public class DataSourceParser {
	
	private static final String DATA_SOURCE_FILE_NAME = "/datasource/datasource.xml"; 
	private static final String ROOT_TAG = "datasource";
	
	private static final String DRIVER = "driver";
	private static final String URL = "url";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String INITIAL_POOL_SIZE = "initialpoolsize";
	private static final String MIN_POOL_SIZE = "minpoolsize";
	private static final String MAX_POOL_SIZE = "maxpoolsize";
	
	private String driver = null;
	private String url = null;
	private String username = null;
	private String password = null;
	private int initialpoolsize = 5;
	private int minPoolSize = 1;
	private int maxPoolSize = 10;
	
	private static DataSourceParser dataSourceParser = null;
	
	private DataSourceParser(){
		try {
			initDataSource();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized DataSourceParser getDataSourceParser(){
		if(dataSourceParser == null){
			dataSourceParser = new DataSourceParser();
		}
		return dataSourceParser;
	}
	
	private void initDataSource() throws DocumentException{
		List<Element> dataSourceCfg = parseDataSourceCfg();
		setDataSourceCfg(dataSourceCfg);
	}
	
	/**
	 * 解析数据源配置文件
	 * 
	 * @return
	 * @throws DocumentException
	 */
	private List<Element> parseDataSourceCfg() throws DocumentException{
		InputStream dataSourceStream = DataSourceParser.class.getResourceAsStream(DATA_SOURCE_FILE_NAME);
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(dataSourceStream);
		Element dataSourceELement = (Element)document.selectSingleNode(ROOT_TAG);
		@SuppressWarnings("unchecked")
		List<Element> dataSourceCfg = dataSourceELement.elements();
		return dataSourceCfg;
	}
	
	/**
	 * 将解析出的数据源配置赋值给解析器的对应属性
	 * 
	 * @param dataSourceCfg
	 */
	private void setDataSourceCfg(List<Element> dataSourceCfg){
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
	
	/**
	 * 重新加载数据源配置
	 * 
	 * @throws DocumentException
	 */
//	public void reloadDataSource() throws DocumentException{
//		initDataSource();
//	}
	
	public String getDriver() {
		return driver;
	}
	public String getUrl() {
		return url;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
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
	
}
