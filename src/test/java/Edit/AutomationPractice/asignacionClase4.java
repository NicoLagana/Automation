package Edit.AutomationPractice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class asignacionClase4 {
String url = "https://automationpractice.pl/";


@Test
public void registro() {
	Faker faker = new Faker();
	WebDriver driver = new ChromeDriver();
	
	//Abrir chrome maximizado
	
	driver.get(url);
	driver.manage().window().maximize();

	
	//Hacer clic en el link “Sign In”
	driver.findElement(By.xpath("//a[contains(text(),'Sign in')]")).click();
	WebDriverWait espera = new WebDriverWait(driver, Duration.ofSeconds(5));
	espera.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Create an account')]")));
	
	//Escribir el correo
	
	String email = faker.internet().emailAddress();
	driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(email);
	
	//Hacer clic en Create an Account
	driver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();
	espera.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Your personal information')]")));
	
	//Completar el formulario
	
	String nombre = faker.name().firstName();
	String apellido = faker.name().lastName();
	String password = faker.internet().password(5, 10, false, false, false);
	
	driver.findElement(By.cssSelector("#id_gender1")).click();
	driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys(nombre);
	driver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys(apellido);
	driver.findElement(By.id("passwd")).sendKeys(password);

	Select dia = new Select(driver.findElement(By.xpath("//select[@id='days']")));
	dia.selectByValue("19");
	
	Select mes = new Select(driver.findElement(By.xpath("//select[@id='months']")));
	mes.selectByIndex(1);
	
	Select year = new Select(driver.findElement(By.xpath("//select[@id='years']")));
	year.selectByValue("1994");
	
	driver.findElement(By.xpath("//input[@id='newsletter']")).click();
	driver.findElement(By.xpath("//input[@id='optin']")).click();

		
	//Hacer clic en el botón Register

	driver.findElement(By.xpath("//button[@id='submitAccount']")).click();
}


}
