package com.yyljlyy.wms.common;

import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.yyljlyy.wms.controller.CustomerController;
import com.yyljlyy.wms.controller.EmployeController;
import com.yyljlyy.wms.controller.FormController;
import com.yyljlyy.wms.controller.InventoryBinController;
import com.yyljlyy.wms.controller.InventoryController;
import com.yyljlyy.wms.controller.MaterialController;
import com.yyljlyy.wms.controller.StorageBinController;
import com.yyljlyy.wms.controller.StorageController;
import com.yyljlyy.wms.controller.SystemerController;

public class RouteConfigWms {
	
	public static void config(Routes me){
		me.add("/wms/storage", StorageController.class);
		me.add("/wms/form", FormController.class);
		me.add("/wms/material", MaterialController.class);
		me.add("/wms/customer", CustomerController.class);
		me.add("/wms/employe", EmployeController.class);
		me.add("/wms/storage_bin", StorageBinController.class);
		me.add("/wms/inventory", InventoryController.class);
		me.add("/wms/inventory_bin", InventoryBinController.class);
		me.add("/wms/systemer", SystemerController.class);
	}
	
	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("WebRoot", 80, "/", 5);
	}
}
