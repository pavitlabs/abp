
angular.module('abp').controller('NewBrokerController', function ($scope, $location, locationParser, BrokerResource , GeographicalLocatorResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.broker = $scope.broker || {};
    
    $scope.genderList = [
        "MALE",
        "FEMALE"
    ];
    
    $scope.geographicalAddressesList = GeographicalLocatorResource.queryAll(function(items){
        $scope.geographicalAddressesSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("geographicalAddressesSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.broker.geographicalAddresses = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.broker.geographicalAddresses.push(collectionItem);
            });
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Brokers/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        BrokerResource.save($scope.broker, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Brokers");
    };
});