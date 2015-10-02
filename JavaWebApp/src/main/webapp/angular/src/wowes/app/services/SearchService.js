
angular.module('WoWES.services', [])
    .factory('SearchService', ['$http', function($http) {
        var currentPage;
        var searchQuery;
        var doRequest = function(path) {
            return $http({
                method: 'JSONP',
                url: 'http://192.168.59.103:8080/elasticwow/spring/api/search?currentPage=' + currentPage + '&searchQuery=' + searchQuery + '&callback=JSON_CALLBACK'
            });
        }
        return {
            search: function() { return doRequest('events'); },
            setCurrentPage: function(newCurrentPage) { currentPage = newCurrentPage; },
            setSearchQuery: function(newSearchQuery) { searchQuery = newSearchQuery; },
            getCurrentPage: function() { return currentPage; },
            getSearchQuery: function() { return searchQuery; }
        };
    }]);


