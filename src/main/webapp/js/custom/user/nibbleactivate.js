;nibbleactivate = {
	name : 'nibbleactivate',
	init : function(){

		$('#user-content').load('views/user/nibbleactivate.html', function(){
			var acode = nibblemain.getParameterByName('acode');

			$('#activate-form').find('input[name="activation_code"]').val(acode);	
			nibbleactivate.setupActivationForm();
			nibbleactivate.setupLinks();
			nibblemain.setupValidations();
			
		});
		
	},
	setupLinks: function(){
		$('#forgot_now').click(function(e){
			nibblemain.showWaitingDialog({dialogSize: 'sm', progressType: 'warning'});
			nibbleforgot.init();
			e.preventDefault();
			nibblemain.hideWaitingDialog();
		});
		$('#login_now').click(function(e){
			nibblemain.showWaitingDialog({dialogSize: 'sm', progressType: 'warning'});
			nibblelogin.init();
			e.preventDefault();
			nibblemain.hideWaitingDialog();
		});
	},
	setupActivationForm: function() {
		$("#activate-form").parsley({
            errorsContainer: function (ParsleyField) {
                return ParsleyField.$element.attr("title");
            },
            errorsWrapper: false
        });
		$('#activate_now').click(function(e){
			nibblemain.showWaitingDialog('Activating your account...please hold on', {dialogSize: 'sm', progressType: 'warning'});
			if($("#activate-form").parsley().validate()){
				var data = {};
				data.username = $('#activate-form').find('input[name="activation_username"]').val();
				data.password = $('#activate-form').find('input[name="activation_password"]').val();
				data.activationCode = $('#activate-form').find('input[name="activation_code"]').val();
				
				var request = {};
				request.type = 'POST';
				request.contentType = 'application/json';
				request.dataType = 'application/json';
				request.url = nibblemain.getServicesUrl()+'/rest/activate';
				request.reqData = data;				
				request.done = function(data){
					$('#activate_form').addClass('hidden');
					$('#activate_result').removeClass('hidden');
				};
				nibblemain.jsonasync(request);				
			}
			
			e.preventDefault();
		});	
	}	

};

