package com.anand.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.anand.batch.bean.DummyItem;

public class DummyWriter implements ItemWriter<DummyItem> {

	@Override
	public void write(List<? extends DummyItem> items) throws Exception {
		System.out.println(items);
	}

}
