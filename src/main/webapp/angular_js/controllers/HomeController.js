'use strict';

app.controller('HomeController',
    function ($scope) {
        $scope.$parent.loadScript('./js/script.js', 'text/javascript', 'utf-8');
    }
);
