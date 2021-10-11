package tests.day07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class C03_DependsOnTest {

    // ● Bir class oluşturun: DependsOnTest
    // ● https://www.amazon.com/ adresine gidin.
    //    1. Test : Amazon ana sayfaya gittiginizi test edin
    //    2. Test : 1.Test basarili ise search Box’i kullanarak “Nutella” icin arama yapin ve aramanizin gerceklestigini Test edin
    //    3.Test : “Nutella” icin arama yapildiysa ilk urunu tiklayin ve fiyatinin    $16.83 oldugunu test edin


    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void test1() {
        driver.get("https://www.amazon.com/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.amazon.com/", "url https://www.amazon.com degil");
        // currentTitle'in expectedTitle esit oldugunu tespit ederek dogruladik
    }

    // Testleri birbirine bagladigimizda:
    // 1.) 2. metodu tek basina calistirmak istesek bile, otomatik olarak once 1.  method calisir.
    // 2.) 1. method failed olursa 2.  method ignore edilir, yani calistirilmaz.

    @Test (dependsOnMethods = "test1")
    public void test2() {
        WebElement aramaKutusu = driver.findElement(By.id("twotabsearchtextbox"));
        aramaKutusu.sendKeys("nutella" + Keys.ENTER);
        Assert.assertTrue(driver.getTitle().contains("nutella"), "nutella icin arama yapilamadi");
    }

    // eger 3 test veya dha fazlasi birbirine dependsOn() ile baglandiysa,
    // 3. yu calistirmak istedgimizda zincir reaksiyon calisip 1. ye gitmez. tum clas

    @Test (dependsOnMethods = "test2")
    public void test3() {
        driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])[1]")).click();
        WebElement ilkUrunFiyat = driver.findElement(By.xpath("//*[@class='a-size-base a-color-price']"));
        System.out.println(ilkUrunFiyat.getText());
        Assert.assertEquals(ilkUrunFiyat.getText(), "$16.83");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

}
