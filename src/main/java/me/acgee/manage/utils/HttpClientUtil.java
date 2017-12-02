package me.acgee.manage.utils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * http/https get/post请求
 * @author 赵云鹏
 *
 */
public class HttpClientUtil {
	private RequestConfig requestConfig = RequestConfig.custom()  
            .setSocketTimeout(8000)  
            .setConnectTimeout(8000)  
            .setConnectionRequestTimeout(8000)  
            .build(); 
	static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
    private static HttpClientUtil instance = null;  
    private HttpClientUtil(){}  
    /**
     * 获取实例
     * @return HttpClientUtil
     */
    public static HttpClientUtil getInstance(){  
        if (instance == null) {  
            instance = new HttpClientUtil();  
        }  
        return instance;  
    }  
      
    /** 
     * 发送 post请求 
     * @param httpUrl 地址 
     * @return string
     */  
    public String sendHttpPost(String httpUrl) {  
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost    
        return sendHttpPost(httpPost);  
    }  
    
    /** 
     * 发送 post请求 (表单形式)
     * @param httpUrl 地址 
     * @param params 参数(格式:key1=value1&key2=value2) 
     * @return string
     */  
    public String sendHttpPost(String httpUrl, String params) {  
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost    
        try {  
            //设置参数  
            StringEntity stringEntity = new StringEntity(params, "UTF-8");  
            stringEntity.setContentType("application/x-www-form-urlencoded");  
            httpPost.setEntity(stringEntity);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return sendHttpPost(httpPost);  
    }
    /**
     * 发送post请求(json)
     * @param httpUrl 地址
     * @param params 参数({"key":"value",...}||[{"key":"value",...},{"key":"value",...},...])
     * @return string
     */
    public String sendHttpPostData(String httpUrl,String params){
    	HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
    	try {
			//设置参数
    		StringEntity stringEntity = new StringEntity(params, "UTF-8");
    		stringEntity.setContentType("application/json");
    		httpPost.setEntity(stringEntity);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return sendHttpPost(httpPost);
    }
    /**
     * 发送post请求(xml)
     * @param httpUrl 地址
     * @param params 参数(<xml><a>***</a>...</xml>)
     * @return string
     */
    public String sendHttpPostXml(String httpUrl,String params){
    	HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
    	try {
			//设置参数
    		StringEntity stringEntity = new StringEntity(params, "UTF-8");
    		stringEntity.setContentType("application/xml");
    		httpPost.setEntity(stringEntity);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return sendHttpPost(httpPost);
    }
    /** 
     * 发送 post请求 
     * @param httpUrl 地址 
     * @param maps 参数 
     * @return string
     */  
    public String sendHttpPost(String httpUrl, Map<String, String> maps) {  
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost    
        // 创建参数队列    
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();  
        for (String key : maps.keySet()) {  
            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));  
        }  
        try {  
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return sendHttpPost(httpPost);  
    }  
      
      
    /** 
     * 发送 post请求（带文件） 
     * @param httpUrl 地址 
     * @param maps 参数 
     * @param fileLists 附件 
     * @return string
     */  
    /*public String sendHttpPost(String httpUrl, Map<String, String> maps, List<File> fileLists) {  
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost    
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();  
        for (String key : maps.keySet()) {  
            meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));  
        }
        for(File file : fileLists) {  
            FileBody fileBody = new FileBody(file);  
            meBuilder.addPart("files", fileBody);  
        }  
        HttpEntity reqEntity = meBuilder.build();  
        httpPost.setEntity(reqEntity);  
        return sendHttpPost(httpPost);  
    } */
      
    /** 
     * 发送Post请求 
     * @param httpPost 
     * @return string
     */  
    private String sendHttpPost(HttpPost httpPost) {  
        CloseableHttpClient httpClient = null;  
        CloseableHttpResponse response = null;  
        HttpEntity entity = null;  
        String responseContent = null;  
        try {  
            // 创建默认的httpClient实例.  
            httpClient = HttpClients.createDefault();  
            httpPost.setConfig(requestConfig);  
            // 执行请求  
            log.info("发送post请求-------------"+httpPost.getURI());
            while(httpPost.getEntity()!=null){
            	log.info("post数据-------------"+EntityUtils.toString(httpPost.getEntity(),"UTF-8"));
            	break;
            }
            response = httpClient.execute(httpPost);  
            entity = response.getEntity();  
            responseContent = EntityUtils.toString(entity, "UTF-8"); 
            log.info("返回-----------"+responseContent);
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                // 关闭连接,释放资源  
                if (response != null) {  
                    response.close();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return responseContent;  
    }  
  
    /** 
     * 发送 get请求 
     * @param httpUrl 
     * @return string
     */  
    public String sendHttpGet(String httpUrl) {  
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求  
        return sendHttpGet(httpGet);  
    }  
      
    /** 
     * 发送 get请求Https 
     * @param httpUrl 
     * @return string
     */  
    public String sendHttpsGet(String httpUrl) {  
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求  
        return sendHttpsGet(httpGet);  
    }  
      
    /** 
     * 发送Get请求 
     * @param httpPost 
     * @return string
     */  
    private String sendHttpGet(HttpGet httpGet) {  
        CloseableHttpClient httpClient = null;  
        CloseableHttpResponse response = null;  
        HttpEntity entity = null;  
        String responseContent = null;  
        try {  
            // 创建默认的httpClient实例.  
            httpClient = HttpClients.createDefault();  
            httpGet.setConfig(requestConfig);  
            // 执行请求  
            log.info("发送get请求-------------"+httpGet.getURI());
            response = httpClient.execute(httpGet);  
            entity = response.getEntity();  
            responseContent = EntityUtils.toString(entity, "UTF-8");  
            log.info("返回-----------"+responseContent);
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                // 关闭连接,释放资源  
                if (response != null) {  
                    response.close();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return responseContent;  
    }  
      
    /** 
     * 发送Get请求Https 
     * @param httpPost 
     * @return string
     */  
    private String sendHttpsGet(HttpGet httpGet) {  
        CloseableHttpClient httpClient = null;  
        CloseableHttpResponse response = null;  
        HttpEntity entity = null;  
        String responseContent = null;  
        try {  
            // 创建默认的httpClient实例.  
            PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader.load(new URL(httpGet.getURI().toString()));  
            DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher);  
            httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier).build();  
            httpGet.setConfig(requestConfig);  
            // 执行请求  
            response = httpClient.execute(httpGet);  
            entity = response.getEntity();  
            responseContent = EntityUtils.toString(entity, "UTF-8");  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                // 关闭连接,释放资源  
                if (response != null) {  
                    response.close();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return responseContent;  
    }  
}
