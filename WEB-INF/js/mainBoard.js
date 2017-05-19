$(function() {
	
	

	$('#nav').tree({
	    url : 'menu/get_menu.html',
	    lines : true,
	    onLoadSuccess : function(node, data){
	    	var _this = this;
	    	if(data){
	    		$(data).each(function(index, value){
	    		    if(this.state == 'closed'){
	    		    	$(_this).tree('expandAll');
	    		    }
	    		});
	    	}
	    },
	    
		onClick : function(node){
			console.log(node.text + node.url);
			if(node.url){
				if($('#tabs').tabs('exists',node.text)){
					$('#tabs').tabs('select', node.text)
				}else {
					$('#tabs').tabs('add', {
						title : node.text,
						closable: true,
						iconCls : node.iconCls,
						href: node.url,
					});
				}
			}
		}
		
	});
	
	$('#tabs').tabs({
		fit : true,
		border: false,
	});
	
	
});