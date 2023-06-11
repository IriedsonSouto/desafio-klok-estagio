package br.com.klok.desafio.mssale.business;

import br.com.klok.desafio.mssale.presetation.dto.SaleDto;
import br.com.klok.desafio.mssale.model.entity.SaleModel;

import java.util.List;

public interface SaleService {


    public SaleModel saveSale(SaleDto sale);

    public List<SaleModel> getAllSale();

    public SaleModel getSaleById(String id);

    public void deleteSaleById(String id);

}
