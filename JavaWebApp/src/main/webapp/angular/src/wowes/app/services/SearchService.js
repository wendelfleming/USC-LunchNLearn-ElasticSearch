
angular.module('WoWES.services', ['WoWES.confservices'])
    .factory('SearchService', ['$http', 'ConfigService', function($http, ConfigService) {
        var currentPage;
        var searchQuery;
        var doRequest = function(path) {
            return $http({
                method: 'JSONP',
                url: ConfigService.getRestServerURL() + 'api/search?currentPage=' + currentPage + '&searchQuery=' + searchQuery + '&callback=JSON_CALLBACK'
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


