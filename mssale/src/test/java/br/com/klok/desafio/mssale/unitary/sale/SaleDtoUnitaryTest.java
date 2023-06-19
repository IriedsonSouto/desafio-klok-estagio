package br.com.klok.desafio.mssale.unitary.sale;

import br.com.klok.desafio.mssale.model.entity.SaleModel;
import br.com.klok.desafio.mssale.presetation.dto.SaleDto;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class SaleDtoUnitaryTest {

    private static SaleDto saleDto;
    private static SaleModel saleModelMock;


    @BeforeAll
    public static void setup() {
        saleDto = new SaleDto();
        saleModelMock = mock(SaleModel.class);
    }

    @Test
    @Order(1)
    @Tag("sets_gets_method")
    @DisplayName("Setters and Getters method SaleDTO")
    public void testSaleDTOSetterAndGetter() {

        String email = "email@example.com";

        saleDto.setEmailClient(email);

        assertEquals(email, saleDto.getEmailClient());
    }

    @Test
    @Order(2)
    @Tag("construct_method")
    @DisplayName("Constructor method SaleDTO by SaleModel")
    public void testSaleDTOBySaleModel() {

        saleDto = new SaleDto(saleModelMock);

        assertInstanceOf(SaleDto.class, saleDto);
    }

    @Test
    @Order(3)
    @Tag("convert_method")
    @DisplayName("Convert SaleDTO for SaleModel")
    public void testSaleDTOForSaleModel() {

        SaleModel saleModel = SaleDto.convertToModel(saleDto);
        assertInstanceOf(SaleModel.class, saleModel);
        assertNotNull(saleModel.getUuid());
        assertNotNull(saleModel.getCreateDate());
    }

}


