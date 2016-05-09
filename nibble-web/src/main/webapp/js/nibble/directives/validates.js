'use strict';
app.directive('zipCode', function () {
    return {
        require: 'ngModel',
        link: function (scope, element, attr, ngModelCtrl) {
        	element.bind('blur', function (e) {
                var currentValue = element.val();
                if (currentValue != '' && currentValue.length < 5) {
                	ngModelCtrl.$setValidity('zipCode', false);
        		} else {
        			ngModelCtrl.$setValidity('zipCode', true);
        		}
            });
        }
    };
});

app.directive('phoneNumber', function () {
    return {
        require: 'ngModel',
        link: function (scope, element, attr, ngModelCtrl) {
        	element.bind('blur', function (e) {
                var currentValue = element.val();
                if (currentValue != '' && currentValue.length < 10) {
                	ngModelCtrl.$setValidity('phoneNumber', false);
        		} else {
        			ngModelCtrl.$setValidity('phoneNumber', true);
        		}
            });
        }
    };
});