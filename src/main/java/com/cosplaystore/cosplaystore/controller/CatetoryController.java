package com.cosplaystore.cosplaystore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cosplaystore.cosplaystore.dto.request.CatetoryRequest;
import com.cosplaystore.cosplaystore.dto.response.CatetoryResponse;
import com.cosplaystore.cosplaystore.dto.response.ResponseObject;
import com.cosplaystore.cosplaystore.mapper.CatetoryMapper;
import com.cosplaystore.cosplaystore.model.Catetory;
import com.cosplaystore.cosplaystore.service.CatetoryService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/catetory")
public class CatetoryController {

        @Autowired
        CatetoryService catetoryService;

        @Autowired
        CatetoryMapper catetoryMapper;

        @PostMapping("/add")
        public ResponseEntity<ResponseObject> postMethodName(@RequestBody @Valid CatetoryRequest catetoryRequest) {
                CatetoryResponse catetory = catetoryService.addCatetory(catetoryRequest);
                return ResponseEntity.ok()
                                .body(ResponseObject.builder()
                                                .data(catetory)
                                                .message("Add a catetory success")
                                                .status_code(HttpStatus.OK.value()).build());
        }

        @GetMapping("/{id}")
        public ResponseEntity<ResponseObject> getMethodName(@PathVariable int id) {
                CatetoryResponse catetory = catetoryService.getCatetory(id);

                return ResponseEntity.ok()
                                .body(ResponseObject.builder()
                                                .status_code(HttpStatus.OK.value())
                                                .message("Get a catetory success!")
                                                .data(catetory).build());

        }

        @PutMapping("/update")
        public ResponseEntity<ResponseObject> putMethodName(@RequestParam int id,
                        @RequestBody @Valid CatetoryRequest catetoryRequest) {
                catetoryService.updateCatetory(id, catetoryRequest);
                return ResponseEntity.ok()
                                .body(ResponseObject.builder()
                                                .data(null)
                                                .message("Update success!")
                                                .status_code(HttpStatus.OK.value()).build());

        }

        @DeleteMapping("/delete")
        public ResponseEntity<ResponseObject> deleteMethodName(@RequestParam int id) {
                catetoryService.deleteCatetory(id);
                return ResponseEntity.ok()
                                .body(ResponseObject.builder()
                                                .data(null)
                                                .message("Delete success!")
                                                .status_code(HttpStatus.OK.value()).build());
        }

        @GetMapping("/all")
        public ResponseEntity<ResponseObject> getMethodName() {
                List<CatetoryResponse> catetories = catetoryService.getAllCatetory();
                return ResponseEntity.ok()
                                .body(ResponseObject.builder()
                                                .status_code(HttpStatus.OK.value())
                                                .message("Get all catetory")
                                                .data(catetories)
                                                .build());
        }

}
