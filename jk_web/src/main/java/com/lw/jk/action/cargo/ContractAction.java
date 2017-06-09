package com.lw.jk.action.cargo;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.lw.jk.action.BaseAction;
import com.lw.jk.action.cargo.print.ContractPrint;
import com.lw.jk.pojo.Contract;
import com.lw.jk.pojo.User;
import com.lw.jk.service.ContractService;
import com.lw.jk.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 购销合同action
 * 
 * @author lw
 */
public class ContractAction extends BaseAction implements ModelDriven<Contract> {

	private Contract model = new Contract();
	// 分页
	private Page page = new Page<>();

	private ContractService contractService;

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	@Override
	public Contract getModel() {
		return model;
	}

	public Page getPage() {
		return page;
	}

	/**
	 * 分页查询
	 */
	public String list() throws Exception {

		model.setId(null);

		// 注意：这里传递的是引用数据类型，因此这里可以用page接收，也可以不用
		// page =
		// HQL查询
		String hql = "from Contract where 1 = 1 ";
		// 1、进行细粒度权限控制
		// 1.1、获取用户
		User user = super.getCurrentUser();
		// 1.2、获取当前用户的等级
		Integer degree = user.getUserInfo().getDegree();
		switch (degree) {
		case 1:// 副总
			break;
		case 2: // 说明管理本部门和子部门
			break;
		case 3: // 部门经理 , 管理本部门
			hql += " and createDept = '" + user.getDept().getId() + "'";
			break;
		case 4: // 普通员工
			hql += " and createBy = '" + user.getId() + "'";
			break;
		}

		contractService.findPage(hql, page, Contract.class, null);
		// 设置分页URL
		page.setUrl("contractAction_list");

		// 将page对象压入栈顶
		super.push(page);
		return "list";
	}

	/**
	 * 查看
	 */
	public String toview() throws Exception {
		// 根據ID查询对象
		Contract dept = contractService.get(Contract.class, model.getId());
		// 存入值栈
		super.push(dept);
		return "toview";
	}

	/**
	 * 新增
	 */
	public String tocreate() throws Exception {
		// 3、跳转页面
		return "tocreate";
	}

	/**
	 * 有一个bug，从前台传递过来的数据总是携带一个ID 新增部门(已解决)
	 */
	public String insert() throws Exception {

		// 进行细粒度权限控制,添加创建者相关信息
		User user = super.getCurrentUser();
		model.setCreateBy(user.getId());
		model.setCreateDept(user.getDept().getId());

		// 强制新增
		model.setId(null);

		contractService.saveOrUpdate(model);
		return "alist";
	}

	/**
	 * 删除 因为可能删除的不只是1条数据，因此需要注意： 如果id是string类型的，则id属性会使用逗号分隔符
	 * 如果id是integer类型的，则只会取最后一条id值
	 */
	public String delete() throws Exception {
		// 1、需要将要删除的部门分割开
		String[] ids = model.getId().split(", ");
		// 2、调用service的方法一次性删除
		contractService.delete(Contract.class, ids);
		// 3、返回页面
		return "alist"; // 删除成功后返回到列表页面
	}

	/**
	 * 进入修改页面
	 */
	public String toupdate() throws Exception {
		// 1、查找对应的购销合同
		Contract contract = contractService.get(Contract.class, model.getId());
		// 2、放入值栈顶
		super.push(contract);

		// 3、为了解决两次请求,两次响应的问题,这里使用拼接字符串的方法来查询对应的货物

		return "toupdate";
	}

	/**
	 * 修改
	 */
	public String update() throws Exception {
		// 找到要修改的对象
		Contract obj = contractService.get(Contract.class, model.getId());

		// 2.设置修改的属性
		obj.setCustomName(model.getCustomName());
		obj.setPrintStyle(model.getPrintStyle());
		obj.setContractNo(model.getContractNo());
		obj.setOfferor(model.getOfferor());
		obj.setInputBy(model.getInputBy());
		obj.setCheckBy(model.getCheckBy());
		obj.setInspector(model.getInspector());
		obj.setSigningDate(model.getSigningDate());
		obj.setImportNum(model.getImportNum());
		obj.setShipTime(model.getShipTime());
		obj.setTradeTerms(model.getTradeTerms());
		obj.setDeliveryPeriod(model.getDeliveryPeriod());
		obj.setCrequest(model.getCrequest());
		obj.setRemark(model.getRemark());

		contractService.saveOrUpdate(obj);

		// 设置要修改的属性
		return "alist";
	}

	/**
	 * 打印购销合同
	 * 
	 * @throws Exception
	 */
	public String print() throws Exception {
		// 1、根据购销合同ID,得到要打印的对象
		Contract contract = contractService.get(Contract.class, model.getId());
		// 2、获取应用程序的路径
		String path = ServletActionContext.getServletContext().getRealPath("/");
		// 3.指定response
		HttpServletResponse response = ServletActionContext.getResponse();
		// 4、创建打印对象
		ContractPrint cp = new ContractPrint();
		// 5、开始打印
		cp.print(contract, path, response);
		return NONE;
	}

	/**
	 * 提交
	 */
	public String submit() {
		String[] ids = model.getId().split(", ");
		contractService.changeState(ids, 1);
		;
		return "alist";
	}

	/**
	 * 取消
	 */
	public String cancel() {
		String[] ids = model.getId().split(", ");
		contractService.changeState(ids, 0);
		;
		return "alist";
	}
}
