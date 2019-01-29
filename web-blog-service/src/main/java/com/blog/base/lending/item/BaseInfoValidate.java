package com.blog.base.lending.item;

public interface BaseInfoValidate<E> {

	E findPreviousResult(Integer platformId, Integer channelId, String processNodeName,Integer custmerNo);
}
