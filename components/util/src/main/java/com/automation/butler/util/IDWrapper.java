package com.automation.butler.util;

import java.util.Iterator;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IDWrapper implements Iterable<String> {

	List<String> list;

	@Override
	public Iterator<String> iterator() {
		return list.iterator();
	}

}
