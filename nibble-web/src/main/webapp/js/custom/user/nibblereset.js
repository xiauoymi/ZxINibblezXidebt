;nibblereset = {

		init : function(){
			$('#user-content').load('views/user/nibblereset.html', function(){

				var rcode = nibblemain.getParameterByName('rcode');	
				$('#reset-form').find('input[name="reset_code"]').val(rcode);
				nibblereset.setupResetForm();
				nibblereset.setupLinks();
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
		setupResetForm: function() {
			$("#reset-form").parsley({
	            errorsContainer: function (ParsleyField) {
	                return ParsleyField.$element.attr("title");
	            },
	            errorsWrapper: false
	        });
			$('#reset_now').click(function(e){
				nibblemain.showWaitingDialog('Resetting your password...please hold on', {dialogSize: 'sm', progressType: 'warning'});
				if($("#reset-form").parsley().validate()){
					var data = {};
					data.username = $('#reset-form').find('input[name="reset_username"]').val();
					data.password = $('#reset-form').find('input[name="reset_password"]').val();
					data.activationCode = $('#reset-form').find('input[name="reset_code"]').val();
					
					var request = {};
					request.type = 'POST';
					request.contentType = 'application/json';
					request.dataType = 'application/json';
					request.url = nibblemain.getServicesUrl()+'/rest/reset';
					request.reqData = data;				
					request.done = function(data){
						$('#reset_form').addClass('hidden');
						$('#reset_result').removeClass('hidden');
					};
					nibblemain.jsonasync(request);
				}else{

					nibblemain.hideWaitingDialog();
				}
				
				e.preventDefault();
			});	
		}		
	
};

