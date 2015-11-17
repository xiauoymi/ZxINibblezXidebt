'use strict';
app.controller('LoginCtrl',
    function LoginCtrl($scope, $state, accountFactory, $cookieStore) {

        $scope.initData = function() {
            NibbleUtils.initAlerts($scope);
            $scope.user = {};
        };

        $scope.registerNow = function () {
            $state.go('user.register');
        };

        $scope.forgotPassword = function () {
            $state.go('user.forgot');
        };

        $scope.login = function() {
            accountFactory.login($scope.user.login, $scope.user.pass, $scope.user.remember)
                .success(function(data, status, headers, config) {
                    accountFactory.profile()
                        .success(function(dataProfile, statusProfile, headersProfile, configProfile){
                            if (NibbleUtils.isDebug()) {
                                console.log(dataProfile);
                            }
                            $cookieStore.put("nibbler_", dataProfile);
                            $state.go('dashboard.home');
                        })
                        .error(function(dataProfile) {
                            console.log(dataProfile);
                        });
                })
                .error(function(data) {
                    console.log(data);
                })
        }


    });