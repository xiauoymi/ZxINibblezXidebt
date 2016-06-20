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
                        .error(function(dataProfile,status) {
                            //dataProfile="Nibble's web app is coming soon. Please check your email for weekly updates or contact us at info@nibbledebt.com";
                        	if(status==0){
                            	NibbleUtils.errorCallback($scope, $state, "Oops. Something went wrong, please reload the page and try again. If the problem persist, please contact Customer Care at info@nibbledebt.com.", status);
                            }else{
                            	NibbleUtils.errorCallback($scope, $state, dataProfile.error, status);
                            }
                        });
                })
                .error(function(data,status) {
                	if(status==0){
                    	NibbleUtils.errorCallback($scope, $state, "Oops. Something went wrong, please reload the page and try again. If the problem persist, please contact Customer Care at info@nibbledebt.com.", status);
                    }else{
                    	NibbleUtils.errorCallback($scope, $state, data.error, status);
                    }
                })
        }


    });