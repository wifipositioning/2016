package com.wifipositioning.model.msg;

public class OutboundPositioingInfo {
	private float xPos;
	private float yPos;
	
	public OutboundPositioingInfo() {
		this(0);
	}
	
	public OutboundPositioingInfo(int xPos){
		this(xPos, 0);
	}
	
	public OutboundPositioingInfo(int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;
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
