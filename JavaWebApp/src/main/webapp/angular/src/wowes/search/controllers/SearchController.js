
var searchController = function ($scope, $state, $stateParams, SearchService) {
    $scope.searchQuery = $stateParams.searchQuery;
    $scope.currentPage = $stateParams.currentPage;


    $scope.$on('$stateChangeSuccess',  function(event, toState, toParams, fromState, fromParams){

        SearchService.search()
            .success(function(data, status) {
                $scope.totalElements = data.totalElements;
                $scope.totalPages = data.totalPages;
                $scope.numberOfElements = data.numberOfElements;
                $scope.content = data.content;
                $scope.last = data.last;
                $scope.first = data.first;
                $scope.number = data.number;
            });

    });

    $scope.range = function() {
        var cur = parseInt($scope.currentPage);
        var tot = parseInt($scope.totalPages);
        var startPage = (cur - 2 < 1 ? 1 : cur - 2);
        var endPage = (cur + 2 < 5 ? 5 : cur + 2);
        var lastStartPage = tot - 4 <= 0 ? 1 : tot - 4;

        var loopStart = startPage > lastStartPage ? lastStartPage : startPage;
        var loopEnd = endPage > tot ? tot : endPage;

        var ret = [];

        for(var i = loopStart; i <= loopEnd; i++ ) {
            ret.push(i);
        }
        return ret;
    };

    $scope.setPage = function() {
        $scope.currentPage = this.num;
        SearchService.setCurrentPage($scope.currentPage);
        $state.transitionTo("searchresult", {currentPage: $scope.currentPage, searchQuery:$scope.searchQuery});
    };

    $scope.nextPage = function() {
        var cur = parseInt($scope.currentPage);
        cur = cur + 1;
        $scope.currentPage = cur;
        SearchService.setCurrentPage($scope.currentPage);
        $state.transitionTo("searchresult", {currentPage: $scope.currentPage, searchQuery:$scope.searchQuery});
    };

    $scope.previousPage = function() {
        var cur = parseInt($scope.currentPage);
        cur = cur - 1;
        $scope.currentPage = cur;
        SearchService.setCurrentPage($scope.currentPage);
        $state.transitionTo("searchresult", {currentPage: $scope.currentPage, searchQuery:$scope.searchQuery});
    };

};



angular.module('WoWES.Search')
    .controller('SearchCtrl', searchController);

