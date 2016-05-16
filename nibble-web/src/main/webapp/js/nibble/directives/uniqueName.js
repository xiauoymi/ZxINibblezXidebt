'use strict';
app.directive('uniqueName', function($http) {
    var toId;
    return {
        require: 'ngModel',
        link: function(scope, elem, attr, ctrl) {
        	elem.bind('blur', function (e) {
                var currentValue = elem.val();
                $http.get(NibbleUtils.getServicesUrl() +'/rest/useruq?register_username=' + currentValue).success(function(data) {
                	ctrl.$setValidity('uniquename', data);
                    
                }).error(function(data, status, headers, config) {
                    console.log("something wrong")
                });
            });
        }
    }
});