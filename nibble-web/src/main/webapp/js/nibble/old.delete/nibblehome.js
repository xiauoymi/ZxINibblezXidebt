$(document).ready( function() {  
	;nibblehome = {
			name 	: 'nibblehome',
			ctxPath : 'nibble-web',
		
			init : function(){
				nibblemain.showWaitingDialog({dialogSize: 'md', progressType: 'warning'});
				nibblehome.checkFirstLogin();
				nibblemain.hideWaitingDialog();
				
			},
			initNav : function(){
				$('#navbar').load('views/partials/navbar.htmp', function(){
					$('#navmenu').load('views/partials/navmenu.htmp', function(){
						var menu = new $.slidebars();
				    	$('.menu-item').click(
			    		    function(e){    		    	
			    		    	if(e.currentTarget.id != 'signout'){
			    		    		$('.menu').removeClass('active');
			    		        	$(this).addClass('active');
			    		        	console.log('clicked : '+e.currentTarget.id);
			    		        	nibblehome.initContent(e.currentTarget.id);
			    		        	menu.slidebars.close();
			    		        }
			    		    	e.preventDefault();
			    		    }
			    		);
					});
				});	
				
			},
			checkFirstLogin : function(){
				var request = {};
				request.type = 'GET';
				request.url = nibblemain.getServicesUrl()+'/rest/isfirstlogin';
				request.done = function(data){
					if(!data){
						nibblehome.initNav();
						nibblehome.initContent('');
					}else{
						nibblehome.initNav();
						nibblehome.initContent('');
						nibblestart.init();
					}			
				};	
				nibblemain.jsonasync(request);			
			},
			
			initContent	: function(namespaceStr){
				if(namespaceStr==''){
					var namespace = eval('nibbledash');
					namespace.init('.home-content');
				}else{
					var namespace = eval(namespaceStr);
					namespace.init('.home-content');
				}
			}
		}
		nibblehome.init();
}); 
	