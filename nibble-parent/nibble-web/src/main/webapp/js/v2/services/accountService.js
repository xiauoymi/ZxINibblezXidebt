'use strict';
app.factory('accountFactory', function($http){

    var urlBase = NibbleUtils.getServicesUrl() +'/rest';
    var accountFactory = {};

    accountFactory.getInstitutions = function() {
        if (NibbleUtils.isDebug()) {
            console.log("factory method: getInstitutions() -> ");
        }
        return $http.get(urlBase + '/banks');
    };

    accountFactory.registerNibbler = function(nibbler) {
        if (NibbleUtils.isDebug()) {
            console.log("factory method: registerNibbler() -> ", nibbler);
        }
        return $http.post(urlBase + '/register', nibbler);
    };

    accountFactory.submitMfa = function(nibbler) {
        if (NibbleUtils.isDebug()) {
            console.log("factory method: submitMfa() -> ", nibbler);
        }
        return $http.post(urlBase + '/submitMfa', nibbler);
    };

    accountFactory.activate = function(nibbler) {
        if (NibbleUtils.isDebug()) {
            console.log("factory method: activate() -> ", nibbler);
        }
        return $http.post(urlBase + '/activate', nibbler);
    };

    return accountFactory;
});