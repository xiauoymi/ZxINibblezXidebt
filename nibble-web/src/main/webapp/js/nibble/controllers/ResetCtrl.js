'use strict';
app.controller('ResetCtrl',
    function ResetCtrl($scope, $state, pwdstrength, userFactory) {
        
        /**
         * send password
         */
        $scope.resetPassword = function () {
            userFactory.resetPassword($scope.reset)
            .success(function (data) {
                $scope.reset.condition = "complete";
            })
            .error(function (data, status) {
            	$scope.msg_alerts= [{type:'danger', msg:"The username you have provded does not exist."}];
            });
        };

        /**
         * form validation
         * @returns {boolean|FormController.$invalid|*|ngModel.NgModelController.$invalid|context.ctrl.$invalid}
         */
        $scope.invalidForgotForm = function() {
            return $scope.reset.form.email.$invalid;

        };

        /**
         * forward to login page
         */
        $scope.goToLogin = function () {
            $state.go('user.login');
        };
        
        /**
         * Check password is weak
         * @returns {boolean}
         */
        $scope.isPasswordWeak = function() {
            return $scope.reset.passwordStrenth < 45;
        };

        /**
         * Check password is ok
         * @returns {boolean}
         */
        $scope.isPasswordOk = function() {
            return $scope.reset.passwordStrenth >=45 && $scope.reset.passwordStrenth < 70;
        };

        /**
         * Check password is strong
         * @returns {boolean}
         */
        $scope.isPasswordStrong = function() {
            return $scope.reset.passwordStrenth >=70;
        };

        /**
         * init data
         */
        $scope.dataInit = function () {
            NibbleUtils.initAlerts($scope);
            $scope.reset = {};
            var resetcode = NibbleUtils.getParameterByName("resetcode");
            var email = NibbleUtils.getParameterByName("email");
            $scope.reset.condition = "default";
            $scope.reset.password = "";
            if (email != "") {
            	$scope.reset.email = email;
            }
            if (resetcode != "") {
            	 $scope.reset.resetcode = resetcode;
            }
            
            $scope.reset.repassword = "";
            $scope.reset.disabled = true;
            
            /* add watcher for custom password validation */
            $scope.$watch('reset.password', function(pass) {
                if ($scope.reset.form && $scope.reset.form.password) {
                    $scope.reset.passwordStrenth = pwdstrength.getStrength(pass);
                    if (pwdstrength.getStrength(pass) < 45) {
                        $scope.reset.form.password.$setValidity('strength', false);
                    } else {
                        $scope.reset.form.password.$setValidity('strength', true);
                    }
                }
            });
            /* add watcher for retype password validation */
            $scope.$watch('reset.repassword', function(pass) {
                if ($scope.reset.form && $scope.reset.form.repassword) {
                    if ($scope.reset.repassword != null && $scope.reset.repassword != '') {
                        $scope.reset.form.repassword.$setValidity('equalto',
                            $scope.reset.password == $scope.reset.repassword);
                    }
                }
            });
        };
        
    });