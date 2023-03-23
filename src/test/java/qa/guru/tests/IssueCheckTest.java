package qa.guru.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qa.guru.pages.MainPage;
import qa.guru.pages.Page;
import qa.guru.pages.RepoPage;
import qa.guru.pages.SearchResultsPage;
import qa.guru.steps.WebSteps;

import java.util.stream.Stream;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class IssueCheckTest {
    static Stream<Arguments> methodSourceValues() {
        return Stream.of(
                Arguments.of("selenide", "2220" ,"Feature request:  Condition.innerText() / Condition.innerHtml()"),
                Arguments.of("selenide","2213","Implement method inNewBrowser(lambda)"),
                Arguments.of("selenide","2205","Add should(Condition.startsWithText()) and should(Condition.endsWithText())"),
                Arguments.of("-appium", "110", "Selenide trying to find element multiple time for logging purpose"),
                Arguments.of("-appium", "105", "Add custom condition and collection condition for validation"),
                Arguments.of("-appium", "102", "Wrapper method in Selenide Appium to switchToSpecificContext"));
    }
    @Feature("Listener")
    @ParameterizedTest(
            name = "В репозитории {0} должна содержаться issue c id {1} и с name: {2}"
    )
    @MethodSource("methodSourceValues")

    public void checkIssuesNamesListener(String nameRepo, String idIssues, String namesIssues){
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");
        $("input.form-control").setValue("Selenide").submit();
        $$("a.v-align-middle").findBy(ownText(nameRepo)).click();
        $("#issues-tab").click();
        $("#issue_" + idIssues + "_link").shouldHave(text(namesIssues));
    }

    @Feature("Lambda")
    @ParameterizedTest(
            name = "В репозитории {0} должна содержаться issue c id {1} и с name: {2} "
    )
    @MethodSource("methodSourceValues")

    public void checkIssuesNamesLambda(String nameRepo, String idIssues, String namesIssues){
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу Githab",()->{
            open("https://github.com/");
        });

        step("Отправляем поисковой запрос с текстом \"Selenide\"",()->{
            $("input.form-control").setValue("Selenide").submit();
        });

        step("Из поисковой выдачи переходим в репозиторий с текстом " + nameRepo,()-> {
            $$("a.v-align-middle").findBy(ownText(nameRepo)).click();
        });

        step("Переходим в табу \"Issue\"",()->{
            $("#issues-tab").click();
        });

        step("В табе \"Issue\" отображается issue c id " + idIssues + " и с name: "+ namesIssues,()->{
            $("#issue_" + idIssues + "_link").shouldHave(text(namesIssues));
        });
    }

    @Feature("WebSteps")
    @ParameterizedTest(
            name = "В репозитории {0} должна содержаться issue c id {1} и с name: {2} "
    )
    @MethodSource("methodSourceValues")

    public void checkIssuesNamesWebSteps(String nameRepo, String idIssues, String namesIssues){
        SelenideLogger.addListener("allure", new AllureSelenide());
        MainPage page = new MainPage("https://github.com/");
        SearchResultsPage searchPage = new SearchResultsPage();
        RepoPage repoPage = new RepoPage();
        WebSteps steps = new WebSteps();

        steps.openPage(page);
        steps.checkElementOnPage(page.headerOnPage,page.actualTextInHeader);
        steps.sendSearchQuery("Selenide",page);
        steps.checkElementFromSearchList(searchPage.elementsRepoLinks,nameRepo);
        steps.clickOnElement(repoPage.issueTab);
        steps.checkRepoById(idIssues,namesIssues);
    }
}
