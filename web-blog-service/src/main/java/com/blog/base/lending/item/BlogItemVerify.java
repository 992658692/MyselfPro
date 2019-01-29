package com.blog.base.lending.item;

import com.blog.core.DataItemResult;

public class BlogItemVerify implements DataItem<String>, BaseInfoValidate<DataItemResult>{

	@Override
	public String getCode() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getDomain() {
		return null;
	}

	@Override
	public String execute(Object... params) {
		return null;
	}

	@Override
	public DataItemResult findPreviousResult(Integer platformId, Integer channelId, String processNodeName,
			Integer custmerNo) {
		return null;
	}

}
