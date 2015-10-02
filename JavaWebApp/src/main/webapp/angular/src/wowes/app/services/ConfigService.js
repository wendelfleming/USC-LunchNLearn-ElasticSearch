
angular.module('WoWES.confservices', [])
    .factory('ConfigService', function() {
        var localRestServerURL = "http://localhost:8080/elasticwow/spring/";
        var remoteRestServerURL = "http://192.168.59.103:8080/elasticwow/spring/";
        var useLocal = false;

        return {
            getRestServerURL: function() { return useLocal ? localRestServerURL : remoteRestServerURL; }
        };
    });


