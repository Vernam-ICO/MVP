'use strict';

app.controller('RegisterController',
    function ($scope, $location, authService) {
        $scope.register = function(userData) {
            authService.register(userData);
        };
    }
);


