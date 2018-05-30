'use strict';

app.controller('PrivacyPolicyController',
    function ($scope) {
        $scope.$parent.loadScript('./js/script.js', 'text/javascript', 'utf-8');
    }
);