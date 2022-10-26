package com.works.restcontrollers;

import com.works.entities.Product;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductRestController {

    final ProductService productService;

    //product save
    @PostMapping("/save")
    public ResponseEntity save (@RequestBody Product product){
        return productService.save(product);
    }

    //product list
    @GetMapping("/list")
    public ResponseEntity list(){
        return productService.list();
    }

    //product delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id){
        return productService.delete(id);
    }

    //product update
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Product product){
        return productService.update(product);
    }
}
