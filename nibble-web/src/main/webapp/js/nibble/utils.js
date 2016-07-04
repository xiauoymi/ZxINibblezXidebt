(function () {
	
	if (typeof String.prototype.endsWith !== 'function') {
	    String.prototype.endsWith = function(suffix) {
	        return this.indexOf(suffix, this.length - suffix.length) !== -1;
	    };
	}
	
	String.prototype.endsWith = function(suffix) {
	    return this.indexOf(suffix, this.length - suffix.length) !== -1;
	};
     if ( typeof window.CustomEvent === "function" ) return false;

     function CustomEvent ( event, params ) {
       params = params || { bubbles: false, cancelable: false, detail:  undefined };
    var evt = document.createEvent( 'CustomEvent' );
    evt.initCustomEvent( event, params.bubbles, params.cancelable, params.detail );
    return evt;
   }

  CustomEvent.prototype = window.Event.prototype;

  window.CustomEvent = CustomEvent;
})();




NibbleUtils = function () {
};

NibbleUtils.getParameterByName = function (name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.hash);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
};

NibbleUtils.initAlerts = function ($scope, message) {
    var alerts = [];
    if (message && message != null && message != '') {
        alerts = [{type:'danger', msg:message}]
    }
    $scope.msg_alerts = alerts;
    $scope.closeAlert = function(index) {
        $scope.msg_alerts.splice(index, 1);
    };
};

NibbleUtils.getServicesUrl = function() {
    return NibbleUtils.getBaseUrl() +'/services';
};

NibbleUtils.getBaseUrl = function() {
    var url = window.location.href;
    var arr = url.split("/");
    url = arr[0] + "//" + arr[2];
    return url;

};

NibbleUtils.errorCallback = function($scope, $state, data, status) {
    if (status == 403 || status == "403") {
        $state.go('user.login', {message: "Please LogIn."});
    }
    if(data && data.data && data.data.message){
        $scope.msg_alerts = [{type:'danger', msg:data.data.message}];
    }
    else if(data.message){
    	$scope.msg_alerts = [{type:'danger', msg:data.message}];
    }else if(data){
    	$scope.msg_alerts = [{type:'danger', msg:data}];
    }else{
        $scope.msg_alerts = [{type:'danger', msg:"Error while processing, please contact support!"}];
    }
    $scope.closeAlert = function(index) {
        $scope.msg_alerts.splice(index, 1);
    };
};



NibbleUtils.pushErrorCallback = function($scope,msg_alerts, $state, data, status) {
    if (status == 403 || status == "403") {
        $state.go('user.login', {message: "Please LogIn."});
    }
    $scope[msg_alerts] = [{type:'danger', msg:data.message}];
    $scope.closeAlert = function(index) {
        $scope[msg_alerts].splice(index, 1);
    };
};

NibbleUtils.checkExceptedURLs = function(url) {
    var excArray = NibbleUtils.exceptedURLs();
    for (var i=0; i < excArray.length; i++) {
        if (url.match(excArray[i])) {
            return true;
        }
    }
    return false;
};

NibbleUtils.exceptedURLs = function() {
    return [
        "rest/useruq"
    ];
};

NibbleUtils.isDebug = function() {
    return true;
};