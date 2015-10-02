var wowESController = function($scope, $state, $http, SearchService) {
    $scope.currentPage = 1;
    $scope.searchQuery = "";

    $scope.onFormSubmit = function() {
        SearchService.setCurrentPage($scope.currentPage);
        SearchService.setSearchQuery($scope.searchQuery);
        $state.transitionTo("searchresult", {currentPage: $scope.currentPage, searchQuery:$scope.searchQuery});
    };

    $scope.searchSuggest = function(partialValue) {
        return $http.jsonp("http://192.168.59.103:8080/elasticwow/spring/suggest/" + partialValue + "?callback=JSON_CALLBACK").then(function(response) {
            return response.data;
        });
    };


};


angular.module('WoWES.Common')
    .controller('MainCtrl', wowESController);
