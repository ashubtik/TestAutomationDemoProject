package test.framework.pages;

import org.openqa.selenium.WebDriver;

public class PageManager {
    private WebDriver driver;
    private LoginPage loginPage;
    private ContactListPage contactListPage;
    private AddContactPage addContactPage;
    private ContactDetailsPage contactDetailsPage;

    public PageManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        return loginPage == null ?
                loginPage = new LoginPage(driver) : loginPage;
    }

    public ContactListPage getContactListPage() {
        return contactListPage == null ?
                contactListPage = new ContactListPage(driver) : contactListPage;
    }

    public AddContactPage getAddContactPage() {
        return addContactPage == null ?
                addContactPage = new AddContactPage(driver) : addContactPage;
    }

    public ContactDetailsPage getContactDetailsPage() {
        return contactDetailsPage == null ?
                contactDetailsPage = new ContactDetailsPage(driver) : contactDetailsPage;
    }
}
