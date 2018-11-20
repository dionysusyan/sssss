import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.testobject.ConditionType as ConditionType


WebUI.openBrowser(GlobalVariable.root_url + 'login-view.php')

WebUI.verifyElementPresent(findTestObject('Page_Login/input_Username_username'), 0)

WebUI.verifyElementPresent(findTestObject('Page_Login/input_password'), 0)

WebUI.verifyElementPresent(findTestObject('Page_Login/input'), 0)

WebUI.setText(findTestObject('Page_Login/input_Username_username'), 'admin')

WebUI.setText(findTestObject('Page_Login/input_password'), 'admin')

WebUI.click(findTestObject('Page_Login/input'))

assert WebUI.getUrl().endsWith('list-view.php')

WebUI.click(findTestObject('bootstrap/a_Bootstrap'))

assert WebUI.getUrl().endsWith('bootstrap-view.php')

WebUI.uploadFile(findTestObject('bootstrap/input_Bootstrap file_bootstrap'), GlobalVariable.file_location)

WebUI.click(findTestObject('bootstrap/input_Bootstrap file_submit'))

WebUI.navigateToUrl(GlobalVariable.root_url + 'login-view.php')

WebUI.setText(findTestObject('Page_Login/input_Username_username'), 'apple.2016')

WebUI.setText(findTestObject('Page_Login/input_password'), 'apple123')

WebUI.click(findTestObject('Page_Login/input'))

WebDriver driver = DriverFactory.getWebDriver()

WebElement Table = driver.findElement(By.id('book-list'))

List<WebElement> Rows = Table.findElements(By.tagName('tr'))

def map = new HashMap<String,List>()

def data = TestDataFactory.findTestData("Data Files/booklist")
data.getValue(2, 1)
println('No. of rows: ' + Rows.size())

for(int i = 1; i<16;i++){
	def list = new ArrayList()
	list.add(data.getValue(1, i))
	list.add(data.getValue(2, i))
	list.add(Double.parseDouble(data.getValue(3, i)))
	list.add(data.getValue(4, i))
	map.put(data.getValue(2, i),list)
}

'Find a matching text in a table and performing action'
'Loop will execute for all the rows of the table'
table: for (int i = 1; i < Rows.size(); i++) {
    'To locate columns(cells) of that specific row'
    List<WebElement> Cols = Rows.get(i).findElements(By.tagName('td'))
	if(Cols.get(4).getText()=="0"){
		String title = Cols.get(0).getText()
		String isbn = Cols.get(1).getText()
		String price = Cols.get(2).getText()
		String availability = Cols.get(3).getText()
		list = map.get(isbn)
		def list2 = new ArrayList()
		list2.add(title)
		list2.add(isbn)
		list2.add(Double.parseDouble(price))
		list2.add(availability)
		if(list != null){
		//	assert map.get(isbn) == list2;
		}
	}
}
int counter = 0;
WebUI.click(findTestObject('Page_delete/a_delete'))

WebElement Table2 = driver.findElement(By.id('book-list'))
List<WebElement> Rows2 = Table2.findElements(By.tagName('tr'))
table2: for (int i = 1; i < Rows2.size(); i++) {
	'To locate columns(cells) of that specific row'
	List<WebElement> Cols2 = Rows2.get(i).findElements(By.tagName('td'))
	if(Cols2.get(4).getText()=="0"){
		String isbn = Cols2.get(1).getText()
		if(isbn != "9781449474224"){
			counter ++;
		}
	}
}
if(counter != 14){
	KeywordUtil.markFailed("object not delete")
}
	
WebUI.click(findTestObject('Page_Edit Price/a_edit'))

WebUI.verifyElementPresent(findTestObject('Page_Edit Price/form_Title'), 0)

WebUI.setText(findTestObject('Page_Edit Price/input_Price_price'), '94.01')

WebUI.click(findTestObject('Page_Edit Price/input'))

assert WebUI.getUrl().endsWith('list-view.php')

WebElement Table3 = driver.findElement(By.id('book-list'))
List<WebElement> Rows3 = Table3.findElements(By.tagName('tr'))
table3: for (int i = 1; i < Rows3.size(); i++) {
	'To locate columns(cells) of that specific row'
	List<WebElement> Cols3 = Rows3.get(i).findElements(By.tagName('td'))
	if(Cols3.get(4).getText()=="0"){
		String isbn = Cols3.get(1).getText()
		String price = Cols3.get(2).getText()
		if(isbn == "9781434474234" && price=="94.01"){
			KeywordUtil.markPassed("All condition has been match")
		
		}
	}
}
