package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object root_url
     
    /**
     * <p></p>
     */
    public static Object file_location
     

    static {
        def allVariables = [:]        
        allVariables.put('default', ['root_url' : 'http://52.14.77.141/bookstore/', 'file_location' : 'C:\\003-bootstrap.zip'])
        
        String profileName = RunConfiguration.getExecutionProfile()
        
        def selectedVariables = allVariables[profileName]
        root_url = selectedVariables['root_url']
        file_location = selectedVariables['file_location']
        
    }
}
