package com.cdkj.loan.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.domain.RepointDetail;
import com.cdkj.loan.dto.req.XN632290Req;
import com.cdkj.loan.dto.res.XN632290Res;

/**
 * 
 * @author: jiafr 
 * @since: 2018年6月16日 下午2:43:42 
 * @history:
 */
@Component
public interface IRepointDetailAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addRepointDetail(RepointDetail data);

    public int dropRepointDetail(String code);

    public int editRepointDetail(RepointDetail data);

    public Paginable<RepointDetail> queryRepointDetailPage(int start,
            int limit, RepointDetail condition);

    public List<RepointDetail> queryRepointDetailList(RepointDetail condition);

    public RepointDetail getRepointDetail(String code);

    // 展示返点明细
    public List<XN632290Res> showRepointDetail(XN632290Req req, Long loanAmount);

    // 列表查询返点明细 按汽车经销商编号和返点账号编号
    public Object queryRepointDetailListByCarDealerCode(RepointDetail condition);

}
