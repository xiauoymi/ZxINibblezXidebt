'use strict';
app.controller('LoginCtrl',
    function LoginCtrl($scope, $rootScope, $state, $stateParams, userFactory) {

        $scope.initData = function () {
            NibbleUtils.initAlerts($scope, $stateParams.message);
            $scope.user = {};
        };

        $scope.registerNow = function () {
            $state.go('user.register');
        };

        $scope.forgotPassword = function () {
            $state.go('user.forgot');
        };

        $scope.login = function() {
            userFactory.login($scope.user.login, $scope.user.pass, $scope.user.remember)
                .success(function(data) {
                    userFactory.profile()
                        .success(function(dataProfile, statusProfile, headersProfile, configProfile){
                            if (NibbleUtils.isDebug()) {
                                console.log(dataProfile);
                            }
                            $state.go('dashboard.users');
                        })
                        .error(function(dataProfile) {
                            NibbleUtils.errorCallback($scope, $state, dataProfile, status);
                        });
                })
                .error(function(data) {
                    NibbleUtils.errorCallback($scope, $state, data, status);
                })
        }


    });