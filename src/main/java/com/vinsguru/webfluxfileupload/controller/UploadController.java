package com.vinsguru.webfluxfileupload.controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.vinsguru.webfluxfileupload.Models.Product;
import com.vinsguru.webfluxfileupload.repositories.ProductRepository;
import com.vinsguru.webfluxfileupload.services.CsvServices;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.RedirectView;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin(origins ={"http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class UploadController {

    private final Path basePath = Paths.get("./src/main/resources/upload/");
    @Autowired
    private final CsvServices csvServices;

    public UploadController(CsvServices csvServices){
        this.csvServices = csvServices;
    }

    @GetMapping("/products")
    public Flux<Product> listProducts(){
        return csvServices.getAll();
    }

    @GetMapping("/products/{id}")
    public Mono<ResponseEntity<Product>> product(@PathVariable Long id) {
        return csvServices.getProduct(id).
        map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @PostMapping(value = "/create")
    public Mono<Product> addNewProduct(@RequestBody Product product) {
        if (product != null){
            product.toString();
            System.out.println(product.toString());
        }
        return csvServices.newProduct(product);
    }

    @PutMapping("/upload/{id}")
    public Mono<ResponseEntity<Product>> updateProduct(@PathVariable Long id,@RequestBody Product productMono){
        return this.csvServices.updateProduct(id,productMono).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable Long id){
        return this.csvServices.deleteProduct(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
//
//    @RequestMapping(value = "index/upload", method = RequestMethod.POST)
//    public String upload(@RequestPart("user-name") String name,
//                               @RequestPart("fileToUpload") Mono<FilePart> filePartMono, Model model){
//        filePartMono
//                .doOnNext(fp -> System.out.println("Received File : " + fp.filename()))
//                .flatMap(fp ->
//                    fp.transferTo(basePath.resolve(fp.filename())))
//                .then();
//
//        model.addAttribute("name",name);
//        RedirectView redirectView = new RedirectView();
//        redirectView.setContextRelative(true);
//        redirectView.setUrl("/home/{name}");
//
//        return "home";
//    }
//
//    @PostMapping("file/multi1")
//    public String upload(@RequestPart("files") Flux<FilePart> partFlux){
//        partFlux
//                .doOnNext(fp -> System.out.println(fp.filename()))
//                .flatMap(fp -> fp.transferTo(basePath.resolve(fp.filename())))
//                .then();
//        return "templates/home";
//    }


}