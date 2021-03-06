package com.huayu.market.domain;

import com.ly.dao.base.BaseDomain;
import com.ly.dao.base.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.lang.Long;
import java.lang.Short;
import java.util.Date;
import java.lang.String;

/**
* 框架合同执行单
* Created by ZXL on 2017-8-23 14:36:56.
*/
@Table(name = "market_cost_contract_execute")
public class MarketCostContractExecute extends BaseDomain  implements Serializable{

 //private String updateFiledKey = executeId,status,useStatus,title,serialsNumber,contractId,contractName,contractCode,projectId,pid,applyDate,departmentId,agentUserId,agentName,applyMoney,remark,createDate,successDate;
 //private String updateFiledKeyValue = :executeId,:status,:useStatus,:title,:serialsNumber,:contractId,:contractName,:contractCode,:projectId,:pid,:applyDate,:departmentId,:agentUserId,:agentName,:applyMoney,:remark,:createDate,:successDate;
 //private String updateFiledValue = executeId=:executeId,status=:status,useStatus=:useStatus,title=:title,serialsNumber=:serialsNumber,contractId=:contractId,contractName=:contractName,contractCode=:contractCode,projectId=:projectId,pid=:pid,applyDate=:applyDate,departmentId=:departmentId,agentUserId=:agentUserId,agentName=:agentName,applyMoney=:applyMoney,remark=:remark,createDate=:createDate,successDate=:successDate;
    private Long executeId;//执行单ID
    private Short status;//状态 1申请中 2已完成 3已撤销
    private Short useStatus;//使用状态 1未使用 2使用中 3已使用
    private String title;//执行单名称
    private String serialsNumber;//流水号
    private Long contractId;//合同ID
    private String contractName;//合同名称
    private String contractCode;//合同编号
    private Long projectId;//项目ID
    private Long pid;//立项ID
    private Date applyDate;//申请日期
    private Long departmentId;//经办部门
    private Long agentUserId;//经办人
    private String agentName;//经办人姓名
    private BigDecimal applyMoney;//申请金额
    private String remark;//备注
    private Date createDate;//创建时间
    private Date successDate;//操作时间

    public MarketCostContractExecute() {
        this.dt = "desc";
        this.dtn = "id";
        this.pageSize = 10;
    }

    public void setExecuteId(Long executeId){//执行单ID
        this.executeId = executeId;
    }
    public Long getExecuteId(){//执行单ID
        return this.executeId;
    }

    public void setStatus(Short status){//状态 1申请中 2已完成 3已撤销
        this.status = status;
    }
    public Short getStatus(){//状态 1申请中 2已完成 3已撤销
        return this.status;
    }

    public void setUseStatus(Short useStatus){//使用状态 1未使用 2使用中 3已使用
        this.useStatus = useStatus;
    }
    public Short getUseStatus(){//使用状态 1未使用 2使用中 3已使用
        return this.useStatus;
    }

    public void setTitle(String title){//执行单名称
        this.title = title;
    }
    public String getTitle(){//执行单名称
        return this.title;
    }

    public void setSerialsNumber(String serialsNumber){//流水号
        this.serialsNumber = serialsNumber;
    }
    public String getSerialsNumber(){//流水号
        return this.serialsNumber;
    }

    public void setContractId(Long contractId){//合同ID
        this.contractId = contractId;
    }
    public Long getContractId(){//合同ID
        return this.contractId;
    }

    public void setContractName(String contractName){//合同名称
        this.contractName = contractName;
    }
    public String getContractName(){//合同名称
        return this.contractName;
    }

    public void setContractCode(String contractCode){//合同编号
        this.contractCode = contractCode;
    }
    public String getContractCode(){//合同编号
        return this.contractCode;
    }

    public void setProjectId(Long projectId){//项目ID
        this.projectId = projectId;
    }
    public Long getProjectId(){//项目ID
        return this.projectId;
    }

    public void setPid(Long pid){//立项ID
        this.pid = pid;
    }
    public Long getPid(){//立项ID
        return this.pid;
    }

    public void setApplyDate(Date applyDate){//申请日期
        this.applyDate = applyDate;
    }
    public Date getApplyDate(){//申请日期
        return this.applyDate;
    }

    public void setDepartmentId(Long departmentId){//经办部门
        this.departmentId = departmentId;
    }
    public Long getDepartmentId(){//经办部门
        return this.departmentId;
    }

    public void setAgentUserId(Long agentUserId){//经办人
        this.agentUserId = agentUserId;
    }
    public Long getAgentUserId(){//经办人
        return this.agentUserId;
    }

    public void setAgentName(String agentName){//经办人姓名
        this.agentName = agentName;
    }
    public String getAgentName(){//经办人姓名
        return this.agentName;
    }

    public void setApplyMoney(BigDecimal applyMoney){//申请金额
        this.applyMoney = applyMoney;
    }
    public BigDecimal getApplyMoney(){//申请金额
        return this.applyMoney;
    }

    public void setRemark(String remark){//备注
        this.remark = remark;
    }
    public String getRemark(){//备注
        return this.remark;
    }

    public void setCreateDate(Date createDate){//创建时间
        this.createDate = createDate;
    }
    public Date getCreateDate(){//创建时间
        return this.createDate;
    }

    public void setSuccessDate(Date successDate){//操作时间
        this.successDate = successDate;
    }
    public Date getSuccessDate(){//操作时间
        return this.successDate;
    }


}
