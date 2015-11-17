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

    accountFactory.forgotPassword = function(email) {
        if (NibbleUtils.isDebug()) {
            console.log("factory method: forgotPassword() ->", email);
        }
        //Todo:asa  implement later
    };

    accountFactory.weeklyStatus = function() {
        return $http.get(urlBase + '/weeksummary');
    };

    accountFactory.login = function(login, pwd, remember) {
        var requestForm = $.param({
            nibbler_username: login,
            nibbler_password: pwd
        });
        var config = {
            headers : {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
            }
        };
        return $http.post(NibbleUtils.getServicesUrl() + "/sslogin", requestForm, config);
    };

    accountFactory.profile = function() {
        return $http.get(urlBase + '/profile');
    };

    return accountFactory;
});