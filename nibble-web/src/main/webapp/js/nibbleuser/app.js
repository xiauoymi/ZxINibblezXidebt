'use strict';
var app = angular.module('nibbleuser',
    ['ui.router', 'ui.bootstrap', 'xtForm', 'snap', 'chart.js',
        'angular-svg-round-progress', 'ngTable', 'ngCookies']);
app.config(['$stateProvider', '$urlRouterProvider', '$httpProvider',
    function ($stateProvider, $urlRouterProvider, $httpProvider) {
        $urlRouterProvider.otherwise("");
        $stateProvider
            .state('user', {
                templateUrl: "/templates/user/user.html"
            })
            .state('user.login', {
                url: "",
                templateUrl: "/templates/user/login.html",
                controller: "LoginCtrl",
                data: {
                    requireLogin: false
                }
            })
            .state('user.login.hash', {
                url: "/login",
                templateUrl: "/templates/user/login.html",
                controller: "LoginCtrl",
                data: {
                    requireLogin: false
                }
            })
            .state('user.register', {
                url: "/register",
                templateUrl: "/templates/user/register.html",
                controller: "RegisterCtrl",
                data: {
                    requireLogin: false
                }
            })
            .state('user.activation', {
                url: "/activate",
                templateUrl: "/templates/user/activate.html",
                controller: "ActivateCtrl",
                data: {
                    requireLogin: false
                }
            })
            .state('user.forgot', {
                url: "/forgot",
                templateUrl: "/templates/user/forgot.html",
                controller: "ForgotCtrl",
                data: {
                    requireLogin: false
                }
            })
            .state('dashboard', {
                templateUrl: "/templates/dashboard/dashboard.html",
                controller: "DashboardCtrl"
            })
            .state('dashboard.home', {
                url: "/home",
                templateUrl: "/templates/dashboard/home.html",
                controller: "HomeCtrl",
                data: {
                    requireLogin: true
                }
            })
            .state('error', {
                templateUrl: "/templates/error.html",
                data: {
                    requireLogin: false
                }
            })
    }]);
app.run(function ($rootScope, $location, $cookieStore) {
    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
        var loggedIn = $cookieStore.get("nibbler_");
        if (restrictedPage && !loggedIn) {
            $location.path('/login');
        } else if (loggedIn && $location.path() == "") {
            $location.path('/home');
        }
    });


});




