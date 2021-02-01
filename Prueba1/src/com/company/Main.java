package com.company;

import com.sun.tools.jconsole.JConsoleContext;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Scanner;

public class Main {

    private static WebDriver driver;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido al comparador de precios");
        System.out.println("Introduzca el telefono que desee buscar");
        String busqueda = sc.nextLine();
        System.out.println("Resultados de Amazon:");
        ChromeAmazon(busqueda);
        System.out.println("Resultados de Fnac:");
        ChromeFnac(busqueda);
    }

    public static void ChromeAmazon(String busqueda) {
        String exePath = "chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://www.amazon.es/gp/browse.html?node=931491031&ref_=nav_em__tele_0_2_13_3");
        WebElement element = driver.findElement(By.id("twotabsearchtextbox"));
        element.sendKeys(busqueda);
        element.submit();
        for (int i = 2; i < 8; i++) {
            try {
                String TelefAmazon = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div/span[3]/div[2]/div[" + i + "]/div/span/div/div/div[2]/div[2]/div/div[1]/div/div/div[1]/h2/a/span")).getText();
                String CosteAmazon = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div/span[3]/div[2]/div[" + i + "]/div/span/div/div/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div/div/div/a/span[1]/span[2]/span[1]")).getText();
                System.out.println(TelefAmazon);
                System.out.println(CosteAmazon + "€");
            } catch (Exception e) {
                System.out.println("No se encuentra resultado a tu busqueda");
            }
        }
    }
        public static void ChromeFnac(String busqueda){
            String exePath = "chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", exePath);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            driver.get("https://www.fnac.es/telefono-MP3-GPS#bl=MMSmartphone");
            WebElement element = driver.findElement(By.id("Fnac_Search"));
            element.sendKeys(busqueda);
            element.submit();
            for (int k = 2; k < 8; k++) {
                try {
                    String TelefFnac= driver.findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div["+k+"]/article/div[2]/div/p[1]/a")).getText();
                    String CosteFnac = driver.findElement(By.xpath("/html/body/div[3]/div/div[7]/div/div["+k+"]/article/div[3]/div/div/div/div[1]/a/strong")).getText();
                    System.out.println(TelefFnac);
                    System.out.println(CosteFnac + "€");
                } catch (Exception e) {
                    System.out.println("No se encuentra resultado a tu busqueda");
                }
            }
        }
    }
