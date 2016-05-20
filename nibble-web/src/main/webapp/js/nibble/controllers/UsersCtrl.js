'use strict';
app.controller('UsersCtrl',
    function AccountsCtrl($scope, $rootScope, $state, $stateParams, NgTableParams, userFactory) {
		
	
		$scope.users=[		
//		    {firstName:"KOKO",
//	        lastName:"last lolo",
//	        serviceFee:14,
//	        triggerAmount:25,
//	        email:"m@m.com",
//	        referral:"ref",
//	        fundingAccount:"din",
//	        loanAccount:"ddd",
//	       	password:"sdsd",
//	       	isEditing:false},
//	       	{firstName:"KOKO1",
//	        lastName:"last lolo",
//	        serviceFee:14,
//	        triggerAmount:25,
//	        email:"m@m.com",
//	        referral:"ref",
//	        fundingAccount:"din",
//	        loanAccount:"ddd",
//	       	password:"sdsd",
//	       	isEditing:false}
	       	];

		/* init alerts */
        NibbleUtils.initAlerts($scope, $stateParams.message);
	    
	    $scope.cancel =  function (idx) {
	    	$scope.users[idx]=angular.copy($scope.originalUser);
	    	$scope.users[idx].isEditing=false;
		}

		$scope.editUser = function (user) {
			$scope.originalUser=angular.copy(user);
			user.isEditing= true;
	    };
	
	    $scope.saveUser = function (idx) {
	        console.log("Saving user");
	        $scope.reset();
	    };
	    
        $scope.initCtrl = function () {
        	 $scope.resetSearchForm();
        	 $scope.loadUsers();
        };
        
        $scope.resetSearchForm=function(){
    		$scope.selectedUser ={};
    		$scope.originalUser={};
        	$scope.searchForm={};
        };
        $scope.loadUsers=function(){
        	userFactory.users($scope.searchForm)
            .success( function (data) {
                $scope.users = data;
                $scope.initUsersTable();
            })
            .error( function (data, status) {
                NibbleUtils.errorCallback($scope, $state, data, status);
            })
        };
        
        $scope.initUsersTable = function () {
            $scope.tableSettings = new NgTableParams(
                {
                    count: 25
                },
                {
                    counts: [],
                    paginationMaxBlocks: 5,
                    paginationMinBlocks: 2,
                    dataset: $scope.users

                }
            )
        };
        
    });