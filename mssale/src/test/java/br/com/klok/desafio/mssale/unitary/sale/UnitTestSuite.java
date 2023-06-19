package br.com.klok.desafio.mssale.unitary.sale;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({SaleModelUnitaryTest.class
             , SaleDtoUnitaryTest.class
             , SaleControllerUnitaryTest.class
             , SaleServiceUnitaryTest.class
})
public class UnitTestSuite {
}
