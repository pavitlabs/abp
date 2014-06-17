

angular.module('abp').controller('EditBrokerController', function($scope, $routeParams, $location, BrokerResource , GeographicalLocatorResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.broker = new BrokerResource(self.original);
            GeographicalLocatorResource.queryAll(function(items) {
                $scope.geographicalAddressesSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.broker.geographicalAddresses){
                        $.each($scope.broker.geographicalAddresses, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.geographicalAddressesSelection.push(labelObject);
                                $scope.broker.geographicalAddresses.push(wrappedObject);
                            }
                        });
                        self.original.geographicalAddresses = $scope.broker.geographicalAddresses;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Brokers");
        };
        BrokerResource.get({BrokerId:$routeParams.BrokerId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.broker);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.broker.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Brokers");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Brokers");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.broker.$remove(successCallback, errorCallback);
    };
    
    $scope.genderList = [
        "MALE",  
        "FEMALE"  
    ];
    $scope.geographicalAddressesSelection = $scope.geographicalAddressesSelection || [];
    $scope.$watch("geographicalAddressesSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.broker) {
            $scope.broker.geographicalAddresses = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.broker.geographicalAddresses.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});