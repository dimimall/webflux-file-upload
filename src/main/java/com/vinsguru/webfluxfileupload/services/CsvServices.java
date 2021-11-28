package com.vinsguru.webfluxfileupload.services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.vinsguru.webfluxfileupload.Models.Product;
import com.vinsguru.webfluxfileupload.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class CsvServices {

    private final Path basePath = Paths.get("./src/main/resources/upload/");

    @Autowired
    private final ProductRepository productRepository;

    public CsvServices(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public CSVReader csvReader(String name) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(name);
        Reader reader = new BufferedReader(new InputStreamReader(inputStream));
        CSVReader csvReader = new CSVReader(reader);
        return csvReader;
    }

    public void nextProduct(CSVReader csvReader) throws IOException, CsvException {
        List<String[]> csv = csvReader.readAll();
        Flux.just(csv).subscribe(new Subscriber<List<String[]>>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println(s.toString());
            }

            @Override
            public void onNext(List<String[]> strings) {
                for (String[] string: strings) {
                    System.out.println(string[0]+" "+string[1]);
                    productRepository.save(new Product(string[0],string[1]));
                }
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Complete");
            }
        });
    }

    public Mono<Product> newProduct(Product product){
        return productRepository.save(product);
    }

    public Flux<Product> getAll(){
        return productRepository.findAll();
    }

    public Mono<Product> getProduct(long id) { return productRepository.findById(id); }

    public Mono<Product> updateProduct(long id,Product productMono) {
        return this.productRepository.findById(id)
               .flatMap(product -> {
                   product.setItemName(productMono.getItemName());
                   product.setProductPrice(productMono.getProductPrice());
                   return productRepository.save(product);
               });
    }

    public Mono<Void> deleteProduct(final long id){
        return this.productRepository.findById(id)
                .flatMap(product ->
                        this.productRepository.delete(product));
    }
}
