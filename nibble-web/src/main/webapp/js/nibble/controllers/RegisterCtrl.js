'use strict';
app.controller('RegisterCtrl',
        function RegisterCtrl($scope, $state,$timeout, $interval, $filter,$stateParams, $modal, pwdstrength, accountFactory, userFactory,dwollaFactory) {

            /**
             * init data and watchers
             */
            $scope.initData = function() {
            	$scope.isEditing=false;
                $scope.registration = {};
                $scope.linkaccount={};
                $scope.newuser = {
                    email: "",
                    password: "",
                    repassword: "",
                    firstName: "",
                    lastName: ""
                };
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
                $scope.banks = [];
                $scope.selected = undefined;
                /* add watcher for custom password validation */
                $scope.$watch('newuser.password', function(pass) {
                    if ($scope.registration.form && $scope.registration.form.password) {
                        $scope.registration.passwordStrenth = pwdstrength.getStrength(pass);
                        if (pwdstrength.getStrength(pass) < 45) {
                            $scope.registration.form.password.$setValidity('strength', false);
                        } else {
                            $scope.registration.form.password.$setValidity('strength', true);
                        }
                    }
                });
                /* add watcher for retype password validation */
                $scope.$watch('newuser.repassword', function(pass) {
                    if ($scope.registration.form && $scope.registration.form.repassword) {
                        if ($scope.newuser.repassword != null && $scope.newuser.repassword != '') {
                            $scope.registration.form.repassword.$setValidity('equalto',
                                $scope.newuser.password == $scope.newuser.repassword);
                        }
                    }
                });
            
                $scope.$watch('user.password', function(pass) {
                    if ($scope.registration.form && $scope.registration.form.password) {
                        $scope.registration.passwordStrenth = pwdstrength.getStrength(pass);
                        if (pwdstrength.getStrength(pass) < 45) {
                            $scope.registration.form.password.$setValidity('strength', false);
                        } else {
                            $scope.registration.form.password.$setValidity('strength', true);
                        }
                    }
                });
                /* add watcher for retype password validation */
                $scope.$watch('user.repassword', function(pass) {
                    if ($scope.registration.form && $scope.registration.form.repassword) {
                        if ($scope.user.repassword != null && $scope.user.repassword != '') {
                            $scope.registration.form.repassword.$setValidity('equalto',
                                $scope.user.password == $scope.user.repassword);
                        }
                    }
                });
            if($stateParams.activate){
           		$scope.activate=$stateParams.activate;
           		 $scope.initLinkAccount("linkAccount");
           		//$scope.initLinkAccount('loanAccount');
           	}
           	if($stateParams.user)
           	$scope.user=$stateParams.user;
            };
            /* init alerts */
            NibbleUtils.initAlerts($scope, $stateParams.message);


            /**
             * show second registration form
             */
            $scope.registerForm2 = function() {
                $scope.registration.condition = "registrationForm2";
            };

            /**
             * back to first form
             */
            $scope.backToForm1 = function() {
                $scope.registration.condition = "registrationForm1";
            };

            /**
             * back to link account
             */
            $scope.backToLinkAccount = function() {
                $scope.initLinkAccount("linkAccount");
            };

            /**
             * check that first registration form is invalid
             * @returns {boolean|FormController.$invalid|*|ngModel.NgModelController.$invalid|context.ctrl.$invalid}
             */
            $scope.invalidRegisterForm1 = function() {
                return $scope.registration.form.email.$invalid ||
                        $scope.registration.form.password.$invalid ||
                        $scope.registration.form.repassword.$invalid ||
                        $scope.registration.form.firstname.$invalid ||
                        $scope.registration.form.lastname.$invalid;
            };

            /**
             * check that second registration form is invalid
             * @returns {boolean|FormController.$invalid|*|ngModel.NgModelController.$invalid|context.ctrl.$invalid}
             */
            $scope.invalidRegisterForm2 = function() {
                if($scope.registration.condition="registrationForm2")
                return $scope.registration.form.address1.$invalid ||
                        $scope.registration.form.address2.$invalid ||
                        $scope.registration.form.city.$invalid ||
                        $scope.registration.form.regstate.$invalid ||
                        $scope.registration.form.zipcode.$invalid ||
                        $scope.registration.form.phone.$invalid;

            };

            /**
             * check that mfa text registration form is invalid
             * @returns {boolean|FormController.$invalid|*|ngModel.NgModelController.$invalid|context.ctrl.$invalid}
             */
            $scope.invalidMfaText = function() {
                return $scope.registration.form.mfaTextAnswer.$invalid;

            };

            /**
             * check that mfa image registration form is invalid
             * @returns {boolean|FormController.$invalid|*|ngModel.NgModelController.$invalid|context.ctrl.$invalid}
             */
            $scope.invalidMfaImage = function() {
                return $scope.registration.form.mfaImageAnswer.$invalid;

            };

            /**
             * check that mfa image registration form is invalid
             * @returns {boolean|FormController.$invalid|*|ngModel.NgModelController.$invalid|context.ctrl.$invalid}
             */
            $scope.invalidMfaTextChoice = function() {
                return $scope.registration.form.radioAnswer.$invalid;

            };

            /**
             * check that mfa image registration form is invalid
             * @returns {boolean|FormController.$invalid|*|ngModel.NgModelController.$invalid|context.ctrl.$invalid}
             */
            $scope.invalidMfaImageChoice = function() {
                return $scope.registration.form.radioImageAnswer.$invalid;

            };


            /**
             * forward to login page
             */
            $scope.goToLogin = function () {
                $state.go('user.login');
            };

            /**
             * Show form for link account
             */
            $scope.initLinkAccount = function(step) {
            	$scope.registration.condition = step;
            };

            $scope.fundingIAVLoading=false;
            $scope.fundingIAVStep=0;
            $scope.finicityBank={};  
            $scope.showNext2Loan=false;
            $scope.loadIAV=function(fn){
                dwollaFactory.newToken($scope.user).then(function(data){
                    $scope.user.iavToken=data.data.iavToken;
                    dwollaFactory.startIavFunding($scope.user.iavToken,$scope[fn],$scope);
                    $scope.promiseOfWatching=$interval($scope.startWatchingIAV, 100);
                });
            };
            
            $scope.startWatchingIAV=function(){
            if($scope.fundingIAVStep==0){
                if(angular.element('iframe').contents().find('#BankName').length>0){                    
                            angular.element('iframe').contents().find( 'button[type=submit]' )[0].addEventListener('click', function(){
                            let institution={};
                            institution.name=angular.element('iframe').contents().find('#BankName')[0].value;
                            //bank -> loginForm ->[loginField {value,description}]
                            let loginField=[];
                            angular.element('iframe').contents().find('label').each(function(k,e) {
                                loginField.push({description:e.innerHTML,value:angular.element("iframe").contents().find("#"+e.htmlFor).val()});
                            });
                            $scope.finicityBank.loginForm={loginField:loginField};
                            $scope.finicityBank.institution=institution;
                            $scope.fundingIAVLoading=false;
                        });                 
                    $scope.fundingIAVStep=1;                        
                }
            }
            if($scope.fundingIAVStep==1){
                    if(angular.element('iframe').contents().find('#QUESTION_1').length>0){
                      angular.element('iframe').contents().find( 'button[type=submit]' )[0].addEventListener('click', function(){
                            let questionRequests=[];
                             angular.element('iframe').contents().find('label').each(function(k,e) {
                                questionRequests.push({text:e.innerHTML,answer:angular.element("iframe").contents().find("#"+e.htmlFor).val()});
                            });
                            $scope.finicityBank.questionRequests=questionRequests;
                            //$scope.addRoundupAccount($scope.finicityBank,$scope.user.email);
                            $scope.fundingIAVLoading=false;     
                            $scope.fundingIAVStep=3;
                        });      
                          
                    }        
            }
            if($scope.fundingIAVStep==3){
            	if(angular.element("iframe").contents().find("input:checked").length>0){
            		angular.element('iframe').contents().find( 'button[type=submit]' )[0].addEventListener('click', function(){
            			if(angular.element("iframe").contents().find("input[name=AccountNumberInput]").val()){
                    		$scope.finicityBank.accountNumber =angular.element("iframe").contents().find("input[name=AccountNumberInput]").val();
                    	}else{
                    		$scope.finicityBank.accountNumber =angular.element("iframe").contents().find( "input:checked" ).val();
                    	}  
                    	$interval.cancel($scope.promiseOfWatching);
                    });      
                    $scope.fundingIAVStep=0;
            	}
            }
            };

            $scope.isFundingIAV=function(){return true;};
            
            $scope.addRoundupAccount=function(bank,email,fundingSource){
                accountFactory.updateRoundupAccount({roundupAccountBank:bank,email:email,fundingSourceToken:fundingSource,accountNumber:bank.accountNumber}).then(function(data){
                   $scope.roundupAccount=data; 
                   $scope.finicityBank={}; 
                    $modal.open({
                        animation: true,
                        templateUrl: 'accountLinked.html',
                        controller: 'ConfirmModalInstanceCtrl',
                        backdrop: 'static'
                    });
                   $scope.showNext2Loan=true;
                },function (data, status) {
                        NibbleUtils.errorCallback($scope, $state, data, status);
                });
            };

            $scope.addLoanAccount=function(bank,email,fundingSource){
                accountFactory.updateLoanAccount({loanAccountBank:bank,email:email,loanToken:fundingSource,accountNumber:bank.accountNumber}).then(function(data){
                     $scope.loanAccount=data; 
                   $scope.registration.condition ="registrationConfirm";
                },function (data, status) {
                        NibbleUtils.errorCallback($scope, $state, data, status);
                });
            };

            $scope.addLoanAccountByReferral=function(){
                accountFactory.updateLoanAccountByReferral({email:$scope.user.email,referral:$scope.linkaccount.referral}).then(function(data){
                     $scope.loanAccount=data; 
                   $scope.registration.condition ="registrationConfirm";
                },function (data, status) {
                        NibbleUtils.errorCallback($scope, $state, data, status);
                });
            };

            $scope.searchInstitutions = function() {
                accountFactory.searchInstitutions($scope.linkaccount.search).then(function(data) {
                    let items = data.data;
                    let length=items.length<10?items.length:10;
                    for (let i=0; i<length; i++) {
                        if(items[i].institution.logoCode){
                            if(items[i].institution.logoCode.match('genericbank')){
                                items[i].institution.defaultLogo=true;
                            }
                            items[i].institution.logoUrl = NibbleUtils.getServicesUrl() + "/rest/logo/" +
                            window.encodeURIComponent(items[i].institution.logoCode);   
                        }
                    }
                    $scope.banks = items;
                },function (data, status) {
                        NibbleUtils.errorCallback($scope, $state, data, status);
                });               

            };

            $scope.autoCompletSearch=function(){
                 if($scope.linkaccount && $scope.linkaccount.search && $scope.linkaccount.search.length>0){
                     $scope.searchInstitutions();
                }
            }

            /**
             * Show form for loan account
             */
            $scope.initLoanAccount = function() {
            	$scope.loanaccount = {};
            	$scope.loanaccount.search ="";
            	
                accountFactory.getInstitutions().then(function(data) {
                    var items = data.data;
                    for (var i=0; i<items.length; i++) {
                        items[i].institution.logoUrl = NibbleUtils.getServicesUrl() + "/rest/logo/" +
                        window.encodeURIComponent(items[i].institution.logoCode);
                    }
                    $scope.banks = items;
                    $scope.registration.condition = "loanAccount";
                },function (data, status) {
                        NibbleUtils.errorCallback($scope, $state, data, status);
                });

            };

            /**
             * Confirm registration
             */
            $scope.confirmRegistration = function(user) {
                var nibbler = $scope.createNibblerObject(user);
                userFactory.registerNibbler(nibbler).then(function(data){
                    $scope.isEditing=false;
                    if (NibbleUtils.isDebug()) {
                        console.log("Register Nibbler response data : ", data);
                    }
                    $scope.parseRegisterResponse(data);

                    $modal.open({
                        animation: true,
                        templateUrl: 'myModalContent.html',
                        controller: 'ConfirmModalInstanceCtrl',
                        backdrop: 'static'
                    });

                }
                ,function(data, status) {
                    NibbleUtils.errorCallback($scope, $state, data, status);
                });
            };

            $scope.updateRegister = function(user) {
                var nibbler = $scope.createNibblerObject(user);
                userFactory.updateRegister(nibbler).then(function(data){
                    $scope.isEditing=false;
                    if (NibbleUtils.isDebug()) {
                        console.log("Register Nibbler response data : ", data);
                    }
                }
                ,function(data, status) {
                    NibbleUtils.errorCallback($scope, $state, data, status);
                });
            };

            $scope.editUser=function(user){
                $scope.oldUser=angular.copy(user);
                $scope.isEditing=true;
            };

            $scope.cancelEditUser=function(){
                $scope.user=angular.copy($scope.oldUser);
                $scope.isEditing=false;
            };


            /**
             * Finish registration process
             */
            $scope.finishRegistration = function() {
                var nibbler = $scope.createNibblerObject();
                userFactory.registerNibbler(nibbler).then(function(data){
                    if (NibbleUtils.isDebug()) {
                        console.log("Register Nibbler response data : ", data);
                    }
                    $scope.parseRegisterResponse(data);
                },function(data, status) {
                        NibbleUtils.errorCallback($scope, $state, data, status);
                });
            };
            
            $scope.createNibblerObject = function(user) {
                if(!user){
                    user=$scope.newuser;
                }
                var nibbler = {};
                nibbler.username = user.username;
                nibbler.firstName = user.firstName;
                nibbler.lastName = user.lastName;
                nibbler.password = user.password;
                nibbler.address1 = user.address1;
                nibbler.address2 = user.address2;
                nibbler.city = user.city;
                nibbler.state = user.state;
                nibbler.zip = user.zip;
                nibbler.email = user.email;
                nibbler.phone = user.phone;
                nibbler.url = NibbleUtils.getBaseUrl();
                nibbler.ssn=user.ssn;
                nibbler.dateOfBirth=$filter('date')(user.dateOfBirth, 'yyyy-MM-dd');
                if ($scope.selected != undefined) {
                    nibbler.bank = {
                        institution : {},
                        loginForm : {}
                    };
                    nibbler.bank.institution = $scope.selected.institution;
                    nibbler.bank.loginForm.loginField = $scope.selected.loginForm.loginField;
                }
                return nibbler;
            };

            $scope.showAccountNumber= function(x){
                if(x.length>3)
                return x.substring(x.length-4,x.length-1);
            };
            /**
             * parse response and forward
             */
            $scope.parseRegisterResponse = function(data) {
                $scope.registration.mfa = {};
                if (data.mfaType == "NON_MFA") {
                    $scope.registration.condition = "registrationFinish";
                } else if (data.mfaType == "TEXT") {
                    $scope.registration.mfa.mfaQuestion = data.mfaChallenges.question[0].text;
                    $scope.registration.mfa.mfaAnswer = "";
                    $scope.registration.condition = "mfaText";
                } else if (data.mfaType == "IMAGE") {
                    $scope.registration.mfa.mfaQuestion = data.mfaChallenges.question[0].text;
                    $scope.registration.mfa.mfaImage = data.mfaChallenges.question[0].image;
                    $scope.registration.condition = "mfaImage";
                } else if (data.mfaType == "TEXT_CHOOSE") {
                    $scope.registration.mfa.mfaQuestion = data.mfaChallenges.question[0].text;
                    $scope.registration.mfa.choose = data.mfaChallenges.question[0].choice;
                    $scope.registration.condition = "mfaTextChoice";
                } else if (data.mfaType == "IMAGE_CHOOSE") {
                    $scope.registration.mfa.mfaQuestion = data.mfaChallenges.question[0].text;
                    $scope.registration.mfa.imageChoose = data.mfaChallenges.question[0].imageChoice;
                    $scope.registration.condition = "mfaImageChoice";
                }
            };

            /**
             * Finish registration process
             */
            $scope.finishRegistrationWithMfa = function() {
                var nibbler = $scope.createNibblerObject();
                nibbler.mfaQuestion = $scope.registration.mfa.mfaQuestion;
                nibbler.mfaAnswer = $scope.registration.mfa.mfaAnswer;

                userFactory.submitMfa(nibbler).then(function(data){
                    console.log(data);
                    $scope.parseRegisterResponse(data);
                },function(data, status) {
                        NibbleUtils.errorCallback($scope, $state, data, status);
                    });
            };



            /**
             * Open modal form with institution credentials form
             * @param bankIndex
             */
            $scope.clickModal = function(bankIndex,submitFunction) {
                var bank = $scope.banks[bankIndex];
                if (bank) {
                    if (bank.loginForm.loginField != null && bank.loginForm.loginField.length > 0) {
                        var modalInstance = $modal.open({
                            animation: true,
                            templateUrl: 'registermodal.html',
                            controller: 'RegisterModalBankCtrl',
                            size: undefined,
                            backdrop: 'static',
                            resolve: {
                                clickedBank: function () {
                                    return bank;
                                },
                                user:function(){
                                    return $scope.user;
                                },
                                submitFunction:function(){
                                    return submitFunction;
                                }
                            }
                        });

                        modalInstance.result.then(function (selectedItem) {
                            $scope.selected = selectedItem;
                        }, function () {
                            $scope.selected = undefined;
                        });
                    } else {
                        $scope.msg_alerts= [{type:'danger', msg:"Sorry, we don't have enough information about " + bank.institution.name}];
                    }
                } else {
                    $scope.msg_alerts= [{type:'danger', msg:"Ops! contact with administrator (variable 'bank' is undefined)"}];
                }
            
            };

            /**
             * Check password is weak
             * @returns {boolean}
             */
            $scope.isPasswordWeak = function() {
                return $scope.registration.passwordStrenth < 45;
            };

            /**
             * Check password is ok
             * @returns {boolean}
             */
            $scope.isPasswordOk = function() {
                return $scope.registration.passwordStrenth >=45 && $scope.registration.passwordStrenth < 70;
            };

            /**
             * Check password is strong
             * @returns {boolean}
             */
            $scope.isPasswordStrong = function() {
                return $scope.registration.passwordStrenth >=70;
            };

            $scope.isBankNotSelected = function() {
                return $scope.selected == undefined;
            }
            

            /* init data */
            $scope.initData();
        });

app.controller('ConfirmModalInstanceCtrl', function ($scope, $modalInstance) {
    $scope.ok = function () {
        $modalInstance.close();
    };
});