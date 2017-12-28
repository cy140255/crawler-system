package com.crawler.Service;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 14025 on 2017/9/13.
 */
@Service

public class FileLoadService {
    public void analysisUrl(String url,String filePath) {
        String hander = getUrlHander(url);
        InputStream inputStream = null;
        try {
            URL u = new URL(url);
            inputStream = u.openStream();
            InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bf = new BufferedReader(isr);
            String str;
            StringBuffer sbf = new StringBuffer();

            while ((str = bf.readLine()) != null) {
                sbf = sbf.append(str);
            }

            String html = sbf.toString();
            if (judgeJsAndCss(html)) {
                String htmlName = htmlName(html);
                File file = new File(filePath + htmlName);
                if (!file.exists()) {
                    file.mkdir();
                }
                html = downLoadJs(html, hander,filePath);
                html = downLoadCSS(html, hander,filePath);
                html = downLoadImages(html, hander,filePath);
                downLoadHTML(html,filePath);
            }

        } catch (Exception e) {
            e.getMessage();
        }

    }


    private Boolean judgeJsAndCss(String str) {
        if (str.contains("<link href=") || str.contains("src=")) {
            return true;
        } else {
            return false;
        }

    }


    private String getUrlHander(String url) {
        String[] arr = url.split("/");
        return arr[0] + "//" + arr[2];
    }


    private String downLoadJs(String html, String hander,String filePath) {

        Pattern pattern = Pattern.compile("<script\\b[^<]*(?:(?!<\\/script>)<[^<]*)*<\\/script>");
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            try {
                String script = matcher.group();
                if (script.contains("src=")) {
                    String[] arr = script.split("\"");
                    String jsUrl = null;
                                    
                    for (String str : arr) {
                        if (str.contains(".js")) {
                            jsUrl = str;
                        }
                    }
                    String name = jsUrl.split("/")[jsUrl.split("/").length - 1];
                    if (!jsUrl.contains("https:") && !jsUrl.contains("http:")) {
                        String jsName = jsUrl;
                        if (jsUrl.substring(0, 1).equals("/")) {
                            jsUrl = hander + jsUrl;
                        } else {
                            jsUrl = hander + "/" + jsUrl;
                        }
                        html = html.replace(jsName, name);
                    } else {

                        html = html.replace(jsUrl, name);
                    }

                    String htmlName = htmlName(html);
                    URL u = new URL(jsUrl);
                    InputStream jsInputStream = u.openStream();
                    FileOutputStream fileOutputStream = new FileOutputStream(filePath + htmlName + "/" + name);
                    int len = 0;
                    while ((len = jsInputStream.read()) != -1) {
                        fileOutputStream.write(len);
                    }
                    fileOutputStream.close();
                    fileOutputStream.flush();
                    jsInputStream.close();
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }

        return html;
    }

    private String downLoadCSS(String html, String hander,String filePath) {


        try {
            Pattern pattern = Pattern.compile("<link[^>]*?>.*?");
            Matcher matcher = pattern.matcher(html);
            while (matcher.find()) {
                String css = matcher.group();
                if (css.contains("rel=\"stylesheet\"")) {
                    String[] arr = css.split("\"");
                    Optional<String> optional = Arrays.stream(arr).filter(t -> t.contains(".css")).findAny();
                    if (optional.get() != null) {
                        String cssUrl = optional.get();
                        if (!cssUrl.contains("https:") && !cssUrl.contains("http:")) {


                            if (cssUrl.substring(0, 1).equals("/")) {
                                cssUrl = hander + cssUrl;
                            } else {
                                cssUrl = hander + "/" + cssUrl;
                            }

                        }

                        String name = cssUrl.split("/")[cssUrl.split("/").length - 1];
                        if (name.contains("?")) {
                            name = name.split("[?]")[0];
                        }


                        String htmlName = htmlName(html);
                        html = html.replace(optional.get(), name);
                        URL url = new URL(cssUrl);
                        InputStream inputStream = url.openStream();

                        FileOutputStream fileOutputStream = new FileOutputStream(filePath + htmlName + "/" + name);
                        int len = 0;
                        while ((len = inputStream.read()) != -1) {
                            fileOutputStream.write(len);
                        }
                        fileOutputStream.close();
                        fileOutputStream.flush();
                        inputStream.close();
                    }
                }
            }


        } catch (Exception e) {
            e.getMessage();
        }
        return html;
    }


    private String downLoadImages(String html, String hander,String filePath) {

        Pattern pattern = Pattern.compile("<img([^>]*)\\s*src=('|\\\")([^'\\\"]+)('|\\\")([>])|url([(].*?)([)])");
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            try {
                String getAllImages = matcher.group();
                String imagesUrl;
                if (getAllImages.contains("url")) {
                    imagesUrl = getAllImages.split("\'")[getAllImages.split("\'").length - 2];
                } else {
                    imagesUrl = getAllImages.split("\"")[getAllImages.split("\"").length - 2];
                }

                String imageName = imagesUrl.split("/")[imagesUrl.split("/").length - 1];
                if (!imagesUrl.contains("http:") && !imagesUrl.contains("https:")) {
                    String imagePath = imagesUrl;
                    if (imagesUrl.substring(0, 1).equals("/")) {
                        imagesUrl = hander + imagesUrl;
                    } else {
                        imagesUrl = hander + "/" + imagesUrl;
                    }
                    html.replace(imagePath, imageName);
                }
                html = html.replace(imagesUrl, imageName);
                String htmlName = htmlName(html);
                URL url = new URL(imagesUrl);
                InputStream inputStream = url.openStream();
                FileOutputStream fileOutputStream = new FileOutputStream(filePath + htmlName + "/" + imageName);
                int len = 0;
                while ((len = inputStream.read()) != -1) {
                    fileOutputStream.write(len);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return html;
    }


    private void downLoadHTML(String html,String filePath) {
        try {
            Pattern pattern = Pattern.compile("<title>.*</title>");
            Matcher matcher = pattern.matcher(html);
            String htmlName = null;
            while (matcher.find()) {
                htmlName = matcher.group().split("<title>")[1].split("</title>")[0].trim();
                if (htmlName != null) {
                    FileOutputStream fileOutputStream = new FileOutputStream(filePath + htmlName + "/" + htmlName + ".html");
                    byte[] bytes = new byte[1024];
                    bytes = html.getBytes("UTF-8");
                    fileOutputStream.write(bytes, 0, bytes.length);
                    fileOutputStream.close();
                    fileOutputStream.flush();
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }


    private String htmlName(String html) {
        try {
            Pattern pattern = Pattern.compile("<title>.*</title>");
            Matcher matcher = pattern.matcher(html);
            String htmlName = null;
            while (matcher.find()) {
                htmlName = matcher.group().split("<title>")[1].split("</title>")[0].trim();
            }
            return htmlName;
        } catch (Exception e) {
        }

        return null;
    }


    public String getHtmlName(String url) {
        try {
            URL u = new URL(url);
            InputStream inputStream = u.openStream();
            InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bf = new BufferedReader(isr);
            String str;
            StringBuffer sbf = new StringBuffer();
            while ((str = bf.readLine()) != null) {
                sbf = sbf.append(str);
            }

            String html = sbf.toString();
            String htmlName = htmlName(html);
            return htmlName;
        } catch (Exception e) {
        }
        return null;
    }
}
