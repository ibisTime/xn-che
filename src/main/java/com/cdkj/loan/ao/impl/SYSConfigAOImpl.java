package com.cdkj.loan.ao.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.loan.ao.ISYSConfigAO;
import com.cdkj.loan.bo.ISYSConfigBO;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.common.SysConstants;
import com.cdkj.loan.domain.SYSConfig;

/**
 * @author: Gejin 
 * @since: 2016年4月17日 下午7:32:28 
 * @history:
 */
@Service
public class SYSConfigAOImpl implements ISYSConfigAO {
    @Autowired
    ISYSConfigBO sysConfigBO;

    @Override
    public void editSYSConfig(Long id, String cvalue) {
        SYSConfig sysConfig = sysConfigBO.getSYSConfig(id);
        sysConfig.setCvalue(cvalue);
        sysConfig.setUpdateDatetime(new Date());
        sysConfigBO.refreshSYSConfig(sysConfig);
    }

    @Override
    public Paginable<SYSConfig> querySYSConfigPage(int start, int limit,
            SYSConfig condition) {
        return sysConfigBO.getPaginable(start, limit, condition);
    }

    @Override
    public SYSConfig getSYSConfig(Long id) {
        return sysConfigBO.getSYSConfig(id);
    }

    @Override
    public SYSConfig getSYSConfig(String key) {
        return sysConfigBO.getSYSConfig(key);
    }

    @Override
    public Map<String, String> getSYSConfigMap(String type) {
        return sysConfigBO.getConfigsMap(type);
    }

    @Override
    public void editGpsLimitNumber(String number) {
        SYSConfig data = sysConfigBO
            .getSYSConfig(SysConstants.PERSONAL_APPLICATION_LIMIT);
        data.setCvalue(number);
        data.setUpdateDatetime(new Date());
        sysConfigBO.refreshSYSConfig(data);
    }
}
