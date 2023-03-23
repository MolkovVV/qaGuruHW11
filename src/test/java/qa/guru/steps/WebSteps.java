package qa.guru.steps;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.ownText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WebSteps {
    @Step ("Открываем главную страницу Githab")
    public void openMainPage() {open("https://github.com/");}

    @Step ("Отправляем поисковой запрос с текстом \"{query}\"")
    public void searchRepos(String query){$("input.form-control").setValue(query).submit();}

    @Step("Из поисковой выдачи переходим в репозиторий с текстом: {nameRepo}")
    public void clickOnRepoFromSearch(String nameRepo) {
        $$("a.v-align-middle").findBy(ownText(nameRepo)).click();
    }

    @Step("Переходим в табу \"Issue\"")
    public void clickTabIssue(){$("#issues-tab").click();}

    @Step("В табе \"Issue\" отображается issue c id: {idIssues}  и с name: {namesIssues}")
    public void  ckeckIssueNameInRepo(String idIssue, String nameIssue){
        $("#issue_" + idIssue + "_link").shouldHave(text(nameIssue));
        Allure.getLifecycle().addAttachment(
                "Исходники страницы",
                "text/html",
                "html",
                WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
        );
    }
}
