'use strict';
app.controller('RegisterCtrl',
        function RegisterCtrl($scope, $state, $stateParams, $modal, pwdstrength, accountFactory, userFactory) {

            /**
             * init data and watchers
             */
            $scope.initData = function() {
                $scope.registration = {};
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
            };
            /* init alerts */
            NibbleUtils.initAlerts($scope, $stateParams.message);

            /* init data */
            $scope.initData();

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
                $scope.registration.condition = "linkAccount";
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
            $scope.linkAccount = function() {
            	$scope.linkaccount = {};
            	$scope.linkaccount.search ="";
            	
                accountFactory.getInstitutions().success(function(data) {
                    var items = data;
                    for (var i=0; i<items.length; i++) {
                        items[i].institution.logoUrl = NibbleUtils.getServicesUrl() + "/rest/logo/" +
                        window.encodeURIComponent(items[i].institution.logoCode);
                    }
                    $scope.banks = items;
                    $scope.registration.condition = "linkAccount";
                })
                .error(function (data, status) {
                        NibbleUtils.errorCallback($scope, $state, data, status);
                });

            };
            
            /**
             * Show form for link account
             */
            $scope.loanAccount = function() {
            	$scope.loanaccount = {};
            	$scope.loanaccount.search ="";
            	
                accountFactory.getInstitutions().success(function(data) {
                    var items = data;
                    for (var i=0; i<items.length; i++) {
                        items[i].institution.logoUrl = NibbleUtils.getServicesUrl() + "/rest/logo/" +
                        window.encodeURIComponent(items[i].institution.logoCode);
                    }
                    $scope.banks = items;
                    $scope.registration.condition = "loanAccount";
                })
                .error(function (data, status) {
                        NibbleUtils.errorCallback($scope, $state, data, status);
                });

            };

            /**
             * Finish registration process
             */
            $scope.finishRegistration = function() {
                var nibbler = $scope.createNibblerObject();
                userFactory.registerNibbler(nibbler).success(function(data){
                    if (NibbleUtils.isDebug()) {
                        console.log("Register Nibbler response data : ", data);
                    }
                    $scope.parseRegisterResponse(data);
                })
                    .error(function(data, status) {
                        NibbleUtils.errorCallback($scope, $state, data, status);
                });
            };
            
            /**
             * Finish registration process
             */
            $scope.confirmRegistration = function() {
                var nibbler = $scope.createNibblerObject();
                $scope.registration.condition = "registrationConfirm";
            };

            $scope.createNibblerObject = function() {
                var nibbler = {};
                nibbler.username = $scope.newuser.username;
                nibbler.firstName = $scope.newuser.firstName;
                nibbler.lastName = $scope.newuser.lastName;
                nibbler.password = $scope.newuser.password;
                nibbler.address1 = $scope.newuser.address1;
                nibbler.address2 = $scope.newuser.address2;
                nibbler.city = $scope.newuser.city;
                nibbler.state = $scope.newuser.state;
                nibbler.zip = $scope.newuser.zip;
                nibbler.email = $scope.newuser.email;
                nibbler.phone = $scope.newuser.phone;
                nibbler.url = NibbleUtils.getBaseUrl();
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

                userFactory.submitMfa(nibbler).success(function(data){
                    console.log(data);
                    $scope.parseRegisterResponse(data);
                })
                    .error(function(data, status) {
                        NibbleUtils.errorCallback($scope, $state, data, status);
                    });
            };



            /**
             * Open modal form with institution credentials form
             * @param bankIndex
             */
            $scope.clickModal = function(bankIndex) {
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
        });