NibbleUtils = function () {
};

NibbleUtils.getParameterByName = function (name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
};

NibbleUtils.initAlerts = function ($scope) {
    var message = NibbleUtils.getParameterByName('message');
    var alerts = [];
    if (message != null && message != '') {
        alerts = [{type:'danger', msg:message}]
    }
    $scope.msg_alerts = alerts;
    $scope.closeAlert = function(index) {
        $scope.msg_alerts.splice(index, 1);
    };
};

NibbleUtils.getServicesUrl = function() {
    var url = window.location.href;
    var arr = url.split("/");
    url = arr[0] + "//" + arr[2]
    return url +'/services';

};

NibbleUtils.errorCallback = function($scope, data) {
    $scope.msg_alerts = [{type:'danger', msg:data}];
    $scope.closeAlert = function(index) {
        $scope.msg_alerts.splice(index, 1);
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