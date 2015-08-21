;nibbleforgot = {
		name : 'nibbleforgot',
		init : function(){
			$('#user-content').load('views/user/nibbleforgot.html', function(){
				nibbleforgot.setupForgotForm();
				nibbleforgot.setupLinks();
				nibblemain.setupValidations();
			});
			
		},		
		setupLinks: function(){
			$('#login_now').click(function(e){
				nibblemain.showWaitingDialog({dialogSize: 'sm', progressType: 'warning'});
				nibblelogin.init();
				e.preventDefault();
				nibblemain.hideWaitingDialog();
			});
		},
		setupForgotForm: function() {
			$("#forgot-form").parsley({
	            errorsContainer: function (ParsleyField) {
	                return ParsleyField.$element.attr("title");
	            },
	            errorsWrapper: false
	        });
			$('#forgot_now').click(function(e){
				nibblemain.showWaitingDialog({dialogSize: 'sm', progressType: 'warning'});
				if($("#forgot-form").parsley().validate()){
					var data = {};
					data.username = $('#forgot-form').find('input[name="forgot_username"]').val();
					
					var request = {};
					request.type = 'POST';
					request.contentType = 'application/json';
					request.dataType = 'application/json';
					request.url = nibblemain.getServicesUrl()+'/rest/forgot';
					request.reqData = data;				
					request.done = function(data){
						$('#forgot_form').addClass('hidden');
						$('#forgot_result').removeClass('hidden');
					};
					nibblemain.jsonasync(request);
				}
				
				e.preventDefault();
			});	
		}	
	
};

