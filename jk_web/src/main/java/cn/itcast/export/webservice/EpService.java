
package cn.itcast.export.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "EpService", targetNamespace = "http://webservice.export.itcast.cn/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EpService {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     * @throws Exception_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "exportE", targetNamespace = "http://webservice.export.itcast.cn/", className = "cn.itcast.export.webservice.ExportE")
    @ResponseWrapper(localName = "exportEResponse", targetNamespace = "http://webservice.export.itcast.cn/", className = "cn.itcast.export.webservice.ExportEResponse")
    public String exportE(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0)
        throws Exception_Exception
    ;

}
