'use strict';

app.controller('ContactController',
    function ($scope, mailService) {
        $scope.$parent.loadScript('./js/script.js', 'text/javascript', 'utf-8');
        $scope.sendMail = function (comment) {
            mailService.sendComment(comment).then(
                function (response) {
                    console.log(response.data);
                },
                function (error) {
                    console.log(error);
                }
            );
        };
    }
);
