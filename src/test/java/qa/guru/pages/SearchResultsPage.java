package qa.guru.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import qa.guru.elements.RepoLinks;
import qa.guru.elements.SearchInput;

public class SearchResultsPage{
    public SelenideElement searchInput = new SearchInput().getSearchInput();
    public ElementsCollection elementsRepoLinks = new RepoLinks().getRepoLinks();
}
