package br.com.klok.desafio.msclient.unitary.client;

import br.com.klok.desafio.msclient.business.ClientService;
import br.com.klok.desafio.msclient.model.entity.ClientModel;
import br.com.klok.desafio.msclient.presetation.controller.ClientController;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;


@WebMvcTest
public class ClientControllerUnitaryTest {

    @Autowired
    private static ClientController clientController;

    @MockBean
    private ClientService clientService;


    @BeforeAll
    public static void setup() {
        standaloneSetup(clientController);
    }

    @Test
    public void getClientTest() {

        when(clientService.getAllClient()).thenReturn(new ArrayList<ClientModel>());

        given()
                .get("/clients")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

}
