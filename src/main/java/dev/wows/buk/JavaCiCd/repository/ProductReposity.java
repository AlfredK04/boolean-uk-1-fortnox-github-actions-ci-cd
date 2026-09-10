package dev.wows.buk.JavaCiCd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.wows.buk.JavaCiCd.entity.Product;

@Repository 
public interface ProductReposity extends JpaRepository<Product, Long>{

}
