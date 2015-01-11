package cn.yyljlyy.db;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class AutoGenController extends Controller {
	public void index() {
		String db_list = getPara("db_list");
		setAttr("dbPage", Db.query("show TABLES"));
		Page<Record> pager = Db.paginate(getParaToInt("pager.pageNumber", 1),getParaToInt("pager.pageSize", 20),
				"select COLUMN_NAME,DATA_TYPE,COLUMN_COMMENT", 
				"FROM information_schema.`COLUMNS` WHERE TABLE_NAME = ? and TABLE_SCHEMA='spark'",db_list);
		setAttr("db_list", db_list);
		setAttr("dbPages", pager);
		render("dbpage.html");
	}
	
	public void dblistpage() {
		String db_list = getPara("db_list");
		String packages = getPara("packages");
		setAttr("dbPage", Db.query("show TABLES"));
		Page<Record> pager = Db.paginate(getParaToInt("pager.pageNumber", 1),getParaToInt("pager.pageSize", 20),
				"select COLUMN_COMMENT", 
				"FROM information_schema.`COLUMNS` WHERE TABLE_NAME = ? and TABLE_SCHEMA='spark'",db_list);
		setAttr("packages", packages);
		setAttr("dblist", pager);
		render("dblistpage.html");
	}
}
