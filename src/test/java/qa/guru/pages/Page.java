package qa.guru.pages;

import com.codeborne.selenide.SelenideElement;
import qa.guru.elements.SearchInput;

import static com.codeborne.selenide.Selenide.$;


public abstract class Page {
    public String pageURL;
    public SelenideElement searchInput = new SearchInput().getSearchInput();

    public Page(String pageURL) {
        this.pageURL = pageURL;
    }
}
