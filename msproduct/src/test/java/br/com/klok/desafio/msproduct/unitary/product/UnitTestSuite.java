package br.com.klok.desafio.msproduct.unitary.product;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ProductModelUnitaryTest.class
             , ProductDtoUnitaryTest.class
             , ProductControllerUnitaryTest.class
             , ProductServiceUnitaryTest.class
})
public class UnitTestSuite {
}
