package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;

    //product save
    public ResponseEntity save (Product product){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        Product save = productRepository.save(product);
        hm.put(REnum.status,true);
        hm.put(REnum.result,save);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    //product list
    public ResponseEntity list(){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        List<Product> listProduct = productRepository.findAll();
        hm.put(REnum.status,true);
        hm.put(REnum.message,"Product have been listed");
        hm.put(REnum.result,listProduct);
        return new ResponseEntity(hm, HttpStatus.OK);
        }

    //product delete
    public ResponseEntity delete(String id){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        int iid= Integer.parseInt(id);
        Optional<Product> optionalProduct = productRepository.findById(iid);
        if (optionalProduct.isPresent()){
            productRepository.deleteById(iid);
            hm.put(REnum.status,true);
            hm.put(REnum.message,"Delete Success.");
            return new ResponseEntity(hm,HttpStatus.OK);
        }else {
            hm.put(REnum.status,false);
            hm.put(REnum.message,"Delete Fail");
            return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
        }
    }

    //product update
    public ResponseEntity update(Product product){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        Optional<Product> optionalProduct = productRepository.findById(product.getPid());
        if (optionalProduct.isPresent()){
            Product product1 = productRepository.saveAndFlush(product);
            hm.put(REnum.status,true);
            hm.put(REnum.result,product1);
            return new ResponseEntity(hm,HttpStatus.OK);
        }else {
            hm.put(REnum.status,false);
            hm.put(REnum.message,"Update Fail.");
            hm.put(REnum.result,product);
            return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
        }

    }

}
