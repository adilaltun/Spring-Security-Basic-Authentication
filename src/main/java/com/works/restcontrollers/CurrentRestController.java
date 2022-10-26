package com.works.restcontrollers;

import com.works.entities.Current;
import com.works.services.CurrentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/current")
public class CurrentRestController {

    final CurrentService currentService;

    //current save
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Current current){
        return currentService.save(current);
    }
    //current list
    @GetMapping("/list")
    public ResponseEntity list(){
        return currentService.list();
    }
}
