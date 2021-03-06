package com.huayu.market.service;

import com.huayu.a.service.tools.SpringSecurityUtil;
import com.huayu.market.dao.MarketCostProjectSignDao;
import com.huayu.market.domain.*;
import com.huayu.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 项目签约信息
 * @author ZXL 2017-06-29 11:23
 **/
@Service
public class MarketCostProjectSignService {

    @Autowired
    private MarketCostProjectSignDao marketCostProjectSignDao;

    /**
     * 以项目ID和年度和公司ID获取数据
     * @param companyId
     * @param projectId
     * @param year
     * @return
     */
    public MarketCostProjectSign getOneByProjectIdAndYear(Integer companyId, Long projectId, Integer year){
        MarketCostProjectSign marketCostProjectSign = new MarketCostProjectSign();
        marketCostProjectSign.setCompanyId(companyId);
        marketCostProjectSign.setProjectId(projectId);
        marketCostProjectSign.setYear(year);
        return marketCostProjectSignDao.getOneByProjectIdAndYear(marketCostProjectSign);
    }

    /**
     * 项目签约信息
     * @param marketCostProjectSign
     * @return
     */
    public List<MarketCostProjectSign> getToSign(MarketCostProjectSign marketCostProjectSign){
        return marketCostProjectSignDao.getToSign(marketCostProjectSign);
    }

