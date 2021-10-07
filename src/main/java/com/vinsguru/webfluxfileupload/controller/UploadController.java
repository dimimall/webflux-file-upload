package com.vinsguru.webfluxfileupload.controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.vinsguru.webfluxfileupload.Models.Product;
import com.vinsguru.webfluxfileupload.repositories.ProductRepository;
import com.vinsguru.webfluxfileupload.services.CsvServices;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
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

@Controller
@RequestMapping("index")
public class UploadController {

    private final Path basePath = Paths.get("./src/main/resources/upload/");
    private final ProductRepository productRepository;
    private final CsvServices csvServices;

    public UploadController(ProductRepository productRepository, CsvServices csvServices){
        this.productRepository = productRepository;
        this.csvServices = csvServices;
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