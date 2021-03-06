/**
 * migr.dynamicTable插件，高级的查找带回和动态生成table行的组件
 * @auth lzy@bjsxsoft.com
 */
(function($){
	$.fn.extend({
		/**
		 * migr动态table组件，根据th生成tr
		 * @returns
		 */
		dynamicTable:function(){
			return this.each(function(){
				var $table = $(this).css("clear","both"), $tbody = $table.find("tbody");
				var fields=[];

				$table.find("tr:first th[type]").each(function(){
					var $th = $(this);
					var field = {
						type: $th.attr("type") || "text",
						patternDate: $th.attr("format") || "yyyy-MM-dd",
						name: $th.attr("name") || "",
						size: $th.attr("size") || "12",
						enumUrl: $th.attr("enumUrl") || "",
						lookupGroup: $th.attr("lookupGroup") || "",
						lookupUrl: $th.attr("lookupUrl") || "",
						suggestUrl: $th.attr("suggestUrl"),
						suggestFields: $th.attr("suggestFields"),
						fieldClass: $th.attr("fieldClass") || "",
						targetId:$th.attr("targetId") || ""
					};
					fields.push(field);
				});
				//缓存table的fields
				$table.data("fields",fields);
				
				$tbody.find("a.btnDel").click(function(){
					var $btnDel = $(this);
					function delDbData(){
						$.ajax({
							type:'POST', dataType:"json", url:$btnDel.attr('href'), cache: false,
							success: function(){
								$btnDel.parents("tr:first").remove();
								$.initDynamicTableSuffix($tbody);
							},
							error: Bird.ajaxError
						});
					}
					if ($btnDel.attr("title")){
						alertMsg.confirm($btnDel.attr("title"), {okCall: delDbData});
					} else {
						delDbData();
					}
					return false;
				});
			});
		}
	});
	$.extend({
		/**
		 * 根据table的Id增加一行
		 * @param tableId
		 */
		addDynamicTableRow:function(tableId,data){
			$table = $("#"+tableId);
			$tbody = $table.find("tbody");
			//获取缓存的fields，以提高效率
			var fields = $table.data("fields");
			
			var trTm = "";
			if (! trTm) trTm = trHtml(fields);
			//插入的行数，暂定每次一行
			var rowNum = 1;
			for (var i=0; i<rowNum; i++){
				var $tr = $(trTm);
				$tr.appendTo($tbody).initUI().find("a.btnDel").click(function(){
					$(this).parents("tr:first").remove();
					$.initDynamicTableSuffix($tbody);
					return false;
				});
			}
			$.initDynamicTableSuffix($tbody);
			
			function tdHtml(field){
				var html = '', suffix = '';
				
				if (field.name.endsWith("[#index#]")) suffix = "[#index#]";
				else if (field.name.endsWith("[]")) suffix = "[]";
				var suffixFrag = suffix ? ' suffix="' + suffix + '" ' : '';
				
				switch(field.type){
					case 'del':
						html = '<a href="javascript:void(0)" class="btnDel '+ field.fieldClass + '">删除</a>';
						break;
					case 'index':
						html = '<span class="index"></span>';
						break;
					case 'indexDel':
						html = '<input type="checkbox" name="ids" size="'+field.size+'" class="'+field.fieldClass+'"/><span class="index"></span>';
						break;
					case 'lookup':
						var suggestFrag = '';
						if (field.suggestFields) {
							suggestFrag = 'autocomplete="off" lookupGroup="'+field.lookupGroup+'"'+suffixFrag+' suggestUrl="'+field.suggestUrl+'" suggestFields="'+field.suggestFields+'"';
						}

						html = '<input type="hidden" id="'+field.lookupGroup+'.id'+suffix+'" name="'+field.targetId+'"/>'
							+ '<input type="text" id="'+field.name+'" '+suggestFrag+' name="'+field.name+'" size="'+field.size+'" class="'+field.fieldClass+'"/>'
							+ '<a class="btnLook" href="'+field.lookupUrl+'" lookupGroup="'+field.lookupGroup+'" '+suggestFrag+' title="查找带回">查找带回</a>';
						break;
					case 'attach':
						html = '<input type="hidden" id="'+field.lookupGroup+'.id'+suffix+'" name="'+field.targetId+'"/>'
							+ '<input type="text" id="'+field.name+'" name="'+field.name+'" size="'+field.size+'" readonly="readonly" class="'+field.fieldClass+'"/>'
							+ '<a class="btnAttach" href="'+field.lookupUrl+'" lookupGroup="'+field.lookupGroup+'" '+suggestFrag+' width="560" height="300" title="查找带回">查找带回</a>';
						break;
					case 'enum':
						$.ajax({
							type:"POST", dataType:"html", async: false,
							url:field.enumUrl, 
							data:{inputName:field.name}, 
							success:function(response){
								html = response;
							}
						});
						break;
					case 'date':
						html = '<input type="text" name="'+field.name+'" class="date '+field.fieldClass+'" format="'+field.patternDate+'" size="'+field.size+'"/>'
							+'<a class="inputDateButton" href="javascript:void(0)">选择</a>';
						break;
					default:
						html = '<input type="text" name="'+field.name+'" size="'+field.size+'" class="'+field.fieldClass+'"/>';
						break;
				}
				return '<td>'+html+'</td>';
			}
			function trHtml(fields){
				var html = '';
				$(fields).each(function(){
					html += tdHtml(this);
				});
				return '<tr class="unitBox">'+html+'</tr>';
			}
		},
		/**
		 * 删除时重新初始化下标
		 */
		initDynamicTableSuffix:function($tbody) {
			$tbody.find('>tr').each(function(i){
				$('span.index',this).each(function(){
					$(this).text(i+1);
				});
				$(':input, a.btnLook', this).each(function(){
					var $this = $(this);
					$this.attr('name', $this.attr('name').replaceSuffix(i));
					
					var lookupGroup = $this.attr('lookupGroup');
					if (lookupGroup) {$this.attr('lookupGroup', lookupGroup.replaceSuffix(i));}
					
					var suffix = $this.attr("suffix");
					if (suffix) {$this.attr('suffix', suffix.replaceSuffix(i));}
					
				});
			});
		}
	});
})(jQuery);