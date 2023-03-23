package qa.guru.elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SearchInput {
    private SelenideElement searchInput = $("input.form-control");

    public SelenideElement getSearchInput() {
        return searchInput;
    }
}
