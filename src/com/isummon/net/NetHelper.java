package com.isummon.net;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class NetHelper {
    // WSDL文档的URL，192.168.17.156为PC的ID地址
    private final static String serviceUrl = "http://192.168.17.156:8080/axis2/services";
    private final static String namespace = "http://edu.fudan.10ss";

    public static void getAllActs() {
        // 定义调用的WebService方法名
        String methodName = "getAllActs";
        // 第1步：创建SoapObject对象，并指定WebService的命名空间和调用的方法名
        SoapObject request = new SoapObject(namespace, methodName);
        // 第2步：设置WebService方法的参数
        request.addProperty("testArg", "test");
        // 第3步：创建SoapSerializationEnvelope对象，并指定WebService的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        // 设置bodyOut属性
        envelope.bodyOut = request;

        // 第4步：创建HttpTransportSE对象，并指定WSDL文档的URL
        HttpTransportSE ht = new HttpTransportSE(serviceUrl);
        try {
            // 第5步：调用WebService
            ht.call(null, envelope);
            if (envelope.getResponse() != null) {
                // 第6步：使用getResponse方法获得WebService方法的返回结果
                SoapObject soapObject = (SoapObject) envelope.getResponse();
                // 通过getProperty方法获得Product对象的属性值
                String result = "产品名称：" + soapObject.getProperty("name") + "\n";
                result += "产品数量：" + soapObject.getProperty("productNumber")
                        + "\n";
                result += "产品价格：" + soapObject.getProperty("price");
                //blabalbla

            } else {
                //blabla
            }
        } catch (Exception e) {
            //blalalb
        }
    }

}
