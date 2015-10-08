'use strict';
app.factory('accountFactory', function($http){

    var urlBase = NibbleUtils.getServicesUrl() +'/rest';
    var accountFactory = {};

    accountFactory.getInstitutions = function() {
        return $http.get(urlBase + '/banks');
    };

    accountFactory.registerNibbler = function(nibbler) {
        console.log(nibbler);
        return $http.post(urlBase + '/register', nibbler);
    };

    accountFactory.activate = function(nibbler) {
        console.log(nibbler);
        return $http.post(urlBase + '/activate', nibbler);
    };

    return accountFactory;
});