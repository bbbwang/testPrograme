package com.lenkee.app;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by amettursun on 2019/8/14.
 */

/**
 *           通过Jsoup的connect方法获取Connection对象
 *           再用Connection对象的execute方法获取Connection.Response对象
 *           使用Response对象，即可获取html原始文本内容
 */
public class JsoupDemo {

    public static final String WEBSITE = "https://github.corp.ebay.com/eBayMobile/ios_core/tree/hgaskins/MOBIDENT-5249/coreapp/Resources";  // 站点URL，注意要加上协议(http://)

    public static Map<String, String> cookies = new HashMap<String, String>(); // cookie

    static {
        cookies.put("Cookie", "cid=mDernkupdIzeZd9r%23541494255; _octo=GH1.1.156005780.1552465486; _ga=GA1.2.1503637214.1557887947; " +
                "lucky9=9441179; _gh_render=BAh7B0kiD3Nlc3Npb25faWQGOgZFVEkiRTQ3YmUyYzAwZjMxZDdmM2NiNTVh%0AMGFiNWI4ZGQ1MTcxMTAxN2E4MDI2ODk0ZjE4MTZiNDJkNjUzNmI4NTY1NDAG%0AOwBGSSIPdXNlcl9sb2dpbgY7AEZJIg9hbWV0dHVyc3VuBjsAVA%3D%3D%0A--df5894e944cff4605790c0de9e8c30557d9f328d; " +
                "tz=Asia%2FShanghai; ds1=ats/1565334958344; " +
                "shs=BAQAAAWyDH9jsAAaAAVUADF8uUS42MzkyODQ4MDA2LDJjvqNQHgtF9xyj+WNvHGILAeVXKw**; " +
                "cssg=83779a0616c0a95b1872e21bfff873ae; ds2=; " +
                "ns1=BAQAAAWyDH9jsAAaAAKUAQV8yCA0xMzgxNzQ4MTE2LzA7MTM4MTc0ODEzMC8wOzEzNzYzOTQxODAvMDsxMzc2Mzg3MzI4LzA7MTM4MTc0ODEwOC8wO8f1mmS1i8xvNPHkQ0v8ovBtI1M8; dp1=bkms/in61133b8d^u1f/qibau5f32080d^exc/0%3A0%3A2%3A25d78618d^u1p/dHhuX2F1X2dzdF9idXllcjE*5f32080d^bl/AU61133b8d^expt/00015650929190115e3a05f8^pbf/%231301000000c000e000008180820000045f32080d^; " +
                "s=BAQAAAWyDH9jsAAWAAPgAIF1SJg02NmNlMzIzYTE2YzBhNGUxNGE2NGFkOWZmZmZmZmIxMwFFAAhfMggNNWQ0MWQ0N2UA7gA+XVImDTE0Bmh0dHBzOi8vdGVzdHJhaWwubTMuZWJheS5jb20vaW5kZXgucGhwPy9jYXNlcy92aWV3LzIxODUwOTAH7r3Jjm9MvftzBTZ9SIH52RG3rvY*; " +
                "nonsession=BAQAAAWyDH9jsAAaAAJ0ACF8yCA0wMDAwMDAwMQFkAAZfMggNIzAwMDBhAAgAHF14YY0xNTY1NTczNzU3eDI2MDAxMjUzNzUxNHgweDJOADMACF8yCA0yMDAwLEFVUwDLAAFdUNuVOABAABFfMggNdHhuX2F1X2dzdF9idXllcjEAEAARXzIIDXR4bl9hdV9nc3RfYnV5ZXIxAMoAIGa21g02NmNlMzIzYTE2YzBhNGUxNGE2NGFkOWZmZmZmZmIxMwAEABFfLlEudHhuX2F1X2dzdF9idXllcjEAnAA4XzIIDW5ZK3NIWjJQckJtZGo2d1ZuWStzRVoyUHJBMmRqNkFIbUlPbURKaURvUVNkajZ4OW5ZK3NlUT09m3XBSzOmc9zVzJxf6opR2u5ZVLk*; " +
                "ebay=%5EsfLMD%3D0%5Esin%3Din%5Edv%3D5d4ce650%5Esbf%3D%2310000100004%5Ecos%3D3%5Ecv%3D15555%5Ejs%3D1%5E; " +
                "npii=btguid/66ce323a16c0a4e14a64ad9ffffffb1361133b93^cguid/66ce51ba16c0a95afbd2912fffc99dd661133b93^; user_session=3C3pIMSRac3cTBKP_3WJkkO6sxBj5bUvPnwT4Ldw4J9ua3vU; __Host-user_session_same_site=3C3pIMSRac3cTBKP_3WJkkO6sxBj5bUvPnwT4Ldw4J9ua3vU; " +
                "private_mode_user_session=BaUTGnJUVNtyA0So6S8yuflwxouOTzJNAsB8cmpW5SK2TMoZ; logged_in=yes; dotcom_user=amettursun; has_recent_activity=1; " +
                "_fi_sess=Nmd1TFdQTTZJSWdsTEFuWU9XdVdJN0tlWnJwMEQ3aWt0SVdsRWRaayswa1BBNi9ZOTYvdmtacHpKSExLNk8wYWU5cXZMcU8zNFFpNXJ4ZnBsWnVOY2xUZGo0UFNnZ3ZjbmRoaWQ3ZWJ5ZXlKOW52K2N0N3c5ejQ1cWJUR3h3eHdIL3V2eTZTWUd4SVR3TUVsS3BON3NkYStDeWdSQkpxMDlZc1BEcVR1UVRldHhVZjJkZ0lyeDdSdXpDVEJQMFhCWjhlcmx5anZhUE9pK3hFVFhhK3hMNmVVY1Z1NmRCK2JFNEtwMCtkK1M4aStiL25lOW41dE52NjJsRXpRbEsxUy0tRzhqcDNLeWZFM1JaWnRiNHo1eEFqQT09--fb6069c13145e708d6cee72e21a013d285d43bb4"); // 初始化cookie
    }

    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect(WEBSITE).cookies(cookies).get();
        System.out.println(doc.title());
        Elements newsHeadlines = doc.select("#js-repo-pjax-container table tbody tr");
        for (Element headline : newsHeadlines) {
            Elements select = headline.select(".content span a");
            System.out.println(select.attr("title"));
        }
//        getHtml();

    }

    public static void getHtml(){
        try {
            Connection.Response response = Jsoup.connect(WEBSITE)
                    .cookies(cookies)
                    .postDataCharset("UTF-8")
                    .execute();
            String body = response.body(); // 获取html原始文本内容
            System.out.println(body);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String getNestedHtml(String raw_html){
        String html = raw_html.replace("\\","");
        Document doc = Jsoup.parse(html);
        //get the script tag
        Elements scripts = doc.getElementsByTag("script");
        //pattern for extracting html
        Pattern pttrn = Pattern.compile("\"html\":\"");
        String nested_html = "";
        for (Element script:scripts){
            Matcher m =  pttrn.matcher(html = script.html());
            if(m.find()){
                nested_html += html.substring(m.end(), html.length() -3);
            }
        }
        return nested_html;
    }
}