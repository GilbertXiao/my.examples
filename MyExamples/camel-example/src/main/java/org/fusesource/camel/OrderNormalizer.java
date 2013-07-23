package org.fusesource.camel;

import java.util.List;

public class OrderNormalizer {
	public Order fromCsvToOrder(List<List<String>> body) {
		System.out.println("OrderNormalizer:fromCsvToOrder");
		List<String> orderHeaders = body.get(0);
		List<String> orderValues = body.get(1);
		Order ord = new Order(orderValues.get(0), Integer.parseInt(orderValues
				.get(1)));
		System.out.println("After Normalizer:" + ord.toString());
		return ord;
	}
}
