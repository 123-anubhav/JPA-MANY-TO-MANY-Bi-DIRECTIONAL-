package com.developers.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.developers.model.Category;
import com.developers.model.Product;
import com.developers.repo.ICategory;
import com.developers.repo.IProduct;

@RestController
public class RestCtrl {

	@Autowired
	private IProduct product;

	@Autowired
	private ICategory category;

	@PostMapping("/save")
	public String save(@RequestBody Product p) throws Exception {
		if (p.getCategories() != null)
			p.getCategories().forEach(ct -> ct.setCName(ct.getCName()));
		else {
			throw new Exception("product category is null");
		}

		Product save = product.save(p);
		return "data saved :: id :: " + save.getPId();
	}

	/*
	 * @PostMapping(value = "/save", consumes = "application/json") public String
	 * save(@RequestBody Product p) throws Exception { if (p.getCategories() !=
	 * null) { // Iterate over the categories and check if they exist, if not,
	 * create them for (Category ct : p.getCategories()) { // If category doesn't
	 * exist, create and save it Optional<Category> existingCategory =
	 * category.findBycName(ct.getCName()); if (!existingCategory.isPresent()) { //
	 * Save the new category if it does not exist category.save(ct); } else { // Set
	 * the existing category to avoid creating duplicates ct =
	 * existingCategory.get(); } } } else { throw new
	 * Exception("Product category is null"); }
	 * 
	 * // Save the product Product savedProduct = product.save(p); return
	 * "Data saved :: id :: " + savedProduct.getPId(); }
	 */
	@GetMapping("/save")
	public ResponseEntity<List<Product>> getListOfData() {
		List<Product> listOfProduct = product.findAll();
		ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(listOfProduct, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/save/{pId}")
	public ResponseEntity<Optional<Product>> getData(@PathVariable Integer pId) {
		Optional<Product> productItem = product.findById(pId);
		ResponseEntity<Optional<Product>> responseEntity = new ResponseEntity<>(productItem, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/getCategory")
	public List<Category> getData() {
		return category.findAll();
	}
}
