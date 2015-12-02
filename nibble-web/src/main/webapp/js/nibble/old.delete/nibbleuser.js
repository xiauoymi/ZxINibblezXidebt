$(document).ready( function() {  
	;nibbleuser = {
		name : 'nibbleuser',
		init : function(){	
	
			var acode = nibblemain.getParameterByName('acode');
			var rcode = nibblemain.getParameterByName('rcode');			
			
			if(acode != null && acode != '') {
				nibbleactivate.init();
			}else if(rcode != null && rcode != ''){
				nibblereset.init();
			}else{
				nibblelogin.init();
			}			
		}
	}
	nibbleuser.init();
}); 