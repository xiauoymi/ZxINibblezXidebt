'use strict';
app.factory('accountFactory', function($http){

    var urlBase = NibbleUtils.getServicesUrl() +'/rest';
    var accountFactory = {};

    /**
     * Get list of institutions
     * @returns {HttpPromise}
     */
    accountFactory.getInstitutions = function() {
        if (NibbleUtils.isDebug()) {
            console.log("factory method: getInstitutions() -> ");
        }
        return $http.get(urlBase + '/banks');
    };

    /**
     * Get list by search
     * @returns {HttpPromise}
     */
    accountFactory.searchInstitutions = function(search) {
        if (NibbleUtils.isDebug()) {
            console.log("factory method: searchInstitutions() -> ");
        }
        return $http.get(urlBase + '/searchInstitutions/'+search);
    };    

    /**
     * get week status
     * @returns {HttpPromise}
     */
    accountFactory.weeklyStatus = function() {
        return $http.get(urlBase + '/weeksummary');
    };

    /**
     * get list of accounts for roundup
     * @returns {HttpPromise}
     */
    accountFactory.getRoundupAccounts = function() {
        return $http.get(urlBase + '/roundupaccounts');
    };

    /**
     * update account's roundup
     * @param account
     * @returns {HttpPromise}
     */
    accountFactory.updateRoundupAccount = function(account) {
        return $http.post(urlBase + '/register/roundupaccount', account);
    };

    /**
     * update account's loan
     * @param account
     * @returns {HttpPromise}
     */
    accountFactory.updateLoanAccount = function(account) {
        return $http.post(urlBase + '/register/loanaccount', account);
    };

    return accountFactory;
});