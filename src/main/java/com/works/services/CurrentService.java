package com.works.services;

import com.works.entities.Current;
import com.works.entities.Product;
import com.works.repositories.CurrentRepository;
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
public class CurrentService {

    final CurrentRepository currentRepository;

    //current save
    public ResponseEntity save(Current current){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        Current save = currentRepository.save(current);
        hm.put(REnum.status,true);
        hm.put(REnum.message,"Save success");
        hm.put(REnum.result,save);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    //current list
    public ResponseEntity list(){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        List<Current> findAllCurrent = currentRepository.findAll();
        hm.put(REnum.status,true);
        hm.put(REnum.message,"Current have been listed");
        hm.put(REnum.result,findAllCurrent);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

}
