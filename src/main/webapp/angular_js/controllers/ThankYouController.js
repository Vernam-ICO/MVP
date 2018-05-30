'use strict';

app.controller('ThankYouController',
    function ($scope, $rootScope) {
        $scope.$parent.loadScript('./js/script.js', 'text/javascript', 'utf-8');
        $scope.message = $rootScope.thankYouMessage;
    }
);
