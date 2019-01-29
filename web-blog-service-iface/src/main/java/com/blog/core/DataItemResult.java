package com.blog.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataItemResult implements Serializable {

	private static final long serialVersionUID = 2354035479093532980L;

	boolean resultFlag;
	
	String result;
	
	Map<String, ?> extra = new HashMap<String, Object>();

	public boolean isResultFlag() {
		return resultFlag;
	}

	public void setResultFlag(boolean resultFlag) {
		this.resultFlag = resultFlag;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Map<String, ?> getExtra() {
		return extra;
	}

	public void setExtra(Map<String, ?> extra) {
		this.extra = extra;
	}
	
}
