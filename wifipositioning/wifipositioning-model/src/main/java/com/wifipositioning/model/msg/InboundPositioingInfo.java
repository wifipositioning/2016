package com.wifipositioning.model.msg;

import java.util.HashMap;
import java.util.Map;

public class InboundPositioingInfo {

	private Map<String, Integer> rssis = new HashMap<String, Integer>();
	
	public InboundPositioingInfo() {
		
	}

	public Map<String, Integer> getRssis() {
		return rssis;
	}

	public void setRssis(Map<String, Integer> rssis) {
		this.rssis = rssis;
	}

}
