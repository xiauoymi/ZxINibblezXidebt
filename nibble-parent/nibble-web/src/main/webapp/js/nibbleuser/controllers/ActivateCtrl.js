'use strict';
app.controller('ActivateCtrl', function ActivateCtrl($scope, $state, accountFactory) {

    /**
     * data init
     */
    $scope.initData = function () {
        $scope.activation = {};
        $scope.activation.condition = "default";
        $scope.user = {};
        var code = NibbleUtils.getParameterByName("acode");
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
        accountFactory.activate($scope.user)
            .success(function (data) {
                $scope.activation.condition = "activated";
            })
            .error(function (error) {
                NibbleUtils.errorCallback($scope, error)
            });
    };

    $scope.initData();


});