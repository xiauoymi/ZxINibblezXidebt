'use strict';
app.factory('accountFactory', function($http){

    var urlBase = NibbleUtils.getServicesUrl() +'/rest';
    var accountFactory = {};

    accountFactory.getInstitutions = function() {
        return $http.get(urlBase + '/banks');
    };

    return accountFactory;
});