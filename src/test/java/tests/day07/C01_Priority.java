package tests.day07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class C01_Priority {

    // 3 Test methodu olusturun
    //  1. amazon ana saufasina
    //  2. techproeducation ana sayfasina
    //  3. facebook ana sayfasina gidin
    // ve sayfa title'larini yazdirin

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test   (priority = 10)
    public void amazonTest() {
        driver.get("https://www.amazon.com/");

    }

    @Test (priority = 1)
    public void techproTest() {
        driver.get("https://www.techproeducation.com/");
    }

    @Test (priority = 1000)
    public void facebookTest() {
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void yahooTest() {
        driver.get("https://www.yahoo.com/");
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    //Testler default olarak harf sirasina gore calisir. istersek priorty olusturabilirz, numara ekleyerek siralama yapariz.
    // verilen sayilar ardidik olmak zorunda degildir.
    // priority yazmadigimiz methodlarin priority=0 olarak kabul edilr. 0 lar numara verilenlerden once calisir.
    // priorty'si 0 olanlar birden fazla ise kendi arasinda harf sirasina gore siralama yapilir
    //



}
