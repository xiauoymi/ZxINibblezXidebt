;nibbleregister = {
	name : 'nibbleregister',
	institutions : new Object(),

	init : function(){
		$('#user-content').load('views/user/'+nibbleregister.name+'.html', function(){
			nibbleregister.setupRegistrationForm();
			nibbleregister.setupLinks();
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
	isUserUnique: function(){
		
	},
	setupRegistrationForm: function() {
		$("#register-form").parsley({
            errorsContainer: function (ParsleyField) {
                return ParsleyField.$element.attr("title");
            },
            errorsWrapper: false
        });
		
		var reg_username_parsley = $("#register_username").parsley();
		reg_username_parsley .addAsyncValidator('useruq', function (xhr) {
			  if(xhr.status == '200'){
				  if($.parseJSON(xhr.responseText)){
					  window.ParsleyUI.removeError(reg_username_parsley,'register_username');
					  $.emit('parsley:field:success');
					  return 200;
				  }else{
					  window.ParsleyUI.addError(reg_username_parsley,'register_username', 'This username already exists.');
					  $.emit('parsley:field:error');
				  }
			  }else{
				  window.ParsleyUI.removeError(reg_username_parsley,'register_username');
				  $.emit('parsley:field:success');
				  return 404;
			  }
		  }, nibblemain.getServicesUrl()+'/rest/useruq/');
		
		$('#register_password').pwstrength({
			minChar: 4,
			bootstrap3: true,
			showVerdicts: false,
			showVerdictsInitially: false,
			usernameField: "#register_username",
			ui : {
				showVerdictsInsideProgressBar: true
			}
		});
		
		$("#register-form-modal").parsley({
            errorsContainer: function (ParsleyField) {
                return ParsleyField.$element.attr("title");
            },
            errorsWrapper: false
        });
		$('#register_back1').click(function(e){
			nibblemain.showWaitingDialog({dialogSize: 'sm', progressType: 'warning'});
			$('#registration_form1').removeClass('hidden');
			$('#registration_form2').addClass('hidden');	
			e.preventDefault();
			nibblemain.hideWaitingDialog();
		});
		$('#register_back2').click(function(e){
			nibblemain.showWaitingDialog({dialogSize: 'sm', progressType: 'warning'});
			$('#registration_form2').removeClass('hidden');
			$('#registration_form3').addClass('hidden');	
			e.preventDefault();
			nibblemain.hideWaitingDialog();
		});
		$('#register_next1').click(function(e){
			nibblemain.showWaitingDialog({dialogSize: 'sm', progressType: 'warning'});
			if($('#register-form').parsley().validate('registration_form1', true) &&
					$("#register_username").parsley().asyncIsValid()){
				$('#registration_form2').removeClass('hidden');
				$('#registration_form1').addClass('hidden');	
			}										
			e.preventDefault();
			nibblemain.hideWaitingDialog();
		});
		$('#register_next2').click(function(e){
			nibblemain.showWaitingDialog({dialogSize: 'sm', progressType: 'warning'});
			if($("#register-form").parsley().validate('registration_form2', true)){
				$('#registration_form3').removeClass('hidden');
				$('#registration_form2').addClass('hidden');	
				nibbleregister.getInstitutionTypes();	
			}else{
				nibblemain.hideWaitingDialog();
			}
			e.preventDefault();
		});
		$('#register_finish').click(function(e){
			nibblemain.showWaitingDialog('Creating your account...please hold on', {dialogSize: 'sm', progressType: 'warning'});
			if($("#register-form").parsley().validate('registration_form3', true)){					
				var request = {};
				request.type = 'POST';
				request.contentType = 'application/json';
				request.dataType = 'application/json';
				request.autherror = function(){
					$('#message').html('<strong>There was an authorization problem. Please check your credentials.</strong>    ');
					$('#alert_message').removeClass('hidden');
					$('#register-form').find('input[name="inst_username"]').text('');
					$('#register-form').find('input[name="inst_username"]').focus();
					$('#register-form').find('input[name="inst_password"]').text('');
					$('#register-form').find('input[name="inst_pin"]').text('');
					$('input[name="register_inst_radio"]').attr('checked', false);
				};
				if(nibbleregister.institutions[$('#register-form').find( "input:radio[name=register_inst_radio]:checked" ).val()].has_mfa){
					var data = {};
					data.institution = nibbleregister.institutions[$('#register-form').find( "input:radio[name=register_inst_radio]:checked" ).val()];
					data.instUsername =$('#registration_form3_modal').find('input[name="inst_username"]').val();	
					data.instPassword =$('#registration_form3_modal').find('input[name="inst_password"]').val();	
					data.instPin =$('#registration_form3_modal').find('input[name="inst_pin"]').val();	
					request.reqData = data;	
					request.url = nibblemain.getServicesUrl()+'/rest/registermfa';

					
					request.done = function(data){
						var mfareq = {};
						mfareq.type = 'POST';
						mfareq.contentType = 'application/json';
						mfareq.dataType = 'json';
						mfareq.url = nibblemain.getServicesUrl()+'/rest/mfa';						
						mfareq.done = function(response) {
							$('#mfa-form').addClass('hidden');
							$('#registration_result').removeClass('hidden');
						};
						$('#registration_form3').addClass('hidden');
						$('#register-form').addClass('hidden');
						var resp = $.parseJSON(data.responseText);
						if(resp){
							$('#registration_form4_info').html('');
							if(resp.type=='selections'){
								for(var i = 0; i<resp.mfa.length; i++){
									var $rowdiv = $("<div>", {id:"mfa-row-"+i, class: "form-group"});
									var $coldiv = $("<div>", {id:"mfa-col-"+i, class: "col-md-12 col-sm-12 col-xs-12"});
									
									$coldiv.append(	'<label for="mfa'+i+'" >'+resp.mfa[i].question+'</label><br/>');
									for(var j=0; j<resp.mfa[i].answers.length; j++){
										var $radiodiv = $("<div>", {class: "radio"});
										$radiodiv.append('<label><input type="radio" id="mfa'+i+'-'+j+'" value="' +resp.mfa[i].answers[j]+ '" name="mfa'+i+'" data-parsley-group="registration_form4"  required data-parsley-required-message="You must select an answer">' +resp.mfa[i].answers[j]+ '</label>');
										$coldiv.append($radiodiv);
									}
									$rowdiv.append($coldiv);	
									$rowdiv.append('<br/>');
									$('#registration_form4_info').append($rowdiv);
								}

								$('#mfa-form').removeClass('hidden');							
								$('#mfa-form-heading').html('<h4 class="form-primary-heading">Please select an answer for the following questions:</h4>');
								
								$('#mfa_submit').click(function(e){
									nibblemain.showWaitingDialog({dialogSize: 'sm', progressType: 'warning'});
									if($("#mfa-form").parsley().validate('registration_form4', false)){	
										var mfadata = nibbleregister.getRegFormData();
										mfadata.access_token = resp.access_token;
										mfadata.mfa = new Array(resp.mfa.length);
										for(var i = 0; i<resp.mfa.length; i++){
											mfadata.mfa[i] = $('#mfa-form').find( 'input:radio[name=mfa'+i+']:checked' ).val();
										}
										mfareq.reqData = mfadata;	
										nibblemain.jsonasync(mfareq);		
									}else{
										$('#message').html('<strong>Your must select an answer to the MFA question(s).</strong>    ');
										$('#alert_message').removeClass('hidden');
										nibblemain.hideWaitingDialog();
									}
									e.preventDefault();
								});
								
							}else if(resp.type=='questions'){
								for(var i = 0; i<resp.mfa.length; i++){
									var $rowdiv = $("<div>", {id:"inst-row-"+i, class: "form-group"});
									var $coldiv = $("<div>", {id:"mfa-col-"+i, class: "col-md-12 col-sm-12 col-xs-12"});
									
									$coldiv.append(	'<label for="mfa'+i+'" >'+resp.mfa[i].question+'</label><br/>');
									$coldiv.append('<input type="text" class="form-control empty" id="mfa-answer'+i+'" name="mfa'+i+'" data-parsley-group="registration_form4"  required data-parsley-required-message="You must enter an answer"/>');
																	
									$rowdiv.append($coldiv);
									$('#registration_form4_info').append($rowdiv);
								}								

								$('#mfa-form').removeClass('hidden');
								$('#mfa-form-heading').html('<h4 class="form-primary-heading">Please enter an answer for the following question(s):</h4>');
								
								$('#mfa_submit').click(function(e){
									nibblemain.showWaitingDialog({dialogSize: 'sm', progressType: 'warning'});
									if($("#mfa-form").parsley().validate('registration_form4', false)){	
										var mfadata = nibbleregister.getRegFormData();
										mfadata.access_token = resp.access_token;
										mfadata.mfa = new Array(resp.mfa.length);
										for(var i = 0; i<resp.mfa.length; i++){
											mfadata.mfa[i] = $('#mfa-form').find( 'input[name=mfa'+i+']' ).val();
										}
										mfareq.reqData = mfadata;	
										nibblemain.jsonasync(mfareq);		
									}else{
										$('#message').html('<strong>Your must answer the MFA question(s).</strong>    ');
										$('#alert_message').removeClass('hidden');
										nibblemain.hideWaitingDialog();
									}
									e.preventDefault();
								});
							}else if(resp.type=='list'){
								$('#registration_form4_info').html('');
								var $rowdiv = $("<div>", {id:"inst-row-"+i, class: "form-group"});
								var $coldiv = $("<div>", {id:"mfa-col-"+i, class: "col-md-12 col-sm-12 col-xs-12"});
								
								for(var i = 0; i<resp.mfa.length; i++){
									var $rowdiv = $("<div>", {id:"mfa-row-"+i, class: "form-group"});
									var $coldiv = $("<div>", {id:"mfa-col-"+i, class: "col-md-12 col-sm-12 col-xs-12"});
									
									var $radiodiv = $("<div>", {class: "radio"});
									$radiodiv.append('<label><input type="radio" id="mfacode'+i+'" value="' +resp.mfa[i].mask+ '" name="mfacode" data-parsley-group="registration_form4"  required data-parsley-required-message="You must select a method">');
									if(resp.mfa[i].type == 'email'){
										$radiodiv.append('<i class="fa fa-envelope"></i> '+resp.mfa[i].mask);
									}else if(resp.mfa[i].type == 'phone')	{
										$radiodiv.append('<i class="fa fa-phone"></i> '+resp.mfa[i].mask);
									}else{
										$radiodiv.append('<i class="fa fa-credit-card"></i> '+resp.mfa[i].mask);
									}
									$radiodiv.append('</label>');		
									$coldiv.append($radiodiv);
									
									$rowdiv.append($coldiv);	
									$rowdiv.append('<br/>');
									$('#registration_form4_info').append($rowdiv);
									
								}

								$('#mfa-form').removeClass('hidden');
								$('#mfa_submit').html('Send Code');
								$('#mfa-form-heading').html('<h4 class="form-primary-heading">Please select the destination for your one-time code:</h4>');
								
								$('#mfa_submit').click(function(e){
									nibblemain.showWaitingDialog({dialogSize: 'sm', progressType: 'warning'});
									if($("#mfa-form").parsley().validate('registration_form4', false)){	
										var mfacodereq = {};
										mfacodereq.type = 'POST';
										mfacodereq.contentType = 'application/json';
										mfacodereq.dataType = 'json';
										mfacodereq.url = nibblemain.getServicesUrl()+'/rest/mfacode';						
										mfacodereq.done = function(response) {
											$('#registration_form4_code').addClass('hidden');
											var $rowdiv = $("<div>", {id:"mfa-row", class: "form-group"});
											var $coldiv = $("<div>", {id:"mfa-col", class: "col-md-12 col-sm-12 col-xs-12"});
											$coldiv.append(	'<label for="mfa" class="sr-only" >MFA Code</label><br/>');
											$coldiv.append(	'<input type="text" id="mfa_code" class="form-control empty" placeholder="&#xf02a;    Code" name="mfa_code" required data-parsley-validation-threshold="2" data-parsley-trigger="change" data-parsley-required-message="The code is required" data-parsley-group="registration_form2">');
											
											$rowdiv.append($coldiv);	
											$rowdiv.append('<br/>');
											$('#registration_form4_info').append($rowdiv);
										
							
											$('#mfa-form-heading').html('<h4 class="form-primary-heading">Please enter the one-time code sent by your institution:</h4>');
											$('#mfa_submit').html('Submit');
											$('#mfa_submit').off('click');
											
											$('#mfa_submit').click(function(e){
												nibblemain.showWaitingDialog({dialogSize: 'sm', progressType: 'warning'});
												if($("#mfa-form").parsley().validate('registration_form4', false)){	
													var mfadata = nibbleregister.getRegFormData();
													
													mfadata.access_token = resp.access_token;
													mfadata.institution = nibbleregister.institutions[$('#register-form').find( "input:radio[name=register_inst_radio]:checked" ).val()];
													mfadata.mfa = $('#mfa-form').find( 'input[name=mfa]' ).val();
													mfareq.reqData = mfadata;	
													nibblemain.jsonasync(mfareq);		
												}else{
													$('#message').html('<strong>Your must enter the code to link this account.</strong>    ');
													$('#alert_message').removeClass('hidden');
													nibblemain.hideWaitingDialog();
												}
												e.preventDefault();
											});
											
											
										};
										
										var mfacodedata = {};
										mfacodedata.access_token = resp.access_token;
										mfacodedata.send_method = $('#mfa-form').find( 'input:radio[name=mfacode]:checked' ).val();
										mfacodereq.reqData = mfadata;	
										nibblemain.jsonasync(mfareq);

										$('#mfa_code_request').addClass('hidden');
										$('#mfa_submit').removeClass('hidden');
									}else{
										$('#message').html('<strong>Your must select a method to receive the one-time code.</strong>    ');
										$('#alert_message').removeClass('hidden');
										nibblemain.hideWaitingDialog();
									}
									e.preventDefault();
								});
								
							}else{
								$('#registration_result').removeClass('hidden');
							}
						}
					};		
				}else{
					request.reqData = nibbleregister.getRegFormData();	
					request.url = nibblemain.getServicesUrl()+'/rest/register';
					request.done = function(data){
						$('#registration_form4_info').html('');
						$('#registration_result').removeClass('hidden');
						$('#registration_form3').addClass('hidden');
						$('#register-form').addClass('hidden');
						nibblemain.hideWaitingDialog();
					}					
				}					
				
				nibblemain.jsonasync(request);				
			}else{
				$('#message').html('<strong>Your must fill out all necessary fields accurately.</strong>    ');
				$('#alert_message').removeClass('hidden');
				nibblemain.hideWaitingDialog();
			}
			e.preventDefault();
		});		
		
		$('#registration_form3_modal').modal({
			backdrop: 'static',
			keyboard: false
		});
		$('#registration_form3_modal').modal('hide');
	},
	getRegFormData: function() {
		var data = {};
		data.firstName = $('#register-form').find('input[name="register_first_name"]').val();
		data.lastName = $('#register-form').find('input[name="register_last_name"]').val();
		data.email = $('#register-form').find('input[name="register_email"]').val();
		data.password = $('#register-form').find('input[name="register_password"]').val();
		data.username = $('#register-form').find('input[name="register_username"]').val();
		data.address1 = $('#register-form').find('input[name="register_address1"]').val();
		data.address2 = $('#register-form').find('input[name="register_address2"]').val();
		data.city = $('#register-form').find('input[name="register_city"]').val();
		data.state = $('#register-form').find('input[name="register_state"]').val();
		data.zip = $('#register-form').find('input[name="register_zip"]').val();
		data.phone =$('#register-form').find('input[name="register_phone"]').val();
		data.url = nibblemain.getWebContext();
		data.institution = nibbleregister.institutions[$('#register-form').find( "input:radio[name=register_inst_radio]:checked" ).val()];
		data.instUsername =$('#registration_form3_modal').find('input[name="inst_username"]').val();	
		data.instPassword =$('#registration_form3_modal').find('input[name="inst_password"]').val();	
		data.instPin =$('#registration_form3_modal').find('input[name="inst_pin"]').val();							
		
		return data;
    },
	getInstitutionTypes: function() {
		var request = {};
		request.type = 'GET';
		request.url = nibblemain.getServicesUrl()+'/rest/banks';
		request.done = function(data){
			var insts = data.items;
			var accountTypesDiv = $('#insts-types');
			accountTypesDiv.html('');
			var $rowdiv;
			for(var i = 0; i<insts.length; i++){
				nibbleregister.institutions[insts[i].type] = insts[i];
				var instsType = insts[i].type;
				var instsName = insts[i].name;				
				
				if(i%3==0) {
					$rowdiv = $("<div>", {id:"inst-row-"+i, class: "row text-center"});
					var $coldiv = $("<div>", {id:"inst-col-"+i, class: "col-md-4 col-sm-4 col-xs-4"});
				}else{
					var $coldiv = $("<div>", {id:"inst-col-"+i, class: "col-md-4 col-sm-4 col-xs-4"});
				}
				$coldiv.append(
						'<input class="img-radio" type="radio" id="'+instsType+'" value="' +instsType+ '" name="register_inst_radio" data-parsley-group="registration_form3"  required data-parsley-required-message="You must select an account"/>'+
						'<label for="'+instsType+'" class="grow" style="background-image: url(' + nibblemain.getServicesUrl()+'/rest/logo/'+instsType + ');"></label>');

				$rowdiv.append($coldiv);
				accountTypesDiv.append($rowdiv);						
								
			}	
			

			$("input[name='register_inst_radio']").change(function(){
				if (this.checked) {
					$('#register-form').find('input[name="inst_username"]').text('');
					$('#register-form').find('input[name="inst_username"]').focus();
					$('#register-form').find('input[name="inst_password"]').text('');
					$('#register-form').find('input[name="inst_pin"]').text('');
					$('#registration_form3_modal').modal('show');
		        }						
			});
			nibbleregister.setupAccountAuthModal();
		};	
		nibblemain.jsonasync(request);
    },
    setupAccountAuthModal : function(){
    	$('#registration_form3_modal').on('show.bs.modal', function (e) {
			$('#modal-body-username-fg').addClass('hidden');
			$('#modal-body-password-fg').addClass('hidden');
			$('#modal-body-pin-fg').addClass('hidden');

			$('#inst_username').attr("data-parsley-required", false);
			$('#inst_password').attr("data-parsley-required", false);
			$('#inst_pin').attr("data-parsley-required", false);
			
			var clickedInst = nibbleregister.institutions[$('#register-form').find( "input:radio[name=register_inst_radio]:checked" ).val()];
			$('#modal-title-div').html('<h4 class="modal-title">'+clickedInst.name+' Authentication Information Required </h4>');
			if(clickedInst.credentials.username){
				$('#inst_username_label').html(clickedInst.credentials.username);
				$('#inst_username').attr("placeholder", clickedInst.credentials.username);
				$('#inst_username').attr("data-parsley-required", true);
				$('#modal-body-username-fg').removeClass('hidden');
			}
			
			if(clickedInst.credentials.password){
				$('#inst_password_label').html(clickedInst.credentials.password);
				$('#inst_password').attr("placeholder", clickedInst.credentials.password);
				$('#inst_password').attr("data-parsley-required", true);
				$('#modal-body-password-fg').removeClass('hidden');
			}
			
			if(clickedInst.credentials.pin){
				$('#inst_pin_label').html(clickedInst.credentials.pin);
				$('#inst_pin').attr("placeholder", clickedInst.credentials.pin);
				$('#inst_pin').attr("data-parsley-required", true);
				$('#modal-body-pin-fg').removeClass('hidden');
			}
			
			$('#modal-title-div').html('<h4 class="modal-title">'+clickedInst.name+' Authentication Information Required </h4>');
		});
		
		$('#registration_form3_modal').on('hide.bs.modal', function (e) {
			if(!$("#register-form-modal").parsley().validate('registration_form4', false)){
				$( "input:radio[name=register_inst_radio]:checked" ).prop('checked', false);
			}
		});
		
		$('#registration_form3_modal_save').click(function(){
			if($("#register-form-modal").parsley().validate('registration_form4', false)){
				if($( "input:radio[name=register_inst_radio]:checked" )){
					$('#registration_form3_modal').modal('hide');
					
				}
			}else{
				$( "input:radio[name=register_inst_radio]:checked" ).prop('checked', false);
				$('#registration_form3_modal').modal('hide');
			}
		});
    }
	
};

