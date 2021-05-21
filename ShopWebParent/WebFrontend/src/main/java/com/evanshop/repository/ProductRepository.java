package com.evanshop.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.evanshop.common.entity.Product;


public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
	
	@Query("SELECT p FROM Product p WHERE p.enabled = true "
			+ "AND (p.category.id = ?1 OR p.category.allParentIds LIKE %?2%)"
			+ "ORDER BY p.name ASC ")
	public Page<Product> listByCategory(Integer categoryId, String categoryIdMatch, Pageable pageable);
}