package com.vinsguru.webfluxfileupload;

import com.vinsguru.webfluxfileupload.Models.User;
import com.vinsguru.webfluxfileupload.repositories.UserRepository;
import com.vinsguru.webfluxfileupload.services.CsvServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRepositoryTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    CsvServices csvServices;

    @Autowired
    private WebTestClient webClient;

    @Test
    public void addSingleUser(){
        User user = new User();
        user.setId(6L);
        user.setName("Mathew");
        user.setRole("user");

        System.out.println(user.toString());

        Mockito.when(csvServices.newUser(user)).thenReturn(Mono.just(user));

        webClient.post()
                .uri("/api/create/user")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(user))
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(csvServices, times(1)).newUser(user);
    }

    @Test
    void testGetUsersById()
    {
        User user = new User();
        user.setId(1L);
        user.setName("Dimitra");
        user.setRole("admin");

        System.out.println(user.toString());

        Mockito
                .when(csvServices.getUser(1L))
                .thenReturn(Mono.just(user));

        webClient.get().uri("/api/users/{id}", 1)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Dimitra")
                .jsonPath("$.role").isEqualTo("admin");

        Mockito.verify(csvServices, times(1)).getUser(1L);
    }

    @Test
    public void testDeleteUser(){
        User user = new User();
        user.setId(1L);
        user.setName("Dimitra");
        user.setRole("admin");

        Mono<Void> voidReturn  = Mono.empty();
        Mockito.when(csvServices.deleteUser(1L))
                .thenReturn(voidReturn);
        webClient.delete().uri("/api/delete/user/{id}", 1)
                .exchange()
                .expectStatus().isNoContent();
    }
}
