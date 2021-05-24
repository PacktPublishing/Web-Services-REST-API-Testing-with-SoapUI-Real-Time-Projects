package Rest;
import java.io.IOException;

import org.apache.xmlbeans.XmlException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.model.testsuite.TestRunner.Status;
import com.eviware.soapui.support.SoapUIException;

public class restRunner2 {
	
	
@Test
	public void RestTest2() throws XmlException, IOException, SoapUIException
	{
			// 
		//	A x=new A();
			//Grab the project
			WsdlProject project=new WsdlProject("C:\\Users\\rahul\\Documents\\LibraryAPI.xml");
			//Grab the Test suite in the project
			
			for(int j=0;j<project.getTestSuiteCount();j++)
			{
				WsdlTestSuite testsuite=	project.getTestSuiteAt(j);
				
				for(int i=0;i<testsuite.getTestCaseCount();i++)
				{
					WsdlTestCase testCase =testsuite.getTestCaseAt(i);
					
					//Run the testcase
					TestRunner runner=testCase.run(new PropertiesMap(), false);
					Assert.assertEquals(Status.FINISHED, runner.getStatus());
					
					
					
				}
			}
			
			
			//Grab the testcases present in test suite
			
}}
