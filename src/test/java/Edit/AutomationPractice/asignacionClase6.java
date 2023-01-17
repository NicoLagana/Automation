package Edit.AutomationPractice;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Utilities.CapturaEvidencia;
import Utilities.DatosExcel;

public class asignacionClase6 {

	String url = "https://www.saucedemo.com/";
	WebDriver driver;
	String rutaEvidencia = "..\\AutomationPractice\\Evidencias\\";
	String nombreDocumento = "Evidencia Clase 6.docx";
	String urlactual;
	String urlesperada = "https://www.saucedemo.com/inventory.html";
	String urlesperada3 = "https://www.saucedemo.com/checkout-step-two.html";

	@BeforeSuite
	public void abrirNavegador() throws InvalidFormatException, IOException, InterruptedException {
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencia + nombreDocumento, "Documento de evidencias Clase 6",
				18);
	}

	@Test(description = "LogIn en SauceDemo", priority = 1, dataProvider = "DatosLogin")
	public void logIn(String usuario, String password)
			throws InvalidFormatException, IOException, InterruptedException {

		if (urlactual != urlesperada) {
			;
			driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(usuario);
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
			driver.findElement(By.xpath("//input[@id='login-button']")).click();}
			
		if (urlactual != urlesperada) {
				CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg",
						rutaEvidencia + nombreDocumento, "Paso 1A - LogIn fallido");
			} 
		driver.navigate().refresh();
		urlactual = driver.getCurrentUrl();
		
		}
	

	@Test(description = "Compra de carrito", priority = 2, dataProvider = "DatosCarrito")
	public void compraCarrito(String nombre, String apellido, String codigopostal)
			throws InvalidFormatException, IOException, InterruptedException {
		String urlesperada2 = "https://www.saucedemo.com/checkout-complete.html";

		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento,
				"Paso 1B - LogIn valido");

		driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
		driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-fleece-jacket']")).click();

		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento,
				"Paso 2 - Agregar al carrito");

		driver.findElement(By.xpath("//div[@id='shopping_cart_container']")).click();

		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento,
				"Paso 3 - Ingresar al carrito");

		driver.findElement(By.xpath("//button[@id='checkout']")).click();

		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento,
				"Paso 4 - Formulario vacio");

		if (urlactual != urlesperada3) {
			;
			driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys(nombre);
			driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys(apellido);
			driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys(codigopostal);

			CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg",
					rutaEvidencia + nombreDocumento, "Paso 5 - Formulario lleno");

			driver.findElement(By.xpath("//input[@id='continue']")).click();
			urlactual = driver.getCurrentUrl();
		}

		WebElement overview = driver.findElement(By.xpath("//span[contains(text(),'Checkout: Overview')]"));
		Assert.assertTrue(overview.isDisplayed());

		driver.findElement(By.xpath("//button[@id='finish']")).click();

		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento,
				"Paso 6 - Compra carrito finalizada");

		String urlactual = driver.getCurrentUrl();
		Assert.assertEquals(urlesperada2, urlactual);
	}

	@DataProvider(name = "DatosLogin")
	public Object[][] leerDatosLogin() throws Exception {
		return DatosExcel.leerExcel("..\\AutomationPractice\\Datos\\DatosLogin.xlsx", "Hoja1");
	}

	@DataProvider(name = "DatosCarrito")
	public Object[][] leerDatosCarrito() throws Exception {
		return DatosExcel.leerExcel("..\\AutomationPractice\\Datos\\DatosLogin.xlsx", "Hoja2");
	}

	@AfterSuite
	public void cerrarNavegador() {

		driver.close();

	}

}
