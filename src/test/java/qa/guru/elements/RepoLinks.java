package qa.guru.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class RepoLinks {
    private ElementsCollection repoLinks =$$("a.v-align-middle");

    public ElementsCollection getRepoLinks() {
        return repoLinks;
    }
}
