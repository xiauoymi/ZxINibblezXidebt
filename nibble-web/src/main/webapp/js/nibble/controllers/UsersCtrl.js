'use strict';
app.controller('UsersCtrl',
    function AccountsCtrl($scope, $rootScope, $state, $stateParams, NgTableParams,$uibModal, userFactory) {
	

	
    $scope.states = [
						'Alabama',
						'Alaska',
						'Arizona',
						'Arkansas',
						'California',
						'Colorado',
						'Connecticut',
						'Delaware',
						'District of Columbia',
						'Florida',
						'Georgia',
						'Hawaii',
						'Idaho',
						'Illinois',
						'Indiana',
						'Iowa',
						'Kansas',
						'Kentucky',
						'Louisiana',
						'Maine',
						'Maryland',
						'Massachusetts',
						'Michigan',
						'Minnesota',
						'Mississippi',
						'Missouri',
						'Montana',
						'Nebraska',
						'Nevada',
						'New Hampshire',
						'New Jersey',
						'New Mexico',
						'New York',
						'North Carolina',
						'North Dakota',
						'Ohio',
						'Oklahoma',
						'Oregon',
						'Pennsylvania',
						'Rhode Island',
						'South Carolina',
						'South Dakota',
						'Tennessee',
						'Texas',
						'Utah',
						'Vermont',
						'Virginia',
						'Washington',
						'West Virginia',
						'Wisconsin',
						'Wyoming'];
	
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
	    
	    $scope.cancel =  function (user) {
	    	angular.copy($scope.originalUser[user.internalUserId],user);
		}

		$scope.editUser = function (user) {
			$scope.originalUser[user.internalUserId]=angular.copy(user);
			user.isEditing= true;
	    };
	
	    $scope.saveUser = function (user) {
	    	userFactory.update(user)
            .success( function (data) {
            	$scope.loadUsers();
            })
            .error( function (data, status) {
                NibbleUtils.pushErrorCallback($scope,'saveUserErrors', $state, data, status);
                $scope.popupError();
            })
	    };
	    
	    $scope.popupError=function(){
	    	 $uibModal.open({
                 animation: true,
                 templateUrl: 'myModalContent.html',
                 controller: 'ModalInstanceCtrl',
                 
                 backdrop: 'static',
                 resolve: {
       	    	  saveUserErrors: function () {
       	          return $scope.saveUserErrors;
       	        }
       	      }
             });
	    };
	    $scope.active = function (user) {
	    	userFactory.active(user)
            .success( function (data) {
            	$scope.loadUsers();
            })
            .error( function (data, status, headers, config, statusText ) {
                NibbleUtils.pushErrorCallback($scope,'saveUserErrors', $state, data, status);
                $scope.popupError();
            })
	    };
	    
	    $scope.suspend = function (user) {
	    	userFactory.suspend(user)
            .success( function (data) {
            	$scope.loadUsers();
            })
            .error( function (data, status) {
                NibbleUtils.pushErrorCallback($scope,'saveUserErrors', $state, data, status);
                $scope.popupError();
            })
	    };
	    
	    
	    $scope.loginAs = function (user) {
	    	userFactory.loginAs(user)
            .success( function (data) {
            	$state.go('dashboard.home');
            })
            .error( function (data, status) {
                NibbleUtils.pushErrorCallback($scope,'saveUserErrors', $state, data, status);
                $scope.popupError();
            })
	    };

	    
	   	$scope.sendWeeklyEmail = function (user) {
	    	userFactory.sendWeeklyEmail(user)
            .success( function (data) {
                   $uibModal.open({
                        animation: true,
                        templateUrl: 'messageModalContent.html',
                        controller: 'MessageModalInstanceCtrl',
                        backdrop: 'static',
                        resolve: {
                        	title:function(){
                        		return "Information";
                        	},
                        	message:function(){
                        		return "Weekly update email has been sent successfully";
                        	}
                        }
                    });            	
            })
            .error( function (data, status) {
                NibbleUtils.pushErrorCallback($scope,'saveUserErrors', $state, data, status);
                $scope.popupError();
            });
	    };

	    $scope.refund=function(user){
	    	userFactory.refund(user)
            .success( function (data) {
                $uibModal.open({
                    animation: true,
                    templateUrl: 'messageModalContent.html',
                    controller: 'MessageModalInstanceCtrl',
                    backdrop: 'static',
                    resolve: {
                    	title:function(){
                    		return "Information";
                    	},
                    	message:function(){
                    		return "Refund has been sent! total "+data.feeAmount;
                    	}
                    }
                });         	
            })
            .error( function (data, status) {
                NibbleUtils.pushErrorCallback($scope,'Refund', $state, data, status);
                $scope.popupError();
            });          
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
                NibbleUtils.pushErrorCallback($scope,'searchFormErrors',$state, data, status);
                //$scope.popupError();
            });
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


//Please note that $uibModalInstance represents a modal window (instance) dependency.
//It is not the same as the $uibModal service used above.

app.controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, saveUserErrors) {

$scope.saveUserErrors = saveUserErrors;


$scope.ok = function () {
	$scope.msg_alerts=[];
	$uibModalInstance.close();
};

$scope.cancel = function () {
	$scope.msg_alerts=[];
	$uibModalInstance.dismiss('cancel');
};
});



app.controller('MessageModalInstanceCtrl', function ($scope, $uibModalInstance,title,message) {
	$scope.message=message;
	$scope.title=title;
    $scope.ok = function () {
        $uibModalInstance.close();
    };
});