'use strict';
app.controller('RegisterCtrl',
        function RegisterCtrl($scope, $state, $modal, pwdstrength, accountFactory) {

            /**
             * init data and watchers
             */
            $scope.initData = function() {
                $scope.registration = {};
                $scope.newuser = {
                    username: "testUser",
                    password: "QWEqweqwe",
                    repassword: "QWEqweqwe",
                    firstName: "John",
                    lastName: "Doe"
                };
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
            NibbleUtils.initAlerts($scope);

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
             * check that first registration form is invalid
             * @returns {boolean|FormController.$invalid|*|ngModel.NgModelController.$invalid|context.ctrl.$invalid}
             */
            $scope.invalidRegisterForm1 = function() {
                return $scope.registration.form.username.$invalid ||
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
                        $scope.registration.form.email.$invalid ||
                        $scope.registration.form.phone.$invalid;

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
                accountFactory.getInstitutions().success(function(data) {
                    var items = data.items;
                    for (var i=0; i<items.length; i++) {
                        items[i].institution.logoUrl = NibbleUtils.getServicesUrl() + "/rest/logo/" +
                        window.encodeURIComponent(items[i].institution.name);
                    }
                    $scope.banks = items;
                    $scope.registration.condition = "linkAccount";
                })
                .error(function (error) {
                    NibbleUtils.errorCallback($scope, error);
                });

            };

            /**
             * Finish registration process
             */
            $scope.finishRegistration = function() {
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

                accountFactory.registerNibbler(nibbler).success(function(data){
                    console.log(data);
                    $scope.registration.condition = "registrationFinish";
                })
                    .error(function(error) {
                    NibbleUtils.errorCallback($scope, error)
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