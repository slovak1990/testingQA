package ils03.ui.pojo;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.proxy.CaptureType;
import com.browserup.harreader.model.HarEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestHelper {
    private BrowserUpProxy bmp;
    private Map<String, String> requestBody = new HashMap<String, String>();

    @Step("Включить запись запросов/ответов браузера")
    public void on() {
        this.bmp = WebDriverRunner.getSelenideProxy().getProxy();
        this.bmp.setHarCaptureTypes(CaptureType.getAllContentCaptureTypes()); // запомнить тело запросов (тело не сохраняется по умолчанию, потому что оно может быть большим)
        this.bmp.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);// запоминаем как запросы, так и ответы
        this.bmp.newHar("pofig");// запускаем процесс сохранения данных передаваемых по сети
    }

    @Step("Вернуть текущий прокси браузера")
    public BrowserUpProxy getBrowserUpProxy() {
        return this.bmp;
    }

    @Step("Вернуть информацию о выполненных HTTP запросах")
    public List<HarEntry> getRequests() {
        return this.bmp.getHar().getLog().getEntries();
    }

    @Step("Получить запрос по эндпоинту")
    private HarEntry findMatchingEntry(String endpoint) {
        for (HarEntry entry : this.getRequests()) {
            if(entry.getRequest().getUrl().endsWith(endpoint) && !entry.getResponse().getContent().getText().isEmpty()) {
                return entry;
            }
        }
        return null;
    }

    @Step("Получить тело ответа")
    public String getBodyOfResponse(String endpoint){
        return findMatchingEntry(endpoint).getResponse().getContent().getText();
    }

    @Step("Очистить тело запроса от лишних символов и строк")
    public String[] cleanRequestBodyToArray(HarEntry bodyData) {
        return bodyData.getRequest()
                .getPostData()
                .getText()
                .replaceAll("\n", "")
                .replaceAll("\\s", "")
                .replaceAll("------We[a-zA-z0-9]+[-\\wa-zA-Z]+:form-data;name=|------[a-zA-Z0-9]+--", "")
                .split("\"");
    }

    @Step("Достать тело запроса по строке URL")
    public void fillDataFromRequestBodyByURL(String url) {
        List<HarEntry> requests = this.getRequests();
        for (HarEntry singleHTTPRequest : requests) {
            if (singleHTTPRequest.getRequest().getUrl().contains(url)) {
                String[] postData = this.cleanRequestBodyToArray(singleHTTPRequest);
                int startIndex = 0;
                if (postData[0].isEmpty()) startIndex = 1;
                for (int i = startIndex; i < postData.length; i = i + 2) {
                    requestBody.put(postData[i], postData[i + 1]);
                }
            }
        }
    }

    @Step("Вернуть тело запроса в виде пары ключ/значение")
    public Map<String, String> getRequestbody() {
        return this.requestBody;
    }

    @Step("Получить значение ссылки из HTML ответа")
    public String extractLinkValue(String content) {
        int startIndex = content.indexOf("https://release-tms.intelogis.ru/");
        if (startIndex != -1) {
            int endIndex = content.indexOf(" ", startIndex);
            if (endIndex == -1) {
                endIndex = content.length();
            }
            return content.substring(startIndex, endIndex);
        }
        return "";
    }
}