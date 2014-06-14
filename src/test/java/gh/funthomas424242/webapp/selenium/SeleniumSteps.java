package gh.funthomas424242.webapp.selenium;

import gh.funthomas424242.lib.selenium.SeleniumPage;
import gh.funthomas424242.webapp.login.AnmeldeSeite;
import gh.funthomas424242.webapp.welcome.EntryPage;
import gh.funthomas424242.webapp.welcome.WelcomePage;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.web.selenium.PerStoriesWebDriverSteps;
import org.jbehave.web.selenium.WebDriverProvider;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;

public class SeleniumSteps extends PerStoriesWebDriverSteps {

    private SeleniumPage currentPage;

    public SeleniumSteps(final WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    @Override
    public void beforeStories() throws Exception {
        // super.beforeStories();
    }

    @Override
    public void afterStories() throws Exception {
        // super.afterStories();
    }

    @Given("sei die Webserver Übersichtsseite.")
    public void atServerHome() {
        this.currentPage = new EntryPage(getDriverProvider()).open();
    }

    @Given("sei als aktuelle Seite die Welcome Seite.")
    public void atWeclcomeFile() {
        this.currentPage = new WelcomePage(getDriverProvider()).open();
    }

    @Given("sei als aktuelle Seite die Loginseite.")
    public void atLoginFile() {
        this.currentPage = new AnmeldeSeite(getDriverProvider()).open();
    }

    @When("der erste Link geklickt wird")
    public void anElementIsAdded() {
        this.currentPage.findElement(By.tagName("a")).click();
    }

    @When("der Link $linkTitel geklickt wird")
    public void anElementIsAdded(final String linkText) {
        this.currentPage.findElement(By.linkText(linkText)).click();
    }

    @When("in das Textfeld $id der Text $text eingegeben wird")
    public void setInputText(final String id, final String text) {
        final HtmlInput textField = (HtmlInput) this.currentPage.findElement(By
                .id(id));
        textField.setTextContent(text);
    }

    @When("in das Passwordfeld $id der Text $text eingegeben wird")
    public void setPasswordText(final String id, final String text) {
        final HtmlPasswordInput passwordField = (HtmlPasswordInput) this.currentPage
                .findElement(By.id(id));
        passwordField.setTextContent(text);
    }

    @When("in das Textfeld $id nichts eingegeben wird")
    public void setInputTextEmpty(final String id) {
        final HtmlInput textField = (HtmlInput) this.currentPage.findElement(By
                .id(id));
        textField.setTextContent(null);
    }

    @When("in das Passwordfeld $id nichts eingegeben wird")
    public void setPasswordTextEmpty(final String id) {
        final HtmlPasswordInput passwordField = (HtmlPasswordInput) this.currentPage
                .findElement(By.id(id));
        passwordField.setTextContent(null);
    }

    @Then("enthält diese unter der Id $id eine Überschrift mit folgendem Text $text.")
    public void containsSection(final String id, final String text) {
        final String foundText = this.currentPage.findElement(By.id(id))
                .getText();
        Assert.assertEquals(text, foundText);
    }

    @Then("enthält diese ein Eingabefeld mit Id $id.")
    public void containsInputField(final String id) {
        final WebElement field = this.currentPage.findElement(By.id(id));
        Assert.assertNotNull(field);
    }

    @Then("enthält diese einen Button mit Text $text.")
    public void containsButton(final String text) {
        final String buttonText = this.currentPage.findElement(
                By.tagName("button")).getText();
        Assert.assertEquals(text, buttonText);
    }

    @Then("wird die Meldung $meldung angezeigt.")
    public void showMessageOK(final String meldung) {
        final String foundText = this.currentPage.findElement(
                By.cssSelector("css=div:contains('" + meldung + "')"))
                .getText();
        Assert.assertEquals(meldung, foundText);
    }

    @Then("wird zur Welcome Seite navigiert.")
    public void currentPageIsWelcome() {
        Assert.assertEquals(WelcomePage.PAGE_URL,
                this.currentPage.getCurrentUrl());
    }

    @Then("wird zur Login Seite navigiert.")
    public void currentPageIsLogin() {
        Assert.assertEquals(AnmeldeSeite.PAGE_URL,
                this.currentPage.getCurrentUrl());
    }

    @Then("wird zur Seite $fileName navigiert.")
    public void theResultingElementShouldBe(final String fileName) {
        Assert.assertEquals(fileName, this.currentPage.getCurrentUrl());
    }

}