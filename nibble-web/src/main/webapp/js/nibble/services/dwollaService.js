'use strict';
app.factory('dwollaFactory', function($http){
    let urlBase = NibbleUtils.getServicesUrl() +'/rest';
    let dwollaFactory={};
    
    // Sandbox (UAT)
    dwolla.configure('sandbox');

    // Production
    //dwolla.configure('prod');

    //"https://api-uat.dwolla.com/customers/11e8ff7e-6627-449f-b92b-ae8584e89a87"

    dwollaFactory.startIavFunding=function(token,fn,scope) {
        dwolla.iav.start(token, {
        container: 'fundingIAV',
        stylesheets: [
            'http://fonts.googleapis.com/css?family=Lato&subset=latin,latin-ext'
            ],
        fallbackToMicrodeposits: false
        }, function(err, res) {
            if(res){
                fn(scope.finicityBank,scope.user.email,res._links['funding-source'].href);
            }
            console.log('Error: ' + JSON.stringify(err) + ' -- Response: ' + JSON.stringify(res));
            if(err){
              //  if(err.code=='UnexpectedPage'){
                    angular.element('iframe')[0].style.visibility = 'visible';
               // }
            }
        });
    };
    
   /**
     * load new iav token
     * @param account
     * @returns {HttpPromise}
     */
    dwollaFactory.newToken = function(nibbler) {
        return $http.post(urlBase + '/customerToken', nibbler);
    };


    return dwollaFactory;
});