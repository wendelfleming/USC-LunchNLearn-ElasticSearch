var wowESController = function($scope, $state, $http, SearchService, ConfigService) {
    $scope.currentPage = 1;
    $scope.searchQuery = "";

    $scope.onFormSubmit = function() {
        SearchService.setCurrentPage($scope.currentPage);
        SearchService.setSearchQuery($scope.searchQuery);
        $state.transitionTo("searchresult", {currentPage: $scope.currentPage, searchQuery:$scope.searchQuery});
    };

    $scope.searchSuggest = function(partialValue) {
        return $http.jsonp(ConfigService.getRestServerURL() + "suggest/" + partialValue + "?callback=JSON_CALLBACK").then(function(response) {
            return response.data;
        });
    };


};


angular.module('WoWES.Common')
    .controller('MainCtrl', wowESController);
