var wowESController = function($scope, $state, SearchService) {
    //$scope.greeting = new Date();
    $scope.greeting = "Hello World Yo";
    $scope.currentPage = 1;
    $scope.searchQuery = "";

    //$scope.thisState = $state;


    $scope.onFormSubmit = function() {
        //alert("SEARCH QUERY: " + $scope.searchQuery);
        var stringURL = "/search/" + $scope.currentPage + "/" + $scope.searchQuery;
        SearchService.setCurrentPage($scope.currentPage);
        SearchService.setSearchQuery($scope.searchQuery);
        //$state.go(stringURL);
        //transitionTo(stringURL);
        //$state.transitionTo("/search/$scope.currentPage/$scope.searchQuery");
        $state.transitionTo("searchresult", {currentPage: $scope.currentPage, searchQuery:$scope.searchQuery});
        //$state.transitionTo(stringURL);
    }

};


angular.module('WoWES.Common')
    .controller('MainCtrl', wowESController);
