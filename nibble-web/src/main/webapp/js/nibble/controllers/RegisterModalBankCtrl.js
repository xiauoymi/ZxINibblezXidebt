'use strict';
app.controller('RegisterModalBankCtrl',
    function RegisterModalBankCtrl($scope, $modalInstance, clickedBank,user,submitFunction) {
        $scope.bank = clickedBank;
        $scope.institutionParams = [];
        $scope.user=user;
        $scope.submitFunction=submitFunction;
        var mas = clickedBank.loginForm.loginField;
        var filtered = [];

        for (var i = 0; i < mas.length; i++) {
            if (mas[i].displayFlag === true) {
                filtered.push(mas[i])
            }
        }

        var ordered = [];
        var sortFlag = false;

        for (var j = 0; j < filtered.length; j++) {
            if (filtered[j].displayOrder != null) {
                ordered[(filtered[j].displayOrder - 1)] = filtered[j];
            } else {
                sortFlag = true;
            }
        }
        if (sortFlag) {
            ordered = filtered;
            for (var k = 0; k < ordered.length; k++) {
                ordered[k].displayOrder = k;
            }
        }

        $scope.keys = ordered;

        $scope.ok = function () {
             $scope.submitFunction($scope.bank,$scope.user.email);
             $modalInstance.close();
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