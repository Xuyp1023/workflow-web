// Copyright (c) 2014-2016 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.0 : 2016年12月1日, liuwl, creation
// ============================================================================
package com.betterjr.modules.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liuwl
 *
 */
@Controller
@RequestMapping("/WorkFlow")
public class WorkFlowController {
    private static final Logger logger = LoggerFactory.getLogger(WorkFlowController.class);

    // 启动流程
    public void startWorkFlow(){

    }

    // 待办业务
    public void queryTask() {

    }

    // 已办业务
    public void queryHistoryTask() {

    }

    // 加载节点
    public void loadTask() {

    }

    // 审批通过
    public void passWorkFlow() {

    }

    // 审批驳回
    public void rejectWofkFlow() {

    }

    // 审批记录
    public void queryAudit() {

    }

    // 查询当前可驳回节点列表 第一项为上一步
    public void queryRejectNode() {

    }
}
