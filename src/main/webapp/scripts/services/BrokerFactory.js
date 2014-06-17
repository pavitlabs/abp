angular.module('abp').factory('BrokerResource', function($resource){
    var resource = $resource('rest/brokers/:BrokerId',{BrokerId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});