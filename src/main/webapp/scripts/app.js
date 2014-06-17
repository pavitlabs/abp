'use strict';

angular.module('abp',['ngRoute','ngResource'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/',{templateUrl:'views/landing.html',controller:'LandingPageController'})
      .when('/Brokers',{templateUrl:'views/Broker/search.html',controller:'SearchBrokerController'})
      .when('/Brokers/new',{templateUrl:'views/Broker/detail.html',controller:'NewBrokerController'})
      .when('/Brokers/edit/:BrokerId',{templateUrl:'views/Broker/detail.html',controller:'EditBrokerController'})
      .otherwise({
        redirectTo: '/'
      });
  }])
  .controller('LandingPageController', function LandingPageController() {
  })
  .controller('NavController', function NavController($scope, $location) {
    $scope.matchesRoute = function(route) {
        var path = $location.path();
        return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
    };
  });
