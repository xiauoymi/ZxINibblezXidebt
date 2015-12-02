'use strict';
app.controller('AccountsCtrl',
    function AccountsCtrl($scope, $rootScope, $state, $stateParams, NgTableParams, accountFactory) {

        $scope.initCtrl = function () {
            accountFactory.getRoundupAccounts()
                .success( function (data) {
                    $scope.accounts = data;
                    $scope.initAccountsTable();
                })
                .error( function (data, status) {
                    NibbleUtils.errorCallback($scope, $state, data, status);
                })
        };

        $scope.initAccountsTable = function () {
            $scope.tableSettings = new NgTableParams(
                {
                    count: 15
                },
                {
                    counts: [],
                    paginationMaxBlocks: 5,
                    paginationMinBlocks: 2,
                    dataset: $scope.accounts

                }
            )
        };

        $scope.roundupAccount = function(acc) {
            accountFactory.updateRoundupAccount(acc)
                .success( function (data) {
                    //doing well
                })
                .error( function (data, status) {
                    NibbleUtils.errorCallback($scope, $state, data, status);
                });
            console.log(acc);
        }
    });