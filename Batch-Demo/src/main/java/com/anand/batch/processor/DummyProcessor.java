package com.anand.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.anand.batch.bean.DummyItem;

public class DummyProcessor implements ItemProcessor<DummyItem, DummyItem> {

	@Override
	public DummyItem process(DummyItem item) throws Exception {
		return item;
	}

}
