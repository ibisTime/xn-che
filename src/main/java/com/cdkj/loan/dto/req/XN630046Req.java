/**
 * @Title XNlh5033Req.java 
 * @Package com.xnjr.moom.dto.req 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午10:43:57 
 * @version V1.0   
 */
package com.cdkj.loan.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午10:43:57 
 * @history:
 */
public class XN630046Req {
    // id （必填）
    @NotBlank(message = "Id不能为空")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
