;nibblemain = {

	name 	: 'nibblemain',
    context : '',
    // Creating modal dialog's DOM
	$dialog : $(
		'<div class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top:40%; overflow-y:visible;">' +
		'<div class="modal-dialog modal-m">' +
		'<div class="modal-content">' +
			'<div class="modal-body">' +
				'<div class="progress progress-striped active" style="margin-bottom:0;"><div class="progress-bar" style="width: 100%;"></div></div>' +
			'</div>' +
		'</div></div></div>'),
		
	gotToLogin: function(){
		window.location.replace(nibblemain.getWebContext());
	},
	getWebContext: function() {
		var url = window.location.href;
		var arr = url.split("/");
		var url = arr[0] + "//" + arr[2]
    	return url+nibblemain.context;
    },
    getServicesUrl: function() {
    	return nibblemain.getWebContext()+'/services';
    },
    getParameterByName: function (name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
        return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    },
	showWaitingDialog: function (options) {
		// Assigning defaults
		var settings = $.extend({
			dialogSize: 'm',
			progressType: 'warn'
		}, options);
		if (typeof options === 'undefined') {
			options = {};
		}
		// Configuring dialog
		nibblemain.$dialog.find('.modal-dialog').attr('class', 'modal-dialog').addClass('modal-' + settings.dialogSize);
		nibblemain.$dialog.find('.progress-bar').attr('class', 'progress-bar');
		if (settings.progressType) {
			nibblemain.$dialog.find('.progress-bar').addClass('progress-bar-' + settings.progressType);
		}
		// Opening dialog
		nibblemain.$dialog.modal();
	},
	hideWaitingDialog: function () {
		nibblemain.$dialog.modal('hide');
	},
	setupValidations: function(){
		// when there is an error, display the tooltip with the error message
        $.listen('parsley:field:error', function(fieldInstance) {
        	var is_touch_device = ("ontouchstart" in window) || window.DocumentTouch && document instanceof DocumentTouch;
        	var trigger = is_touch_device ? "click" : "hover";
            var messages = ParsleyUI.getErrorsMessages(fieldInstance);
            fieldInstance.$element.tooltip('destroy');
            fieldInstance.$element.tooltip({
                animation: false,
                container: 'body',
                placement: 'bottom',
                trigger: trigger,
                title: messages
            });
        });

        // destroy tooltip when field is valid
        $.listen('parsley:field:success', function(fieldInstance) {     
            fieldInstance.$element.tooltip('destroy');
        });
	},
    jsonasync: function(req){
    	$.ajax({
		  type: req.type,
		  contentType: req.contentType,
		  url: req.url,
		  dataType: req.dataType,
		  data: JSON.stringify(req.reqData),
		  done : req.done,
		  statusCode : {
				200 : req.done,
				204 : req.done,
				402 : req.autherror,
				401 : req.autherror,
				501 : nibblemain.handle500s,
				502 : nibblemain.handle500s,
				503 : nibblemain.handle500s,
				403 : function(response){
					debugger;
					window.location.replace(nibblemain.getWebContext()+'nibbleuser.html?message=Your Session has expired.');
				}
		  },
		  complete : function(){
				nibblemain.hideWaitingDialog();
		  }
		});	  
    },
	handle500s: function (data) {
		$('#message').html('<strong>'+data.responseText+'</strong>    ');
		$('#alert_message').removeClass('hidden');
	},
	
	initContent	: function(namespaceStr){
		if(namespaceStr==''){
			var namespace = eval('phxdash');
			namespace.init('.phxadmin-content-wrapper');
		}else{
			var namespace = eval(namespaceStr);
			namespace.init('.phxadmin-content-wrapper');
		}
	}	    

};

