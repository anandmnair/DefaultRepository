package com.anand.batch.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.anand.batch.bean.DummyItem;

public class DummyReader implements ItemReader<DummyItem> {

	private DummyItem[] items = new DummyItem[]{ new DummyItem(1,"item1"), new DummyItem(2,"item2"), new DummyItem(3,"item3")};
	
	private int counter;
	
	@Override
	public DummyItem read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return counter<items.length?items[counter++]:null;
	}

}
