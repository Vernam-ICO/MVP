'use strict';

app.controller('UserProfileController',
    function ($scope, $window, $sce, userProfileService, userService, authService) {
        $scope.$parent.loadScript('./js/script.js', 'text/javascript', 'utf-8');

        $scope.user = {};

        if (authService.isLoggedIn()) {
            userService.getUser().then(function (response) {
                    $scope.user = response.data
                },
                function (errors) {
                    console.log(errors)
                });
        }

        $scope.edit = function (userData) {
            userProfileService.edit(userData);
        };
    }
);