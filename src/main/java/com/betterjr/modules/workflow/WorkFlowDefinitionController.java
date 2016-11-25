// Copyright (c) 2014-2016 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.0 : 2016年11月25日, liuwl, creation
// ============================================================================
package com.betterjr.modules.workflow;

import static com.betterjr.common.web.ControllerExceptionHandler.exec;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.betterjr.common.web.Servlets;

/**
 * @author liuwl
 *
 */
@Controller
@RequestMapping("/WorkFlow/Definition")
public class WorkFlowDefinitionController {
    private static final Logger logger = LoggerFactory.getLogger(WorkFlowDefinitionController.class);

    @Reference(interfaceClass = IWorkFlowDefinitionService.class)
    private IWorkFlowDefinitionService workFlowDefinitionService;

    @RequestMapping(value = "/queryWorkFlowBase", method = RequestMethod.POST)
    public @ResponseBody String queryWorkFlowBase(final HttpServletRequest request, final long custNo, final int flag, final int pageNum,
            final int pageSize) {
        logger.info("查询公司拥有流程");

        return exec(() -> workFlowDefinitionService.webQueryWorkFlowBase(custNo, flag, pageNum, pageSize), "查询流程定义列表失败！", logger);
    }

    @RequestMapping(value = "/findWorkFlowBase", method = RequestMethod.POST)
    public @ResponseBody String findWorkFlowBase(final HttpServletRequest request, final long baseId) {
        logger.info("查询流程详情");

        return exec(() -> workFlowDefinitionService.webFindWorkFlowBase(baseId), "查询流程定义详情失败！", logger);
    }

    @RequestMapping(value = "/queryWorkFlowNode", method = RequestMethod.POST)
    public @ResponseBody String queryWorkFlowNode(final HttpServletRequest request, final long baseId) {
        logger.info("查询流程节点列表");

        return exec(() -> workFlowDefinitionService.webQueryWorkFlowNode(baseId), "查询流程节点列表失败！", logger);
    }

    @RequestMapping(value = "/findWorkFlowNode", method = RequestMethod.POST)
    public @ResponseBody String findWorkFlowNode(final HttpServletRequest request, final long nodeId) {
        logger.info("查询流程节点详情");

        return exec(() -> workFlowDefinitionService.webFindWorkFlowNode(nodeId), "查询流程节点详情失败！", logger);
    }

    @RequestMapping(value = "/queryWorkFlowStep", method = RequestMethod.POST)
    public @ResponseBody String queryWorkFlowStep(final HttpServletRequest request, final long nodeId) {
        logger.info("查询流程节点步骤");

        return exec(() -> workFlowDefinitionService.webQueryWorkFlowStep(nodeId), "查询流程步骤列表失败！", logger);
    }

    @RequestMapping(value = "/findWorkFlowStep", method = RequestMethod.POST)
    public @ResponseBody String findWorkFlowStep(final HttpServletRequest request, final long stepId) {
        logger.info("查询流程节点详情");

        return exec(() -> workFlowDefinitionService.webFindWorkFlowStep(stepId), "查询流程步骤详情失败！", logger);
    }

    @RequestMapping(value = "/addWorkFlowBase", method = RequestMethod.POST)
    public @ResponseBody String addWorkFlowBase(final HttpServletRequest request, final long defaultBaseId, final long custNo) {
        logger.info("添加流程");
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        return exec(() -> workFlowDefinitionService.webAddWorkFlowBase(param, defaultBaseId, custNo), "添加流程失败！", logger);
    }

    @RequestMapping(value = "/saveWorkFlowBase", method = RequestMethod.POST)
    public @ResponseBody String saveWorkFlowBase(final HttpServletRequest request, final long baseId) {
        logger.info("修改流程");
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        return exec(() -> workFlowDefinitionService.webSaveWorkFlowBase(param, baseId), "修改流程失败！", logger);
    }

    @RequestMapping(value = "/addWorkFlowStep", method = RequestMethod.POST)
    public @ResponseBody String addWorkFlowStep(final HttpServletRequest request, final long baseId, final long nodeId) {
        logger.info("添加流程审批步骤");
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        return exec(() -> workFlowDefinitionService.webAddWorkFlowStep(param, baseId, nodeId), "添加步骤失败！", logger);
    }

    @RequestMapping(value = "/saveWorkFlowStep", method = RequestMethod.POST)
    public @ResponseBody String saveWorkFlowStep(final HttpServletRequest request, final long baseId, final long nodeId, final long stepId) {
        logger.info("修改流程审批步骤");
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        return exec(() -> workFlowDefinitionService.webSaveWorkFlowStep(param, baseId, nodeId, stepId), "修改步骤失败！", logger);
    }

    @RequestMapping(value = "/delWorkFlowStep", method = RequestMethod.POST)
    public @ResponseBody String delWorkFlowStep(final HttpServletRequest request, final long baseId, final long nodeId, final long stepId) {
        logger.info("删除流程审批步骤");
        return exec(() -> workFlowDefinitionService.webDelWorkFlowStep(baseId, nodeId, stepId), "删除步骤失败！", logger);
    }

    @RequestMapping(value = "/saveWorkFlowStepDefine", method = RequestMethod.POST)
    public @ResponseBody String saveWorkFlowStepDefine(final HttpServletRequest request, final long baseId, final long nodeId, final long stepId) {
        logger.info("保存流程定义");
        final Map<String, Object> param = Servlets.getParametersStartingWith(request, "");
        return exec(() -> workFlowDefinitionService.webSaveWorkFlowStepDefine(param, baseId, nodeId, stepId), "删除步骤失败！", logger);
    }

    @RequestMapping(value = "/saveDisableWorkFlow", method = RequestMethod.POST)
    public @ResponseBody String saveDisableWorkFlow(final HttpServletRequest request, final long baseId) {
        logger.info("停用流程");
        return exec(() -> workFlowDefinitionService.webSaveDisableWorkFlow(baseId), "停用流程失败！", logger);
    }

    @RequestMapping(value = "/saveEnableWorkFlow", method = RequestMethod.POST)
    public @ResponseBody String saveEnableWorkFlow(final HttpServletRequest request, final long baseId) {
        logger.info("启用流程");
        return exec(() -> workFlowDefinitionService.webSaveEnableWorkFlow(baseId), "启用流程失败！", logger);
    }

    @RequestMapping(value = "/saveDisableWorkFlowNode", method = RequestMethod.POST)
    public @ResponseBody String saveDisableWorkFlowNode(final HttpServletRequest request, final long baseId, final long nodeId) {
        logger.info("停用流程节点");
        return exec(() -> workFlowDefinitionService.webSaveDisableWorkFlowNode(baseId, nodeId), "停用流程节点失败！", logger);
    }

    @RequestMapping(value = "/saveEnableWorkFlowNode", method = RequestMethod.POST)
    public @ResponseBody String saveEnableWorkFlowNode(final HttpServletRequest request, final long baseId, final long nodeId) {
        logger.info("启用流程节点");
        return exec(() -> workFlowDefinitionService.webSaveEnableWorkFlowNode(baseId, nodeId), "启用流程节点失败！", logger);
    }

    @RequestMapping(value = "/savePublishWorkFlow", method = RequestMethod.POST)
    public @ResponseBody String savePublishWorkFlow(final HttpServletRequest request, final long baseId) {
        logger.info("发布流程");
        return exec(() -> workFlowDefinitionService.webSavePublishWorkFlow(baseId), "发布流程失败！", logger);
    }
}
