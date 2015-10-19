'use strict';
app.controller('LoginCtrl',
    function LoginCtrl($scope, $state) {
        NibbleUtils.initAlerts($scope);

        $scope.registerNow = function () {
            $state.go('user.register');
        };
    });