'use strict';
app.directive('uniqueName', function($http) {
    var toId;
    return {
        require: 'ngModel',
        link: function(scope, elem, attr, ctrl) {
            //when the scope changes, check the name.
            scope.$watch(attr.ngModel, function(value) {
                // if there was a previous attempt, stop it.
                if(toId) clearTimeout(toId);

                // start a new attempt with a delay to keep it from
                // getting too "chatty".
                toId = setTimeout(function(){
                    // call to some API that returns { isValid: true } or { isValid: false }
                    $http.get(NibbleUtils.getServicesUrl() +'/rest/useruq?register_username=' + value).success(function(data) {

                        //set the validity of the field
                        //if (data == true) {
                            ctrl.$setValidity('uniquename', data);
                        //} else if (data == false) {
                        //    ctrl.$setValidity('uniqueName', true);
                        //}
                    }).error(function(data, status, headers, config) {
                        console.log("something wrong")
                    });
                }, 200);
            })
        }
    }
});