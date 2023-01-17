package Edit.AutomationPractice;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Clase2 {
	// Seccion 1 : Atributos o Variables de uso comun
	// Seccion 2: Métodos de prueba
	String url ="http://automationpractice.pl";
	
	@Test
	public void hacerBusquedaEnChrome() 
	{
		// Acciones para poder hacer la búsqueda en el navegador Chrome
		// Paso 1. Configurar el navegador que vamos a usar
		
		WebDriver navegador = new ChromeDriver();
		
		// Paso 2. Abrir el navegador en la URL requerida
		
		navegador.get(url); //Para abrir el navegador se usa .GET
		
		// Paso 3. Escribir la palabra que queremos buscar
		
		WebElement txtBuscador = navegador.findElement(By.id("search_query_top"));
		txtBuscador.sendKeys("Dress");
		
		// Paso 4. Hacer la búsqueda (Presionar ENTER)
		
		txtBuscador.sendKeys(Keys.ENTER); //Simula tocar la tecla Enter
		
		// Paso 5. Cerrar el navegador
		navegador.close();
	}
	
	@Test
	public void hacerBusquedaFireFox() 
	{
		// Acciones para poder hacer la búsqueda en el navegador Chrome
		// Paso 1. Configurar el navegador que vamos a usar
		
		WebDriver navegador = new FirefoxDriver();
		
		// Paso 2. Abrir el navegador en la URL requerida
		
		navegador.get(url); //Para abrir el navegador se usa .GET
		
		// Paso 3. Escribir la palabra que queremos buscar
		
		WebElement txtBuscador = navegador.findElement(By.id("search_query_top"));
		txtBuscador.sendKeys("Dress");
		
		// Paso 4. Hacer la búsqueda (Presionar ENTER)
		
		txtBuscador.sendKeys(Keys.ENTER); //Simula tocar la tecla Enter
		
		// Paso 5. Cerrar el navegador
		navegador.close();
	}
}
