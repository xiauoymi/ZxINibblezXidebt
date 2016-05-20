'use strict';
app.factory('userFactory', function($http){

    var urlBase = NibbleUtils.getServicesUrl() +'/rest';
    var userFactory = {};

    /**
     * register nibbler
     * @param nibbler - object
     * @returns {HttpPromise}
     */
    userFactory.registerNibbler = function(nibbler) {
        if (NibbleUtils.isDebug()) {
            console.log("factory method: registerNibbler() -> ", nibbler);
        }
        return $http.post(urlBase + '/register', nibbler);
    };

    /**
     * submit mfa answers
     * @param nibbler
     * @returns {HttpPromise}
     */
    userFactory.submitMfa = function(nibbler) {
        if (NibbleUtils.isDebug()) {
            console.log("factory method: submitMfa() -> ", nibbler);
        }
        return $http.post(urlBase + '/submitMfa', nibbler);
    };

    /**
     * activate nibbler
     * @param nibbler
     * @returns {HttpPromise}
     */
    userFactory.activate = function(nibbler) {
        if (NibbleUtils.isDebug()) {
            console.log("factory method: activate() -> ", nibbler);
        }
        return $http.post(urlBase + '/register/activate', nibbler);
    };

    /**
     * Forgot Password
     * @param email
     */
    userFactory.forgotPassword = function(nibbler) {
        if (NibbleUtils.isDebug()) {
            console.log("factory method: forgotPassword() ->", nibbler);
        }
        return $http.post(urlBase + '/sendResetCode', nibbler);
    };
    
    /**
     * Reset Password
     * @param email
     * @param newpassword
     * @param resetcode
     */
    userFactory.resetPassword = function(nibbler) {
        if (NibbleUtils.isDebug()) {
            console.log("factory method: resetPassword() ->", nibbler);
        }
        return $http.post(urlBase + '/reset', nibbler);
    };

    /**
     * Login Service
     * @param login - login name
     * @param pwd - password
     * @param remember - remember check box
     * @returns {HttpPromise}
     */
    userFactory.login = function(login, pwd, remember) {
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

    /**
     * User Profile
     * @returns {HttpPromise}
     */
    userFactory.profile = function() {
        return $http.get(urlBase + '/profile');
    };
    
    
    /**
     * Search users
     * @param searchForm - search form
     * @returns {HttpPromise}
     */
    userFactory.users = function(searchForm) {
        return $http.post(urlBase + '/users',searchForm);
    };

    return userFactory;
});