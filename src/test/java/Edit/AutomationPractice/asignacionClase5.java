package Edit.AutomationPractice;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;



public class asignacionClase5 {
	String url = "https://www.saucedemo.com/";
	WebDriver driver = new ChromeDriver ();
	Faker faker = new Faker();
	File pantalla;
	String rutaEvidencia = "..\\AutomationPractice\\EvidenciaClase5\\";
	
	
@BeforeSuite	
	public void abrirNavegador() {
		driver.get(url);
		driver.manage().window().maximize();
	
	}


@Test (description = "LogIn en SauceDemo", priority = 1 )
	public void logIn () throws IOException{
	String usuario = "standard_user";
	String password = "secret_sauce";
	
	
	driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(usuario);
	driver.findElement(By.cssSelector("#password")).sendKeys(password);
	
	pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(pantalla, new File(rutaEvidencia + "01_Login.jpg"));
	
	driver.findElement(By.xpath("//input[@id='login-button']")).click();
	
	pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(pantalla, new File(rutaEvidencia + "02_Home.jpg"));
	
	
}
@Test (description = "Realizar la compra del carrito", priority = 2 )
	public void comprarCarrito () throws IOException {
	String name = faker.name().firstName();
	String lastname = faker.name().lastName();
	String urlesperada = "https://www.saucedemo.com/checkout-complete.html";
	
	
	driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
	
	pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(pantalla, new File(rutaEvidencia + "03_AddtoCart.jpg"));
	
	driver.findElement(By.xpath("//div[@id='shopping_cart_container']")).click();
	
	pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(pantalla, new File(rutaEvidencia + "04_Buy.jpg"));
	
	driver.findElement(By.xpath("//button[@id='checkout']")).click();
	
	
	
	driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys(name);
	driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys(lastname);
	driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("1414");
	
	pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(pantalla, new File(rutaEvidencia + "05_Infomacion.jpg"));
	
	driver.findElement(By.xpath("//input[@id='continue']")).click();
	driver.findElement(By.xpath("//button[@id='finish']")).click();
	
	pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(pantalla, new File(rutaEvidencia + "06_Complete.jpg"));
	
	String urlactual = driver.getCurrentUrl();
	Assert.assertEquals(urlesperada, urlactual);
	
}

@AfterSuite
	public void cerrarNavegador () {
	driver.close();
}

} 
