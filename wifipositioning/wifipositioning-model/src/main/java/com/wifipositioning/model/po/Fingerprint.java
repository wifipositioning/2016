package com.wifipositioning.model.po;

/**
 * 位置指纹对象
 * <br/>
 * 数据库位置指纹表的映射对象
 * 
 * @author liuyujie
 *
 */
public class Fingerprint {
	
	private String mac;
	private int rss;
	private float xPos;
	private float yPos;
	
	
	public Fingerprint() {
	}
	
	public Fingerprint(String mac, int rss, float xPos, float yPos){
		this.mac = mac;
		this.rss = rss;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public int getRss() {
		return rss;
	}
	public void setRss(int rss) {
		this.rss = rss;
	}
	public float getxPos() {
		return xPos;
	}
	public void setxPos(float xPos) {
		this.xPos = xPos;
	}
	public float getyPos() {
		return yPos;
	}
	public void setyPos(float yPos) {
		this.yPos = yPos;
	}
	
}
