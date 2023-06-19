package br.com.klok.desafio.msclient.unitary.client;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ClientModelUnitaryTest.class
             , ClientDtoUnitaryTest.class
             , ClientControllerUnitaryTest.class
             , ClientServiceUnitaryTest.class
})
public class UnitTestSuite {
}
