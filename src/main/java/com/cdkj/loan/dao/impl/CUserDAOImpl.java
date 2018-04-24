package com.cdkj.loan.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.loan.dao.ICUserDAO;
import com.cdkj.loan.dao.base.support.AMybatisTemplate;
import com.cdkj.loan.domain.CUser;

@Repository("cuserDAOImpl")
public class CUserDAOImpl extends AMybatisTemplate implements ICUserDAO {

    // 注册
    @Override
    public int insert(CUser data) {
        return super.insert(NAMESPACE.concat("insert_cuser"), data);
    }

    @Override
    public int delete(CUser data) {
        return 0;
    }

    @Override
    public CUser select(CUser condition) {
        return super.select(NAMESPACE.concat("select_cuser"), condition,
            CUser.class);
    }

    @Override
    public long selectTotalCount(CUser condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_cuser"),
            condition);
    }

    @Override
    public List<CUser> selectList(CUser condition) {
        return super.selectList(NAMESPACE.concat("select_cuser"), condition,
            CUser.class);
    }

    @Override
    public List<CUser> selectList(CUser condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_cuser"), start, count,
            condition, CUser.class);
    }

}