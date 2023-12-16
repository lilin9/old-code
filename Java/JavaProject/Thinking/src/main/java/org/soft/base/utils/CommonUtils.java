package org.soft.base.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.soft.base.model.ResponseResult;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by LILIN on 2023/8/2/15:32:08
 * 通用工具类
 */
@Component
public class CommonUtils {
    private final RestTemplate restTemplate;
    public CommonUtils(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * @param sets   sets
     * @param prefix prefix
     * @Return 文章 id 列表
     * @Description 从 redis 取得的数据集，把里面数据的前缀去掉，得到 id 列表
     * @Author LILIN
     * @Date 2023/8/2 15:33:48
     */
    public List<Integer> removePrefix(Set<Object> sets, String prefix) {
        return sets.stream().map(item -> {
            String str = item.toString();
            //去掉前缀，转成 int
            return Integer.parseInt(str.replace(prefix, ""));
        }).collect(Collectors.toList());
    }

    /**
     * @param fileName 文件名
     * @param filePath 文件路径
     * @param content  内容
     * @Return
     * @Description 往磁盘写入文件
     * @Author LILIN
     * @Date 2023/8/3 13:42:17
     */
    public void fileWrite(String fileName, String filePath, String content) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath + "/" + fileName + ".txt"));
        bufferedWriter.write(content);
        bufferedWriter.close();
    }

    /**
     * @param filename 文件名
     * @param filePath 文件路径
     * @Return 返回读取的字符串
     * @Description 从磁盘读取文件
     * @Author LILIN
     * @Date 2023/8/3 13:58:11
     */
    public String fileRead(String filename, String filePath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath + "/" + filename + ".txt"));

        StringBuilder content = new StringBuilder();
        String temp;
        while ((temp = bufferedReader.readLine()) != null) {
            content.append(temp);
            content.append(System.lineSeparator()); // 添加换行符
        }

        return content.toString();
    }

    /**
     * @param hour hour
     * @param minute minute
     * @Return
     * @Description 判断当前时间是否是指定时间
     * @Author LILIN
     * @Date 2023/8/3 14:35:18
     */
    public boolean isSpecificTime(long hour, long minute) {
        Calendar now = Calendar.getInstance(); // 获取当前时间

        int currentHour = now.get(Calendar.HOUR_OF_DAY); // 获取当前小时
        int currentMinute = now.get(Calendar.MINUTE); // 获取当前分钟

        return currentHour == hour && currentMinute == minute;
    }

    /**
     * @param list list
     * @Return
     * @Description 将 List 列表转换成指定格式的字符串
     * @Author LILIN
     * @Date 2023/8/8 14:26:36
     */
    public String convertListToString(List<Integer> list) {
        StringBuilder result = new StringBuilder();
        list.forEach(item -> {
            result.append(item).append("\n");
        });
        return result.toString();
    }

    /**
     * @Return
     * @Description 获取当前时间并格式化
     * @Author LILIN
     * @Date 2023/8/8 14:27:23
     */
    public String getNowDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        return format.format(date);
    }

    /**
     * @param request request
     * @Return
     * @Description 获取请求的 IP 地址
     * @Author LILIN
     * @Date 2023/8/8 15:14:26
     */
    public String getIpAddress(HttpServletRequest request) {
        String ipAddress;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress="";
        }
        return ipAddress;
    }

    /**
     * @param url 请求路径
     * @param method 请求方法：get、post等
     * @param data 发送的数据
     * @Return
     * @Description 向指定路径发送 restful 请求
     * @Author LILIN
     * @Date 2023/8/8 20:04:45
     */
    public <T> ResponseResult<Object> httpRequest(String url, HttpMethod method, T data) {
        //定义请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        //定义请求实体
        HttpEntity<T> requestEntity = new HttpEntity<>(data, headers);

        //发送 request 请求
        ResponseEntity<ResponseResult> responseEntity = restTemplate.exchange(url, method, requestEntity, ResponseResult.class);
        //判断是否发送成功
        ResponseResult<Object> responseResult = null;
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            responseResult = responseEntity.getBody();
        } else {
            System.out.println("消息发送失败");
        }

        return responseResult;
    }
}
