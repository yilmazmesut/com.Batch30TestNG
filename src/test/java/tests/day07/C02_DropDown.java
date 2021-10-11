package tests.day07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class C02_DropDown {

    // Dropdown'lar %90 <select> tag'i ile baslar. uzerine tiklayinca calisir. yani mause actions gerektirir.
    //  3 yerden locator yapabiliriz:
    // 1. vizible(gorunur) text
    // 2. index
    // 3. value'si

    // SORU: Bir class oluşturun: DropDown
    //● https://the-internet.herokuapp.com/dropdown adresine gidin.


    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void test() {
        driver.get("https://the-internet.herokuapp.com/dropdown");

        //    1.Index kullanarak Seçenek 1’i (Option 1) seçin ve yazdırın
        // 1. adim: dropdown' locate et:
        WebElement dropDown = driver.findElement(By.id("dropdown"));
        // 2. adim: Select class'i kullanarak bir obje olustur ve parametre olarak locate ettigimiz elementi yaz
        Select select = new Select(dropDown);
        // istedigimiz option'i select objesi kullanarak sec:
        select.selectByIndex(1);
        // once seciyoruz, sonra GetFirstSelectedOption() ile yazdiriyoruz. en son ne secili ise onu yazdirir:
        System.out.println(select.getFirstSelectedOption().getText());


        //    2.Value kullanarak Seçenek 2'yi (Option 2) seçin ve yazdırın:
        select.selectByValue("2");  // kod'da "" icerisinde yazildigi icin string olarak yazdik.
        System.out.println(select.getFirstSelectedOption().getText());

        //    3.Visible Text(Görünen metin) kullanarak Seçenek 1’i (Option 1) seçin ve yazdırın:
        select.selectByVisibleText("Option 1");
        System.out.println(select.getFirstSelectedOption().getText());

        //    4.Tüm dropdown değerleri(value) yazdırın
        List<WebElement> tumOpsiyonlar =select.getOptions();
        System.out.println("Tum Opsiyonlar Listesi");
        for (WebElement w : tumOpsiyonlar) {
            System.out.println(w.getText());
        }

        // 5. Dropdown'un boyutunu bulun, Dropdown'da 4 oge varsa konsolda True, degilse False yazdirin
        System.out.println();

    }


    @AfterMethod
    public void tearDown() {
        driver.close();
    }

}
