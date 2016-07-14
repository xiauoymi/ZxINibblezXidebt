'use strict';
var app = angular.module('nibbleuser',
    ['ui.router','ui.bootstrap', 'xtForm', 'snap', 'chart.js',
        'angular-svg-round-progress', 'ngTable', 'ngCookies']);

app.config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise("");
        $stateProvider
            .state('dashboard', {
                templateUrl: "/view/dashboard/dashboard.html",
                controller: "DashboardCtrl"
            })
            .state('dashboard.home', {
                url: "",
                templateUrl: "/view/dashboard/home.html",
                controller: "HomeCtrl"
            })
            .state('dashboard.home.hash', {
                url: "/",
                templateUrl: "/view/dashboard/home.html",
                controller: "HomeCtrl"
            })
            .state('dashboard.accounts', {
                url: "/accounts",
                templateUrl: "/view/dashboard/accounts.html",
                controller: "AccountsCtrl"
            }).state('dashboard.users', {
                url: "/users",
                templateUrl: "/view/dashboard/users.html",
                controller: "UsersCtrl"
            })

            /***********************************************/
            /* user login, registration, activation states */
            /***********************************************/
            .state('user', {
                templateUrl: "/view/user/user.html"
            })
            .state('user.login', {
                url: "/login?message",
                templateUrl: "/view/user/login.html",
                controller: "LoginCtrl"
            })
            .state('user.register', {
                url: "/register?message",
                templateUrl: "/view/user/register.html",
                controller: "RegisterCtrl",
                data: {
                    requireLogin: false
                },
                params: {user:null,activate:false }
            })
            .state('user.activation', {
                url: "/activate",
                templateUrl: "/view/user/activate.html",
                controller: "ActivateCtrl",
                data: {
                    requireLogin: false
                }
            })
            .state('user.forgot', {
                url: "/forgot",
                templateUrl: "/view/user/forgot.html",
                controller: "ForgotCtrl",
                data: {
                    requireLogin: false
                }
            })
            .state('user.reset', {
                url: "/reset",
                templateUrl: "/view/user/reset.html",
                controller: "ResetCtrl",
                data: {
                    requireLogin: false
                }
            })

        ;

    }]);
