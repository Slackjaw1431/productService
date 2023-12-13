package com.shopforhome.productService.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.math.BigDecimal;

public class ProductDeserializer extends StdDeserializer<Product> {

	public ProductDeserializer() {
		this(null);
	}

	public ProductDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Product deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		JsonNode node = jp.getCodec().readTree(jp);
		
		Product product = new Product();
		String name = node.get("name").asText();
		String sku = node.get("sku").asText();
		String description = node.get("description").asText();
		String brand = node.get("brand").asText();
		String imageUrl = node.get("imageUrl").asText();
		BigDecimal discount = node.get("discount").decimalValue();
		BigDecimal price = node.get("unitPrice").decimalValue();
		int totalSold = node.get("totalSold").asInt();
		int unitsInStock = node.get("unitsInStock").asInt();
	    product.setName(name);
	    product.setSku(sku);
	    product.setDescription(description);
	    product.setBrand(brand);
	    product.setImageUrl(imageUrl);
	    product.setDiscount(discount);
	    product.setUnitPrice(price);
	    product.setTotalSold(totalSold);
	    product.setUnitsInStock(unitsInStock);

        Long categoryId = node.get("category").asLong(); // Assuming category_id is represented in JSON
        ProductCategory category = new ProductCategory();
        category.setId(categoryId);
        product.setCategory(category);
        
		return product;
	}
}
