package qa.guru.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import qa.guru.pages.Page;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {
    @Step("Открываем страницу по url {page.pageURL}" )
    public void openPage(Page page){
        open(page.pageURL);
    }

    @Step("Отправляем поисковой запрос с текстом {query}")
    public  void  sendSearchQuery(String query, Page page){
        page.searchInput.setValue(query).submit();
    }

    @Step("Проверим наличие текста \"{text}\" на странице")
    public void checkElementOnPage(SelenideElement element,String text){
        element.shouldHave(Condition.text(text));
    }

    @Step("Выбрать элемент из списка, содержащего текст {text}")
    public void checkElementFromSearchList(ElementsCollection elementLink, String text){
        elementLink.findBy(Condition.text(text)).click();
    }

    @Step("Клик по элементу {element}")
    public void clickOnElement(SelenideElement element){
        element.click();
    }

    @Step("Issue c id {id} содержит name: {name}")
    public void checkRepoById(String id, String name){

        $("#issue_"+id+"_link").shouldHave(Condition.text(name));
        Allure.getLifecycle().addAttachment(
                "Исходники страницы",
                "text/html",
                "html",
                WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
        );
    }
}
