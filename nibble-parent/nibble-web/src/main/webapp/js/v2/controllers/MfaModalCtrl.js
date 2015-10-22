'use strict';
app.controller('MfaModalCtrl',
    function MfaModalCtrl($scope, $modalInstance, withMfaResponse) {
        $scope.withMfaResponse = withMfaResponse;
        $scope.mfa = {
            condition: "default"
        };

        withMfaResponse

        $scope.institutionParams = [];

        var mas = clickedBank.loginForm.loginField;
        var filtered = [];

        for (var i = 0; i < mas.length; i++) {
            filtered.push(mas[i])
        }

        var ordered = [];
        for (var j = 0; j < filtered.length; j++) {
            ordered[(filtered[j].displayOrder - 1)] = filtered[j];
        }

        $scope.keys = ordered;

        $scope.ok = function () {
            $modalInstance.close($scope.institutionParams);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

        $scope.fieldType = function (key) {
            if (key.mask == true) {
                return "Password";
            } else {
                return "text";
            }
        };

        $scope.fieldPlaceholder = function (key) {
            return key.description;
        };

        $scope.invalidModalForm = function () {
            return $scope.institution.form.$invalid;
        };

    });

