'use strict';
var app = angular.module('nibble', ['ui.router', 'ui.bootstrap', 'xtForm']);
app.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("");
    $stateProvider
        .state('user', {
            templateUrl: "/templates/user/user.html"
        })
        .state('user.login', {
            url: "",
            templateUrl: "/templates/user/login.html",
            controller: "LoginCtrl"
        })
        .state('user.login.hash', {
            url: "/",
            templateUrl: "/templates/user/login.html",
            controller: "LoginCtrl"
        })
        .state('user.register', {
            url: "/register",
            templateUrl: "/templates/user/register.html",
            controller: "RegisterCtrl"
        })
        .state('user.activation', {
            url: "/activate",
            templateUrl: "/templates/user/activate.html",
            controller: "ActivateCtrl"
        })
        .state('error', {
            templateUrl: "/templates/error.html"
        })
}]);



