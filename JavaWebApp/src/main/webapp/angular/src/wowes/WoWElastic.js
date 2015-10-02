angular
    .module('WoWES', ['ui.router', 'ui.bootstrap', 'WoWES.services', 'WoWES.Common', 'WoWES.Search'])
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
                templateUrl: 'src/wowes/search/templates/partial-searchresults.html'
            });

    });


