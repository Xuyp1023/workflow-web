package com.betterjr.modules.request;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.betterjr.common.utils.UserUtils;
import com.betterjr.common.web.AjaxObject;
import com.betterjr.common.web.Servlets;
import com.betterjr.modules.approval.IScfSellerApprovalService;
import com.betterjr.modules.approval.IScfSupplyApprovalService;
import com.betterjr.modules.workflow.IWorkFlowService;
import com.betterjr.modules.workflow.constants.WorkFlowInput;
import com.betterjr.modules.workflow.data.WorkFlowBusinessType;

@Controller
@RequestMapping(value = "/WorkFlow/Request")
public class RequestController {
	private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

	@Reference(interfaceClass = IWorkFlowService.class)
	private IWorkFlowService workFlowService;

	@Reference(interfaceClass = IScfSellerApprovalService.class)
	private IScfSellerApprovalService scfSellerFlowService;
	
	@Reference(interfaceClass = IScfSupplyApprovalService.class)
	private IScfSupplyApprovalService scfSupplyFlowService;

	@RequestMapping(value = "/supplyRequest", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String supplyRequest(final HttpServletRequest request) {
		try {
			Map<String, Object> anContext = Servlets.getParametersStartingWith(request, "");
			anContext = scfSupplyFlowService.application(anContext);

			// 启动流程
			WorkFlowInput workFlowInput = new WorkFlowInput(UserUtils.getOperatorInfo().getId(), "资金方-供应商融资审批流程",
					(Long) anContext.get("factorNo"), anContext.get("requestNo").toString(),
					WorkFlowBusinessType.SUPPLIER_FINANCING_REQUEST);
			workFlowInput.setCoreCustNo((Long) anContext.get("coreCustNo"));
			workFlowInput.setFactorCustNo((Long) anContext.get("factorNo"));
			workFlowInput.setSupplierCustNo((Long) anContext.get("custNo"));
			workFlowInput.addParam("balance", (BigDecimal) anContext.get("balance"));
			workFlowService.startWorkFlow(workFlowInput);
			return AjaxObject.newOk(request).toJson();
		} catch (Exception ex) {
			logger.error("添加融资申请:", ex);
			return AjaxObject.newError(ex.getMessage()).toJson();
		}
	}

	@RequestMapping(value = "/sellerRequest", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String sellerRequest(final HttpServletRequest request) {
		try {
			Map<String, Object> anContext = Servlets.getParametersStartingWith(request, "");
			anContext = scfSellerFlowService.application(anContext);

			// 启动流程
			WorkFlowInput workFlowInput = new WorkFlowInput(UserUtils.getOperatorInfo().getId(), "资金方-经销商融资审批流程",
					(Long) anContext.get("factorNo"), anContext.get("requestNo").toString(),
					WorkFlowBusinessType.SELLER_FINANCING_REQUEST);
			workFlowInput.setCoreCustNo((Long) anContext.get("coreCustNo"));
			workFlowInput.setFactorCustNo((Long) anContext.get("factorNo"));
			workFlowInput.setSellerCustNo((Long) anContext.get("custNo"));
			workFlowInput.addParam("balance", (BigDecimal) anContext.get("balance"));
			workFlowService.startWorkFlow(workFlowInput);
			return AjaxObject.newOk(request).toJson();
		} catch (Exception ex) {
			logger.error("添加融资申请:", ex);
			return AjaxObject.newError(ex.getMessage()).toJson();
		}
	}
}
