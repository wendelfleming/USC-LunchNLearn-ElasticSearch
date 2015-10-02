//var SearchService = angular.module('SearchService', [])
//    .service('SearchSvc', function () {
//
//        this.search = function (currentPage, queryString) {
//            return "QUERY: " + queryString + " & CURRENTPAGE: " + currentPage;
//        };
//
//    });

angular.module('WoWES.services', [])
    .factory('SearchService', ['$http', function($http) {
        var currentPage;
        var searchQuery;
        var doRequest = function(path) {
            //alert("SEARCHSERVICE QUERY: " + searchQuery + " PAGE: " + currentPage);
            //return "SearchService: " + searchQuery + " Page: " + currentPage;


            return $http({
                method: 'JSONP',
                url: 'http://192.168.59.103:8080/elasticwow/spring/api/search?currentPage=' + currentPage + '&searchQuery=' + searchQuery + '&callback=JSON_CALLBACK'
                //url: 'http://api.github.com/users/' + githubUsername + '/' + path + '?callback=JSON_CALLBACK'
            });


            //$http.get('http://192.168.59.103:8080/elasticwow/spring/api/search?currentPage=' + currentPage + '&searchQuery=' + searchQuery).
            //    success(function(data) {
            //        alert("SUCCESS");
            //        var blah = data;
            //        var blahblah = 0;
            //    }).
            //    error(function(data) {
            //        alert("FAIL");
            //        console.log('Error searching');
            //    });
            //
            //return "SearchService: " + searchQuery + " Page: " + currentPage;


            //return $http({
            //    method: 'JSONP',
            //    url: 'http://192.168.59.103:8080/elasticwow/spring/api/search?currentPage=' + currentPage + '&searchQuery=sword'
            //    url: 'http://api.github.com/users/' + githubUsername + '/' + path + '?callback=JSON_CALLBACK'
            //});
        }
        return {
            search: function() { return doRequest('events'); },
            setCurrentPage: function(newCurrentPage) { currentPage = newCurrentPage; },
            setSearchQuery: function(newSearchQuery) { searchQuery = newSearchQuery; },
            getCurrentPage: function() { return currentPage; },
            getSearchQuery: function() { return searchQuery; }
        };
    }]);


