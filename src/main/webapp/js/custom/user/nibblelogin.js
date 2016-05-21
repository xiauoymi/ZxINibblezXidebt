;nibblelogin = {
	name : 'nibblelogin',
	init : function(){
		$('#user-content').load('views/user/'+nibblelogin.name+'.html', function(){
			var message = nibblemain.getParameterByName('message');	
			if(message != null && message != ''){
				$('#message').html('<strong>'+message+'</strong>    ');
				$('#alert_message').removeClass('hidden');
			}
			nibblelogin.setupLoginForm();
			nibblelogin.setupLinks();
			nibblemain.setupValidations();		
		});
	},
	setupLinks: function(){
		$('#register_now').click(function(e){
			nibblemain.showWaitingDialog({dialogSize: 'sm', progressType: 'warning'});
			nibbleregister.init();
			nibblemain.hideWaitingDialog();
			e.preventDefault();
		});
		$('#forgot_now').click(function(e){
			nibblemain.showWaitingDialog({dialogSize: 'sm', progressType: 'warning'});
			nibbleforgot.init();
			nibblemain.hideWaitingDialog();
			e.preventDefault();
		});
	},
	setupLoginForm: function() {
		$("#login-form").parsley({
            errorsContainer: function (ParsleyField) {
                return ParsleyField.$element.attr("title");
            },
            errorsWrapper: false
        });
	}	
	
};

