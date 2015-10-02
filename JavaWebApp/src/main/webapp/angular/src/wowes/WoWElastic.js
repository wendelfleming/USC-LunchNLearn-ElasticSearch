angular
    .module('WoWES', ['ui.router', 'ui.bootstrap', 'WoWES.services', 'WoWES.Common', 'WoWES.Search'])
    //.controller('WoWESCtrl', wowESController)
    //.controller('WoWSearchCtrl', wowESSearchController)
    .config(function($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/');

        $stateProvider
            .state('home', {
                url: '/',
                controller: 'MainCtrl',
                templateUrl: 'src/wowes/app/templates/partial-search.html'
            })

            .state('searchresult', {
                url: '/search/:currentPage/:searchQuery',
                controller: 'SearchCtrl',
                //resolve: {
                //    currentPage: [ '$stateParams', function($stateParams) {
                //        return $stateParams.currentPage;
                //    }],
                //    searchQuery: [ '$stateParams', function($stateParams) {
                //        return $stateParams.queryString;
                //    }]
                //},
                templateUrl: 'src/wowes/search/templates/partial-searchresults.html'
            });

    });


