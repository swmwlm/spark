package com.yyljlyy.wms.controller;

import static com.yyljlyy.wms.common.ModelConfigWms.*;

import com.jfinal.plugin.activerecord.Page;
import com.yyljlyy.base.BaseController;
import com.yyljlyy.wms.model.Employe;


public class EmployeController extends BaseController{
	public void index() {
		Page<Employe> pager = Employe.dao.paginate(
				getParaToInt("pager.pageNumber", 1),
				getParaToInt("pager.pageSize", 20),
				"select * ", 
				" from " + TABLE_Employe);
		setAttr("pager",pager);
		render("employe_list.html");
	}
	public void add() {
		render("../employe_input.html");
	}
	
	public void checkIsNotExist(){
		renderJson(checkIsNotExist(TABLE_Employe, "id", getPara("employe.id")));
	}
	
	public void checkNameIsNotExist(){
		renderJson(checkIsNotExist(TABLE_Employe, "name", getPara("employe.name")));
	}
	
	public void save() {
		Employe s = getModel(Employe.class, "employe");
		s.save();
		renderDWZSuccessJson("客户资料创建成功！");
	}
	
	public void edit() {
		setAttr("employe",Employe.dao.findById(getPara("id")));
		render("../employe_input.html");
	}
	
	public void update() {
		Employe s = getModel(Employe.class, "employe");
		s.update();
		renderDWZSuccessJson("客户资料修改成功！");
	}
	
	public void delete() {
		String[] ids = getParaValues("ids");
		for(String id:ids){
			Employe.dao.deleteById(id);
		}
		renderDWZSuccessJson("删除成功!");
	}
}
