package com.wzydev.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wzydev.mapper.RequisitionMapper;
import com.wzydev.mapper.SupplyMapper;
import com.wzydev.pojo.PageResult;
import com.wzydev.pojo.RequisitionQueryParam;
import com.wzydev.pojo.Requisition;
import com.wzydev.pojo.Supply;
import com.wzydev.service.RequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequisitionServiceImpl implements RequisitionService {
    @Autowired
    private RequisitionMapper requisitionMapper;
    @Autowired
    private SupplyMapper supplyMapper;

    @Override
    public PageResult<Requisition> page(RequisitionQueryParam requisitionQueryParam) {
        PageHelper.startPage(requisitionQueryParam.getPage(), requisitionQueryParam.getPageSize());
        List<Requisition> requisitionList = requisitionMapper.list(requisitionQueryParam);
        Page<Requisition> p = (Page<Requisition>) requisitionList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public void delete(List<Integer> ids) {
        requisitionMapper.deleteByIds(ids);
    }

    @Override
    public void add(Requisition requisition) {
        requisition.setCreateTime(LocalDateTime.now());
        requisition.setUpdateTime(LocalDateTime.now());
        requisitionMapper.insert(requisition);
    }

    @Override
    public Requisition getInfo(Integer id) {
        return requisitionMapper.getById(id);
    }

    @Override
    public void update(Requisition requisition) {
        requisition.setUpdateTime(LocalDateTime.now());
        requisitionMapper.updateById(requisition);
    }

    @Override
    public String approve(Integer id) {
        //1.获取申请单和物资信息
        Requisition requisition = requisitionMapper.getById(id);
        Supply supply = supplyMapper.getById(requisition.getSupplyId());

        //2.校验物资库存是否充足
        if (supply.getQuantity() < requisition.getQuantity()) {
            return "库存不足，无法审批通过。当前库存：" + supply.getQuantity() + "，申请数量：" + requisition.getQuantity();
        }

        //3.更新物资库存
        supplyMapper.decreaseQuantity(supply.getId(), requisition.getQuantity());

        //4.更新申请单状态
        requisition.setStatus(1);
        requisition.setUpdateTime(java.time.LocalDateTime.now());
        requisitionMapper.updateById(requisition);

        return null;
    }
}
