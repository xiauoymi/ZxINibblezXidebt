'use strict';
app.controller('UsersCtrl',
    function AccountsCtrl($scope, $rootScope, $state, $stateParams, NgTableParams, accountFactory) {
		
	 	$scope.getTemplate = function (user) {
	        if (user.firstName === $scope.selectedUser.firstName) return 'edit';
	        else return 'display';
	    };
	
	
		$scope.users=[		
		    {firstName:"KOKO",
	        lastName:"last lolo",
	        serviceFee:14,
	        triggerAmount:25,
	        email:"m@m.com",
	        referral:"ref",
	        fundingAccount:"din",
	        loanAccount:"ddd",
	       	password:"sdsd"},
	       	{firstName:"KOKO1",
	        lastName:"last lolo",
	        serviceFee:14,
	        triggerAmount:25,
	        email:"m@m.com",
	        referral:"ref",
	        fundingAccount:"din",
	        loanAccount:"ddd",
	       	password:"sdsd"},
	       	{firstName:"KOKO2",
	        lastName:"last lolo",
	        serviceFee:14,
	        triggerAmount:25,
	        email:"m@m.com",
	        referral:"ref",
	        fundingAccount:"din",
	        loanAccount:"ddd",
	       	password:"sdsd"},
	       	{firstName:"KOKO3",
	        lastName:"last lolo",
	        serviceFee:14,
	        triggerAmount:25,
	        email:"m@m.com",
	        referral:"ref",
	        fundingAccount:"din",
	        loanAccount:"ddd",
	       	password:"sdsd"}];
			
		$scope.selectedUser ={};
			
		$scope.editUser = function (user) {
	        $scope.selectedUser = angular.copy(user);
	    };
	
	    $scope.saveContact = function (idx) {
	        console.log("Saving user");
//	        $scope.model.contacts[idx] = angular.copy($scope.model.selected);
	        $scope.reset();
	    };
	    
	    $scope.reset = function () {
	        $scope.selectedUser = {};
	    };
	    
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