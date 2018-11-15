package com.cdkj.loan.ao;

import java.util.List;

import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.domain.Brand;
import com.cdkj.loan.dto.req.XN630400Req;
import com.cdkj.loan.dto.req.XN630402Req;
import com.cdkj.loan.dto.req.XN630408Req;

public interface IBrandAO {
    String DEFAULT_ORDER_COLUMN = "code";

    // 新增品牌
    public void addBrand(XN630400Req req);

    // 修改品牌
    public void editBrand(XN630402Req req);

    // 上架品牌
    public void upBrand(String code, String updater, String remark);

    // 下架品牌
    public void downBrand(String code, String updater, String remark);

    // 分页查询
    public Paginable<Brand> queryBrandPage(int start, int limit,
            Brand condition);

    // 详情查询
    public Brand getBrand(String code);

    // 列表查询
    public List<Brand> queryBrandList(Brand condition);

    // 品牌刷新
    public void refreshBrand(XN630408Req req);

}
