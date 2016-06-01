'use strict';
app.controller('ActivateCtrl', function ActivateCtrl($scope, $state, userFactory) {

    /**
     * data init
     */
    $scope.initData = function () {
        $scope.activation = {};
        $scope.activation.condition = "default";
        $scope.user = {};
        var code = NibbleUtils.getParameterByName("code");
        if (code != "") {
            $scope.user.activationCode = code;
        }
    };

    /**
     * is button "Activate" active
     * @returns {boolean|FormController.$invalid|*|ngModel.NgModelController.$invalid|context.ctrl.$invalid}
     */
    $scope.invalidActivateForm = function () {
        return $scope.activation.form.username.$invalid ||
            $scope.activation.form.password.$invalid ||
            $scope.activation.form.code.$invalid;
    };

    /**
     * forward to login page
     */
    $scope.goToLogin = function () {
        $state.go('user.login');
    };

    /**
     * Activate nibbler
     */
    $scope.activateAccount = function () {
        userFactory.activate($scope.user)
            .success(function (data) {
                $scope.user=data;
                $scope.activation.condition = "activated";
            })
            .error(function (data, status) {
                NibbleUtils.errorCallback($scope, $state, data, status);
            });
    };

    $scope.forgotPassword = function () {
            $state.go('user.forgot');
    };

    $scope.initData();
    
    /**
     * Redirects user into account, to the linking bank account flow
     */
    $scope.goToAccountLinking = function(){
    	$state.go('user.register',{user:$scope.user,activate:true});
    };


});