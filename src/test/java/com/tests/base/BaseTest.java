package com.tests.base;

import com.config.ConfigurationReader;
import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.models.UserModel;
import com.pages.LoginPage;
import com.pages.ProductsPage;
import com.utils.BrowserManager;
import com.utils.PageFactory;
import com.utils.UserManager;
import io.qameta.allure.Attachment;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

/** this annotation needed for create this class one time for saving resources */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;
    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected UserModel userForTest;

    @BeforeAll
    public void init() {
        playwright = Playwright.create();
        browser = BrowserManager.getBrowser(playwright);

        AllureEnvironmentWriter.allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("System Name", "os.name")
                        .put("System Version", "os.version")
                        .put("Browser", ConfigurationReader.config().browserToLaunch().toUpperCase())
                        .put("Url", ConfigurationReader.config().appUrl())
                        .build(),
                ConfigurationReader.config().allureResultsDir().concat("/")
        );

        userForTest = UserManager.getUserForTest();
    }

    @BeforeEach
    public void createBrowserContext() {
        if (ConfigurationReader.config().isNeedToRecordVideo()) {
            browserContext = browser.newContext(
                    new Browser.NewContextOptions()
                            .setRecordVideoDir(Paths.get(ConfigurationReader.config().videoResultDir())));
        }
        browserContext = browser.newContext();
        page = browserContext.newPage();
        loginPage = PageFactory.createPageInstance(page, LoginPage.class);
        loginPage.open();

        if (ConfigurationReader.config().isUserNeedToBeLogin()) {
            productsPage = loginPage.login(userForTest);
        }
    }

    /** we cannot put call captureVideoOnFailure() in the callback because firstly we need to all data for video and close context*/
    @AfterEach
    public void attachVideo() {
        browserContext.close();

        if (ConfigurationReader.config().isNeedToRecordVideo()) {
            captureVideoOnFailure();
        }
    }

    @AfterAll
    public void close() {
        browser.close();
        playwright.close();
    }

    /** we try to catch exception and add screenshot and video on failure step*/
    @RegisterExtension
    AfterTestExecutionCallback callback =
            extensionContext -> {
                Optional<Throwable> exception = extensionContext.getExecutionException();

                if (exception.isPresent()) {
                    if (ConfigurationReader.config().isNeedToRecordVideo()) {
                        captureVideoOnFailure();
                    }
                    captureScreenshotOnFailure();
                }
            };

    @Attachment(value = "TestVideo", type = "video/webm")
    @SneakyThrows
    private byte[] captureVideoOnFailure() {
        return Files.readAllBytes(page.video().path());
    }

    @Attachment(value = "TestFail", type = "image/png")
    private byte[] captureScreenshotOnFailure() {
        return page.screenshot();
    }
}
