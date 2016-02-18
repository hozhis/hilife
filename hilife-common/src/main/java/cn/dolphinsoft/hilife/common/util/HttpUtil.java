package cn.dolphinsoft.hilife.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.dolphinsoft.hilife.common.constant.ApplicationConstant;
import cn.dolphinsoft.hilife.common.exception.HiLifeException;

/**
 * Class Name: HttpRequestUtil Description: 发送http请求
 * 
 * @author hozhis
 *
 */
public class HttpUtil {

    public static Object doPostForJson(String requestUrl, String params) {
        String result = doPost(requestUrl, ApplicationConstant.JSON_CONTENT_TYPE, params);
        return HiLifeUtil.parseStringToObj(result, Object.class);
    }

    public static <T> T doPostForJson(String requestUrl, Object obj, Class<T> valueType) {
        String result = doPost(requestUrl, ApplicationConstant.JSON_CONTENT_TYPE, HiLifeUtil.parseObjTOString(obj));
        return HiLifeUtil.parseStringToObj(result, valueType);
    }

    public static Object doPostForForm(String requestUrl, String params) {
        String result = doPost(requestUrl, ApplicationConstant.FORM_CONTENT_TYPE, params);
        return HiLifeUtil.parseStringToObj(result, Object.class);
    }

    public static <T> Object doPostForForm(String requestUrl, String params, Class<T> valueType) {
        String result = doPost(requestUrl, ApplicationConstant.FORM_CONTENT_TYPE, params);
        return HiLifeUtil.parseStringToObj(result, valueType);
    }

    public static String doPost(String requestUrl, String contentType, String params) {
        StringBuffer buffer = new StringBuffer();
        HttpURLConnection httpUrlConn = null;
        try {
            URL url = new URL(requestUrl);
            httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setRequestProperty(ApplicationConstant.CONTENT_TYPE, contentType);
            httpUrlConn.setRequestMethod(ApplicationConstant.POST);
            // 当有数据需要提交时
            if (null != params) {
                try (OutputStream outputStream = httpUrlConn.getOutputStream()) {
                    // 注意编码格式，防止中文乱码
                    outputStream.write(params.getBytes(ApplicationConstant.ENCODING));
                }
            }
            // 将返回的输入流转换成字符串
            try (InputStream inputStream = httpUrlConn.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream,
                            ApplicationConstant.ENCODING);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                String str = null;
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new HiLifeException(e);
        } finally {
            if (null != httpUrlConn) {
                httpUrlConn.disconnect();
            }
        }
        return buffer.toString();
    }

    public static String doGet(String requestUrl) {
        StringBuffer buffer = new StringBuffer();
        HttpURLConnection httpUrlConn = null;
        try {
            URL url = new URL(requestUrl);
            httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoOutput(false);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setRequestMethod(ApplicationConstant.GET);
            httpUrlConn.connect();
            // 将返回的输入流转换成字符串
            try (InputStream inputStream = httpUrlConn.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream,
                            ApplicationConstant.ENCODING);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                String str = null;
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != httpUrlConn) {
                httpUrlConn.disconnect();
            }
        }
        return buffer.toString();
    }
}
