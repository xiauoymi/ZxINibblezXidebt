'use strict';
app.controller('ForgotCtrl',
    function LoginCtrl($scope, $state, userFactory) {


        /**
         * send password
         */
        $scope.sendPassword = function () {
            userFactory.forgotPassword($scope.forgot.email).success(function(data) {
                $scope.forgot.condition = "complete";
            }).error(function(data, status) {
                NibbleUtils.errorCallback($scope, $state, data, status);
            });
        };

        /**
         * form validation
         * @returns {boolean|FormController.$invalid|*|ngModel.NgModelController.$invalid|context.ctrl.$invalid}
         */
        $scope.invalidForgotForm = function() {
            return $scope.forgot.form.email.$invalid;

        };

        /**
         * forward to login page
         */
        $scope.goToLogin = function () {
            $state.go('user.login');
        };

        /**
         * init data
         */
        $scope.dataInit = function () {
            NibbleUtils.initAlerts($scope);
            $scope.forgot = {};
            $scope.forgot.condition = "default";
            $scope.forgot.email = "";
        }
    });