    /**
     * 批量修改数据
     * @param projectId
     * @param companyId
     * @param year
     * @param marketCostProjectBudgetItemList
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void batchPut(Integer year, List<MarketCostProjectSign> marketCostProjectSignList, MarketCostProjectSign marketCostProjectSign) throws Exception{
        MarketCostProjectSign marketCostProjectSignTemp = this.getOneByProjectIdAndYear(marketCostProjectSign.getCompanyId(),marketCostProjectSign.getProjectId(),year);
        MarketCostProjectSign marketCostProjectSignAdmin = this.getOneByProjectIdAndYear(1001,1001L,year);
        User user = SpringSecurityUtil.getUser();
        List<MarketCostProjectSign> addList = new ArrayList<>();
        List<MarketCostProjectSign> updateList = new ArrayList<>();
        if (null!=marketCostProjectSignTemp){
            updateList.add(marketCostProjectSign);
        } else {
            addList.add(this.createDomain(marketCostProjectSign,year,user));
        }
        if (null!=marketCostProjectSignAdmin){
            updateList.add(this.createAdmin(marketCostProjectSign,year,user,marketCostProjectSignTemp,marketCostProjectSignAdmin));
        } else {
            addList.add(this.createAdmin(marketCostProjectSign,year,user,marketCostProjectSignTemp,marketCostProjectSignAdmin));
        }
        for (MarketCostProjectSign costProjectSign : marketCostProjectSignList) {
            if (costProjectSign.getSignId()>0){
                updateList.add(costProjectSign);
            } else {
                addList.add(this.createDomain(costProjectSign,year,user));
            }
        }
        if (addList.size()>0){
            marketCostProjectSignDao.bathPost(addList);
        }
        if (updateList.size()>0){
            marketCostProjectSignDao.bathPut(updateList);
        }
    }

    private MarketCostProjectSign createDomain(MarketCostProjectSign marketCostProjectSign, Integer year, User user){
        marketCostProjectSign.setSignId(marketCostProjectSignDao.getKey("market_cost_project_sign",marketCostProjectSign));
        marketCostProjectSign.setYear(year);
        marketCostProjectSign.setCreateUserId(user.getUserId());
        marketCostProjectSign.setCreateDate(new Date());
        return marketCostProjectSign;
    }

    private MarketCostProjectSign createAdmin(MarketCostProjectSign marketCostProjectSign, Integer year, User user, MarketCostProjectSign marketCostProjectSignTemp, MarketCostProjectSign marketCostProjectSignAdmin){
        if (null==marketCostProjectSignAdmin){
            marketCostProjectSignAdmin = new MarketCostProjectSign();
            marketCostProjectSignAdmin.setSignId(marketCostProjectSignDao.getKey("market_cost_project_sign",marketCostProjectSign));
            marketCostProjectSignAdmin.setCompanyId(1001);
            marketCostProjectSignAdmin.setProjectId(1001L);
            marketCostProjectSignAdmin.setYear(year);
            marketCostProjectSignAdmin.setType((short)1);
            marketCostProjectSignAdmin.setSignMoney(marketCostProjectSign.getSignMoney());
            marketCostProjectSignAdmin.setSignMoney01(marketCostProjectSign.getSignMoney01());
            marketCostProjectSignAdmin.setSignMoney02(marketCostProjectSign.getSignMoney02());
            marketCostProjectSignAdmin.setSignMoney03(marketCostProjectSign.getSignMoney03());
            marketCostProjectSignAdmin.setSignMoney04(marketCostProjectSign.getSignMoney04());
            marketCostProjectSignAdmin.setSignMoney05(marketCostProjectSign.getSignMoney05());
            marketCostProjectSignAdmin.setSignMoney06(marketCostProjectSign.getSignMoney06());
            marketCostProjectSignAdmin.setSignMoney07(marketCostProjectSign.getSignMoney07());
            marketCostProjectSignAdmin.setSignMoney08(marketCostProjectSign.getSignMoney08());
            marketCostProjectSignAdmin.setSignMoney09(marketCostProjectSign.getSignMoney09());
            marketCostProjectSignAdmin.setSignMoney10(marketCostProjectSign.getSignMoney10());
            marketCostProjectSignAdmin.setSignMoney11(marketCostProjectSign.getSignMoney11());
            marketCostProjectSignAdmin.setSignMoney12(marketCostProjectSign.getSignMoney12());
            marketCostProjectSignAdmin.setCreateUserId(user.getUserId());
            marketCostProjectSignAdmin.setCreateDate(new Date());
        } else {
            marketCostProjectSignAdmin.setSignMoney(null==marketCostProjectSignTemp?marketCostProjectSignAdmin.getSignMoney().add(marketCostProjectSign.getSignMoney()):marketCostProjectSignAdmin.getSignMoney().subtract(marketCostProjectSignTemp.getSignMoney()).add(marketCostProjectSign.getSignMoney()));
            marketCostProjectSignAdmin.setSignMoney01(null==marketCostProjectSignTemp?marketCostProjectSignAdmin.getSignMoney01().add(marketCostProjectSign.getSignMoney01()):marketCostProjectSignAdmin.getSignMoney01().subtract(marketCostProjectSignTemp.getSignMoney01()).add(marketCostProjectSign.getSignMoney01()));
            marketCostProjectSignAdmin.setSignMoney02(null==marketCostProjectSignTemp?marketCostProjectSignAdmin.getSignMoney02().add(marketCostProjectSign.getSignMoney02()):marketCostProjectSignAdmin.getSignMoney02().subtract(marketCostProjectSignTemp.getSignMoney02()).add(marketCostProjectSign.getSignMoney02()));
            marketCostProjectSignAdmin.setSignMoney03(null==marketCostProjectSignTemp?marketCostProjectSignAdmin.getSignMoney03().add(marketCostProjectSign.getSignMoney03()):marketCostProjectSignAdmin.getSignMoney03().subtract(marketCostProjectSignTemp.getSignMoney03()).add(marketCostProjectSign.getSignMoney03()));
            marketCostProjectSignAdmin.setSignMoney04(null==marketCostProjectSignTemp?marketCostProjectSignAdmin.getSignMoney04().add(marketCostProjectSign.getSignMoney04()):marketCostProjectSignAdmin.getSignMoney04().subtract(marketCostProjectSignTemp.getSignMoney04()).add(marketCostProjectSign.getSignMoney04()));
            marketCostProjectSignAdmin.setSignMoney05(null==marketCostProjectSignTemp?marketCostProjectSignAdmin.getSignMoney05().add(marketCostProjectSign.getSignMoney05()):marketCostProjectSignAdmin.getSignMoney05().subtract(marketCostProjectSignTemp.getSignMoney05()).add(marketCostProjectSign.getSignMoney05()));
            marketCostProjectSignAdmin.setSignMoney06(null==marketCostProjectSignTemp?marketCostProjectSignAdmin.getSignMoney06().add(marketCostProjectSign.getSignMoney06()):marketCostProjectSignAdmin.getSignMoney06().subtract(marketCostProjectSignTemp.getSignMoney06()).add(marketCostProjectSign.getSignMoney06()));
            marketCostProjectSignAdmin.setSignMoney07(null==marketCostProjectSignTemp?marketCostProjectSignAdmin.getSignMoney07().add(marketCostProjectSign.getSignMoney07()):marketCostProjectSignAdmin.getSignMoney07().subtract(marketCostProjectSignTemp.getSignMoney07()).add(marketCostProjectSign.getSignMoney07()));
            marketCostProjectSignAdmin.setSignMoney08(null==marketCostProjectSignTemp?marketCostProjectSignAdmin.getSignMoney08().add(marketCostProjectSign.getSignMoney08()):marketCostProjectSignAdmin.getSignMoney08().subtract(marketCostProjectSignTemp.getSignMoney08()).add(marketCostProjectSign.getSignMoney08()));
            marketCostProjectSignAdmin.setSignMoney09(null==marketCostProjectSignTemp?marketCostProjectSignAdmin.getSignMoney09().add(marketCostProjectSign.getSignMoney09()):marketCostProjectSignAdmin.getSignMoney09().subtract(marketCostProjectSignTemp.getSignMoney09()).add(marketCostProjectSign.getSignMoney09()));
            marketCostProjectSignAdmin.setSignMoney10(null==marketCostProjectSignTemp?marketCostProjectSignAdmin.getSignMoney10().add(marketCostProjectSign.getSignMoney10()):marketCostProjectSignAdmin.getSignMoney10().subtract(marketCostProjectSignTemp.getSignMoney10()).add(marketCostProjectSign.getSignMoney10()));
            marketCostProjectSignAdmin.setSignMoney11(null==marketCostProjectSignTemp?marketCostProjectSignAdmin.getSignMoney11().add(marketCostProjectSign.getSignMoney11()):marketCostProjectSignAdmin.getSignMoney11().subtract(marketCostProjectSignTemp.getSignMoney11()).add(marketCostProjectSign.getSignMoney11()));
            marketCostProjectSignAdmin.setSignMoney12(null==marketCostProjectSignTemp?marketCostProjectSignAdmin.getSignMoney12().add(marketCostProjectSign.getSignMoney12()):marketCostProjectSignAdmin.getSignMoney12().subtract(marketCostProjectSignTemp.getSignMoney12()).add(marketCostProjectSign.getSignMoney12()));
        }
        return marketCostProjectSignAdmin;
    }

}
