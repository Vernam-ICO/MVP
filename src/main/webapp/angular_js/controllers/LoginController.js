'use strict';

app.controller('LoginController',
    function ($scope, $window, authService) {
        $scope.login = function (userData) {
            authService.login(userData);
        };
    }
);

