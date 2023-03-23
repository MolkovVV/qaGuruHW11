package qa.guru.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class MainPage extends Page {
    public String pageURL;

    public MainPage(String pageURL) {
        super(pageURL);
    }

    public SelenideElement headerOnPage = $("h1.h0-mktg");
    public String actualTextInHeader = "\n" +
            "          Let’s build from here\n" +
            "        ";

}
