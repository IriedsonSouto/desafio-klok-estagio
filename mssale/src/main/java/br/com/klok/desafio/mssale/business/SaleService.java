package br.com.klok.desafio.mssale.business;

import br.com.klok.desafio.mssale.presetation.dto.SaleDto;
import br.com.klok.desafio.mssale.model.entity.SaleModel;

import java.util.List;

public interface SaleService {


    public SaleModel saveSale(SaleDto sale);

    public List<SaleModel> getAllSale();

    public SaleModel getSaleById(String id);

    public SaleModel getSaleByClientId(String id);

    public SaleModel updateSaleById(String id, SaleDto sale);

    public SaleModel updateSaleProductList(String saleId, String productId);

    public SaleModel updateSaleStatus(String id);

    public void deleteSaleById(String id);

}
